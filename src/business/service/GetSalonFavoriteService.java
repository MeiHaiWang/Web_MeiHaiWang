package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetSalonFavoriteService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> SalonIdList  = new ArrayList<Integer>();
			List<HairSalonInfo> SalonInfoList = new ArrayList<HairSalonInfo>();

	        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
	        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
	        		//TODO テスト用
			userId =1;			
			
			if(conn!=null){
				SalonDao dao = new SalonDao();
				SalonIdList  = dao.getSalonFavoriteIdList(dbConnection, userId);
				SalonInfoList = dao.getSalonFavoriteInfo(dbConnection, SalonIdList);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray SalonArray = new JSONArray();
		    for(HairSalonInfo SalonInfo : SalonInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", SalonInfo.getHairSalonId());
		    	jsonOneData.put("name", SalonInfo.getHairSalonName());
		    	jsonOneData.put("image", SalonInfo.getHairSalonImagePath());
		    	jsonOneData.put("shopId", SalonInfo.getHairSalonId());
		    	jsonOneData.put("isgood", SalonInfo.getIsGood());
		    	jsonOneData.put("good_count", SalonInfo.getFavoriteNumber());
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