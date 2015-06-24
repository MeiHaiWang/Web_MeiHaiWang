package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.CouponDao;
import common.constant.Constant;
import common.model.CouponInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
    出力：

    {
      coupon:[
        {
      t_coupon_Id,
      t_coupon_couponKindId,
          t_coupon_name,
          t_coupon_detailText,
          t_coupon_price,
          t_coupon_deadLine,
          t_coupon_presentationCondition,
          t_coupon_useCondition,
          t_coupon_imagePath,
        },
      ]
    }
 */

public class GetCouponInfoService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response) {

		int responseStatus = HttpServletResponse.SC_OK;
		/*
		int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;
        */
		
		HttpSession session = request.getSession(false);
		String salonId_str = "";
		int salonId = -1;
		//TODO: test
		salonId = 1;
		
		if (session != null){
			salonId_str = (String)session.getAttribute("salonId");
		}else{
			//session is null.
		}
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}else{
			//salonId is null.
		}
		
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<CouponInfo> infoList = new ArrayList<CouponInfo>();
			
			if(conn!=null){
				CouponDao couponDao = new CouponDao();
				String couponIdList = couponDao.getCouponId(dbConnection, salonId);
				infoList = couponDao.getCouponInfo(dbConnection, couponIdList,salonId);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			/*
			    出力：
			
			    {
			      coupon:[
			        {
			      t_coupon_Id,
			      t_coupon_couponKindId,
			          t_coupon_name,
			          t_coupon_detailText,
			          t_coupon_price,
			          t_coupon_deadLine,
			          t_coupon_presentationCondition,
			          t_coupon_useCondition,
			          t_coupon_imagePath,
			        },
			      ]
			    }
			 */
			
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用クーポンデータ（jsonデータの作成）
			JSONArray AreaArray = new JSONArray();
		    for(CouponInfo couponInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_coupon_Id", couponInfo.getCouponId());		    	
		    	jsonOneData.put("t_coupon_couponKindId", couponInfo.getCouponKindId());		    	
		    	jsonOneData.put("t_coupon_name", couponInfo.getCouponName());
		    	jsonOneData.put("t_coupon_detailText", couponInfo.getCouponDetailText());
		    	jsonOneData.put("t_coupon_price", couponInfo.getPrice());
		    	jsonOneData.put("t_coupon_deadLine", couponInfo.getDeadLine());
		    	jsonOneData.put("t_coupon_presentationCondition", couponInfo.getPresentationCondition());
		    	jsonOneData.put("t_coupon_useCondition", couponInfo.getUseCondition());
		    	jsonOneData.put("t_coupon_imagePath", couponInfo.getImagePath());
		    	AreaArray.add(jsonOneData);
		    }
		    jsonObject.put("coupon",AreaArray);

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
