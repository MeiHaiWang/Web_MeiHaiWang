package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.dao.UserDao;

import common.constant.Constant;
import common.util.DBConnection;

public class AddHairStyleFavoriteService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        int hairStyleId = request.getParameter("id")!= null
        		?Integer.parseInt(request.getParameter("id")) : -1;
		 // userIdがパラメータ。なかったら-1を入れておく。
        //TODO テスト用
        userId = 1;
        hairStyleId = 2;
        
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			//List<UserInfo> infoList = new ArrayList<UserInfo>();
			int status = -1;
			if(conn!=null){
				UserDao userDao = new UserDao();
				//infoList = userDao.getuserInfo(dbConnection, userId);
				status = userDao.addFavoriteHairStyle(dbConnection, userId, hairStyleId);
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
