package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ConditionDao;
import business.dao.NewsDao;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.BeautyNewsInfo;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetSalonDetailService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int salonId = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        		
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
				//検索条件の名前一覧を取得
				ConditionDao conditionDao = new ConditionDao();
				List<ConditionInfo> ConditionInfoList  = new ArrayList<ConditionInfo>();
				List<ConditionTitleInfo> ConditionTitleInfoList  = new ArrayList<ConditionTitleInfo>();
				ConditionTitleInfoList = conditionDao.getConditionTitleInfo(dbConnection, Constant.TYPE_FOR_SALON_CONDITION);
				ConditionInfoList = conditionDao.getConditionInfo(dbConnection, ConditionTitleInfoList);
				for(int i=0;i<salonInfoList.size();i++){
					HairSalonInfo oneSalon = salonInfoList.get(i);
					List<String> salonCondIdList = Arrays.asList(oneSalon.getSalonSearchConditionId().split(","));
					String str = "";
					for(String oneCondId : salonCondIdList){
						for(ConditionInfo cInfo : ConditionInfoList){
							if(cInfo.getConditionId()==Integer.parseInt(oneCondId)){
								str+=cInfo.getConditionName()+",";
							}
						}
					}
					if(str.length()>0){
						str=str.substring(0,str.length()-1);
						oneSalon.setSalonConditionName(str);
						salonInfoList.set(i, oneSalon);
					}
				}
				dbConnection.close();
			}else{
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
		    	int i = 0;
		    	for(String str : hairSalonInfo.getHairSalonImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	//jsonOneData.put("image", hairSalonInfo.getHairSalonImagePath());
		    	jsonOneData.put("star_count", hairSalonInfo.getEvaluationPointMid());
		    	jsonOneData.put("message", hairSalonInfo.getMessage());
		    	jsonOneData.put("tel", hairSalonInfo.getTel());
		    	jsonOneData.put("adress",hairSalonInfo.getAreaNameList().toArray(new String[0]));
		    	jsonOneData.put("business_hours", hairSalonInfo.getBusinessHour());
		    	jsonOneData.put("regular_holiday", hairSalonInfo.getRegularHoliday());
		    	//jsonOneData.put("multilingual", hairSalonInfo.getMultiLingual());
		    	jsonOneData.put("condition_name", hairSalonInfo.getSalonCondName());
		    	jsonOneData.put("word_of_mouth_count", hairSalonInfo.getWordOfMonth());
		    	jsonOneData.put("isgood", hairSalonInfo.getIsGood());
		    	jsonOneData.put("good_count", hairSalonInfo.getFavoriteNumber());
		    	jsonOneData.put("isNetReservation", hairSalonInfo.getIsNetReservation());
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
