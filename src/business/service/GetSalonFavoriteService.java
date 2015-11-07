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

public class GetSalonFavoriteService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<Integer> SalonIdList  = new ArrayList<Integer>();
			List<String> salonIdList = new ArrayList<String>();
			List<THairSalonMasterInfo> salonInfoList = new ArrayList<THairSalonMasterInfo>();

	        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
	        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;

			if(conn!=null){
				SalonDao dao = new SalonDao();
				UserDao userDao = new UserDao();
				//SalonIdList  = dao.getSalonFavoriteIdList(dbConnection, userId);
				salonIdList = Arrays.asList(userDao.get(dbConnection, userId).getTUserFavoriteSalonId().split(","));
				for(String id : salonIdList){
					THairSalonMasterInfo info = new THairSalonMasterInfo();
					info = dao.get(dbConnection, Integer.parseInt(id));
					salonInfoList.add(info);
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/*
			 *  salon_lists: [
			    {
			      id: 834336,
			      name: "美美美",
			      image: "http://madeth-mac.local:3000/system/uploads/banner_information/image/2/ff037c9c15.png",
			      message: "サロンからのメッセージだよーん",
			      place: "北京",
			    }, 
			 */			
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray SalonArray = new JSONArray();
		    for(THairSalonMasterInfo salonInfo : salonInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", salonInfo.getTHairSalonMasterSalonId());
		    	jsonOneData.put("name", salonInfo.getTHairSalonMasterName());
		    	//jsonOneData.put("image", SalonInfo.getHairSalonImagePath());
		    	int i = 0;
		    	for(String str : Arrays.asList(salonInfo.getTHairSalonMasterSalonImagePath().split(","))){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("message", salonInfo.getTHairSalonMasterMessage());
		    	//TODO: place?
		    	jsonOneData.put("place", salonInfo.getTHairSalonMasterAddress());
		    	SalonArray.add(jsonOneData);
		    }
		    jsonObject.put("salon_lists",SalonArray);		    

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
