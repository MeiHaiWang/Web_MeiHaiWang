package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.dao.UserDao;
import net.sf.json.JSONObject;
import common.constant.Constant;
import common.model.UserInfo;
import common.util.DBConnection;
import common.util.EncryptUtil;

public class UserLoginService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response,String tel,String pw,String userHash){

		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
        String retHash="";
        int updated=-1;
        int autoLogin = 0;
        String cause="noError";
    	//レスポンスに設定するJSON Object
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonOneData = new JSONObject();
		
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			UserDao userDao = new UserDao();
			UserInfo info = null;
			if(conn!=null){
				//自動ログイン
				if(userHash != null){
					info = userDao.getUserInfoByHash(dbConnection, userHash);
					//自動ログイン成功
					if(info != null){
						autoLogin = 1;
					}
				}
				else{
					info = userDao.getUserInfoByLoginInfo(dbConnection, pw, tel);
					//ログイン成功 Hash値を再計算してユーザテーブルに格納する
					if(info != null){
						retHash = EncryptUtil.getHashValue(pw + tel);
						updated = userDao.updateUserHash(dbConnection, info.getUserId(),retHash);
					}
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				cause = "DataBaseConnectError";
				//throw new Exception("DabaBase Connect Error");
			}
		
			//非自動ログイン
			if(!retHash.equals("") && updated > 0){
				jsonOneData.put("userHash", retHash);
				jsonOneData.put("cause", cause);
				jsonObject.put("user_info", jsonOneData);
			}else if(autoLogin == 1){
				jsonObject.put("autoLoginSuccess", 1);
				jsonObject.put("cause", cause);
			}
		    
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
			cause =  e.getStackTrace().toString();
		}
        
        try{
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
