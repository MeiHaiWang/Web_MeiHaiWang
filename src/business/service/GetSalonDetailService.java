package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.NewsDao;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.BeautyNewsInfo;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetSalonDetailService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
		/*int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;*/
		 
        //TODO テスト用
        int salonId =1;
        int userId =1;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				RecommendDao recomendDao = new RecommendDao();
				salonInfoList = salonDao.getSalonDetailInfo(salonId, dbConnection);
				//ユーザがお気に入りしているかどうかを設定する
				recomendDao.setIsFavoriteSalon(userId, salonInfoList, dbConnection);
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
		    	jsonOneData.put("star_count", String.valueOf(hairSalonInfo.getEvaluationPointMid()));
		    	jsonOneData.put("message", hairSalonInfo.getMessage());
		    	jsonOneData.put("tel", hairSalonInfo.getTel());
		    	jsonOneData.put("place",hairSalonInfo.getAreaNameList().toArray(new String[0]));
		    	jsonOneData.put("business_hours", hairSalonInfo.getBusinessHour());
		    	jsonOneData.put("regular_holiday", hairSalonInfo.getRegularHoliday());
		    	jsonOneData.put("multilingual", hairSalonInfo.getMultiLingual());
		    	jsonOneData.put("word_of_mouth_count", String.valueOf(hairSalonInfo.getWordOfMonth()));
		    	jsonOneData.put("isgood", hairSalonInfo.getIsGood());
		    	jsonOneData.put("good_count", hairSalonInfo.getFavoriteNumber());
		    	jsonOneData.put("isNetReservation", String.valueOf(hairSalonInfo.getIsNetReservation()));
		    	salonArray.add(jsonOneData);
		    }
		    jsonObject.put("shop",salonArray);
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
