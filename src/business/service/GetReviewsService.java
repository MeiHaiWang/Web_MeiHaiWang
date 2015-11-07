package business.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ReviewDao;
import business.dao.SalonDao;
import business.dao.UserDao;
import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.CommonUtil;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 * 
 * Request Params
		
		shopId=48742
		店舗ID
		page=0
		ページ数
 *
 */

public class GetReviewsService implements IServiceExcuter {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int shopId = request.getParameter("shopId")!= null
        		?Integer.parseInt(request.getParameter("shopId")) : -1;
        int pageNum = request.getParameter("page")!= null
        		?Integer.parseInt(request.getParameter("page")) : -1;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<Integer> reviewIdList = new ArrayList<Integer>();
			List<String> reviewIdList = new ArrayList<String>();
			List<ReviewInfo> reviewInfoList = new ArrayList<ReviewInfo>();
			//List<UserInfo> userInfoList = new ArrayList<UserInfo>();

			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();

			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				ReviewDao reviewDao = new ReviewDao();
				//UserDao userDao = new UserDao();
				//EvaluationDao evaluationDao = new EvaluationDao();
				
				//reviewIdList = salonDao.getHairSalonReviewIdList(dbConnection, shopId);
				reviewIdList = Arrays.asList(salonDao.get(dbConnection, shopId).getTHairSalonMasterReviewId().split(","));
				//TODO:
				//reviewInfoList = reviewDao.getReviewDetailInfo(dbConnection, reviewIdList, pageNum, jsonObject);
				//userInfoList = userDao.getReviewerUserInfo(dbConnection, reviewInfoList);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
		    // 返却用サロンデータ（jsonデータの作成）
			/**
			 *  * Response
			 *   reviews:[
			      {
			         id: 1,
			         isReviewWrite:1,
			         isRe:1,
			         name: "美々日",
			         gender: 0,
			         age: 20,
			         comment: "aaaaaaaaaaaaaaaa",
			         evaluation1: 3.5,
			         evaluation2: 2.2,
			         evaluation3: 1.5,
			         evaluation4: 3.3,
			         evaluation5: 3.6
			　　　　　day: 20150324
			      },
			 */
			JSONArray ReviewArray = new JSONArray();
			//int index=0;
		    for(ReviewInfo reviewInfo : reviewInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", reviewInfo.getReviewId());		 
		    	//jsonOneData.put("isReviewWrite", userInfoList.get(index).getisReviewWrite());
		    	List<String> commentList = Arrays.asList(reviewInfo.getReviewCommentId().split(","));
		    	jsonOneData.put("isRe", commentList.size());
		    	/*
		    	jsonOneData.put("name", userInfoList.get(index).getUserName());
		    	jsonOneData.put("gender", userInfoList.get(index).getUserSex());
		    	Date userBirth = userInfoList.get(index).getUserBirth();
		    	*/
		    	jsonOneData.put("name", reviewInfo.getReviewUserName());
		    	jsonOneData.put("gender", reviewInfo.getReviewUserSex());
		    	Date userBirth = reviewInfo.getReviewUserBirth();
		    	/* 年齢を求める*/
		    	int age = CommonUtil.getAgeForBirthday(userBirth);
		    	jsonOneData.put("age", age);
		    	jsonOneData.put("comment", reviewInfo.getReviewText());		    	
		    	
		    	List<String> evalList = Arrays.asList(reviewInfo.getReviewPoint().split(","));
		    	//jsonOneData.put("evaluation", evaluationInfoList.get(i).getEvaluationPoint());
		    	jsonOneData.put("evaluation1", evalList.get(0));
		    	jsonOneData.put("evaluation2", evalList.get(1));
		    	jsonOneData.put("evaluation3", evalList.get(2));
		    	jsonOneData.put("evaluation4", evalList.get(3));
		    	jsonOneData.put("evaluation5", evalList.get(4));
		    	ReviewArray.add(jsonOneData);
		    	//index++;
		    }
		    jsonObject.put("reviews",ReviewArray);
		    
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
