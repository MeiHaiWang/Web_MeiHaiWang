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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.MenuDao;
import business.dao.ReservationDao;
import business.dao.UserDao;
import common._model.TUserInfo;
import common.constant.Constant;
import common.model.ReservationInfo;
import common.model.UserInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
	 *入力：
	  {salonId}
	出力：
	user_lists:[
	  {
	　 t_user_name,
	   t_user_phoneNumber,
	   t_user_age,
	   t_user_gender,
	   t_stylist_name,
	   t_reservation_memo,
	   salon_traffic,
	   total_payment,
	   t_reservation_previous,
   	   t_reservation_next
	　},
	  {}
	]
 */

public class GetCustomerListService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){

	/**
	 * Declaration value
	 */
	HttpSession session = request.getSession();
    int responseStatus = HttpServletResponse.SC_OK;

	//salonId kokokara
    int salonId = -1;
    //get a salonId by session
	String salonId_str = "";
	if (session != null){
		salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
	}
	if(salonId_str != null){			
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}
	}   
	if(salonId < 0){
        //get a salonId by parameter
        salonId = request.getParameter(Constant.PARAMETER_SALONID)!= null 
		?Integer.parseInt(request.getParameter(Constant.PARAMETER_SALONID)) : -1;
	}
	//salonId kokomade

	String t_reservation_salonId = request.getParameter("t_reservation_salonId") != null ?
			request.getParameter("t_reservation_salonId").toString() : null;

	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
//		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		//List<ReservationInfo> reservationInfoList = new ArrayList<ReservationInfo>();		
		List<Integer> userIdList = new ArrayList<Integer>();
		List<TUserInfo> userInfoList = new ArrayList<TUserInfo>();
		
		ReservationDao dao = new ReservationDao();
		UserDao userDao = new UserDao();
		MenuDao menuDao = new MenuDao();
		if(conn!=null){
			userIdList = dao.getReservationUserList(dbConnection, t_reservation_salonId);
			for(int userId : userIdList){
				List<ReservationInfo> reservationInfoList = new ArrayList<ReservationInfo>();
				//ReservationInfo reservationInfo = new ReservationInfo();
				reservationInfoList = dao.getReservationInfo(dbConnection, userId);
				//来店回数
				int salonTraffic = reservationInfoList.size();
				//最後に来店した際の担当者とメモ
				ReservationInfo lastReservationInfo = new ReservationInfo();
				String lastReservationStylistName = "";
				String lastReservationMemo = "";
				for(int i=1; i<=reservationInfoList.size(); i++){
					lastReservationInfo = reservationInfoList.get(reservationInfoList.size()-i);
					//debug
					//System.out.println("lR1:"+lastReservationInfo.getReservationId());
					if(lastReservationInfo.getisFinished()==1){
						//debug
						//System.out.println("lR2:"+lastReservationInfo.getReservationId());
						lastReservationStylistName = lastReservationInfo.getReservationStylistName();
						lastReservationMemo = lastReservationInfo.getReservationMemo();
						break;
					}
				}
				//支払い総額
				double totalPayment = 0;
				for(ReservationInfo ri : reservationInfoList){
					List<String> menuIdList = new ArrayList<String>();
					menuIdList = Arrays.asList(ri.getReservationMenuId().split(","));
					//totalPayment += menuDao.getMenuCost(dbConnection, menuIdList);
					for(String id: menuIdList){
						totalPayment += menuDao.get(dbConnection, Integer.parseInt(id)).getTMenuPrice();
					}
				}
				
				//前回来店日時、次回来店日時を取得
				String previousDate ="";
				String nextDate = "";
				for(ReservationInfo ri: reservationInfoList){
					System.out.println(ri.getisFinished()+","+ri.getReservationDate());
					if(ri.getisFinished()>0){
						previousDate = ri.getReservationDate().substring(0,10);
						break;
					}
				}
				for(ReservationInfo ri: reservationInfoList){
					if(ri.getisFinished()==0){
						nextDate = ri.getReservationDate().substring(0,10);
						break;
					}
				}
				
				//UserInfoにデータを格納
				TUserInfo userInfo = new TUserInfo();
				userInfo = userDao.get(dbConnection, userId);							
				userInfo.setLatestCutStylist(lastReservationStylistName);
				userInfo.setLatestCutMemo(lastReservationMemo);
				userInfo.setSalonTraffic(salonTraffic);
				userInfo.setTotalPayment(totalPayment);
				userInfo.setReservationPreviousDate(previousDate);
				userInfo.setReservationNextDate(nextDate);
				userInfoList.add(userInfo);
			}
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

		//レスポンスに設定するJSON Object
		//JSONObject jsonObject = new JSONObject();
		/*
			出力：
			user_lists:[
			  {
			　 t_user_name,
			   t_user_phoneNumber,
			   t_user_age,
			   t_user_gender,
			   t_stylist_name,
			   t_reservation_memo,
			   salon_traffic,
			   total_payment
			　},
			  {
				
			  }
		*/
		
		// 返却用サロンデータ（jsonデータの作成）
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(TUserInfo userInfo : userInfoList){
			JSONObject jsonOneData = new JSONObject();
			jsonOneData.put("t_user_name", userInfo.getName());
			jsonOneData.put("t_user_phoneNumber", userInfo.getTUserTel());
	    	/* 年齢を求める*/
	    	Date userBirth = userInfo.getTUserBirth();
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
	    	jsonOneData.put("t_user_age", age);			
			jsonOneData.put("t_user_gender", userInfo.getTUserSex());
			jsonOneData.put("t_stylist_name", userInfo.getLatestCutStylist());
			jsonOneData.put("t_reservation_memo", userInfo.getLatestCutMemo());
			jsonOneData.put("salon_traffic", userInfo.getSalonTraffic());
			jsonOneData.put("total_payment", userInfo.getTotalPayment());
			jsonOneData.put("t_reservation_previous", userInfo.getReservationPreviousDate());
			jsonOneData.put("t_reservation_next", userInfo.getReservationNextDate());
			jsonArray.add(jsonOneData);
		}
		jsonObject.put("user_lists", jsonArray);
	    
    	PrintWriter out = response.getWriter();
		out.print(jsonObject);
		//debug
		System.out.println(jsonObject.toString(4));
		out.flush();
		
	}catch(Exception e){
		responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		e.printStackTrace();
	}
    
	response.setStatus(responseStatus);
	return response;

	}
}
