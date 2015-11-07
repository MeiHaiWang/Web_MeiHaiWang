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
import business.dao.SalonDao;
import business.dao.UserDao;
import common._model.THairSalonMasterInfo;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetSalonHistoryService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<Integer> hairsalonIdList  = new ArrayList<Integer>();
			List<String> hairsalonIdList  = new ArrayList<String>();
			List<THairSalonMasterInfo> hairSalonInfoList = new ArrayList<THairSalonMasterInfo>();

	        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
	        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			
			if(conn!=null){
				SalonDao dao = new SalonDao();
				UserDao userDao = new UserDao();
				//hairsalonIdList  = dao.getHairSalonHistoryIdList(dbConnection, userId);
				//TODO: historySalonIdをvarcharに?テーブルがおかしい？
				//hairsalonIdList  = Arrays.asList(userDao.get(dbConnection, userId).getTUserHistorySalonId().split(","));
				//hairSalonInfoList = dao.getHairSalonHistoryInfo(dbConnection, hairsalonIdList);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray salonArray = new JSONArray();
		    for(THairSalonMasterInfo hairSalonInfo : hairSalonInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairSalonInfo.getTHairSalonMasterSalonId());
		    	jsonOneData.put("name", hairSalonInfo.getTHairSalonMasterName());
		    	int i = 0;
		    	for(String str : Arrays.asList(hairSalonInfo.getTHairSalonMasterSalonImagePath().split(","))){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	//jsonOneData.put("image", hairSalonInfo.getHairSalonImagePath());
		    	jsonOneData.put("message", hairSalonInfo.getTHairSalonMasterMessage());
		    	//TODO オススメサロンを返却する際は地域レベル１の地名を返却すればいい?
		    	jsonOneData.put("place", hairSalonInfo.getTHairSalonMasterAddress());
		    	jsonOneData.put("isgood", hairSalonInfo.getTHairSalonMasterGoodNumber()>0?1:0);
		    	//good? favorite?
		    	jsonOneData.put("good_count", hairSalonInfo.getTHairSalonMasterFavoriteNumber());
		    	salonArray.add(jsonOneData);
		    }
		    jsonObject.put("salon_lists",salonArray);

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
