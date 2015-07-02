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
public class GetSalonInfoService {
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
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();

		SalonDao dao = new SalonDao();
		if(conn!=null){
			salonInfoList = dao.getSalonInfo(dbConnection, salonId);
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}

		//レスポンスに設定するJSON Object
		JSONObject jsonObject = new JSONObject();
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
		HairSalonInfo hairSalonInfo = salonInfoList.get(0);
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
		jsonOneData.put("t_hairSalonMaster_salonImagePath", hairSalonInfo.getHairSalonImagePathOneLine());		    		
    	jsonOneData.put("t_hairSalonMaster_japaneseAvailable", hairSalonInfo.getSalonCarParkAvailable());
		PrintWriter out = response.getWriter();
		out.print(jsonOneData);
		out.flush();
		
	}catch(Exception e){
		responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		e.printStackTrace();
	}
    
	response.setStatus(responseStatus);
	return response;

	}
}
