package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.SalonDao;

import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetMapInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession(false);
		String salonId_str = "";
		int salonId = -1;
		//TODO: test
		
		if (session != null){
			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
		}else{
			//session is null.
		}
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}else{
			//salonId is null.
		}

        int responseStatus = HttpServletResponse.SC_OK;
				
		try{
			DBConnection dbConnection = new DBConnection();
			JSONObject jsonObject = new JSONObject();
			java.sql.Connection conn = dbConnection.connectDB();

			//List<Integer> salonIdList = new ArrayList<Integer>();
			HairSalonInfo salonInfo = new HairSalonInfo();
			//List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				salonInfo = salonDao.getSalonMapInfo(salonId, dbConnection);				
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object(title)
			/**
			 *  
			    {
			      t_hairSalonMaster_mapUrl,
			      t_hairSalonMaster_mapImagePath,
			    }
			 */
			jsonObject.put("t_hairSalonMaster_mapUrl", salonInfo.getSalonMapUrl());
			jsonObject.put("t_hairSalonMaster_mapImagePath", salonInfo.getSalonMapImagePath());

			// 返却用サロンデータ（jsonデータの作成）
						    		    
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
