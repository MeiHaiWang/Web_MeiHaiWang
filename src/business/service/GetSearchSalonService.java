package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.RecommendDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;

public class GetSearchSalonService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
				HttpServletResponse response){
		
	/*int userId = request.getHeader(Constant.HEADER_USERID) != null ?
			Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
	*/
	List<String> areaIdList = request.getParameter("area") != null ?
			Arrays.asList(request.getParameter("area").split(",")) : new ArrayList<String>();	
			
	List<String> searchConditionIdList = request.getParameter("condition") != null ?
			Arrays.asList(request.getParameter("condition").split(",")) : new ArrayList<String>();;	
		
	int pageNumber = request.getParameter("page") != null ?
			Integer.valueOf(request.getParameter("page").toString()) : 1;
	
	int onePageDisplayNum = request.getParameter("onePageNum") != null ?
			Integer.valueOf(request.getParameter("onePageNum").toString()) : Constant.ONE_PAGE_NUM;

	int responseStatus = HttpServletResponse.SC_OK;
	try{
		DBConnection dbConnection = new DBConnection();
		java.sql.Connection conn = dbConnection.connectDB();
		
		if(conn!=null){
			SalonDao dao = new SalonDao();
			
		}else{
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			throw new Exception("DabaBase Connect Error");
		}
	    
	}catch(Exception e){
		responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		e.printStackTrace();
	}
    
	response.setStatus(responseStatus);
	return response;

	}
}
