package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.CountryDao;
import business.dao.HairStyleDao;
import common.constant.Constant;
import common.model.AreaInfo;
import common.model.CountryInfo;
import common.model.HairStyleInfo;
import common.util.DBConnection;

public class GetCountryAreaListService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
        //TODO: country number
        //int COUNTRY_NUMBER = 20;
        int CountryNumber = -1;
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> areaList  = new ArrayList<Integer>();
			//List<AreaInfo> areaInfoAllList = new ArrayList<AreaInfo>();
			//国ごとのarealist二次元リストに整列する?
			List<CountryInfo> countryInfoList = new ArrayList<CountryInfo>();
			
			List<AreaInfo>[] areaInfoList = new ArrayList[Constant.COUNTRY_NUMBER];
			for (int i = 0; i < areaInfoList.length; i++) {
				areaInfoList[i] = new ArrayList();
			}
			
			/*
			int userId = request.getHeader(Constant.HEADER_USERID)!= null 
	        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			//TODO テスト用
			userId =1;			
			*/
			
			if(conn!=null){
				CountryDao countryDao = new CountryDao();
				countryInfoList = countryDao.getCountryListInfo(dbConnection);	
				//CountryNumber = countryInfoList.size();
				
				AreaDao areaDao = new AreaDao();
				for(CountryInfo cInfo: countryInfoList){
					areaInfoList[cInfo.getCountryId()] = areaDao.getAreaInfoListForCountry(dbConnection, cInfo.getCountryId());
				}
				
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/*
			    {
			      country:[
			        {
			          t_counrty_id,
			          t_country_name,
			          area:[
			            {
			              t_area_id,
			              t_area_name
			            },
			            ...
			          ]
			        },
			        ...
			      ]
			    }
			 */
			
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray countryArray = new JSONArray();
		    JSONArray areaArray = new JSONArray();
		    for(CountryInfo cInfo : countryInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_country_id", cInfo.getCountryId());
		    	jsonOneData.put("t_country_name", cInfo.getCountryName());
			    for(AreaInfo areaInfo : areaInfoList[cInfo.getCountryId()]){
			    	JSONObject jsonTwoData = new JSONObject();
			    	jsonTwoData.put("t_area_id", areaInfo.getAreaId());
			    	jsonTwoData.put("t_area_name", areaInfo.getAreaName());
			    	areaArray.add(jsonTwoData);
			    }		    	
			    jsonOneData.put("area", areaArray);
			    countryArray.add(jsonOneData);
		    }
		    jsonObject.put("country",countryArray);		    
		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
	    }catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}

}
