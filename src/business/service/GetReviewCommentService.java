package business.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.CommentDao;
import business.dao.ReviewDao;
import business.dao.SalonDao;
import business.dao.UserDao;
import common.model.CommentInfo;
import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class GetReviewCommentService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
		/*int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;*/
        //TODO テスト用
        int commentId = 1;
        
		CommentInfo commentInfo = new CommentInfo();
		CommentDao commentDao = new CommentDao(); 
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			if(conn!=null){
				commentInfo = commentDao.getCommentInfo(dbConnection, commentId);
				
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}

			/*
			 * reviewRe:
				{
				   name: "美々日",
				   comment: "aaaaaaaaaaaaaaaa",
				   day: 20150324
				}
			 */
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();

		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray commentArray = new JSONArray();
			//int i=0;
		    //for(ReviewInfo reviewInfo : reviewInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("name", commentInfo.getCommentUserName());		    	
		    	/*
		    	Date day = commentInfo.getCommentDay();
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    	jsonOneData.put("day", sdf.format(day));
		    	*/
		    	jsonOneData.put("comment", commentInfo.getCommentMessage());		    	
		    	commentArray.add(jsonOneData);
		    	//i++;
		    //}
		    jsonObject.put("reviewRe",commentArray);
		    
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
