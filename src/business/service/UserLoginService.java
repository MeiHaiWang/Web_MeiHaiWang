package business.service;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.dao.UserDao;
import net.sf.json.JSONObject;
import common._model.TUserInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.UserInfo;
import common.util.DBConnection;
import common.util.EncryptUtil;

import org.bouncycastle.util.encoders.Base64;


public class UserLoginService implements IServiceExcuter {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
        		
		//変数定義
        String cause="noError";
		String retHash="";
        int updated=-1;
        int autoLogin = 0;

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

			//TODO: SNS連携
    		int snsLinkFlag = request.getParameter("snsLink") != null ?
    				Integer.parseInt(request.getParameter("snsLink").toString()) : 0;
    				
			//ユーザハッシュ（自動ログイン）
    		String userHash = request.getParameter("userHash") != null ?
    				request.getParameter("userHash").toString() : null;
			if(userHash == "NULL" ) userHash = null;
	
	    	//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonOneData = new JSONObject();
		
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			UserDao userDao = new UserDao();
			List<TUserInfo> infoList = new ArrayList<TUserInfo>();
			
			if(conn!=null){
				//自動ログイン
				if(userHash != null){
					//info = userDao.getUserInfoByHash(dbConnection, userHash);
					infoList = userDao.getByColumn(dbConnection, TableConstant.COLUMN_USER_HASH, userHash);
					//自動ログイン成功
					if(infoList.size() != 0){
						autoLogin = 1;
					}
				}
				else{
					Map<String, Object> source = new HashMap<String, Object>();
					source.put(TableConstant.COLUMN_USER_TEL, tel);
					source.put(TableConstant.COLUMN_USER_PASSWORD, tel);
					//info = userDao.getUserInfoByLoginInfo(dbConnection, pw, tel);
					infoList = userDao.getByColumns(dbConnection, source);
					//ログイン成功 Hash値を再計算してユーザテーブルに格納する
					if(infoList.size() != 0){
						retHash = EncryptUtil.getHashValue(pw + tel);
						//updated = userDao.updateUserHash(dbConnection, info.getObjectId(),retHash);
						TUserInfo userInfo = infoList.get(0);
						userInfo.setTUserCookie(retHash);
						updated = userDao.update(dbConnection, userInfo);
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
        
        	PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();

        }catch(UnsupportedEncodingException e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
			cause =  e.getStackTrace().toString();
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
			cause =  e.getStackTrace().toString();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}
}
