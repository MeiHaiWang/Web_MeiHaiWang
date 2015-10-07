package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.NewsDao;
import common.constant.Constant;
import common.model.BeautyNewsInfo;
import common.util.DBConnection;

public class GetNewsService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
        
        int page = request.getParameter("page") != null ?
        		Integer.parseInt(request.getParameter("page")) : 0;
        
        int offset= Constant.ONE_PAGE_NUM * page;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<BeautyNewsInfo> beautyNewsListAll  = new ArrayList<BeautyNewsInfo>();
			List<BeautyNewsInfo> beautyNewsList  = new ArrayList<BeautyNewsInfo>();
			if(conn!=null){
				NewsDao dao = new NewsDao();
				beautyNewsListAll = dao.getBeautyNewsInfo(dbConnection);
				beautyNewsList = dao.getBeautyNewsInfoByOffSetAndLimit(dbConnection, offset, Constant.ONE_PAGE_NUM);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			int next = (offset + Constant.ONE_PAGE_NUM) < (beautyNewsListAll.size()) ?
					1 : 0;
			
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray newsArray = new JSONArray();
		    for(BeautyNewsInfo returnNews : beautyNewsList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("image", returnNews.getbeautyNewsImagePath());
		    	jsonOneData.put("title", returnNews.getbeautyNewsName());
		    	jsonOneData.put("URL", returnNews.getbeautyNewsURL());
		    	jsonOneData.put("day", returnNews.getUpdateDate());
		    	newsArray.add(jsonOneData);
		    }
		    jsonObject.put("news",newsArray);
		    jsonObject.put("next",next);
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

