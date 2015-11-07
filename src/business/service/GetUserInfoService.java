package business.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import common._model.TUserInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.UserInfo;
import common.util.DBConnection;

public class GetUserInfoService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){

	/**
	 * Declaration value
	 */
	HttpSession session = request.getSession();
    int responseStatus = HttpServletResponse.SC_OK;

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

	String t_user_tel = request.getParameter("t_user_tel") != null ?
			request.getParameter("t_user_tel").toString() : null;

	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
//		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		List<TUserInfo> userInfoList = new ArrayList<TUserInfo>();
		UserDao dao = new UserDao();
		TUserInfo userInfo = new TUserInfo();
		if(conn!=null){
			//userInfo = dao.getUserInfoByTel(dbConnection, t_user_tel);
			userInfoList = dao.getByColumn(dbConnection, TableConstant.COLUMN_USER_TEL, t_user_tel);
			userInfo = userInfoList.get(0);
			if(userInfo!=null && userInfo.getTUserId()>0){
				userInfo = dao.get(dbConnection, userInfo.getTUserId());
			}
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

		//レスポンスに設定するJSON Object
		//JSONObject jsonObject = new JSONObject();
		/**
		 * 
		 * {
		    t_user_id,
		    t_user_name,
		    t_user_tel,
		    t_user_age,
		    t_user_gender
		    }
		 */
		
		if(userInfo!=null){
			// 返却用サロンデータ（jsonデータの作成）
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("t_user_id", userInfo.getTUserId());
			jsonObject.put("t_user_name", userInfo.getTUserName());
			jsonObject.put("t_user_tel", userInfo.getTUserTel());
	    	/* 年齢を求める*/
	    	Date userBirth = userInfo.getTUserBirth();
	    	Date nowDate = new Date();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    	Calendar birthDay = Calendar.getInstance();
	    	birthDay.setTime(userBirth);
	    	Calendar today = Calendar.getInstance();
	    	today.setTime(nowDate);
	    	int age = today.get(Calendar.YEAR)-birthDay.get(Calendar.YEAR);
	    	birthDay.clear(Calendar.YEAR);
	    	today.clear(Calendar.YEAR);
	    	if(birthDay.after(today)){
	    		age-=1;
	    	}
	    	jsonObject.put("t_user_age", age);			
			jsonObject.put("t_user_gender", userInfo.getTUserSex());
		    
	    	PrintWriter out = response.getWriter();
			out.print(jsonObject);
			//debug
			System.out.println(jsonObject.toString(4));
			out.flush();
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("t_user_id", "-1");
	    	PrintWriter out = response.getWriter();
			out.print(jsonObject);
			System.out.println("I don't have a this-client. ");
			//responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		
	}catch(Exception e){
		responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		e.printStackTrace();
	}
    
	response.setStatus(responseStatus);
	return response;

	}
}
