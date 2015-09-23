package business.service;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.ReservationDao;

import common.model.ReservationInfo;
import common.util.DBConnection;
/**
 * 
 * @author kanijunnari
 * 入力：
	　{
	     t_reservation_id,
	     t_reservation_isFinished(1:来店、2:キャンセル(連絡あり)、3:キャンセル（連絡なし）)
	　}
	出力：
	{true／false}
 *
 */

public class SetReservationFinishService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
   		HttpSession session = request.getSession(false);
		
		String t_reservation_id = request.getParameter("t_reservation_id") != null ?
				request.getParameter("t_reservation_id").toString() : null;
		String t_reservation_isFinished = request.getParameter("t_reservation_isFinished") != null ?
				request.getParameter("t_reservation_isFinished").toString() : null;

		//debug
		//System.out.println("update menuId:"+t_menu_t_menu_id+"...:"+t_menu_t_menu_id.charAt(0));

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				ReservationDao reservationDao = new ReservationDao();
				result = reservationDao.setReservationFinish(
						dbConnection,
						t_reservation_id,
						t_reservation_isFinished
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
}
