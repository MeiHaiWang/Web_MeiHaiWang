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

import common.constant.Constant;
import common.model.ReservationInfo;
import common.util.DBConnection;

public class GetReservationInfoService implements IServiceExcuter{
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

	String t_reservation_id = request.getParameter("t_reservation_id") != null ?
			request.getParameter("t_reservation_id").toString() : null;

	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
//		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		ReservationInfo reservationInfo = new ReservationInfo();		
		
		ReservationDao dao = new ReservationDao();
		if(conn!=null){
			reservationInfo = dao.getReservationInfo(dbConnection, t_reservation_id);
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

		//レスポンスに設定するJSON Object
		//JSONObject jsonObject = new JSONObject();
		/**
		 * 
			{
			　 t_reservation_userTel,
			　 t_reservation_salonId,
			　 t_reservation_stylistId,
			　 t_reservation_date,
			　 t_reservation_menuId,
			  t_reservation_isFinished,
			　 t_reservation_seatId,
			     t_reservation_memo　,
			     t_reservation_appoint
			　}
		 */
		
		// 返却用サロンデータ（jsonデータの作成）
		JSONObject jsonObject = new JSONObject();
			jsonObject.put("t_reservation_userTel", reservationInfo.getReservationUserTel());
			jsonObject.put("t_reservation_salonId", reservationInfo.getReservationSalonId());
			jsonObject.put("t_reservation_stylistId", reservationInfo.getReservationStylistId());
			jsonObject.put("t_reservation_date", reservationInfo.getReservationDate());
			jsonObject.put("t_reservation_menuId", reservationInfo.getReservationMenuId());
			jsonObject.put("t_reservation_seatId", reservationInfo.getReservationSeatId());
			jsonObject.put("t_reservation_isFinished", reservationInfo.getisFinished());
			jsonObject.put("t_reservation_memo", reservationInfo.getReservationMemo());
			jsonObject.put("t_reservation_appoint", reservationInfo.getReservationAppoint());
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
