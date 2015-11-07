package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.StylistDao;
import business.dao.UserDao;
import common._model.TStylistInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class GetStylistHistoryService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<String> stylistIdList  = new ArrayList<String>();
			List<TStylistInfo> stylistInfoList = new ArrayList<TStylistInfo>();

			int userId = request.getHeader(Constant.HEADER_USERID)!= null 
	        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			
			if(conn!=null){
				StylistDao dao = new StylistDao();
				UserDao userDao = new UserDao();
				String stylistIdListStr = userDao.get(dbConnection, userId).getLatestCutStylist();
				if(stylistIdListStr != null) stylistIdList = Arrays.asList(stylistIdListStr.split(","));
				//stylistIdList  = dao.getStylistHistoryIdList(dbConnection, userId);
				//stylistInfoList = dao.getStylistHistoryInfo(dbConnection, stylistIdList);
				for(String id : stylistIdList){
					stylistInfoList.add(dao.get(dbConnection, Integer.parseInt(id)));
				}
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
		    for(TStylistInfo stylistInfo : stylistInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", stylistInfo.getTStylistId());
		    	jsonOneData.put("shopID", stylistInfo.getTStylistSalonId());
		    	jsonOneData.put("name", stylistInfo.getTStylistName());
		    	jsonOneData.put("gender", stylistInfo.getTStylistSex());
		    	//jsonOneData.put("image", stylistInfo.getStylistImagePath());
		    	int i = 0;
		    	for(String str : Arrays.asList(stylistInfo.getTStylistImagePath().split(","))){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("message", stylistInfo.getTStylistMessage());
		    	jsonOneData.put("years", stylistInfo.getTStylistExperienceYear());
		    	jsonOneData.put("good_count", stylistInfo.getTStylistFavoriteNumber());
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
