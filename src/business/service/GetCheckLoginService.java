package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.AreaInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class GetCheckLoginService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        String[] mail = request.getParameterValues("mail");
        String mail_1;
        if(mail!=null){
           mail_1  = mail[0];
        }else{
        	//test
            mail_1  = "mail.test.com";        	
        }
        String[] password = request.getParameterValues("password");
        String password_1;
        if(password!=null){
        	password_1 = password[0];        	
        }else{
        	password_1 = "password";
        }
		boolean result = false;
		int salonId = -1;
		String salonId_str = "";
		
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<UserInfo> infoList = new ArrayList<UserInfo>();
			if(conn!=null){
				UserDao userDao = new UserDao();
				result = userDao.getCheckLoginInfo(dbConnection, mail_1, password_1);
				//salonId = userDao.getUserSalonId(dbConnection, userId);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			if(result){
				HttpSession session = request.getSession();
				salonId_str = Integer.toString(salonId);
				session.setAttribute("salonId", salonId_str);
				//cookie write?
			}

			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/* output
			 *{ result:ログイン成否 }
			 */
			
			
		    /*
			JSONArray userArray = new JSONArray();
		    for(UserInfo userInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", AreaInfo.getAreaId());		    	
		    	AreaArray.add(jsonOneData);
		    }
		    */
		    jsonObject.put("result", result);

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
