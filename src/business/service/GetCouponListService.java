package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.CouponDao;
import common.constant.Constant;
import common.model.CouponInfo;
import common.util.CommonUtil;
import common.util.DBConnection;

public class GetCouponListService implements IServiceExcuter{
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response) {

		int responseStatus = HttpServletResponse.SC_OK;
		/*
		int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;
		*/
	    String salonIdStr = request.getParameter(Constant.ID)!= null
	    		?request.getParameter(Constant.ID) : null;
	    int salonId = -1;
	    if(salonIdStr!=null && CommonUtil.isNum(salonIdStr)){
	    	salonId = Integer.parseInt(salonIdStr);
	    }

        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<CouponInfo> infoList = new ArrayList<CouponInfo>();
			
			if(conn!=null){
				CouponDao couponDao = new CouponDao();
				String couponIdList = couponDao.getCouponId(dbConnection, salonId);
				if(couponIdList!=null){
					infoList = couponDao.getCouponInfo(dbConnection, couponIdList,salonId);
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用クーポンデータ（jsonデータの作成）
			JSONArray AreaArray = new JSONArray();
		    for(CouponInfo couponInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", couponInfo.getCouponId());		    	
		    	jsonOneData.put("shopID", couponInfo.getSalonId());
		    	jsonOneData.put("title", couponInfo.getCouponName());
		    	jsonOneData.put("detail", couponInfo.getCouponDetailText());
		    	jsonOneData.put("category", couponInfo.getCouponCategoryName());
		    	jsonOneData.put("price", couponInfo.getPrice());
		    	jsonOneData.put("presentation", couponInfo.getPresentationCondition());
		    	jsonOneData.put("deadline", couponInfo.getDeadLine());
		    	jsonOneData.put("conditions", couponInfo.getUseCondition());
		    	jsonOneData.put("isFirst", couponInfo.getIsFirstFlag());
		    	AreaArray.add(jsonOneData);
		    }
		    jsonObject.put("coupon_lists",AreaArray);

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
