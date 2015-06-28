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

/**
 * 
 * @author kanijunnari
 *

    checkSession
        概要：セッションが確立されているか確認し、確立されていればサロンIDを取得
        入力：なし
        処理：getSession
        出力：

    {
      status:セッション有無,
      t_hairSalonMaster_salonId,
      t_hairSalonMaster_contactUserName
    }
 */

public class CheckSessionService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		/**
		 * Declaration values
		 */

		//Http-servlet-response
		int responseStatus = HttpServletResponse.SC_OK;
        //result of sql
		boolean result = false;

		//user-Id
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        //user-Idがあればすでにログイン済
        if(userId != -1){
    		response.setStatus(responseStatus);
    		return response;
        }

		HttpSession session = request.getSession(false);
		//Whether session is created
		if (session == null){
		  // まだ認証されていない
		  session = request.getSession(true);
		  	try {
				response.sendRedirect("/checkLogin");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//認証しているかを確認
		}else{
		  Object loginCheck = session.getAttribute("login");
		  if (loginCheck == null){
		    // まだ認証されていない
		    try {
				response.sendRedirect("/checkLogin");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		}
		
		//get salonId in session-info
		String salonId_str = "";
		int salonId = 0;
		salonId_str = (String)session.getAttribute("salonId");
		if(salonId_str != null){
			salonId = Integer.parseInt(salonId_str);
			result = true;
		}

		//get Contact-user-name by SalonId in salonTable
		String salonContactUserName = "";
		SalonDao salonDao = new SalonDao();
		
		try{
			if(result){
				DBConnection dbConnection = new DBConnection();
				java.sql.Connection conn = dbConnection.connectDB();
				if(conn!=null){
					salonContactUserName = salonDao.getCheckSession(dbConnection, salonId);
					if(salonContactUserName == "") result = false;
					dbConnection.close();
				}else{
					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
					throw new Exception("DabaBase Connect Error");
				}
			}
			
			
			/* output
		    {
		      status:セッション有無,
		      t_hairSalonMaster_salonId,
		      t_hairSalonMaster_contactUserName
		    }			 
		 	*/					
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("status", result);		    	
	    	jsonObject.put("t_hairSalonMaster_salonId", salonId);		    	
	    	jsonObject.put("t_hairSalonMaster_contactUserName", salonContactUserName);		    	
		    
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