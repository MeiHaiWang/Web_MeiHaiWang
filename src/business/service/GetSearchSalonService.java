package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

/*
 * TODO
 * salonCondを複数にした場合を未検討
 * 
 */
public class GetSearchSalonService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){
		
	int userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;

	/*
	List<String> areaIdList = request.getParameter("area") != null ?
			Arrays.asList(request.getParameter("area").split(",")) : new ArrayList<String>();	
	if(areaIdList.isEmpty()){
		areaIdList.add("-1");
		//TODO テスト用パラメータ
		//areaIdList.add("1");
	}
	*/
	List<String> areaIdList = new ArrayList<String>();
	String areaId =  request.getParameter("area") != null ?
			request.getParameter("area") : "";
	if(areaId == ""){
		areaId = "0";
	}else{
		areaIdList.add(areaId);
	}
	List<String> searchConditionIdList = request.getParameter("condition") != null ?
			Arrays.asList(request.getParameter("condition").split(",")) : new ArrayList<String>();	
	if(searchConditionIdList.isEmpty()){
		searchConditionIdList.add("0");
		//TODO テスト用パラメータ
		//searchConditionIdList.add("1");
	}
	int pageNumber = request.getParameter("page") != null ?
			Integer.valueOf(request.getParameter("page").toString()) : 0;
	
	int onePageDisplayNum = request.getParameter("onePageNum") != null ?
			Integer.valueOf(request.getParameter("onePageNum").toString()) : Constant.ONE_PAGE_NUM;
	
	int responseStatus = HttpServletResponse.SC_OK;
	
	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		//レスポンスに設定するJSON Object
		JSONObject jsonObject = new JSONObject();
		AreaDao areaDao = new AreaDao();
		SalonDao dao = new SalonDao();
		RecommendDao recomendDao = new RecommendDao();
		if(conn!=null){
			List<String> childAreaIdList = areaDao.getAreaChildren(dbConnection, areaId);
			for(String child: childAreaIdList){
				areaIdList.add(child);
			}
			List<String> salonIdList = dao.getSalonIdListByArea(dbConnection, areaIdList);
			if(salonIdList.size() > 0){
				salonInfoList = dao.getSalonListBySearchCondition(dbConnection, searchConditionIdList, salonIdList,pageNumber,jsonObject);
			}
			recomendDao.setIsFavoriteSalon(userId, salonInfoList, dbConnection);
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

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
			jsonOneData.put("star_count", hairSalonInfo.getEvaluationPointMid());
			jsonOneData.put("message", hairSalonInfo.getMessage());
			jsonOneData.put("address", hairSalonInfo.getAddress());
			jsonOneData.put("business_hours", hairSalonInfo.getBusinessHour());
			jsonOneData.put("regular_holiday", hairSalonInfo.getRegularHoliday());
			jsonOneData.put("word_of_mouth_count", hairSalonInfo.getWordOfMonth());
			jsonOneData.put("isgood", hairSalonInfo.getIsGood());
			jsonOneData.put("good_count", hairSalonInfo.getFavoriteNumber());
			salonArray.add(jsonOneData);
		}
		jsonObject.put("shop_list",salonArray);
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
