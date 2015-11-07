package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import business.dao.UserDao;
import common._model.THairStyleInfo;
import common.constant.Constant;
import common.model.HairStyleInfo;
import common.util.DBConnection;

public class GetHairStyleFavoriteService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<Integer> HairStyleIdList  = new ArrayList<Integer>();
			List<String> hairStyleIdList  = new ArrayList<String>();
			List<THairStyleInfo> hairStyleInfoList = new ArrayList<THairStyleInfo>();

			int userId = request.getHeader(Constant.HEADER_USERID)!= null 
	        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
			
			if(conn!=null){
				HairStyleDao dao = new HairStyleDao();
				UserDao userDao = new UserDao();
				//HairStyleIdList  = dao.getHairStyleFavoriteIdList(dbConnection, userId);
				hairStyleIdList  = Arrays.asList(userDao.get(dbConnection, userId).getTUserFavoriteHairStyleId().split(","));
				for(String id : hairStyleIdList){
					hairStyleInfoList.add(dao.get(dbConnection, Integer.parseInt(id)));
				}
				//hairStyleInfoList = dao.getHairStyleFavoriteInfo(dbConnection, HairStyleIdList);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/*
			 *   hair_lists: [
			    {
			       id: 1,
			       image1: "http://exsample.com/minibobex.png",
			       good_count: 1,
			       stylistID: 1
			    },
			 */
			
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray HairStyleArray = new JSONArray();
		    for(THairStyleInfo hairStyleInfo : hairStyleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairStyleInfo.getTHairStyleId());
		    	jsonOneData.put("image", hairStyleInfo.getTHairStyleImagePath().substring(0,hairStyleInfo.getTHairStyleImagePath().indexOf(",")-1));
		    	/*
		    	int i=0;
		    	for(String str : hairStyleInfo.getTHairStyleImagePath()){
		    		i++;
		    		jsonOneData.put("image"+i, str);		    		
		    	}
		    	*/
		    	jsonOneData.put("good_count", hairStyleInfo.getTHairStyleFavoriteNumber());
		    	jsonOneData.put("stylistID", hairStyleInfo.getTHairStyleStylistId());
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
