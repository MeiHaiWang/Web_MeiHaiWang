package business.service;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.StylistDao;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.StylistInfo;
import common.util.DBConnection;

/**
 *   input:
 *     {
      t_stylist_name,
      t_stylist_sex,
      t_stylist_phoneNumber,
      t_stylist_mail,
      t_stylist_imagePath,
      t_stylist_birth,
      t_stylist_position,
      t_stylist_experienceYear,
      t_stylist_specialMenu,
      t_stylist_message,
    }
 * @author kanijunnari
 *
 */

public class SetStaffInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		 // userIdがパラメータ。なかったら-1を入れておく。
        //TODO テスト用
        userId = 1;
        
  		HttpSession session = request.getSession(false);
		String salonId_str = "";
		int salonId = -1;
		//TODO: test
		salonId = 5;
		
		if (session != null){
			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
		}else{
			//session is null.
		}
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}else{
			//salonId is null.
		}

		String t_stylist_name = request.getParameter("t_stylist_name") != null ?
				request.getParameter("t_stylist_name").toString() : null;
		String t_stylist_sex = request.getParameter("t_stylist_sex") != null ?
				request.getParameter("t_stylist_sex").toString() : null;
		String t_stylist_phoneNumber = request.getParameter("t_stylist_phoneNumber") != null ?
				request.getParameter("t_stylist_phoneNumber").toString() : null;
		String t_stylist_mail = request.getParameter("t_stylist_mail") != null ?
				request.getParameter("t_stylist_mail").toString() : null;
		String t_stylist_imagePath = request.getParameter("t_stylist_imagePath") != null ?
				request.getParameter("t_stylist_imagePath").toString() : null;
		String t_stylist_birth = request.getParameter("t_stylist_birth") != null ?
				request.getParameter("t_stylist_birth").toString() : null;
		String t_stylist_position = request.getParameter("t_stylist_position") != null ?
				request.getParameter("t_stylist_position").toString() : null;
		String t_stylist_experienceYear = request.getParameter("t_stylist_experienceYear") != null ?
				request.getParameter("t_stylist_experienceYear").toString() : null;
		String t_stylist_specialMenu = request.getParameter("t_stylist_specialMenu") != null ?
				request.getParameter("t_stylist_specialMenu").toString() : null;
		String t_stylist_message = request.getParameter("t_stylist_message") != null ?
				request.getParameter("t_stylist_message").toString() : null;

		//stylistInfo を渡したほうがきれいかも.
		StylistInfo stylistInfo = new StylistInfo();
		stylistInfo.setStylistName(t_stylist_name);
		//stylistInfo.setStylistGender(Integer.parseInt(t_stylist_sex));
		int sex = -1;
		if(t_stylist_sex != null) sex = Integer.parseInt(t_stylist_sex);
		stylistInfo.setStylistGender(sex);
		stylistInfo.setPhoneNumber(t_stylist_phoneNumber);
		stylistInfo.setMail(t_stylist_mail);
		stylistInfo.setStylistImagePath(t_stylist_imagePath);
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date birth = new Date(0);
		try {
			if(t_stylist_birth != null)
				birth = sdf.parse(t_stylist_birth);
			stylistInfo.setBirth(birth);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stylistInfo.setPosition(t_stylist_position);
		int year = -1;
		if(t_stylist_experienceYear != null) year = Integer.parseInt(t_stylist_experienceYear);
		//stylistInfo.setStylistYears(Integer.parseInt(t_stylist_experienceYear));
		stylistInfo.setStylistYears(year);
		stylistInfo.setSpecialMenu(t_stylist_specialMenu);
		stylistInfo.setStylistMessage(t_stylist_message);

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			int stylistId = -1;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				StylistDao stylistDao = new StylistDao();
				stylistId = stylistDao.setStylistInfoForMaster(
						dbConnection,
						salonId,
						stylistInfo
						);
				if(stylistId >= 0) result = true;
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			      t_stylist_Id:登録したスタッフのID,
			    }
			 * 
			 */
			
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
		    jsonObject.put("t_stylist_Id", stylistId);
			
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
	/*
    public static Date toDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(0);
        if(str != null){
            date = sdf.parse(str);        	
        }
        return date;
    }
    */
}
