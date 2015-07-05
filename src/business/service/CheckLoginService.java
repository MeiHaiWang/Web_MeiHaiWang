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
import business.dao.SalonDao;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.AreaInfo;
import common.model.UserInfo;
import common.util.DBConnection;
import common.util.EncryptUtil;

/**
 * 
 * @author kanijunnari
 * 
 * 概要：認証＆セッションの確立セット
 * 入力：{ mail:メールアドレス, passowrd：パスワード ｝
	処理：ユーザのメアドとパスワードが一致しているかチェックし、ログインできればセッションにログイン済みであることをセットする。
	同時にセッション情報にサロンIDをセットする。
	出力：{ result:ログイン成否 } 
 */

public class CheckLoginService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		/**
		 * Declaration values
		 */
        int responseStatus = HttpServletResponse.SC_OK;
        		
        /*
        //user-cookie for auto-login
		String userHash = request.getParameter("userHash") != null ?
				request.getParameter("userHash").toString() : null;
		if(userHash == "NULL") userHash = null;
		*/

		//user-input for manual-login
		String[] mailList = request.getParameterValues("mail_address")!=null
        		?request.getParameterValues("mail_address") : null;
        String[] passwordList = request.getParameterValues("password")!=null
        		?request.getParameterValues("password") : null;
        String mail = null;
        String password = null;
        if(mailList!=null){
           mail  = mailList[0];
        }
        if(passwordList!=null){
        	password = passwordList[0];        	
        }

        //TODO: test
        //mail  = "mail.test.com";        	
    	//password = "password";
        
        /*
        //dubug
        System.out.println("mail: " + mail);
        System.out.println("password: " + password);
        System.out.println("userHash: " + userHash);
        */
        
    	//result of sql
    	boolean result = false;
    	//master-userId
        int masterUserId = -1;
        //master-salonId
        int salonId = -1;
		String salonId_str = null;
		
		//userDao
		//UserDao userDao = new UserDao();
		//UserInfo info = null;
		SalonDao salonDao = new SalonDao();
		
		//return-value
		String retHash="";

		//updated-flag
		int updated=-1;
				
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			if(conn!=null){
				//do Login 
				salonId = salonDao.getCheckLoginInfo(dbConnection, mail, password);
				if(salonId >= 0) result = true;
				//if(masterUserId >= 0) result = true;
				//ログイン成功
//				if(result){
					//Master-salonId by userId
					//salonId = userDao.getMsterSalonId(dbConnection, masterUserId);
					//debug
					//System.out.println("Got a SalonId: " + salonId);
					/*
					//Hash値を再計算してユーザテーブルに格納する
					if(salonId>=0){
						retHash = EncryptUtil.getHashValue(mail + password);
						updated = userDao.updateUserHash(dbConnection, masterUserId, retHash);
					}else{
						result = false;
					}
					*/
//				}
				
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
			
			//Create Session and add SalonId to session.
			if(result){
				HttpSession session = request.getSession();
				salonId_str = Integer.toString(salonId);
				session.setAttribute("login", "logined");
				session.setAttribute("t_hairSalonMaster_salonId", salonId_str);
				//cookie write?
				
			}else{
				System.out.println("ログイン失敗.");
			}
			
			/* output
			 *{ result:ログイン成否 }
			 */
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
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
