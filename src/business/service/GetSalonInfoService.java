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
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common._model.THairSalonMasterInfo;
import common.constant.Constant;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.HairSalonInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
    getSalonInfo
        概要：サロン情報管理ページ表示用データを取得する
        入力：{ t_hairSalonMaster_salonId }
        出力：

    {
      t_hairSalonMaster_salon_name,
      t_country_name,
      t_area_name,
      t_hairSalonMaster_detailText,
      t_hairSalonMaster_openTime,
      t_hairSalonMaster_closeTime,
      t_hairSalonMaster_closeDay,
      t_hairSalonMaster_creditAvailable,
      t_hairSalonMaster_carParkAvailable,
      t_hairSalonMaster_salonImagePath,
      t_hairSalonMaster_japaneseAvailable,
      
    }
 *
 */
public class GetSalonInfoService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){

	/**
	 * Declaration value
	 */
	HttpSession session = request.getSession();
    int responseStatus = HttpServletResponse.SC_OK;

	//salonId kokokara
    int salonId = -1;
    //get a salonId by session
	String salonId_str = "";
	if (session != null){
		salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
	}
	if(salonId_str != null){			
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}
	}   
	if(salonId < 0){
        //get a salonId by parameter
        salonId = request.getParameter(Constant.PARAMETER_SALONID)!= null 
		?Integer.parseInt(request.getParameter(Constant.PARAMETER_SALONID)) : -1;
	}
	//salonId kokomade
	
	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
		//List<THairSalonMasterInfo> salonInfoList = new ArrayList<THairSalonMasterInfo>();
		THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
		
		SalonDao dao = new SalonDao();
		if(conn!=null){
			salonInfo = dao.get(dbConnection, salonId);
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

		//レスポンスに設定するJSON Object
		//JSONObject jsonObject = new JSONObject();
		/**
		 * 
	     {
		      t_hairSalonMaster_salon_name,
		      t_country_name,
		      t_area_name,
		      t_hairSalonMaster_detailText,
		      t_hairSalonMaster_openTime,
		      t_hairSalonMaster_closeTime,
		      t_hairSalonMaster_closeDay,
		      t_hairSalonMaster_creditAvailable,
		      t_hairSalonMaster_carParkAvailable,
		      t_hairSalonMaster_salonImagePath,
		      t_hairSalonMaster_japaneseAvailable,
		    }
		 */
		
		// 返却用サロンデータ（jsonデータの作成）
		JSONObject jsonOneData = new JSONObject();
		jsonOneData.put("t_hairSalonMaster_salon_name", salonInfo.getTHairSalonMasterName());
		//TODO: country名,area名をareaDaoからひっぱる 
		/*
		//jsonOneData.put("t_country_name", salonInfo.);
		if(salonInfo.getTHairSalonMasterAreaId().size()>0){
			jsonOneData.put("t_area_name", salonInfo.getAreaNameList().get(0));
		}else{
			jsonOneData.put("t_area_name", "");
		}
		*/
		jsonOneData.put("t_hairSalonMaster_areaId", salonInfo.getTHairSalonMasterAreaId());
		jsonOneData.put("t_hairSalonMaster_detailText", salonInfo.getTHairSalonMasterDetailText());
		jsonOneData.put("t_hairSalonMaster_openTime", salonInfo.getTHairSalonMasterOpenTime());
		jsonOneData.put("t_hairSalonMaster_closeTime", salonInfo.getTHairSalonMasterCloseTime());
		jsonOneData.put("t_hairSalonMaster_closeDay", salonInfo.getTHairSalonMasterCloseDay());
		jsonOneData.put("t_hairSalonMaster_creditAvailable", salonInfo.getTHairSalonMasterCreditAvailable());
		jsonOneData.put("t_hairSalonMaster_carParkAvailable", salonInfo.getTHairSalonMasterCarParkAvailable());
		jsonOneData.put("t_hairSalonMaster_salonImagePath", salonInfo.getTHairSalonMasterSalonImagePath());		
		//TODO: japaneseAvailable
    	//jsonOneData.put("t_hairSalonMaster_japaneseAvailable", salonInfo.getTHairSalonMasterJapaneseAvailable());
		int japaneseAvailable = 0;
		List<String> langCoList = Arrays.asList(salonInfo.getTHairSalonMasterAvailableCountryId().split(","));
		if(langCoList.contains(Constant.JAPANESE_COUNTRY_ID)) japaneseAvailable = 1;
    	jsonOneData.put("t_hairSalonMaster_japaneseAvailable", japaneseAvailable);
    	jsonOneData.put("t_hairSalonMaster_searchConditionId", salonInfo.getTHairSalonMasterSearchConditionId());
    	jsonOneData.put("t_hairSalonMaster_mail", salonInfo.getTHairSalonMasterMail());
    	
    	/*
		List<ConditionInfo> ConditionInfoList  = new ArrayList<ConditionInfo>();
		List<ConditionTitleInfo> ConditionTitleInfoList  = new ArrayList<ConditionTitleInfo>();
		if(conn!=null){
			ConditionDao conditionDao = new ConditionDao();
			ConditionTitleInfoList = conditionDao.getConditionTitleInfo(dbConnection, Constant.TYPE_FOR_SALON_CONDITION);
			ConditionInfoList = conditionDao.getConditionInfo(dbConnection, ConditionTitleInfoList);				
			dbConnection.close();
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}
    	//レスポンスJSON Object(title)
		JSONArray condTitleArray = new JSONArray();
	    for(ConditionTitleInfo condTitleInfo : ConditionTitleInfoList){
	    	JSONObject jsonConditionOneData = new JSONObject();
	    	jsonConditionOneData.put("id", condTitleInfo.getConditionTitleId());
	    	jsonConditionOneData.put("name", condTitleInfo.getConditionTitleName());
	    	condTitleArray.add(jsonConditionOneData);
	    }
	    jsonOneData.put("t_hairSalonMaster_searchCondition_titles",condTitleArray);
		// レスポンスJSON Object(value)
		JSONArray condInfoArray = new JSONArray();
	    for(ConditionInfo condInfo : ConditionInfoList){
	    	JSONObject jsonConditionOneData = new JSONObject();
	    	jsonConditionOneData.put("id", condInfo.getConditionId());
	    	jsonConditionOneData.put("titleID", condInfo.getConditionTitleId());
	    	jsonConditionOneData.put("name", condInfo.getConditionName());
	    	condInfoArray.add(jsonConditionOneData);
	    }
	    jsonOneData.put("t_hairSalonMaster_searchCondition_values", condInfoArray);
	    //検索条件ここまで
	    */
	    
    	PrintWriter out = response.getWriter();
		out.print(jsonOneData);
		//debug
		System.out.println(jsonOneData.toString(4));
		out.flush();
		
	}catch(Exception e){
		responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		e.printStackTrace();
	}
    
	response.setStatus(responseStatus);
	return response;

	}
}
