package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import business.dao.RecommendDao;
import business.dao.StylistDao;
import common._model.TStylistInfo;
import common.constant.Constant;
import common.model.StylistInfo;
import common.util.CommonUtil;
import common.util.DBConnection;

public class GetStylistDetailService implements IServiceExcuter {

	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;

        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;

        /*
        int stylistId = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
        		*/
	    String stylistIdStr = request.getParameter("id")!= null
	    		?request.getParameter("id") : "-1";
	    int stylistId = -1;
	    if(stylistIdStr!=null && CommonUtil.isNum(stylistIdStr)){
	    	stylistId = Integer.parseInt(stylistIdStr);
	    }
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			TStylistInfo stylistInfo = new TStylistInfo();
			if(conn!=null && stylistId>0){
				StylistDao stylistDao = new StylistDao();
				//stylistInfo = stylistDao.getStylistDetailInfo(dbConnection, stylistId);
				stylistInfo = stylistDao.get(dbConnection, stylistId);
				RecommendDao recomendDao = new RecommendDao();
				//ユーザがお気に入りしているかどうかを設定する
				//TODO
				//recomendDao.setIsFavoriteStylist(userId, stylistInfoList, dbConnection);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonOneData = new JSONObject();
			
			jsonOneData.put("id", stylistInfo.getTStylistId());
			jsonOneData.put("shopID", stylistInfo.getTStylistSalonId());
			jsonOneData.put("name", stylistInfo.getTStylistName());
			jsonOneData.put("gender", stylistInfo.getTStylistSex());
	    	int i = 0;
	    	for(String str : Arrays.asList(stylistInfo.getTStylistImagePath().split(","))){
	    		i++;
	    		jsonOneData.put("image"+i, str);		    		
	    	}

			//jsonOneData.put("image", stylistInfo.getStylistImagePath());
			jsonOneData.put("message", stylistInfo.getTStylistMessage());
			jsonOneData.put("years", stylistInfo.getTStylistExperienceYear());
			jsonOneData.put("isgood", stylistInfo.getTStylistGoodNumber()>0? 1:0);
			jsonOneData.put("good_count", stylistInfo.getTStylistFavoriteNumber());
			jsonOneData.put("isNetReservation", stylistInfo.getTStylistIsNetReservation());
		    // 返却用サロンデータ（jsonデータの作成）
		    jsonObject.put("stylist",jsonOneData);
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
