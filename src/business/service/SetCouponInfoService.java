package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.CouponDao;
import common.constant.Constant;
import common.model.CouponInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *        入力：

    {
      t_hairSalonMaster_salonId,
      t_coupon_couponKindId,
      t_coupon_name,
      t_coupon_detailText,
      t_coupon_price,
      t_coupon_deadLine,
      t_coupon_presentationCondition,
      t_coupon_useCondition,
      t_coupon_imagePath,
    }

    出力：

    {
      result:レコード更新成否,
      t_coupon_Id:登録したクーポンのID,
    }
 */
public class SetCouponInfoService {
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
		
		String t_hairSalonMaster_salonId = request.getParameter("t_hairSalonMaster_salonId") != null ?
				request.getParameter("t_hairSalonMaster_salonId").toString() : null;
		String t_coupon_Id = request.getParameter("t_coupon_Id") != null ?
				request.getParameter("t_coupon_Id").toString() : null;
		String t_coupon_couponKindId = request.getParameter("t_coupon_couponKindId") != null ?
				request.getParameter("t_coupon_couponKindId").toString() : null;
		String t_coupon_name = request.getParameter("t_coupon_name") != null ?
				request.getParameter("t_coupon_name").toString() : null;
		String t_coupon_detailText = request.getParameter("t_coupon_detailText") != null ?
				request.getParameter("t_coupon_detailText").toString() : null;
		String t_coupon_price = request.getParameter("t_coupon_price") != null ?
				request.getParameter("t_coupon_price").toString() : null;
		String t_coupon_deadLine = request.getParameter("t_coupon_deadLine") != null ?
				request.getParameter("t_coupon_deadLine").toString() : null;
		String t_coupon_presentationCondition = request.getParameter("t_coupon_presentationCondition") != null ?
				request.getParameter("t_coupon_presentationCondition").toString() : null;
		String t_coupon_useCondition = request.getParameter("t_coupon_useCondition") != null ?
				request.getParameter("t_coupon_useCondition").toString() : null;
		String t_coupon_imagePath = request.getParameter("t_coupon_imagePath") != null ?
				request.getParameter("t_coupon_imagePath").toString() : null;

				
		//CouponInfo を渡したほうがきれいかも.
		CouponInfo couponInfo = new CouponInfo();
		int couponId = -1;
		if(t_coupon_Id != null && t_coupon_Id != "") couponId = Integer.parseInt(t_coupon_Id);
		int kindId = -1;
		if(t_coupon_couponKindId != null&& t_coupon_couponKindId != "") kindId = Integer.parseInt(t_coupon_couponKindId);
		couponInfo.setCouponKindId(kindId);
		couponInfo.setCouponName(t_coupon_name);
		couponInfo.setCouponDetailText(t_coupon_detailText);
		int price = -1;
		if(t_coupon_price != null&& t_coupon_price!= "") price = Integer.parseInt(t_coupon_price);
		couponInfo.setPrice(price);
		couponInfo.setDeadLine(t_coupon_deadLine);
		couponInfo.setPresentationCondition(t_coupon_presentationCondition);
		couponInfo.setUseCondition(t_coupon_useCondition);
		couponInfo.setImagePath(t_coupon_imagePath);

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			//int couponId = -1;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				CouponDao couponDao = new CouponDao();
				couponId = couponDao.setCouponInfoForMaster(
						dbConnection,
						salonId,
						couponInfo
						);
				if(couponId > 0) result = true;
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
		    jsonObject.put("t_coupon_Id", couponId);
			
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
