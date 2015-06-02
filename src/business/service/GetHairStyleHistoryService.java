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
import common.model.HairStyleInfo;
import common.util.DBConnection;

public class GetHairStyleHistoryService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> hairstyleIdList  = new ArrayList<Integer>();
			List<HairStyleInfo> hairStyleInfoList = new ArrayList<HairStyleInfo>();
			/*String userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			 */
			//TODO テスト用
			int userId =1;			
			
			if(conn!=null){
				HairStyleDao dao = new HairStyleDao();
				hairstyleIdList  = dao.getHairStyleHistoryIdList(dbConnection, userId);
				hairStyleInfoList = dao.getHairStyleHistoryInfo(dbConnection, hairstyleIdList);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    //返却用ヘアースタイルデータ(Jsonデータの作成)
		    JSONArray hairStyleArray = new JSONArray();
		    for(HairStyleInfo hairStyleInfo : hairStyleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairStyleInfo.getHairStyleId());
		    	jsonOneData.put("name", hairStyleInfo.getHairStyleName());
		    	jsonOneData.put("image", hairStyleInfo.getHairStyleImagePath());
		    	jsonOneData.put("shopId", hairStyleInfo.getSalonId());
		    	jsonOneData.put("stylistId", hairStyleInfo.getStylistId());
		    	jsonOneData.put("isgood", hairStyleInfo.getIsGood());
		    	jsonOneData.put("good_count", hairStyleInfo.getFavoriteNumber());
		    	hairStyleArray.add(jsonOneData);
		    }
		    jsonObject.put("hair_lists",hairStyleArray);

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
