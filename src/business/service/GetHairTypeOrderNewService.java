package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.HairTypeDao;
import business.dao.StylistDao;
import common.constant.Constant;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.model.HairTypeInfo;
import common.util.DBConnection;

public class GetHairTypeOrderNewService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();

/**
	 * categoryID=0
	ヘアカタログID
	stylistID=0
	スタイリストID
	menu=0&menu=1&menu=2
	メニュー
	face=0&face=1
	顔型
	page=0
 */
        int categoryId = request.getParameter("categoryID")!= null
        		?Integer.parseInt(request.getParameter("categoryID")) : -1;
        int stylistId = request.getParameter("stylistID")!= null
           		?Integer.parseInt(request.getParameter("stylistID")) : -1;
        int menu = request.getParameter("menu")!= null
           		?Integer.parseInt(request.getParameter("menu")) : -1;
        int face = request.getParameter("face")!= null
           		?Integer.parseInt(request.getParameter("face")) : -1;
        int page = request.getParameter("page")!= null
           		?Integer.parseInt(request.getParameter("page")) : -1;
		//TODO テスト用
		//リクエストにどちらかのidが付加されてくる
		categoryId = 1;
		stylistId = -1;

        int responseStatus = HttpServletResponse.SC_OK;
				
		try{
			DBConnection dbConnection = new DBConnection();
			JSONObject jsonObject = new JSONObject();
			List<HairStyleInfo> HairStyleOrderNewList  = new ArrayList<HairStyleInfo>();
			java.sql.Connection conn = dbConnection.connectDB();
			
			if(categoryId >= 0 && stylistId < 0){
				HairTypeInfo hairTypeInfo = new HairTypeInfo();
				if(conn!=null){
					HairTypeDao hairTypeDao = new HairTypeDao();
					hairTypeInfo = hairTypeDao.getHairTypeInfo(dbConnection, categoryId);				
					HairStyleOrderNewList = hairTypeDao.getHairTypeOrderNewInfo(dbConnection, stylistId, categoryId);
					dbConnection.close();
				}else{
					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
					throw new Exception("DabaBase Connect Error");
				}
				
				//レスポンスに設定するJSON Object
				JSONObject jsonOneData = new JSONObject();
				jsonOneData.put("id", hairTypeInfo.getHairTypeId());
				jsonOneData.put("name", hairTypeInfo.getHairTypeName());
			    // 返却用サロンデータ（jsonデータの作成）
			    jsonObject.put("category",jsonOneData);
				
			}else if(stylistId >=0 && categoryId < 0){
				StylistInfo stylistInfo = new StylistInfo();
				if(conn!=null){
					HairTypeDao hairTypeDao = new HairTypeDao();
					StylistDao stylistDao = new StylistDao();
					stylistInfo = stylistDao.getStylistDetailInfo(dbConnection, stylistId);
					HairStyleOrderNewList = hairTypeDao.getHairTypeOrderNewInfo(dbConnection, stylistId, categoryId);
					dbConnection.close();
				}

				//レスポンスに設定するJSON Object
				JSONObject jsonOneData = new JSONObject();
				jsonOneData.put("id", stylistInfo.getStylistId());
				jsonOneData.put("name", stylistInfo.getStylistName());
			    // 返却用サロンデータ（jsonデータの作成）
			    jsonObject.put("stylist",jsonOneData);
			}

			// 返却用サロンデータ（jsonデータの作成）
			JSONArray HairStyleArray = new JSONArray();
		    for(HairStyleInfo hairStyleInfo : HairStyleOrderNewList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairStyleInfo.getHairStyleId());
		    	jsonOneData.put("image", hairStyleInfo.getHairStyleImagePath());
		    	HairStyleArray.add(jsonOneData);
		    }
		    jsonObject.put("cataloglist", HairStyleArray);
		    		    
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
