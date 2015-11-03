package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.CommentDao;
import business.dao.ReviewDao;
import business.dao.SalonDao;

import common.constant.Constant;
import common.util.DBConnection;

public class DeleteCommentService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
        
  		HttpSession session = request.getSession(false);
		
		String t_commentId = request.getParameter("t_commentId") != null ?
				request.getParameter("t_commentId").toString() : null;

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();

			int reviewId = -1;
			if(conn!=null){
				ReviewDao reviewDao = new ReviewDao();
				CommentDao commentDao = new CommentDao();
				reviewId = commentDao.getReviewId(dbConnection,t_commentId);
				/*
				int reviewUserId = reviewDao.getReviewUserId(dbConnection, t_reviewId);
				if(reviewUserId == userId){
					result = true;
				}
				*/
				result = reviewDao.DeleteCommentId(
						dbConnection,
						reviewId,
						t_commentId
						);
				if(result){
					result = commentDao.DeleteCommentId(
							dbConnection,
							t_commentId
							);
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
