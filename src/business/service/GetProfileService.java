package business.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ReservationDao;
import business.dao.StylistDao;
import business.dao.UserDao;
import common._model.TUserInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.ReservationInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class GetProfileService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			TUserInfo userInfo = new TUserInfo();
			
			if(conn!=null){
				UserDao userDao = new UserDao();
				userInfo = userDao.get(dbConnection, userId);
				//Userがスタイリストかどうかを確認
				StylistDao stylistDao = new StylistDao();
				if(stylistDao.getByColumn(dbConnection, TableConstant.COLUMN_STYLIST_USERID, userId).size()>0){
					userInfo.setUserIsStylist(1);
				}else{
					userInfo.setUserIsStylist(0);
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			/*
			 *     isStylist:0,
				   name: "美々日",   
				   image: "http://sdasdas.asasda.png",
				   birth: "1988年10月14日",
				   gender: 1
			 */
			
			JSONArray myPageArray = new JSONArray();
	    	JSONObject jsonOneData = new JSONObject();
	    	jsonObject.put("isstylist", userInfo.getUserIsStylist());
	    	jsonObject.put("name", userInfo.getName());
	    	jsonObject.put("image", userInfo.getTUserImagePath());
	    	Date oneDate = userInfo.getTUserBirth();
	    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
	    	jsonOneData.put("day", sdf1.format(oneDate));
	    	jsonObject.put("gender", userInfo.getTUserSex());
	    	myPageArray.add(jsonOneData);

		    //Debug
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
