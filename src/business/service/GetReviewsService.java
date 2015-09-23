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
import business.dao.EvaluationDao;
import business.dao.ReviewDao;
import business.dao.SalonDao;
import business.dao.UserDao;
import common.model.EvaluationInfo;
import common.model.HairSalonInfo;
import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class GetReviewsService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int shopId = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
        int pageNum = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> reviewIdList = new ArrayList<Integer>();
			//List<Integer> evalIdList = new ArrayList<Integer>();
			List<ReviewInfo> reviewInfoList = new ArrayList<ReviewInfo>();
			//List<EvaluationInfo> evaluationInfoList = new ArrayList<EvaluationInfo>();
			List<UserInfo> userInfoList = new ArrayList<UserInfo>();

			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();

			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				ReviewDao reviewDao = new ReviewDao();
				UserDao userDao = new UserDao();
				//EvaluationDao evaluationDao = new EvaluationDao();
				
				reviewIdList = salonDao.getHairSalonReviewIdList(dbConnection, shopId);
				reviewInfoList = reviewDao.getReviewDetailInfo(dbConnection, reviewIdList, pageNum, jsonObject);
				userInfoList = userDao.getReviewerUserInfo(dbConnection, reviewIdList);
				
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray ReviewArray = new JSONArray();
			int i=0;
		    for(ReviewInfo reviewInfo : reviewInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", reviewInfo.getReviewId());		    	
		    	jsonOneData.put("name", userInfoList.get(i).getUserName());
		    	jsonOneData.put("gender", userInfoList.get(i).getUserSex());

		    	/* 年齢を求める*/
		    	Date userBirth = userInfoList.get(i).getUserBirth();
		    	Date nowDate = new Date();
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    	Calendar birthDay = Calendar.getInstance();
		    	birthDay.setTime(userBirth);
		    	Calendar today = Calendar.getInstance();
		    	today.setTime(nowDate);
		    	int age = today.get(Calendar.YEAR)-birthDay.get(Calendar.YEAR);
		    	birthDay.clear(Calendar.YEAR);
		    	today.clear(Calendar.YEAR);
		    	if(birthDay.after(today)){
		    		age-=1;
		    	}
		    	jsonOneData.put("age", age);
		    	jsonOneData.put("comment", reviewInfo.getReviewText());		    	
		    	//jsonOneData.put("evaluation", evaluationInfoList.get(i).getEvaluationPoint());
		    	jsonOneData.put("evaluation", reviewInfo.getReviewPoint());
		    	ReviewArray.add(jsonOneData);
		    	i++;
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
