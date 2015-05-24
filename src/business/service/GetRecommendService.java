package business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.dao.RecommendDao;

import com.mysql.jdbc.Connection;

import common.util.DBConnection;

public class GetRecommendService {

	
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		
		try{
		

			
				RecommendDao dao = new RecommendDao();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
