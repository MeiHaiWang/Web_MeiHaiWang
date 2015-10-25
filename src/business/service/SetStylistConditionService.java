package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.ConditionDao;
import common.util.DBConnection;

public class SetStylistConditionService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        HttpSession session = request.getSession(false);

		String stylistIdstr = request.getParameter("id") != null ?
				request.getParameter("id") : null;
		int stylistId = -1;
		if(stylistIdstr!=null){
			stylistId = Integer.parseInt(stylistIdstr);
		}
		/*
		List<String> searchConditionIdList = request.getParameter("condition") != null ?
				Arrays.asList(request.getParameter("condition").split(",")) : new ArrayList<String>();	
		String searchConditionIdList = request.getParameter("condition") != null ?
				request.getParameter("condition").toString() : null;
		*/
		List<String> conditionIdList = request.getParameterValues("condition") != null ?
				Arrays.asList(request.getParameterValues("condition")) : new ArrayList<String>();	
		
		String searchConditionIdList = "";
		if(conditionIdList.size()>0){
			for(String cond: conditionIdList){
				searchConditionIdList += cond+",";
			}
			searchConditionIdList = searchConditionIdList.substring(0,searchConditionIdList.length()-1);
		}
		
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			int status = -1;
			
			if(conn!=null){
				ConditionDao condDao = new ConditionDao();
				if(!searchConditionIdList.equals("") && stylistId>0){
					status = condDao.setStylistCondition(
							dbConnection,
							stylistId,
							searchConditionIdList
							);
				}
				if(status > 0) result = true;
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}	
			
			/*
				{status: 0}
			 */		
			String resultStr = String.valueOf(result);
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
