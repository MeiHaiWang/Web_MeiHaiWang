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
　}
	出力：
	{true（予約取れた）／false}　

 */

public class SetReservationInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
   		HttpSession session = request.getSession(false);
		
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

		//debug
		//System.out.println("update menuId:"+t_menu_t_menu_id+"...:"+t_menu_t_menu_id.charAt(0));

		ReservationInfo reservationInfo = new ReservationInfo();
		reservationInfo.setReservationUserId(Integer.parseInt(t_reservation_userId));
		reservationInfo.setReservationSalonId(Integer.parseInt(t_reservation_salonId));
		reservationInfo.setReservationStylistId(Integer.parseInt(t_reservation_stylistId));
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
		reservationInfo.setReservationDate(t_reservation_date);
		reservationInfo.setReservationMenuId(t_reservation_menuId);
		reservationInfo.setReservationSeatId(Integer.parseInt(t_reservation_seatId));
		reservationInfo.setReservationMemo(t_reservation_memo);

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				ReservationDao reservationDao = new ReservationDao();
				result = reservationDao.setReservationInfo(
						dbConnection,
						reservationInfo
						);
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
