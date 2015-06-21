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
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetSalonInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){

	HttpSession session = request.getSession();
    int responseStatus = HttpServletResponse.SC_OK;

	/*
	int userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
	 */
	/*
	int salonId = request.getParameter("salonId") != null ?
			Integer.valueOf(request.getParameter("salonId").toString()) : -1;
			*/
	//TODO: test
	int salonId = 1;
	
	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		//レスポンスに設定するJSON Object
		JSONObject jsonObject = new JSONObject();
		SalonDao dao = new SalonDao();
		if(conn!=null){
			salonInfoList = dao.getSalonInfo(dbConnection, salonId);
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

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
		JSONArray salonArray = new JSONArray();
		for(HairSalonInfo hairSalonInfo : salonInfoList){
			JSONObject jsonOneData = new JSONObject();
			jsonOneData.put("t_hairSalonMaster_salon_name", hairSalonInfo.getHairSalonName());
			jsonOneData.put("t_country_name", hairSalonInfo.getSalonCountryName());
	    	jsonOneData.put("t_area_name", hairSalonInfo.getAreaNameList().get(0));
			jsonOneData.put("t_hairSalonMaster_detailText", hairSalonInfo.getSalonDetailText());
			jsonOneData.put("t_hairSalonMaster_openTime", hairSalonInfo.getSalonOpenTime());
			jsonOneData.put("t_hairSalonMaster_closeTime", hairSalonInfo.getSalonCloseTime());
			jsonOneData.put("t_hairSalonMaster_closeDay", hairSalonInfo.getSalonCloseDay());
			jsonOneData.put("t_hairSalonMaster_creditAvailable", hairSalonInfo.getSalonCreditAvailable());
			jsonOneData.put("t_hairSalonMaster_carParkAvailable", hairSalonInfo.getSalonCarParkAvailable());

			int i = 0;
	    	for(String str : hairSalonInfo.getHairSalonImagePath()){
	    		i++;
	    		jsonOneData.put("t_hairSalonMaster_salonImagePath"+i, str);		    		
	    	}

	    	jsonOneData.put("t_hairSalonMaster_japaneseAvailable", hairSalonInfo.getSalonCarParkAvailable());

			salonArray.add(jsonOneData);
		}
		jsonObject.put("",salonArray);
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
