package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import business.dao.RecommendDao;
import business.dao.StylistDao;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetStylistDetailService {

	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
		/*int salonId = request.getParameter(Constant.ID) != null ?
		Integer.parseInt(request.getParameter(Constant.ID)) : -1;*/
		 
        //TODO テスト用
        int stylistId =1;
        int userId = 1;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			StylistInfo stylistInfo = new StylistInfo();
			List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
			if(conn!=null){
				StylistDao stylistDao = new StylistDao();
				stylistInfo = stylistDao.getStylistDetailInfo(dbConnection, stylistId);
				stylistInfoList.add(stylistInfo);
				RecommendDao recomendDao = new RecommendDao();
				//ユーザがお気に入りしているかどうかを設定する
				recomendDao.setIsFavoriteStylist(userId, stylistInfoList, dbConnection);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonOneData = new JSONObject();
			jsonOneData.put("id", stylistInfoList.get(0).getStylistId());
			jsonOneData.put("shopID", stylistInfoList.get(0).getSalonId());
			jsonOneData.put("name", stylistInfoList.get(0).getStylistName());
			jsonOneData.put("gender", String.valueOf(stylistInfoList.get(0).getStylistGender()));
			jsonOneData.put("image", stylistInfoList.get(0).getStylistImagePath());
			jsonOneData.put("message", stylistInfoList.get(0).getStylistMessage());
			jsonOneData.put("years", String.valueOf(stylistInfoList.get(0).getStylistYearsNumber()));
			jsonOneData.put("isgood", String.valueOf(stylistInfoList.get(0).getIsGood()));
			jsonOneData.put("good_count", String.valueOf(stylistInfoList.get(0).getFavoriteNumber()));
			jsonOneData.put("isNetReservation", String.valueOf(stylistInfoList.get(0).getIsNetReservation()));
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
