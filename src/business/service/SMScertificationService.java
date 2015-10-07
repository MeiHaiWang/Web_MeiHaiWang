package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.StylistDao;
import business.dao.UserDao;
import common.model.UserInfo;
import common.util.DBConnection;

public class SMScertificationService {

		public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response,String tel ,String pw){
			
	        int responseStatus = HttpServletResponse.SC_OK;
	        JSONObject jsonObject = new JSONObject();
	        String cause="noError";
	        boolean result = false;
	        
			try{
				DBConnection dbConnection = new DBConnection();
				java.sql.Connection conn = dbConnection.connectDB();
				if(conn!=null){
					UserDao userDao = new UserDao();
					UserInfo userInfo = new UserInfo();
					userInfo.setUserPhoneNumber(tel);
					userInfo.setUserPass(pw);
					int userId = userDao.setUserAcount(dbConnection, userInfo);
					if(userId < 0) result = false;
					dbConnection.close();
				}else{
					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
					cause = "DataBaseConnectError";
					//throw new Exception("DabaBase Connect Error");
				}
			    
			}catch(Exception e){
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				e.printStackTrace();
				cause = e.getStackTrace().toString();
			}
		    
			try{
				String resultStr = String.valueOf(result);
		    	jsonObject.put("result", resultStr);		    	
		    	jsonObject.put("cause", cause);
			    PrintWriter out = response.getWriter();
			    out.print(jsonObject);
			    out.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			response.setStatus(responseStatus);
			return response;
		}
}
