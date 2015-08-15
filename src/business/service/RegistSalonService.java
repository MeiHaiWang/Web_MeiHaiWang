package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.SalonDao;
import common.model.AreaInfo;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class RegistSalonService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		/**
		 * Declaration value
		 */
        int responseStatus = HttpServletResponse.SC_OK;

        String contactUserName = request.getParameter("userName")!= null?
        		request.getParameter("userName") : "";
 
        String salonName = request.getParameter("salonName")!= null?
                request.getParameter("salonName") : "";   
            
        String parentAreaId = request.getParameter("parentArea")!= null?
                request.getParameter("parentArea") : "";        

        String childAreaId = request.getParameter("childArea")!= null?
                request.getParameter("childArea") : "";
                
        String address = request.getParameter("address")!= null?
                request.getParameter("address") : ""; 
        
        String tel = request.getParameter("tel")!= null?
                request.getParameter("tel") : "";          
       
        String mail = request.getParameter("mail")!= null?
                request.getParameter("mail") : "";    
        
        String pass = request.getParameter("pass")!= null?
                request.getParameter("pass") : "";                
        boolean result = false;
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			HairSalonInfo salonInfo = new HairSalonInfo();
			if(conn!=null){
				salonInfo.setSalonContactUserName(contactUserName);
				salonInfo.setHairSalonName(salonName);
				salonInfo.setSalonAreaId(parentAreaId + "," + childAreaId);
				salonInfo.setAddress(address);
				salonInfo.setTelNumber(tel);
				salonInfo.setMail(mail);
				salonInfo.setPassword(pass);
				SalonDao salonDao = new SalonDao();
				result  = salonDao.registSalonOnDisable(salonInfo, dbConnection);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			String resultStr = String.valueOf(result);
	    	jsonObject.put("regist", resultStr);
	    	
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
