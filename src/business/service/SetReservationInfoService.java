package business.service;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.ReservationDao;
import business.dao.StylistDao;
import common.model.ReservationInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
 *入力：
　{
　 userId,
　 salonId,
　 stylistId,
　 date,
　 menuId,
　 seatId,
	memo　
	appoint
　}
	出力：
	{true（予約取れた）／false}　

 */

public class SetReservationInfoService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
   		HttpSession session = request.getSession(false);

		String t_reservation_id = request.getParameter("t_reservation_id") != null ?
				request.getParameter("t_reservation_id").toString() : null;
		String t_reservation_userId = request.getParameter("t_reservation_userId") != null ?
				request.getParameter("t_reservation_userId").toString() : null;
		String t_reservation_salonId = request.getParameter("t_reservation_salonId") != null ?
				request.getParameter("t_reservation_salonId").toString() : null;
		String t_reservation_stylistId = request.getParameter("t_reservation_stylistId") != null ?
				request.getParameter("t_reservation_stylistId").toString() : null;
		String t_reservation_date = request.getParameter("t_reservation_date") != null ?
				request.getParameter("t_reservation_date").toString() : null;
		String t_reservation_menuId = request.getParameter("t_reservation_menuId") != null ?
				request.getParameter("t_reservation_menuId").toString() : null;
		String t_reservation_seatId = request.getParameter("t_reservation_seatId") != null ?
				request.getParameter("t_reservation_seatId").toString() : null;
		String t_reservation_memo = request.getParameter("t_reservation_memo") != null ?
				request.getParameter("t_reservation_memo").toString() : null;
		String t_reservation_appoint = request.getParameter("t_reservation_appoint") != null ?
				request.getParameter("t_reservation_appoint") : null;

		/*
		//debug
		System.out.println("t_reservation_id:"+t_reservation_id);
		System.out.println("t_reservation_userId:"+t_reservation_userId);
		System.out.println("t_reservation_salonId:"+t_reservation_salonId);
		System.out.println("t_reservation_stylistId:"+t_reservation_stylistId);
		System.out.println("t_reservation_date:"+t_reservation_date);
		System.out.println("t_reservation_menuId:"+t_reservation_menuId);
		System.out.println("t_reservation_seatId:"+t_reservation_seatId);
		System.out.println("t_reservation_memo:"+t_reservation_memo);
		System.out.println("t_reservation_appoint:"+t_reservation_appoint);
		*/

		boolean rt = true;
		if(t_reservation_userId==null || t_reservation_userId==""){
			rt = false;
		}
		if(t_reservation_salonId==null || t_reservation_salonId==""){
			rt = false;
		}
		if(t_reservation_stylistId==null || t_reservation_stylistId==""){
			rt = false;
		}
		if(t_reservation_seatId==null || t_reservation_seatId==""){
			rt = false;
		}
		int appoint = 0;
		if(t_reservation_appoint!=null && t_reservation_appoint!=""){
			appoint = Integer.parseInt(t_reservation_appoint);
		}
				
		/*
		Date oneDate;
		try {
			oneDate = toDate(t_reservation_date, "YYYY-MM-DD HH:mm:ss");
			reservationInfo.setReservationDate(oneDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		ReservationInfo reservationInfo = new ReservationInfo();
		if(rt){
			if(t_reservation_id!=null && t_reservation_id!=""){
				//予約修正時にのみ入る
				reservationInfo.setReservationId(Integer.parseInt(t_reservation_id));
			}
			reservationInfo.setReservationUserId(Integer.parseInt(t_reservation_userId));
			reservationInfo.setReservationSalonId(Integer.parseInt(t_reservation_salonId));
			reservationInfo.setReservationStylistId(Integer.parseInt(t_reservation_stylistId));
			reservationInfo.setReservationDate(t_reservation_date);
			reservationInfo.setReservationMenuId(t_reservation_menuId);
			reservationInfo.setReservationSeatId(Integer.parseInt(t_reservation_seatId));
			reservationInfo.setReservationMemo(t_reservation_memo);
			reservationInfo.setReservationAppoint(appoint);
		}

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				if(rt){
					System.out.println("setReservation call!!");
					ReservationDao reservationDao = new ReservationDao();
					result = reservationDao.setReservationInfo(
							dbConnection,
							reservationInfo
							);
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			    }
			 * 
			 */
			
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
			
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
	/*
    public static Date toDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(0);
        if(str != null){
            date = sdf.parse(str);        	
        }
        return date;
    }
    */
}
