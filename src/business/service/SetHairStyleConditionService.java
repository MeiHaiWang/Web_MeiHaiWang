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
import business.dao.MenuDao;
import common.constant.Constant;
import common.model.MenuInfo;
import common.util.CommonUtil;
import common.util.DBConnection;

public class SetHairStyleConditionService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        HttpSession session = request.getSession(false);

	    String hairStyleIdStr = request.getParameter("id")!= null
	    		?request.getParameter("id") : null;
	    int hairStyleId = -1;
	    if(hairStyleIdStr!=null && CommonUtil.isNum(hairStyleIdStr)){
	    	hairStyleId = Integer.parseInt(hairStyleIdStr);
	    }

        /*
		int hairStyleId = request.getParameter("id") != null ?
				Integer.parseInt(request.getParameter("id")) : null;
				*/
		List<String> searchConditionIdList = request.getParameterValues("condition") != null ?
				Arrays.asList(request.getParameterValues("condition")) : new ArrayList<String>();	
	    /*
		String searchConditionIdList = request.getParameter("condition") != null ?
				request.getParameter("condition").toString() : null;
				*/
				
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			int status;
			
			if(conn!=null){
				ConditionDao condDao = new ConditionDao();
				status = condDao.setHairStyleCondition(
						dbConnection,
						hairStyleId,
						searchConditionIdList
						);
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