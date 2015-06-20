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
import common.util.EncryptUtil;

public class GetCheckLoginService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		/**
		 * header 取得
		 */
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        String[] mailList = request.getParameterValues("mail");
        String mail;
        String[] passwordList = request.getParameterValues("password");
        String password;
		String userHash = request.getParameter("userHash") != null ?
				request.getParameter("userHash").toString() : null;

        if(mailList!=null){
           mail  = mailList[0];
        }else{
        	//test
            mail  = "mail.test.com";        	
        }
        if(passwordList!=null){
        	password = passwordList[0];        	
        }else{
        	password = "password";
        }
        
        /*
        //dubug
        System.out.println("mail: " + mail);
        System.out.println("password: " + password);
        System.out.println("userHash: " + userHash);
        */
        //header ここまで
        
        boolean result = false;
        int masterUserId = -1;
		int salonId = -1;
		String salonId_str = "";
				
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<UserInfo> infoList = new ArrayList<UserInfo>();
			UserDao userDao = new UserDao();
			UserInfo info = null;
	        String retHash="";
	        int updated=-1;
			
			if(conn!=null){
				//自動ログイン
				if(userHash != null){
					info = userDao.getUserInfoByHash(dbConnection, userHash);
					//自動ログイン成功
					if(info != null){
						result = true;
					}
				}
				else{
					//info = userDao.getUserInfoByLoginInfo(dbConnection, mail, password);
					masterUserId = userDao.getCheckLoginInfo(dbConnection, mail, password);
					if(masterUserId >= 0) result = true;
					//ログイン成功 Hash値を再計算してユーザテーブルに格納する
					if(result){
						salonId = userDao.getMsterSalonId(dbConnection, masterUserId);
						retHash = EncryptUtil.getHashValue(mail + password);
						updated = userDao.updateUserHash(dbConnection, masterUserId, retHash);
					}
				}
				
				/*
				//userTable にユーザIDが存在するかを確認。
				result = userDao.getCheckLoginInfo(dbConnection, mail, password);
				salonId = userDao.getMsterSalonId(dbConnection, userId);
				*/
				dbConnection.close();
				
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			if(result){
				HttpSession session = request.getSession();
				salonId_str = Integer.toString(salonId);
				session.setAttribute("login", "logined");
				session.setAttribute("salonId", salonId_str);
				//cookie write?
			}else{
				System.out.println("ログイン失敗.");
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
