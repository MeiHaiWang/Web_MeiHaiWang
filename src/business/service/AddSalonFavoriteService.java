package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.UserInfo;
import common.util.DBConnection;

public class AddSalonFavoriteService implements IServiceExcuter{
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        String salonId = request.getParameter("id")!= null
        		?request.getParameter("id") : null;
		 // userIdがパラメータ。なかったら-1を入れておく。
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<UserInfo> infoList = new ArrayList<UserInfo>();
			int status = -1;
			if(conn!=null && salonId!=null){
				UserDao userDao = new UserDao();
				UserInfo userInfo = new UserInfo();
				userInfo.setObjectId(userId);
				userDao.appendId(dbConnection, TableConstant.COLUMN_USER_FAVORITE_SALON, salonId, userInfo);
				status = 0; //正常終了
				//status = userDao.addFavoriteSalon(dbConnection, userId, salonId);
				dbConnection.close();
			}else{
				status = 1; //異常終了
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
		    PrintWriter out = response.getWriter();
		    out.print("{ status : "+status+" }");
		    out.flush();
		    
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}
}
