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
import common.util.DBConnection;

public class GetFaceTypeForHairStyleSearchService {
	//@SuppressWarnings({ "unchecked", "unused" })
	
	/**
	public static final String TITLE_FOR_SALON_CONDITION = "サロン条件検索";
	public static final String TITLE_FOR_STYLIST_CODITION = "スタイリスト検索条件";
	public static final String TITLE_FOR_STYLIST_LIKE = "スタイリスト検索好み";
	public static final String TITLE_FOR_HAIRSTYLE_MENU = "ヘアスタイル検索メニュー";
	public static final String TITLE_FOR_HAIRSTYLE_FACE = "ヘアスタイル検索顔型";

	 */
	
	/*
	public HttpServletResponse excuteService(HttpServletRequest request,
	 
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
				
		try{
			DBConnection dbConnection = new DBConnection();
			JSONObject jsonObject = new JSONObject();
			List<ConditionInfo> ConditionInfoList  = new ArrayList<ConditionInfo>();
			List<ConditionTitleInfo> ConditionTitleInfoList  = new ArrayList<ConditionTitleInfo>();
			java.sql.Connection conn = dbConnection.connectDB();
			
			ConditionInfo conditionInfo = new ConditionInfo();
			ConditionTitleInfo conditionTitleInfo = new ConditionTitleInfo();
			if(conn!=null){
				ConditionDao conditionDao = new ConditionDao();
				ConditionTitleInfoList = conditionDao.getConditionTitleInfo(dbConnection, Constant.TYPE_FOR_HAIRSTYLE_FACE);
				ConditionInfoList = conditionDao.getConditionInfo(dbConnection, ConditionTitleInfoList);				
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object(title)
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
	*/

}
