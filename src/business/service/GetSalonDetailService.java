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
import business.dao.UserDao;
import common._model.THairSalonMasterInfo;
import common.constant.Constant;
import common.model.BeautyNewsInfo;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.HairSalonInfo;
import common.util.CommonUtil;
import common.util.DBConnection;

public class GetSalonDetailService implements IServiceExcuter{
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        
	    String salonIdStr = request.getParameter("id")!= null
	    		?request.getParameter("id") : "-1";
	    int salonId = -1;
	    if(salonIdStr!=null && CommonUtil.isNum(salonIdStr)){
	    	salonId = Integer.parseInt(salonIdStr);
	    }

        		
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
			THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				RecommendDao recomendDao = new RecommendDao();
				//salonInfoList = salonDao.getSalonDetailInfo(salonId, dbConnection);
				salonInfo = salonDao.get(dbConnection, salonId);
				//ユーザがお気に入りしているかどうかを設定する?
				UserDao userDao = new UserDao();
				List<String> favoriteSalonList = Arrays.asList(userDao.get(dbConnection, userId).getTUserFavoriteSalonId().split(","));
				//TODO: salonInfo.favoriteNumberをどうするか考えなきゃいけない?

				//検索条件の名前一覧を取得
				ConditionDao conditionDao = new ConditionDao();
				List<ConditionInfo> ConditionInfoList  = new ArrayList<ConditionInfo>();
				List<ConditionTitleInfo> ConditionTitleInfoList  = new ArrayList<ConditionTitleInfo>();
				ConditionTitleInfoList = conditionDao.getConditionTitleInfo(dbConnection, Constant.TYPE_FOR_SALON_CONDITION);
				ConditionInfoList = conditionDao.getConditionInfo(dbConnection, ConditionTitleInfoList);
				List<String> salonCondIdList = Arrays.asList(salonInfo.getTHairSalonMasterSearchConditionId().split(","));
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
					salonInfo.setSalonConditionName(str);
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
	    	JSONObject jsonOneData = new JSONObject();
	    	jsonOneData.put("id", salonInfo.getTHairSalonMasterSalonId());
	    	jsonOneData.put("name", salonInfo.getTHairSalonMasterName());
	    	int i = 0;
	    	for(String str : Arrays.asList(salonInfo.getTHairSalonMasterSalonImagePath().split(","))){
	    		i++;
	    		jsonOneData.put("image"+i, str);		    		
	    	}
	    	//TODO 評価レビューからポイントを取得する必要あり
	    	//jsonOneData.put("star_count", salonInfo.getTEvaluationPointMid());
	    	jsonOneData.put("message", salonInfo.getTHairSalonMasterMessage());
	    	jsonOneData.put("tel", salonInfo.getTHairSalonMasterPhoneNumber());
	    	jsonOneData.put("adress",salonInfo.getTHairSalonMasterAddress());
	    	jsonOneData.put("business_hours", salonInfo.getTHairSalonMasterOpenTime()+"-"+salonInfo.getTHairSalonMasterCloseTime());
	    	jsonOneData.put("regular_holiday", salonInfo.getTHairSalonMasterCloseDay());
	    	//jsonOneData.put("multilingual", salonInfo.getMultiLingual());
	    	jsonOneData.put("condition_name", salonInfo.getSalonCondName());
	    	int reviewNum = salonInfo.getTHairSalonMasterReviewId().equals("") ?
	    			0 : Arrays.asList(salonInfo.getTHairSalonMasterReviewId().split(",")).size();
	    	jsonOneData.put("word_of_mouth_count", reviewNum);
	    	//TODO: good と　favoriteのちがい
	    	jsonOneData.put("isgood", salonInfo.getTHairSalonMasterGoodNumber()>0 ? 1:0);
	    	jsonOneData.put("good_count", salonInfo.getTHairSalonMasterGoodNumber());
	    	jsonOneData.put("isNetReservation", salonInfo.getTHairSalonMasterIsNetReservation());
		    jsonObject.put("shop",jsonOneData);
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
