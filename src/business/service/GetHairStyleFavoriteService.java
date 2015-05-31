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

public class GetHairStyleFavoriteService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<Integer> HairStyleIdList  = new ArrayList<Integer>();
			List<HairStyleInfo> HairStyleInfoList = new ArrayList<HairStyleInfo>();
			/*String userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			 */
			//TODO テスト用
			int userId =1;			
			
			if(conn!=null){
				HairStyleDao dao = new HairStyleDao();
				HairStyleIdList  = dao.getHairStyleFavoriteIdList(dbConnection, userId);
				HairStyleInfoList = dao.getHairStyleFavoriteInfo(dbConnection, HairStyleIdList);
				dbConnection.close();
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray HairStyleArray = new JSONArray();
		    for(HairStyleInfo HairStyleInfo : HairStyleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", HairStyleInfo.getHairStyleId());
		    	jsonOneData.put("name", HairStyleInfo.getHairStyleName());
		    	jsonOneData.put("image", HairStyleInfo.getHairStyleImagePath());
		    	jsonOneData.put("shopId", HairStyleInfo.getHairStyleId());
		    	jsonOneData.put("isgood", HairStyleInfo.getIsGood());
		    	jsonOneData.put("good_count", HairStyleInfo.getFavoriteNumber());
		    	HairStyleArray.add(jsonOneData);
		    }
		    jsonObject.put("HairStyle_lists",HairStyleArray);		    

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
