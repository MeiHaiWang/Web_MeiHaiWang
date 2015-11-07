package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.CommentDao;
import business.dao.ReviewDao;
import business.dao.SalonDao;
import common._model.TReviewInfo;
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
			if(conn!=null && t_commentId!=null){
				ReviewDao reviewDao = new ReviewDao();
				CommentDao commentDao = new CommentDao();
				//reviewId = commentDao.getReviewId(dbConnection,t_commentId);
				reviewId = commentDao.get(dbConnection,Integer.parseInt(t_commentId)).getTCommentReviewId();
				/*
				 * TODO コメントを削除できる人をレビュアーだけにするか？
				int reviewUserId = reviewDao.getReviewUserId(dbConnection, t_reviewId);
				if(reviewUserId == userId){
					result = true;
				}
				*/
				/**
				 * レビューカラムからコメントidを削除
				 */
				TReviewInfo reviewInfo = new TReviewInfo();
				reviewInfo = reviewDao.get(dbConnection, reviewId);
				String commentIds = reviewInfo.getTReviewCommentId();
				List<String> commentIdList = Arrays.asList(commentIds.split(","));
				commentIds = "";
				for(int index=0;index<commentIdList.size();index++){
					if(!commentIdList.get(index).equals(t_commentId)){
						commentIds += commentIdList.get(index)+",";
					}
				}
				commentIds = commentIds.substring(0,commentIds.length()-1);
				reviewInfo.setTReviewCommentId(commentIds);
				int resultInt = reviewDao.update(dbConnection, reviewInfo);
				/**
				 * コメントを削除
				 */
				if(resultInt>0){
					resultInt = commentDao.logicalDelete(dbConnection, Integer.parseInt(t_commentId));
					if(resultInt>0) result=true;
				}
				/*
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
				*/
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
