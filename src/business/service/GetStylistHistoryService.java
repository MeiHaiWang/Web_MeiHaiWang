package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.StylistDao;
import common.constant.Constant;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetStylistHistoryService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> stylistIdList  = new ArrayList<Integer>();
			List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();

			int userId = request.getHeader(Constant.HEADER_USERID)!= null 
	        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			
			if(conn!=null){
				StylistDao dao = new StylistDao();
				stylistIdList  = dao.getStylistHistoryIdList(dbConnection, userId);
				stylistInfoList = dao.getStylistHistoryInfo(dbConnection, stylistIdList);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/*
			 * stylelist_lists: [
			    {
			      id: 834336,
			      shopID:3945773,
			      name: "イケメンちゃん",
			      gender: 0,
			      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
			      message: "スタイリストからのメッセージだよーん",
			      years: "7年間",
			      good_count: 155
			    },
			 * */
			
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray stylistArray = new JSONArray();
		    for(StylistInfo stylistInfo : stylistInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", stylistInfo.getStylistId());
		    	jsonOneData.put("shopID", stylistInfo.getSalonId());
		    	jsonOneData.put("name", stylistInfo.getStylistName());
		    	jsonOneData.put("gender", stylistInfo.getStylistGender());
		    	//jsonOneData.put("image", stylistInfo.getStylistImagePath());
		    	int i = 0;
		    	for(String str : stylistInfo.getStylistImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("message", stylistInfo.getStylistMessage());
		    	jsonOneData.put("years", stylistInfo.getStylistYearsNumber());
		    	jsonOneData.put("good_count", stylistInfo.getFavoriteNumber());
		    	stylistArray.add(jsonOneData);
		    }
		    jsonObject.put("stylist_lists",stylistArray);		    

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
