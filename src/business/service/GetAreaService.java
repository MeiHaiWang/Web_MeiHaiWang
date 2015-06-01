package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import common.model.AreaInfo;
import common.util.DBConnection;

public class GetAreaService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
		/*int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;*/
		 // areaIdがパラメータ。なかったら-1を入れておく。
        //TODO テスト用
        int areaId = -1;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<AreaInfo> infoList = new ArrayList<AreaInfo>();
			if(conn!=null){
				AreaDao AreaDao = new AreaDao();
				infoList = AreaDao.getAreaInfo(dbConnection, areaId);
				dbConnection.close();
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray AreaArray = new JSONArray();
		    for(AreaInfo AreaInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", AreaInfo.getAreaId());		    	
		    	jsonOneData.put("name", AreaInfo.getAreaName());
		    	jsonOneData.put("isDetail", AreaInfo.getisDetailFlag());
		    	AreaArray.add(jsonOneData);
		    }
		    jsonObject.put("areaList",AreaArray);

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
