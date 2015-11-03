package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.dao.UserDao;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.UserInfo;
import common.util.DBConnection;

public class AddHairStyleFavoriteService implements IServiceExcuter{
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        String hairStyleId = request.getParameter("id")!= null
        		?request.getParameter("id") : null;
		 // userIdがパラメータ。なかったら-1を入れておく。
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<UserInfo> infoList = new ArrayList<UserInfo>();
			int status = -1;
			if(conn!=null && hairStyleId!=null){
				UserDao userDao = new UserDao();
				//infoList = userDao.getuserInfo(dbConnection, userId);
				//status = userDao.addFavoriteHairStyle(dbConnection, userId, hairStyleId);
				UserInfo info = new UserInfo();
				info.setObjectId(userId);
				userDao.appendId(dbConnection, TableConstant.COLUMN_USER_FAVORITE_HAIR_STYLE, hairStyleId, info);
				dbConnection.close();
			}else{
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
