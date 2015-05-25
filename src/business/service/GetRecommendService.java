package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.RecommendDao;

import com.mysql.jdbc.Connection;

import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.util.DBConnection;

public class GetRecommendService {

	
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        Date lastUpdateSalon = new Date(0);
        Date lastUpdateHair = new Date(0);
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<HairSalonInfo> salonInfoList  = new ArrayList<HairSalonInfo>();
			List<HairStyleInfo> hairStyleInfoList = new ArrayList<HairStyleInfo>();
			
			if(conn!=null){
				RecommendDao dao = new RecommendDao();
				salonInfoList  = dao.getRecommendSalonInfo(dbConnection);
				hairStyleInfoList =  dao.getRecommendHairStyleInfo(dbConnection);
				lastUpdateSalon = dao.getRecommendSalonLastUpdate(dbConnection);
				lastUpdateHair = dao.getRecommendHairLastUpdate(dbConnection);
				dbConnection.close();
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray salonArray = new JSONArray();
		    for(HairSalonInfo hairSalonInfo : salonInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairSalonInfo.getHairSalonId());
		    	jsonOneData.put("name", hairSalonInfo.getHairSalonName());
		    	jsonOneData.put("image", hairSalonInfo.getHairSalonImagePath());
		    	jsonOneData.put("message", hairSalonInfo.getMessage());
		    	//オススメサロンを返却する際は地域レベル１の地名を返却すればいい
		    	jsonOneData.put("place", hairSalonInfo.getAreaNameList().get(0));
		    	salonArray.add(jsonOneData);
		    }
		    jsonObject.put("salon_lists",salonArray);
		    
		    //返却用ヘアースタイルデータ(Jsonデータの作成)
		    JSONArray hairStyleArray = new JSONArray();
		    for(HairStyleInfo hairStyleInfo : hairStyleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairStyleInfo.getHairStyleId());
		    	jsonOneData.put("name", hairStyleInfo.getHairStyleName());
		    	jsonOneData.put("image", hairStyleInfo.getHairStyleImagePath());
		    	jsonOneData.put("shopId", hairStyleInfo.getSalonId());
		    	jsonOneData.put("stylistId", hairStyleInfo.getStylistId());
		    	hairStyleArray.add(jsonOneData);
		    }
		    jsonObject.put("style_lists",hairStyleArray);
			
		    //返却用インフォーメーション用データ(Jsonデータの作成)
		    JSONArray informationArray = new JSONArray();
		    JSONObject jsonOneData = new JSONObject();
		    jsonOneData.put("published_at_salon",lastUpdateSalon);
		    jsonOneData.put("published_at_hair",lastUpdateHair);
		    jsonObject.put("information",informationArray);
		    
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
