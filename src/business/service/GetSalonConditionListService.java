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
import business.dao.ConditionDao;
import business.dao.SalonDao;
import business.dao.StylistDao;
import common.constant.Constant;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetSalonConditionListService {
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
		
		//getParameter
		String t_masterSearchConditionType = request.getParameter("t_masterSearchConditionType") != null ?
				request.getParameter("t_masterSearchConditionType").toString() : null;				
				
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<ConditionInfo> ConditionInfoList  = new ArrayList<ConditionInfo>();
			List<ConditionTitleInfo> ConditionTitleInfoList  = new ArrayList<ConditionTitleInfo>();
			
			ConditionInfo conditionInfo = new ConditionInfo();
			ConditionTitleInfo conditionTitleInfo = new ConditionTitleInfo();
			if(conn!=null){
				ConditionDao conditionDao = new ConditionDao();
				int conditionType = -1;
				if(t_masterSearchConditionType != null){
					conditionType = Integer.parseInt(t_masterSearchConditionType);
					ConditionTitleInfoList = conditionDao.getConditionTitleInfo(dbConnection, conditionType);
					ConditionInfoList = conditionDao.getConditionInfo(dbConnection, ConditionTitleInfoList);				
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object(title)
			JSONObject jsonObject = new JSONObject();
			JSONArray condTitleArray = new JSONArray();
		    for(ConditionTitleInfo condTitleInfo : ConditionTitleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", condTitleInfo.getConditionTitleId());
		    	jsonOneData.put("name", condTitleInfo.getConditionTitleName());
		    	condTitleArray.add(jsonOneData);
		    }
		    // 返却用サロンデータ（jsonデータの作成）
		    jsonObject.put("titles",condTitleArray);
				
			// レスポンスに設定するJSON Object(value)
			JSONArray condInfoArray = new JSONArray();
		    for(ConditionInfo condInfo : ConditionInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", condInfo.getConditionId());
		    	jsonOneData.put("titleID", condInfo.getConditionTitleId());
		    	jsonOneData.put("name", condInfo.getConditionName());
		    	condInfoArray.add(jsonOneData);
		    }
		    jsonObject.put("values", condInfoArray);
		    		    
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
