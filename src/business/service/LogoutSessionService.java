package business.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.UserInfo;
import common.util.DBConnection;
import common.util.EncryptUtil;

public class LogoutSessionService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		boolean result = false;
		
		/**
		 * Declaration values
		 */
        int responseStatus = HttpServletResponse.SC_OK;
        //user-Id
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;

		//session-invalidate
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
			result = true;
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;			
		}
		
		//Reset User-cookie
		if(userId!=-1){
			try {
				DBConnection dbConnection = new DBConnection();
				UserDao userDao = new UserDao();
				String retHash = "NULL";
				int updated = userDao.updateUserHash(dbConnection, userId, retHash);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try {
			/* output
			 *{ result:ログイン成否 }
			 */
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			String resultStr = String.valueOf( result );
			jsonObject.put("result", resultStr);
	
		    PrintWriter out;
			out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}
}
