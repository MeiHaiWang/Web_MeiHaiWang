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
import common.constant.Constant;
import common.model.StylistInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
    getStylistList
        概要：スタイリスト一覧取得
        入力：{ t_hairSalonMaster_salonId }
        出力：

    {
      stylist:[
        {
          t_stylist_Id,
          t_stylist_name
        },
        ...
      ]
    }
 *
 */

public class GetStylistListService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
 
		//salonId kokokara
        int salonId = -1;
        //get a salonId by session
		String salonId_str = "";
		if (session != null){
			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
		}
		if(salonId_str != null){			
			if(salonId_str.compareTo("") != 0){
				salonId = Integer.parseInt(salonId_str);
			}
		}   
		if(salonId < 0){
	        //get a salonId by parameter
	        salonId = request.getParameter(Constant.PARAMETER_SALONID)!= null 
			?Integer.parseInt(request.getParameter(Constant.PARAMETER_SALONID)) : -1;
		}
		//salonId kokomade
		
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<Integer> stylistIdList = new ArrayList<Integer>();
			List<StylistInfo> stylistInfoList  = new ArrayList<StylistInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				StylistDao stylistDao = new StylistDao();

				//stylistIdList for salonId in salonTable
				stylistIdList = salonDao.getStylistIdList(dbConnection, salonId);

				//stylistInfoList for stylistIdList in stylistTable
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
		    	//jsonOneData.put("t_stylist_stylist_id", info.getStylistId());
		    	jsonOneData.put("t_stylist_Id", info.getStylistId());
		    	jsonOneData.put("t_stylist_name", info.getStylistName());
			    stylistArray.add(jsonOneData);
		    }
		    jsonObject.put("stylist",stylistArray);	
		    //debug
		    //System.out.println(jsonObject.toString(4));
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
