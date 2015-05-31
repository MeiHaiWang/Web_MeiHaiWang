package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import business.dao.StylistDao;
import common.model.HairSalonInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetStylistDetailService {

	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
		/*int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;*/
		 
        //TODO テスト用
        int salonId =1;
        int userId =1;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			StylistInfo stylistInfo = new StylistInfo();
			
			if(conn!=null){
				StylistDao stylistDao = new StylistDao();
				RecommendDao recomendDao = new RecommendDao();
				//salonInfoList = salonDao.getSalonDetailInfo(salonId, dbConnection);
				//ユーザがお気に入りしているかどうかを設定する
				//recomendDao.setIsFavoriteSalon(userId, salonInfoList, dbConnection);
				dbConnection.close();
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray salonArray = new JSONArray();
		    jsonObject.put("shop",salonArray);
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
