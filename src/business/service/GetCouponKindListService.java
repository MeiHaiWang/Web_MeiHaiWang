package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.CouponDao;
import business.dao.CouponKindDao;
import common.constant.Constant;
import common.model.CouponInfo;
import common.model.CouponKindInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
 *
        概要：クーポン種別一覧取得
        入力：なし
        出力：

    {
      kind:[
        {
          t_couponKind_id,
          t_couponKind_name
        },
        ...
      ]
    }
    
    SELECT * FROM `t_masterCouponKind`
    
 */

public class GetCouponKindListService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response) {

		/**
		 * Declaration value
		 */		
		int responseStatus = HttpServletResponse.SC_OK;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<CouponKindInfo> infoList = new ArrayList<CouponKindInfo>();
			
			if(conn!=null){
				CouponKindDao couponKindDao = new CouponKindDao();
				infoList = couponKindDao.getCouponKindInfo(dbConnection);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用クーポンデータ（jsonデータの作成）
			JSONArray AreaArray = new JSONArray();
		    for(CouponKindInfo couponKindInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_couponKind_id", couponKindInfo.getCouponKindId());		    	
		    	jsonOneData.put("t_couponKind_Name", couponKindInfo.getCouponKindName());
		    	AreaArray.add(jsonOneData);
		    }
		    jsonObject.put("kind", AreaArray);

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
