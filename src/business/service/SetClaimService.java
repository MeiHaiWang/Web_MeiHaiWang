package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.ClaimDao;
import business.dao.CouponDao;
import common.constant.Constant;
import common.model.ClaimInfo;
import common.model.CouponInfo;
import common.util.DBConnection;

/*
	 *入力：
	　{
	   t_claim_id,
	   t_claim_reservationId,
	　	t_claim_salonId,
	　	t_claim_menuId,
	　	t_claim_message
	　}
	出力：
	　{
	    true/false
	　} 
 */

public class SetClaimService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
  		HttpSession session = request.getSession(false);
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

  		String t_claim_reservationId = request.getParameter("t_claim_reservationId") != null ?
				request.getParameter("t_claim_reservationId").toString() : null;
  		String t_claim_userId = request.getParameter("t_claim_userId") != null ?
				request.getParameter("t_claim_userId").toString() : null;
  		String t_claim_salonId = request.getParameter("t_claim_salonId") != null ?
				request.getParameter("t_claim_salonId").toString() : null;
  		String t_claim_menuId = request.getParameter("t_claim_menuId") != null ?
				request.getParameter("t_claim_menuId").toString() : null;
  		String t_claim_message = request.getParameter("t_claim_message") != null ?
				request.getParameter("t_claim_message").toString() : null;
  		String t_claim_date = request.getParameter("t_claim_date") != null ?
				request.getParameter("t_claim_date").toString() : null;
				
		ClaimInfo claimInfo = new ClaimInfo();
		claimInfo.setClaimReservationId(Integer.parseInt(t_claim_reservationId));
		claimInfo.setClaimSalonId(Integer.parseInt(t_claim_salonId));
		claimInfo.setClaimUserId(Integer.parseInt(t_claim_userId));
		claimInfo.setClaimMenuId(Integer.parseInt(t_claim_menuId));
		claimInfo.setClaimMessage(t_claim_message);
		claimInfo.setClaimDate(t_claim_date);
		
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				ClaimDao claimDao = new ClaimDao();
				result = claimDao.setColaim(
						dbConnection,
						claimInfo
						);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			      t_coupon_couponId:登録したサービスのID,
			    }
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
