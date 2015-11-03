package business.service;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.SalonDao;
import common.constant.Constant;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *    
 	{
      t_hairSalonMaster_mapUrl,
      t_hairSalonMaster_mapImagePath,
    }
    UPDATE `MEIHAIWAN_TEST`.`t_hairSalonMaster` SET `t_hairSalonMaster_mapUrl` = 'mapUrl', `t_hairSalonMaster_mapImagePath` = 'image' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = 7;
 */

public class SetMapInfoService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		 // userIdがパラメータ。なかったら-1を入れておく。
        
		HttpSession session = request.getSession(false);
		String salonId_str = "";
		int salonId = -1;
		
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

	    String t_hairSalonMaster_mapUrl = request.getParameter("t_hairSalonMaster_mapUrl") != null ?
				request.getParameter("t_hairSalonMaster_mapUrl").toString() : null;
				/*
		String t_hairSalonMaster_mapImagePath = request.getParameter("t_hairSalonMaster_mapImagePath") != null ?
				request.getParameter("t_hairSalonMaster_mapImagePath").toString() : null;
				*/
		String t_hairSalonMaster_mapImagePath = "";

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				result = salonDao.setSalonMapInfo(
						dbConnection,
						salonId,
					      t_hairSalonMaster_mapUrl,
					      t_hairSalonMaster_mapImagePath
				      );
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
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
