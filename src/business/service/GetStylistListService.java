package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.dao.SalonDao;
import business.dao.StylistDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetStylistListService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;

        /*
		int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		*/
		//TODO テスト用
        int salonId = 1;
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<Integer> stylistIdList = new ArrayList<Integer>();
			List<StylistInfo> stylistInfoList  = new ArrayList<StylistInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				stylistIdList = salonDao.getStylistIdList(dbConnection, salonId);
				StylistDao stylistDao = new StylistDao();
				stylistInfoList = stylistDao.getStylistListInfo(dbConnection, stylistIdList);	
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			
			/**
			 *
			    {
			      stylist:[
			        {
			          t_stylist_stylistId,
			          t_stylist_name
			        },
			        ...
			      ]
			    }
			 */
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray stylistArray = new JSONArray();
		    for(StylistInfo info : stylistInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_stylist_stylist_id", info.getStylistId());
		    	jsonOneData.put("t_stylist_name", info.getStylistName());
			    stylistArray.add(jsonOneData);
		    }
		    jsonObject.put("stylist",stylistArray);		    
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
