package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.HairStyleDao;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import business.dao.StylistDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetHairStyleDetailService implements IServiceExcuter{
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;

        int cataloglistId = request.getParameter("cataloglistId")!= null
        		?Integer.parseInt(request.getParameter("cataloglistId")) : -1;

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			HairStyleInfo hInfo = new HairStyleInfo();
			StylistInfo stylistInfo = new StylistInfo();
			if(conn!=null){
				HairStyleDao hairStyleDao = new HairStyleDao();
				hInfo = hairStyleDao.getHairStyleDetailInfo(dbConnection, cataloglistId);
				StylistDao stylistDao = new StylistDao();
				//stylistInfo = stylistDao.getStylistDetailInfo(dbConnection, hInfo.getStylistId());
				stylistInfo = stylistDao.getStylistObject(dbConnection, hInfo.getStylistId());
				AreaDao areaDao = new AreaDao();
				List<String> areaNameList = areaDao.getAreaName(dbConnection, hInfo.getHairStyleAreaId());
				String areaName = "";
				for(String area : areaNameList){
					areaName += area + ",";
				}
				if(areaNameList.size()>0) areaName.substring(0, areaName.length()-1);
				hInfo.setHairStyleAreaName(areaName);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}

			/**
			 * {
				   id: 1,
				   image1: "http://exsample.com/minibobex.png",
				   image2: "http://exsample.com/minibobex.png",
				   image3: "http://exsample.com/minibobex.png",
				   isgood: 1,
				   stylistName: "aaaa",
				   message: "aaaaaa",
				   area: "北京",
				   good_count: 144,
				   stylistID: 1
				}
			 */
			//レスポンスに設定するJSON Object
			JSONObject jsonOneData = new JSONObject();
	    	jsonOneData.put("id", hInfo.getHairStyleId());
	    	int i = 0;
	    	for(String str : hInfo.getHairStyleImagePath()){
	    		i++;
	    		jsonOneData.put("image"+i, str);		    		
	    	}
	    	if(hInfo.getHairStyleGoodNumber()>0){
	    		jsonOneData.put("isgood", 1);
	    	}else{
	    		jsonOneData.put("isgood", 0);
	    	}		    	
	    	jsonOneData.put("stylistName", stylistInfo.getName());
	    	jsonOneData.put("message", hInfo.getHairStyleMessage());
	    	jsonOneData.put("area", hInfo.getHairStyleAreaName());
	    	jsonOneData.put("good_count", hInfo.getHairStyleGoodNumber());
	    	jsonOneData.put("stylistID", hInfo.getStylistId());

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
