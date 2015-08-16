package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import business.dao.HairTypeDao;
import common.constant.Constant;
import common.model.HairStyleInfo;
import common.model.HairTypeInfo;
import common.util.DBConnection;

public class GetHairTypeCategoryService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int gender = request.getParameter("gender")!= null
        		?Integer.parseInt(request.getParameter("gender")) : -1;
		 //gender=0 　性別フラグ　0=男性、1=女性
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<HairTypeInfo> infoList = new ArrayList<HairTypeInfo>();
			if(conn!=null){
				HairTypeDao hairTypeDao = new HairTypeDao();
				infoList = hairTypeDao.getHairTypeCategoryInfo(dbConnection, gender);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray hairTypeArray = new JSONArray();
		    for(HairTypeInfo hairTypeInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairTypeInfo.getHairTypeId());
		    	jsonOneData.put("name", hairTypeInfo.getHairTypeName());
		    	jsonOneData.put("image", hairTypeInfo.getHairTypeImagePath());
		    	hairTypeArray.add(jsonOneData);
		    }
		    jsonObject.put("catalogCategory",hairTypeArray);

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
