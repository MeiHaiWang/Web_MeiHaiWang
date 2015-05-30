package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.HairSalonHistryDao;

import common.model.HairSalonInfo;
import common.util.DBConnection;

public class GetHairSalonHistryService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> hairsalonIdList  = new ArrayList<Integer>();
			List<HairSalonInfo> hairSalonInfoList = new ArrayList<HairSalonInfo>();
			/*String userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			 */
			//TODO テスト用
			int userId =1;			
			
			if(conn!=null){
				HairSalonHistryDao dao = new HairSalonHistryDao();
				hairsalonIdList  = dao.getHairSalonIdList(dbConnection, userId);
				hairSalonInfoList = dao.getHairSalonHistryInfo(dbConnection, hairsalonIdList);
				dbConnection.close();
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray salonArray = new JSONArray();
		    for(HairSalonInfo hairSalonInfo : hairSalonInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairSalonInfo.getHairSalonId());
		    	jsonOneData.put("name", hairSalonInfo.getHairSalonName());
		    	jsonOneData.put("image", hairSalonInfo.getHairSalonImagePath());
		    	jsonOneData.put("message", hairSalonInfo.getMessage());
		    	//オススメサロンを返却する際は地域レベル１の地名を返却すればいい
		    	jsonOneData.put("place", hairSalonInfo.getAreaNameList().get(0));
		    	jsonOneData.put("isgood", hairSalonInfo.getIsGood());
		    	jsonOneData.put("good_count", hairSalonInfo.getGoodNumber());
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
