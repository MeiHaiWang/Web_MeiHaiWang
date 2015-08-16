package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetSalonMapService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		int userId = request.getHeader(Constant.HEADER_USERID) != null ?
				Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		
        int salonId = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
        
        		
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			HairSalonInfo salonInfo = new HairSalonInfo();
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				salonInfo = salonDao.getSalonMapInfo(salonId, dbConnection);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonOneData = new JSONObject();
			jsonOneData.put("image", salonInfo.getSalonMapImagePath());
			jsonOneData.put("latitude", salonInfo.getSalonLatitude());
			jsonOneData.put("longitude", salonInfo.getSalonLongitude());
			jsonOneData.put("info", salonInfo.getSalonMapInfo());
		    // 返却用サロンデータ（jsonデータの作成）
		    jsonObject.put("shop_map",jsonOneData);
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
