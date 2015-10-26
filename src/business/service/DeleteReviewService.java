package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.CommentDao;
import business.dao.MenuDao;
import business.dao.ReviewDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.util.DBConnection;

public class DeleteReviewService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        
  		HttpSession session = request.getSession(false);
		
		String t_reviewId = request.getParameter("t_reviewId") != null ?
				request.getParameter("t_reviewId").toString() : null;

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();

			int salonId = -1;
			List<String> commentIdList = new ArrayList<String>();
			
			if(conn!=null){
				ReviewDao reviewDao = new ReviewDao();
				SalonDao salonDao = new SalonDao();
				CommentDao commentDao = new CommentDao();
				commentIdList = reviewDao.getReviewCommentIdList(dbConnection,t_reviewId);
				salonId = salonDao.getReviewedSalonId(dbConnection,t_reviewId);
				int reviewUserId = reviewDao.getReviewUserId(dbConnection, t_reviewId);
				if(reviewUserId == userId){
					result = true;
				}
				if(result){
					result = reviewDao.DeleteReviewInfo(
							dbConnection,
							t_reviewId
							);
				}
				if(result){
					result = salonDao.DeleteReviewId(dbConnection,
							t_reviewId, salonId);
				}
				if(result && !commentIdList.get(0).equals("")){
					for(String commentId: commentIdList){
						result = commentDao.DeleteCommentId(
								dbConnection,
								commentId
								);
					}
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			    }
			 * 
			 */
			
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
			
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
