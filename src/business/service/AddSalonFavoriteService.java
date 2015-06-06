package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import common.constant.Constant;
import common.model.UserInfo;
import common.util.DBConnection;

public class AddSalonFavoriteService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        int salonId = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
		 // userIdがパラメータ。なかったら-1を入れておく。
        //TODO テスト用
        userId = 1;
        salonId = 2;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<UserInfo> infoList = new ArrayList<UserInfo>();
			int status = -1;
			if(conn!=null){
				UserDao userDao = new UserDao();
				//infoList = userDao.getuserInfo(dbConnection, userId);
				status = userDao.addFavoriteSalon(dbConnection, userId, salonId);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}

			/*
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray userArray = new JSONArray();
		    for(UserInfo userInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", userInfo.getuserId());		    	
		    	jsonOneData.put("name", userInfo.getuserName());
		    	jsonOneData.put("isDetail", userInfo.getisDetailFlag());
		    	userArray.add(jsonOneData);
		    }
		    jsonObject.put("userList",userArray);

		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		    */
			
		    PrintWriter out = response.getWriter();
		    out.print("{ status : "+status+" }");
		    out.flush();
		    
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}
}
