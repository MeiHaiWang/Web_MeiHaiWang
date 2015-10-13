package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.HairTypeDao;
import business.dao.StylistDao;
import common.constant.Constant;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.model.HairTypeInfo;
import common.util.DBConnection;

public class GetHairTypeOrderNewService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();

		/**
			 * categoryID=0
			ヘアカタログID
			stylistID=0
			スタイリストID
			menu=0&menu=1&menu=2
			メニュー
			face=0&face=1
			顔型
			page=0
		 */
		//パラメータ取得
        int stylistId = request.getParameter("stylistID")!= null
           		?Integer.parseInt(request.getParameter("stylistID")) : -1;
		List<String> searchConditionIdList = request.getParameter("condition") != null ?
				Arrays.asList(request.getParameter("condition").split(",")) : new ArrayList<String>();	
		int page = request.getParameter("page")!= null
           		?Integer.parseInt(request.getParameter("page")) : -1;

        int responseStatus = HttpServletResponse.SC_OK;
				
		try{
			DBConnection dbConnection = new DBConnection();
			JSONObject jsonObject = new JSONObject();
			List<HairStyleInfo> HairStyleOrderNewList  = new ArrayList<HairStyleInfo>();
			java.sql.Connection conn = dbConnection.connectDB();
			
			StylistInfo stylistInfo = new StylistInfo();
			if(conn!=null){
				HairTypeDao hairTypeDao = new HairTypeDao();
				StylistDao stylistDao = new StylistDao();
				stylistInfo = stylistDao.getStylistDetailInfo(dbConnection, stylistId);
				HairStyleOrderNewList = hairTypeDao.getHairTypeOrderNewInfo(dbConnection, stylistId, page, jsonObject, searchConditionIdList);
				AreaDao areaDao = new AreaDao();
				for(int i=0;i<HairStyleOrderNewList.size();i++){
					HairStyleInfo hInfo = HairStyleOrderNewList.get(i);
					List<String> areaNameList = areaDao.getAreaName(dbConnection, hInfo.getHairStyleAreaId());
					String areaName = "";
					for(String area : areaNameList){
						areaName += area + ",";
					}
					if(areaNameList.size()>0) areaName.substring(0, areaName.length()-1);
					hInfo.setHairStyleAreaName(areaName);
					HairStyleOrderNewList.set(i, hInfo);
				}
				dbConnection.close();
			}
			

			/**
			 * {
				   cataloglist:[
				      {
				         id:1,
				         image: "http://exsample.com/Shor1.png",
				         stylistName: "aaaaaa",
				         area: "北京",
				　　　　　goodCount: 100,
				         isGood: 1
				      },
				      {
				         id:2,
				         image: "http://exsample.com/Shor2.png",
				         stylistName: "aaaaaa",
				         area: "北京",
				　　　　　goodCount: 100,
				         isGood: 1
				      },
				      {
				         id:3,
				         image: "http://exsample.com/Shor3.png",
				         stylistName: "aaaaaa",
				         area: "北京",
				　　　　　goodCount: 100,
				         isGood: 1
				      },
				      {
				         id:4,
				         image: "http://exsample.com/Shor4.png",
				         stylistName: "aaaaaa",
				         area: "北京",
				　　　　　goodCount: 100,
				         isGood: 1
				      }
				   ],
				   next:1
			   }
			 */
			
			/*
			//レスポンスに設定するJSON Object
			JSONObject jsonOneData = new JSONObject();
			jsonOneData.put("id", stylistInfo.getStylistId());
			jsonOneData.put("name", stylistInfo.getStylistName());
		    // 返却用サロンデータ（jsonデータの作成）
		    jsonObject.put("stylist",jsonOneData);
		    */

			// 返却用サロンデータ（jsonデータの作成）
			JSONArray HairStyleArray = new JSONArray();
		    for(HairStyleInfo hairStyleInfo : HairStyleOrderNewList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairStyleInfo.getHairStyleId());
		    	//jsonOneData.put("image", hairStyleInfo.getHairStyleImagePath());
		    	int i = 0;
		    	for(String str : hairStyleInfo.getHairStyleImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("stylistName", stylistInfo.getStylistName());
		    	jsonOneData.put("area", hairStyleInfo.getHairStyleAreaName());
		    	jsonOneData.put("goodCount", hairStyleInfo.getHairStyleGoodNumber());
		    	if(hairStyleInfo.getHairStyleGoodNumber()>0){
		    		jsonOneData.put("isGood", 1);
		    	}else{
		    		jsonOneData.put("isGood", 0);
		    	}		    	
		    	HairStyleArray.add(jsonOneData);
		    }
		    jsonObject.put("cataloglist", HairStyleArray);
		    		    
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
