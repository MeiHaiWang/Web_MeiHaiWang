package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.util.DBConnection;

public class GetHairStyleDetailService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;

        int cataloglistId = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<HairStyleInfo> infoList = new ArrayList<HairStyleInfo>();
			if(conn!=null){
				HairStyleDao hairStyleDao = new HairStyleDao();
				infoList = hairStyleDao.getHairStyleDetailInfo(dbConnection, cataloglistId);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray hairStyleArray = new JSONArray();
		    for(HairStyleInfo hairStyleInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairStyleInfo.getHairStyleId());
		    	int i = 0;
		    	for(String str : hairStyleInfo.getHairStyleImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	jsonOneData.put("isgood", hairStyleInfo.getIsGood());
		    	jsonOneData.put("good_count", hairStyleInfo.getFavoriteNumber());
		    	jsonOneData.put("stylistId", hairStyleInfo.getStylistId());
		    	hairStyleArray.add(jsonOneData);
		    }
		    jsonObject.put("",hairStyleArray);

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
