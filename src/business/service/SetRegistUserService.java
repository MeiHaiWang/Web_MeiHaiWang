package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.dao.UserDao;
import net.sf.json.JSONObject;
import common._model.TUserInfo;
import common.constant.Constant;
import common.model.UserInfo;
import common.util.DBConnection;

public class SetRegistUserService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
  		HttpSession session = request.getSession(false);
  		//salonId kokokara
  	    int salonId = -1;
  	    //get a salonId by session
  		String salonId_str = "";
  		if (session != null){
  			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
  		}
  		if(salonId_str != null){			
  			if(salonId_str.compareTo("") != 0){
  				salonId = Integer.parseInt(salonId_str);
  			}
  		}   
  		if(salonId < 0){
  	        //get a salonId by parameter
  	        salonId = request.getParameter(Constant.PARAMETER_SALONID)!= null 
  			?Integer.parseInt(request.getParameter(Constant.PARAMETER_SALONID)) : -1;
  		}
  		//salonId kokomade		
  		
  		//パラメータ取得
		String t_user_name = request.getParameter("t_user_name") != null ?
				request.getParameter("t_user_name").toString() : null;
		String t_user_age = request.getParameter("t_user_age") != null ?
				request.getParameter("t_user_age").toString() : null;
		String t_user_gender = request.getParameter("t_user_gender") != null ?
				request.getParameter("t_user_gender").toString() : null;
		String t_user_tel = request.getParameter("t_user_tel") != null ?
				request.getParameter("t_user_tel").toString() : null;

		//userInfo を渡したほうがきれいかも.
		TUserInfo userInfo = new TUserInfo();
		userInfo.setName(t_user_name);
		int age = 0;
		if(t_user_age != null && t_user_age != ""){
			age = Integer.parseInt(t_user_age);
		}
		userInfo.setUserAge(age);
		int gender = 0;
		if(t_user_gender != null && t_user_gender != ""){
			gender = Integer.parseInt(t_user_gender);
		}
		userInfo.setTUserSex(gender);
		userInfo.setTUserTel(t_user_tel);

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			int userId = -1;
			JSONObject jsonObject = new JSONObject();

			if(conn!=null){
				UserDao userDao = new UserDao();
				/*
				userId = userDao.setRegistUserInfo(
						dbConnection,
						salonId,
						userInfo
						);
						*/
				userId = userDao.save(dbConnection, userInfo);
				if(userId > 0) result = true;
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			      t_user_userId:登録したサービスのID,
			    }
			 */		
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
		    jsonObject.put("t_user_id", userId);
			
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
