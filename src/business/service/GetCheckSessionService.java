package business.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.SalonDao;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetCheckSessionService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;

		boolean result = false;
		HttpSession session = request.getSession(false);
		//debug
		//System.out.println("login: " + session.getAttribute("salonId"));
		
		//認証しているかを確認
		if (session == null){
		  // まだ認証されていない
		  session = request.getSession(true);
		  try {
			response.sendRedirect("/CheckLogin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
		  Object loginCheck = session.getAttribute("login");
		  if (loginCheck == null){
		    // まだ認証されていない
		    try {
				response.sendRedirect("/CheckLogin");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		}
		
		String salonId_str = "";
		String salonContactUserName = "";
		int salonId = 0;
		salonId_str = (String)session.getAttribute("salonId");
		if(salonId_str != null){
			salonId = Integer.parseInt(salonId_str);
			result = true;
		}else{
			result = false;
		}
		
		try{
			List<HairSalonInfo> infoList = new ArrayList<HairSalonInfo>();
			if(result){
				DBConnection dbConnection = new DBConnection();
				java.sql.Connection conn = dbConnection.connectDB();
				if(conn!=null){
					SalonDao salonDao = new SalonDao();
					salonContactUserName = salonDao.getCheckSession(dbConnection, salonId);
					dbConnection.close();
				}else{
					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
					throw new Exception("DabaBase Connect Error");
				}
			}			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/* output
			    {
			      status:セッション有無,
			      t_hairSalonMaster_salonId,
			      t_hairSalonMaster_contactUserName
			    }			 
			*/
						
			JSONArray salonArray = new JSONArray();
			/*
			for(HairSalonInfo salonInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	//jsonOneData.put("t_hairSalonMaster_salonId", salonInfo.getHairSalonId());		    	
		    	//jsonOneData.put("t_hairSalonMaster_contactUserName", salonInfo.getContactUserName());		    	
		    	salonArray.add(jsonOneData);
		    }
		    */
	    	JSONObject jsonOneData = new JSONObject();
	    	jsonOneData.put("t_hairSalonMaster_salonId", salonId);		    	
	    	jsonOneData.put("t_hairSalonMaster_contactUserName", salonContactUserName);		    	
	    	salonArray.add(jsonOneData);
		    jsonObject.put("", salonArray);
		    
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