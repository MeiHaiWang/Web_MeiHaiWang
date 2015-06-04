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
        int userId =1 ;
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<ReservationInfo> reservationList  = new ArrayList<ReservationInfo>();
			UserInfo userInfo = new UserInfo();
			
			if(conn!=null){
				ReservationDao dao = new ReservationDao();
				reservationList = dao.getReservationInfo(dbConnection, userId);
				UserDao userDao = new UserDao();
				userInfo = userDao.getUserMypageInfo(dbConnection, userId);

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
	    	jsonOneData.put("isstylist", userInfo.getUserIsStylist());
	    	jsonOneData.put("mypoint", userInfo.getUserPoint());
		    for(ReservationInfo returnMypage : reservationList){
		    	jsonOneData.put("id", returnMypage.getReservationId());
		    	jsonOneData.put("day", returnMypage.getReservationDate());
		    	jsonOneData.put("salon_id", returnMypage.getReservationSalonId());
		    	jsonOneData.put("salon_name", returnMypage.getReservationSalonName());
		    	jsonOneData.put("stylist_id", returnMypage.getReservationStylistId());
		    	jsonOneData.put("stylist_name", returnMypage.getReservationStylistName());
		    	/*
		    	 *   id: 834336,
				      day: "201304121613",
				      salon_id: 222,
				      salon_name: "美々日",
				      stylist_Id: 123,
				      stylist_name: "イケメンちゃん"
		    	 * 
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
