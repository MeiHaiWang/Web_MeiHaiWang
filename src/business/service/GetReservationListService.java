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
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.ReservationInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
 *入力：
　{
　 t_reservation_salonId,
　 t_reservation_date
　}
出力：
reservation_lists:[
  {
    t_reservation_id,
    t_reservation_date,
    t_reservation_time,
    t_user_name,
    t_user_gender,
    t_stylist_name,
    t_menu_name,
    t_seat_name,
    t_reservation_memo
  },
  {

  }
]
 */

public class GetReservationListService implements IServiceExcuter{
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
	String t_reservation_date = request.getParameter("t_reservation_date") != null ?
			request.getParameter("t_reservation_date").toString() : null;

	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
//		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		List<ReservationInfo> reservationInfoList = new ArrayList<ReservationInfo>();		
		
		ReservationDao dao = new ReservationDao();
		if(conn!=null){
			reservationInfoList = dao.getReservationInfoList(dbConnection, t_reservation_salonId, t_reservation_date);
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

		//レスポンスに設定するJSON Object
		//JSONObject jsonObject = new JSONObject();
		/**
		 * 
			reservation_lists:[
			  {
			    t_reservation_id,
			    t_reservation_date,
    			t_reservation_time,
			    t_user_name,
			    t_user_gender,
			    t_stylist_name,
			    t_menu_name,
			    t_seat_name,
			    t_reservation_isFinished,
			    t_reservation_memo,
			    t_reservation_menuId
			  },
			  {
			
			  }
			]
		 */
		
		// 返却用サロンデータ（jsonデータの作成）
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(ReservationInfo reservationInfo : reservationInfoList){
			JSONObject jsonOneData = new JSONObject();
			jsonOneData.put("t_reservation_id", reservationInfo.getReservationId());
			jsonOneData.put("t_reservation_date", reservationInfo.getReservationDate());
			jsonOneData.put("t_reservation_time", reservationInfo.getReservationTime());
			jsonOneData.put("t_user_name", reservationInfo.getReservationUserName());
			jsonOneData.put("t_user_gender", reservationInfo.getReservationUserSex());
			jsonOneData.put("t_stylist_name", reservationInfo.getReservationStylistName());
			jsonOneData.put("t_menu_name", reservationInfo.getReservationMenuName());
			jsonOneData.put("t_seat_name", reservationInfo.getReservationSeatName());
			jsonOneData.put("t_reservation_isFinished", reservationInfo.getisFinished());
			jsonOneData.put("t_reservation_memo", reservationInfo.getReservationMemo());
			jsonOneData.put("t_reservation_menuId", reservationInfo.getReservationMenuId());
			jsonArray.add(jsonOneData);
		}
		jsonObject.put("reservation_list", jsonArray);
	    
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
