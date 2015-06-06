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
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetSearchSalonService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){
		
	int userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
	/*List<String> areaIdList = request.getParameter("area") != null ?
			Arrays.asList(request.getParameter("area").split(",")) : new ArrayList<String>();	
			
	List<String> searchConditionIdList = request.getParameter("condition") != null ?
			Arrays.asList(request.getParameter("condition").split(",")) : new ArrayList<String>();;	
		
	int pageNumber = request.getParameter("page") != null ?
			Integer.valueOf(request.getParameter("page").toString()) : 1;
	
	int onePageDisplayNum = request.getParameter("onePageNum") != null ?
			Integer.valueOf(request.getParameter("onePageNum").toString()) : Constant.ONE_PAGE_NUM;
	*/
	userId=1;
	
	List<String> areaIdList = new ArrayList<String>();
	areaIdList.add("1");
	areaIdList.add("2");
	int pageNumber =0;
	List<String> searchConditionIdList = new ArrayList<String>();
	searchConditionIdList.add("1");
	searchConditionIdList.add("2");
	
	int responseStatus = HttpServletResponse.SC_OK;
	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		//レスポンスに設定するJSON Object
		JSONObject jsonObject = new JSONObject();
		if(conn!=null){
			SalonDao dao = new SalonDao();
			RecommendDao recomendDao = new RecommendDao();
			List<String> salonIdList = dao.getSalonIdListByArea(dbConnection, areaIdList);
			salonInfoList = dao.getSalonListBySearchCondition(dbConnection, searchConditionIdList, salonIdList,pageNumber,jsonObject);
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
			jsonOneData.put("image", hairSalonInfo.getHairSalonImagePath());
			jsonOneData.put("star_count", String.valueOf(hairSalonInfo.getEvaluationPointMid()));
			jsonOneData.put("message", hairSalonInfo.getMessage());
			jsonOneData.put("address", hairSalonInfo.getAddress());
			jsonOneData.put("business_hours", hairSalonInfo.getBusinessHour());
			jsonOneData.put("regular_holiday", hairSalonInfo.getRegularHoliday());
			jsonOneData.put("word_of_mouth_count", String.valueOf(hairSalonInfo.getWordOfMonth()));
			jsonOneData.put("isgood", hairSalonInfo.getIsGood());
			jsonOneData.put("good_count", hairSalonInfo.getFavoriteNumber());
			salonArray.add(jsonOneData);
		}
		jsonObject.put("shop_list",salonArray);
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
