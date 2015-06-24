package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import business.dao.SalonDao;

import common.model.HairStyleInfo;
import common.util.DBConnection;

public class GetAlbumInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession(false);
		String salonId_str = "";
		int salonId = -1;
		//TODO: test
		salonId = 1;
		
		if (session != null){
			salonId_str = (String)session.getAttribute("salonId");
		}else{
			//session is null.
		}
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}else{
			//salonId is null.
		}

        int responseStatus = HttpServletResponse.SC_OK;
				
		try{
			DBConnection dbConnection = new DBConnection();
			JSONObject jsonObject = new JSONObject();
			java.sql.Connection conn = dbConnection.connectDB();

			List<Integer> hairStyleIdList = new ArrayList<Integer>();
			HairStyleInfo hairStyleInfo = new HairStyleInfo();
			List<HairStyleInfo> hairStyleInfoList = new ArrayList<HairStyleInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				hairStyleIdList = salonDao.getHairStyleIdList(dbConnection, salonId);
				HairStyleDao hairStyleDao = new HairStyleDao();
				hairStyleInfoList = hairStyleDao.getHairStyleInfoForMaster(dbConnection, hairStyleIdList, salonId);				
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object(title)
			/**
			 *  
			    {
			      album:[
			        {
			          t_hairStyle_id,
			          t_hairSalonMaster_salonId,
			          t_hairStyle_hairTypeId,
			          t_hairStyle_name,
			          t_hairStyle_stylistId,
			          t_hairStyle_imagePath,
			        },
			      ]
			    }
			 */
			JSONArray jsonArray = new JSONArray();
		    for(HairStyleInfo hairStyleOneInfo : hairStyleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_hairStyle_id", hairStyleOneInfo.getHairStyleId());
		    	jsonOneData.put("t_hairSalonMaster_salonId", hairStyleOneInfo.getSalonId());
		    	jsonOneData.put("t_hairStyle_hairTypeId", hairStyleOneInfo.getHairTypeId());
		    	jsonOneData.put("t_hairStyle_name", hairStyleOneInfo.getHairStyleName());
		    	jsonOneData.put("t_hairStyle_stylistId", hairStyleOneInfo.getStylistId());
		    	jsonOneData.put("t_hairStyle_imagePath", hairStyleOneInfo.getHairStyleImagePath());
		    	jsonArray.add(jsonOneData);
		    }
		    // 返却用サロンデータ（jsonデータの作成）
		    jsonObject.put("album",jsonArray);
						    		    
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
