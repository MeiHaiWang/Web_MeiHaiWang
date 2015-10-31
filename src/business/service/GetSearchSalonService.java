package business.service;

import java.io.IOException;
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

	List<String> areaIdList = new ArrayList<String>();
	String areaIdListStr  = request.getParameter("area") != null ?
			request.getParameter("area") : null;
	if(areaIdListStr!=null){
		areaIdList.addAll(Arrays.asList(areaIdListStr));
	}
	if(areaIdList.isEmpty()||areaIdList.get(0)==""){
		areaIdList.add("");
	}
	String[] searchConditionListStr = request.getParameterValues("condition") != null ?
			request.getParameterValues("condition") : null;
			/*
	List<String> searchConditionIdList = request.getParameterValues("condition") != null ?
			Arrays.asList(request.getParameterValues("condition")) : new ArrayList<String>();	
			*/
	List<String> searchConditionIdList = new ArrayList<String>();
	if(searchConditionListStr!=null){
		searchConditionIdList.addAll(Arrays.asList(searchConditionListStr));
	}
	if(searchConditionIdList.isEmpty()||searchConditionIdList.get(0)==""){
		searchConditionIdList.add("");
	}

	//debug
	//System.out.println("area:"+areaId+",cond:"+searchConditionIdList.get(0)+","+searchConditionIdList.size());

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
			List<String> aList = new ArrayList<String>();
			aList.addAll(areaIdList);
			for(String aId : aList){
				if(aId!=""){
					List<String> childAreaIdList = areaDao.getAreaChildren(dbConnection, aId);
					if(!childAreaIdList.isEmpty()){
						areaIdList.addAll(childAreaIdList);
					}
					/*
					for(String child: childAreaIdList){
						areaIdList.add(child);
					}
					*/
				}
			}
			
			//List<HairSalonInfo> salonInfoListByArea = dao.getSalonListByArea(dbConnection, areaIdList);
			//salonInfoList = dao.getSalonListBySearchCondition(dbConnection, searchConditionIdList, salonInfoListByArea, pageNumber, jsonObject);
			salonInfoList = dao.getSalonInfoList(dbConnection, searchConditionIdList, areaIdList, pageNumber, jsonObject);
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
	
	private HttpServletResponse retError(HttpServletResponse response, String reason){
		//response
		int responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		response.setStatus(responseStatus);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "false");
		jsonObject.put("reason", reason);
		try {
			PrintWriter out;
			out = response.getWriter();
			out.print(jsonObject);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
}
