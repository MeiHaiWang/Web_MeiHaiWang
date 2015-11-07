package business.service;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ConditionDao;
import business.dao.SalonDao;
import business.dao.StylistDao;
import common._model.TStylistInfo;
import common.constant.Constant;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetStaffInfoService implements IServiceExcuter{
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

			//List<Integer> stylistIdList = new ArrayList<Integer>();
			List<String> stylistIdList = new ArrayList<String>();
			List<TStylistInfo> stylistInfoList  = new ArrayList<TStylistInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				//stylistIdList = salonDao.getStylistIdList(dbConnection, salonId);
				stylistIdList = Arrays.asList(salonDao.get(dbConnection, salonId).getTHairSalonMasterStylistId().split(","));
				if(stylistIdList.size()>0){
					StylistDao stylistDao = new StylistDao();
					for(String id : stylistIdList){
						stylistInfoList.add(stylistDao.get(dbConnection, Integer.parseInt(id)));
					}
					//stylistInfoList = stylistDao.getStylistInfoForMaster(dbConnection, stylistIdList);	
				}
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
			          t_stylist_restDay,
			          t_stylist_restTime
			        },
			        ...
			      ]
			    }
			 */
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray stylistArray = new JSONArray();
		    for(TStylistInfo info : stylistInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_stylist_Id", info.getTStylistId());
		    	jsonOneData.put("t_stylist_name", info.getTStylistName());
		    	jsonOneData.put("t_stylist_sex", info.getTStylistSex());
		    	jsonOneData.put("t_stylist_phoneNumber", info.getTStylistPhoneNumber());
		    	jsonOneData.put("t_stylist_mail", info.getTStylistMail());
		    	jsonOneData.put("t_stylist_imagePath", info.getTStylistImagePath());
		    	//jsonOneData.put("t_stylist_birth", info.getBirth());
		    	Date birthDate = info.getTStylistBirth();
		    	DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		    	String birthStr = sdf.format(birthDate);
		    	//debug
		    	//System.out.println("birthStr = " +birthStr);
		    	jsonOneData.put("t_stylist_birth", birthStr);
		    	jsonOneData.put("t_stylist_position", info.getTStylistPosition());
		    	jsonOneData.put("t_stylist_experienceYear", info.getTStylistExperienceYear());
		    	jsonOneData.put("t_stylist_specialMenu", info.getTStylistSpecialMenu());
		    	jsonOneData.put("t_stylist_message", info.getTStylistMessage());
		    	jsonOneData.put("t_menu_t_menu_id", info.getTStylistMenuId());
		    	jsonOneData.put("t_stylist_searchConditionId", info.getTStylistSearchConditionId());
		    	jsonOneData.put("t_stylist_restDay", info.getTStylistRestday());
		    	jsonOneData.put("t_stylist_restTime", info.getTStylistResttime());
		    	
		    	/*
		    	//検索条件
		    	List<ConditionInfo> ConditionInfoList  = new ArrayList<ConditionInfo>();
				List<ConditionTitleInfo> ConditionTitleInfoList  = new ArrayList<ConditionTitleInfo>();
				if(conn!=null){
					ConditionDao conditionDao = new ConditionDao();
					ConditionTitleInfoList = conditionDao.getConditionTitleInfo(dbConnection, Constant.TYPE_FOR_STYLIST_CONDITION);
					ConditionInfoList = conditionDao.getConditionInfo(dbConnection, ConditionTitleInfoList);				
					dbConnection.close();
				}else{
					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
					throw new Exception("DabaBase Connect Error");
				}
				//レスポンスJSON Object(title)
				JSONArray condTitleArray = new JSONArray();
			    for(ConditionTitleInfo condTitleInfo : ConditionTitleInfoList){
			    	JSONObject jsonConditionOneData = new JSONObject();
			    	jsonConditionOneData.put("id", condTitleInfo.getConditionTitleId());
			    	jsonConditionOneData.put("name", condTitleInfo.getConditionTitleName());
			    	condTitleArray.add(jsonConditionOneData);
			    }
			    jsonOneData.put("t_stylist_searchCondition_titles",condTitleArray);
				// レスポンスJSON Object(value)
				JSONArray condInfoArray = new JSONArray();
			    for(ConditionInfo condInfo : ConditionInfoList){
			    	JSONObject jsonConditionOneData = new JSONObject();
			    	jsonConditionOneData.put("id", condInfo.getConditionId());
			    	jsonConditionOneData.put("titleID", condInfo.getConditionTitleId());
			    	jsonConditionOneData.put("name", condInfo.getConditionName());
			    	condInfoArray.add(jsonConditionOneData);
			    }
			    jsonOneData.put("t_stylist_searchCondition_values", condInfoArray);
			    //検索条件ここまで
			     */
			    stylistArray.add(jsonOneData);
		    }
		    jsonObject.put("stylist",stylistArray);	
		    //debug
	    	System.out.println(jsonObject.toString(4));
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
