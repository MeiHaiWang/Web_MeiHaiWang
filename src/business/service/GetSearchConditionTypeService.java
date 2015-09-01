package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ConditionDao;
import common.constant.Constant;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.ConditionTypeInfo;
import common.util.DBConnection;

public class GetSearchConditionTypeService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<ConditionTypeInfo> ConditionTypeList  = new ArrayList<ConditionTypeInfo>();
			
			ConditionTypeInfo conditionTypeInfo = new ConditionTypeInfo();
			if(conn!=null){
				ConditionDao conditionDao = new ConditionDao();
				ConditionTypeList = conditionDao.getConditionTypeInfo(dbConnection);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object(title)
			JSONObject jsonObject = new JSONObject();
			JSONArray condTitleArray = new JSONArray();
		    for(ConditionTypeInfo condTypeInfo : ConditionTypeList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", condTypeInfo.getConditionTypeId());
		    	jsonOneData.put("name", condTypeInfo.getConditionTypeName());
		    	condTitleArray.add(jsonOneData);
		    }
		    // 返却用サロンデータ（jsonデータの作成）
		    //jsonObject.put("titles",condTitleArray);
				
		    PrintWriter out = response.getWriter();
		    out.print(condTitleArray);
		    out.flush();
	    }catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}

}
