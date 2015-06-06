package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import business.dao.StylistDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetSearchStylistService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
	
	int userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
	List<String> areaIdList = request.getParameter("area") != null ?
			Arrays.asList(request.getParameter("area").split(",")) : new ArrayList<String>();	
	if(areaIdList.isEmpty()){
			areaIdList.add("-1");
	}	
	List<String> searchConditionIdList = request.getParameter("condition") != null ?
			Arrays.asList(request.getParameter("condition").split(",")) : new ArrayList<String>();	
	if(searchConditionIdList.isEmpty()){
			searchConditionIdList.add("-1");
	}
	
	int pageNumber = request.getParameter("page") != null ?
			Integer.valueOf(request.getParameter("page").toString()) : 1;
	
	int onePageDisplayNum = request.getParameter("onePageNum") != null ?
			Integer.valueOf(request.getParameter("onePageNum").toString()) : Constant.ONE_PAGE_NUM;
	
	int salonId = request.getParameter("shopId") != null ?
			Integer.valueOf(request.getParameter("shopId").toString()) : -1;

	int responseStatus = HttpServletResponse.SC_OK;
	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		//レスポンスに設定するJSON Object
		JSONObject jsonObject = new JSONObject();
		if(conn!=null){
			StylistDao dao = new StylistDao();
			RecommendDao recomendDao = new RecommendDao();
			stylistInfoList = dao.getStylistInfoList(dbConnection, salonId,areaIdList,searchConditionIdList,pageNumber,jsonObject);
			recomendDao.setIsFavoriteStylist(userId, stylistInfoList, dbConnection);
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}
		
		// 返却用サロンデータ（jsonデータの作成）
		JSONArray stylistArray = new JSONArray();
		for(StylistInfo stylistInfo : stylistInfoList){
			JSONObject jsonOneData = new JSONObject();
			jsonOneData.put("id", stylistInfo.getStylistId());
			jsonOneData.put("shopID", stylistInfo.getSalonId());
			jsonOneData.put("name", stylistInfo.getStylistName());
			jsonOneData.put("gender", stylistInfo.getStylistGender());
			jsonOneData.put("image", stylistInfo.getStylistImagePath());
			jsonOneData.put("message", stylistInfo.getStylistMessage());
			jsonOneData.put("years", stylistInfo.getStylistYearsNumber());
			jsonOneData.put("good_count", stylistInfo.getFavoriteNumber());
			jsonOneData.put("isgood", stylistInfo.getIsGood());
			stylistArray.add(jsonOneData);
		}
		jsonObject.put("stylelist_lists",stylistArray);
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
