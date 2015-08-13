package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.scripts.JO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.CountryDao;
import business.dao.HairStyleDao;
import common.constant.Constant;
import common.model.AreaInfo;
import common.model.CountryInfo;
import common.model.HairStyleInfo;
import common.util.AreaNode;
import common.util.DBConnection;
import common.util.TreeUtil;

/**
 * 
 * @author kanijunnari
 *
 * 

    getCounrtyAreaList
        概要：都市、地域一覧取得
        入力：なし
        出力：

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

public class GetCountryAreaListService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		/**
		 * Declaration value
		 */
		boolean result = true;
        int responseStatus = HttpServletResponse.SC_OK;
		List<Integer> areaList  = new ArrayList<Integer>();
		List<CountryInfo> countryInfoList = new ArrayList<CountryInfo>();			
		List<AreaInfo>[] areaInfoList = new ArrayList[Constant.COUNTRY_NUMBER]; //country Number?
		for (int i = 0; i < areaInfoList.length; i++) {
			areaInfoList[i] = new ArrayList<AreaInfo>();
		}
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
						
			if(conn!=null){
				CountryDao countryDao = new CountryDao();
				AreaDao areaDao = new AreaDao();
				
				//get CountryInfoData
				countryInfoList = countryDao.getCountryListInfo(dbConnection);	

				//get AreaIngoDatas
				for(CountryInfo cInfo: countryInfoList){
					areaInfoList[cInfo.getCountryId()] 
							= areaDao.getAreaInfoListForCountry(dbConnection, cInfo.getCountryId());
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			/*
			 * {
				      area:[
				        {
				          t_area_id,
				          t_area_name,
				          area_slave:[
				            {
				              t_area_id,
				              t_area_name
				            },
				          ]
				        },
				      ]
				}
			 */
			
			Map<Integer,AreaNode> areaNodeMap = new HashMap<Integer, AreaNode>();
			for(CountryInfo ci : countryInfoList){
				areaNodeMap.put(ci.getCountryId(), TreeUtil.createTreeObject(areaInfoList[ci.getCountryId()]));
			}

			/*
			AreaNode[] root = new AreaNode[countryInfoList.size()];			
			root[countryInfoList.get(0).getCountryId()]
					= TreeUtil.createTreeObject(areaInfoList[countryInfoList.get(0).getCountryId()]);
			*/
//			JSONArray ja = new JSONArray();
			//debug
			//System.out.println("r"+root[countryInfoList.get(0).getCountryId()]);
			//jsonObject = root[countryInfoList.get(0).getCountryId()].output();
/*
			for(CountryInfo ci : countryInfoList){
				JSONObject jo = new JSONObject();
				jo = areaNodeMap.get(ci.getCountryId()).output();
				ja.add(jo);
			}
			jsonObject.put("country", ja);
*/			
			/*
			for(CountryInfo cInfo: countryInfoList){
				//tree
				root[cInfo.getCountryId()]
						= TreeUtil.createTreeObject(areaInfoList[cInfo.getCountryId()]);
				//output
				ja.add(root[cInfo.getCountryId()].output());
			}
			*/
			//jsonObject.put("country", ja);

			if(countryInfoList.size()!=0 && areaNodeMap.size()!=0){
				jsonObject = areaNodeMap.get(countryInfoList.get(0).getCountryId()).output();
			}
			//debug
			//System.out.println(jsonObject.toString(4));
			PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
	    }catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
			
			
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
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray countryArray = new JSONArray();
		    for(CountryInfo cInfo : countryInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_country_id", cInfo.getCountryId());
		    	jsonOneData.put("t_country_name", cInfo.getCountryName());
		    	JSONArray areaArray = new JSONArray();
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
			 */
	    
		response.setStatus(responseStatus);
		return response;
	}

}
