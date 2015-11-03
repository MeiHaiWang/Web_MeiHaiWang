package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.util.encoders.Base64;

import net.sf.json.JSONObject;
import business.dao.StylistDao;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.UserInfo;
import common.util.DBConnection;
import common.util.EncryptUtil;

public class SMScertificationService implements IServiceExcuter{

	/*
		public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response,String tel ,String pw){
				*/
		public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){			

			int responseStatus = HttpServletResponse.SC_OK;
	        JSONObject jsonObject = new JSONObject();
	        String cause="noError";
	        boolean result = false;
	        
			try{
				//キー定義
	        	byte[] key = Constant.KEY.getBytes("UTF-8");
	        	byte[] ivIv = Constant.IV_IV.getBytes("UTF-8");

	            //パラメータ処理(tel番とpwを復号)
	    		byte[] eTel = Base64.decode(request.getParameter("etel"));
	    		byte[] etelIv = Base64.decode(request.getParameter("etelIv"));
	    		byte[] epw = Base64.decode(request.getParameter("epw"));
	    		byte[] epwIv = Base64.decode(request.getParameter("epwIv"));

				String telIv = new String(EncryptUtil.decrypt(key, ivIv, etelIv));
				String pwIv = new String(EncryptUtil.decrypt(key, ivIv, epwIv));
				String tel = new String(EncryptUtil.decrypt(key, telIv.getBytes("UTF-8"), eTel));
				String pw = new String(EncryptUtil.decrypt(key, pwIv.getBytes("UTF-8"), epw));
				
				DBConnection dbConnection = new DBConnection();
				java.sql.Connection conn = dbConnection.connectDB();
				if(conn!=null){
					UserDao userDao = new UserDao();
					UserInfo userInfo = new UserInfo();
					userInfo.setUserPhoneNumber(tel);
					userInfo.setUserPass(pw);
					//int userId = userDao.setUserAcount(dbConnection, userInfo);
					int userId = userDao.setUserInfoInsert(dbConnection, userInfo);
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
