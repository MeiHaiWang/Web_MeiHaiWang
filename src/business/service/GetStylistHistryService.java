package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.StylistDao;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetStylistHistryService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		/*
		Date lastUpdateSalon = new Date(0);
        Date lastUpdateHair = new Date(0);
        */
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> stylistIdList  = new ArrayList<Integer>();
			List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
			/*String userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			 */
			//TODO テスト用
			int userId =1;			
			
			if(conn!=null){
				StylistDao dao = new StylistDao();
				stylistIdList  = dao.getStylistHistryIdList(dbConnection, userId);
				stylistInfoList = dao.getStylistHistryInfo(dbConnection, stylistIdList);
				dbConnection.close();
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray stylistArray = new JSONArray();
		    for(StylistInfo stylistInfo : stylistInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", stylistInfo.getStylistId());
		    	jsonOneData.put("name", stylistInfo.getStylistName());
		    	jsonOneData.put("image", stylistInfo.getStylistImagePath());
		    	jsonOneData.put("shopId", stylistInfo.getSalonId());
		    	jsonOneData.put("isgood", stylistInfo.getIsGood());
		    	jsonOneData.put("good_count", stylistInfo.getFavoriteNumber());
		    	stylistArray.add(jsonOneData);
		    }
		    jsonObject.put("stylist_lists",stylistArray);		    

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
