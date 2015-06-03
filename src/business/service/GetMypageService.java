package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ReservationDao;
import business.dao.UserDao;
import common.model.BeautyNewsInfo;
import common.model.ReservationInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class GetMypageService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		
        int responseStatus = HttpServletResponse.SC_OK;
		/*int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;*/
        //TODO テスト用
        int userId =0 ;
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<ReservationInfo> reservationList  = new ArrayList<ReservationInfo>();
			
			if(conn!=null){
				ReservationDao dao = new ReservationDao();
				reservationList = dao.getReservationInfo(dbConnection, userId);
				UserDao userDao = new UserDao();
				UserInfo userInfo = userDao.getUserMydataInfo(dbConnection, userId);

				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray myPageArray = new JSONArray();
	    	JSONObject jsonOneData = new JSONObject();
	    	jsonOneData.put("mypoint", userInfo.getMypoint());
		    for(ReservationInfo returnMypage : reservationList){
		    	/*
		    	jsonOneData.put("image", returnNews.getbeautyNewsImagePath());
		    	jsonOneData.put("title", returnNews.getbeautyNewsName());
		    	jsonOneData.put("URL", returnNews.getbeautyNewsURL());
		    	*/
		    	myPageArray.add(jsonOneData);
		    }
		    jsonObject.put("reservation_lists",myPageArray);
		    
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
