package business.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.RecommendDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetRecommendService {
	
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		
        Date lastUpdateSalon = new Date(0);
        Date lastUpdateHair = new Date(0);
        Date lastUpdateStylist = new Date(0);
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<HairSalonInfo> salonInfoList  = new ArrayList<HairSalonInfo>();
			List<HairStyleInfo> hairStyleInfoList = new ArrayList<HairStyleInfo>();
			List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
			
			if(conn!=null){
				RecommendDao dao = new RecommendDao();
				salonInfoList  = dao.getRecommendSalonInfo(dbConnection);
				hairStyleInfoList =  dao.getRecommendHairStyleInfo(dbConnection);
				stylistInfoList = dao.getRecommendStylistInfo(dbConnection);
				
				//お気に入りされているかどうかを設定する
				dao.setIsFavoriteSalon(userId, salonInfoList, dbConnection);
				dao.setIsFavoriteHair(userId, hairStyleInfoList, dbConnection);
				dao.setIsFavoriteStylist(userId, stylistInfoList, dbConnection);
				
				lastUpdateSalon = dao.getRecommendSalonLastUpdate(dbConnection);
				lastUpdateHair = dao.getRecommendHairLastUpdate(dbConnection);
				lastUpdateStylist = dao.getRecommendStylistLastUpdate(dbConnection);
				
				dbConnection.close();
			}
			else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray salonArray = new JSONArray();
		    for(HairSalonInfo hairSalonInfo : salonInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairSalonInfo.getHairSalonId());
		    	jsonOneData.put("name", hairSalonInfo.getHairSalonName());
		    	//jsonOneData.put("image", hairSalonInfo.getHairSalonImagePath());
		    	int i = 0;
		    	for(String str : hairSalonInfo.getHairSalonImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("message", hairSalonInfo.getMessage());
		    	//オススメサロンを返却する際は地域レベル１の地名を返却すればいい
		    	jsonOneData.put("place", hairSalonInfo.getAreaNameList().get(0));
		    	jsonOneData.put("isgood", hairSalonInfo.getIsGood());
		    	jsonOneData.put("good_count", hairSalonInfo.getFavoriteNumber());
		    	salonArray.add(jsonOneData);
		    }
		    jsonObject.put("salon_lists",salonArray);
		    
		    //返却用ヘアースタイルデータ(Jsonデータの作成)
		    JSONArray hairStyleArray = new JSONArray();
		    for(HairStyleInfo hairStyleInfo : hairStyleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairStyleInfo.getHairStyleId());
		    	jsonOneData.put("name", hairStyleInfo.getHairStyleName());
		    	//jsonOneData.put("image", hairStyleInfo.getHairStyleImagePath());
		    	int i = 0;
		    	for(String str : hairStyleInfo.getHairStyleImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("shopId", hairStyleInfo.getSalonId());
		    	jsonOneData.put("stylistId", hairStyleInfo.getStylistId());
		    	jsonOneData.put("isgood", hairStyleInfo.getIsGood());
		    	jsonOneData.put("good_count", hairStyleInfo.getFavoriteNumber());
		    	hairStyleArray.add(jsonOneData);
		    }
		    jsonObject.put("hair_lists",hairStyleArray);
			
		    //返却用スタイリストデータの(JSONデータの作成)
		    JSONArray stylistArray = new JSONArray();
		    for(StylistInfo stylistInfo : stylistInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", stylistInfo.getStylistId());
		    	jsonOneData.put("name", stylistInfo.getStylistName());
		    	//jsonOneData.put("image", stylistInfo.getStylistImagePath());
		    	int i = 0;
		    	for(String str : stylistInfo.getStylistImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("shopId", stylistInfo.getSalonId());
		    	jsonOneData.put("isgood", stylistInfo.getIsGood());
		    	jsonOneData.put("good_count", stylistInfo.getFavoriteNumber());
		    	stylistArray.add(jsonOneData);
		    }
		    jsonObject.put("stylist_lists",stylistArray);		    
		    
		    //返却用インフォーメーション用データ(Jsonデータの作成)
		    JSONArray informationArray = new JSONArray();
		    JSONObject jsonOneData = new JSONObject();
		    SimpleDateFormat sdf1 = new SimpleDateFormat("YYYYMMDDHHmm");
		    jsonOneData.put("published_at_salon",sdf1.format(lastUpdateSalon));
		    jsonOneData.put("published_at_hair",sdf1.format(lastUpdateHair));
		    jsonOneData.put("published_at_stylist",sdf1.format(lastUpdateStylist));
		    informationArray.add(jsonOneData);
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
