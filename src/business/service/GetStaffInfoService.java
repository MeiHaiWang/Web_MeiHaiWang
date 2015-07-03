package business.service;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.SalonDao;
import business.dao.StylistDao;
import common.constant.Constant;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetStaffInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
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
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<Integer> stylistIdList = new ArrayList<Integer>();
			List<StylistInfo> stylistInfoList  = new ArrayList<StylistInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				stylistIdList = salonDao.getStylistIdList(dbConnection, salonId);
				StylistDao stylistDao = new StylistDao();
				stylistInfoList = stylistDao.getStylistInfoForMaster(dbConnection, stylistIdList);	
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			
			/**
			 *
			    {
			      stylist:[
			        {
			          t_stylist_Id,
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
			          t_menu_t_menu_id:カンマ区切りの文字列,
			        },
			        ...
			      ]
			    }
			 */
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray stylistArray = new JSONArray();
		    for(StylistInfo info : stylistInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_stylist_stylist_id", info.getStylistId());
		    	jsonOneData.put("t_stylist_name", info.getStylistName());
		    	jsonOneData.put("t_stylist_sex", info.getStylistGender());
		    	jsonOneData.put("t_stylist_photoNumber", info.getPhoneNumber());
		    	jsonOneData.put("t_stylist_mail", info.getMail());
		    	jsonOneData.put("t_stylist_imagePath", info.getStylistImagePath());
		    	//jsonOneData.put("t_stylist_birth", info.getBirth());
		    	Date birthDate = info.getBirth();
		    	DateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		    	String birthStr = sdf.format(birthDate);
		    	jsonOneData.put("t_stylist_birth", birthStr);
		    	jsonOneData.put("t_stylist_position", info.getPosition());
		    	jsonOneData.put("t_stylist_experienceYear", info.getStylistYearsNumber());
		    	jsonOneData.put("t_stylist_specialMenu", info.getSpecialMenu());
		    	jsonOneData.put("t_stylist_message", info.getStylistMessage());
		    	jsonOneData.put("t_menu_t_menu_id:", info.getMenuId());
			    stylistArray.add(jsonOneData);
		    }
		    jsonObject.put("stylist",stylistArray);		    
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
