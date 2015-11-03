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
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.CouponInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
 *
    getCouponInfo
        概要：クーポン情報管理ページ表示用データを取得する
        入力：

    {
      t_hairSalonMaster_salonId,
    }
    
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

public class GetCouponInfoService implements IServiceExcuter{
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response) {

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

  		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<Integer> couponIdList = new ArrayList<Integer>();
			List<CouponInfo> infoList = new ArrayList<CouponInfo>();
			
			if(conn!=null){
				//SalonDao salonDao = new SalonDao();
				CouponDao couponDao = new CouponDao();
				//couponIdList = salonDao.getCouponIdList(dbConnection, salonId);
				String couponIdList = couponDao.getCouponId(dbConnection, salonId);
				if(couponIdList!=""){
					infoList = couponDao.getCouponInfo(dbConnection, couponIdList,salonId);
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
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
