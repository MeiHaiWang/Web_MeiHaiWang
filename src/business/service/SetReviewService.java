package business.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.CommentDao;
import business.dao.ReviewDao;
import business.dao.SalonDao;
import business.dao.StylistDao;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.CommentInfo;
import common.model.ReviewInfo;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class SetReviewService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;

		String reviewId = request.getParameter("reviewId") != null ?
				request.getParameter("reviewId").toString() : null;
		String salonId = request.getParameter("shopId") != null ?
				request.getParameter("shopId").toString() : null;
		String comment = request.getParameter("comment") != null ?
				request.getParameter("comment").toString() : null;
		String evaluation1 = request.getParameter("evaluation1") != null ?
				request.getParameter("evaluation1").toString() : "0";
		String evaluation2 = request.getParameter("evaluation2") != null ?
				request.getParameter("evaluation2").toString() : "0";
		String evaluation3 = request.getParameter("evaluation3") != null ?
				request.getParameter("evaluation3").toString() : "0";
		String evaluation4 = request.getParameter("evaluation4") != null ?
				request.getParameter("evaluation4").toString() : "0";
		String evaluation5 = request.getParameter("evaluation5") != null ?
				request.getParameter("evaluation5").toString() : "0";
		String evaluation = null;
		evaluation = evaluation1 + "," + evaluation2 + "," + evaluation3 + "," + evaluation4 + "," + evaluation5;
				
		ReviewInfo reviewInfo = new ReviewInfo();
		if(comment!=null&&!comment.equals("")) reviewInfo.setReviewText(comment);
		//if(evaluation!=null&&evaluation.equals("")) reviewInfo.setReviewPoint(Double.parseDouble(evaluation));
		if(evaluation!=null) reviewInfo.setReviewPoint(evaluation);
		reviewInfo.setReviewUserId(userId);
		//reviewInfo.setReviewPostedDate();
		CommentInfo commentInfo = new CommentInfo();
		if(reviewId!=null&&!reviewId.equals("")) commentInfo.setCommentReviewId(Integer.parseInt(reviewId));
		if(comment!=null&&!comment.equals("")) commentInfo.setCommentMessage(comment);
		commentInfo.setCommentUserId(userId);
		//commentInfo.setCommentUserName(userName);
		
		if((salonId==null || salonId.equals("")) || evaluation==null || comment==null){
			if(commentInfo.getCommentReviewId()<0){
				return resultError(response, "Invalid Parameter.");
			}
		}
		
		boolean result = false;
		int rid = -1;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			
			if(conn!=null){
				StylistInfo stylistInfo = new StylistInfo();
				StylistDao stylistDao = new StylistDao();
				stylistInfo = stylistDao.getStylistInfoByUserId(dbConnection,userId);
				ReviewDao reviewDao = new ReviewDao();
				if(commentInfo.getCommentReviewId()<0){
					//ユーザーレビュー
					//レビューを登録
					rid = reviewDao.setReview(dbConnection, reviewInfo);
					if(rid>0){
						//サロンにレビューidを追加
						SalonDao salonDao = new SalonDao();
						result = salonDao.setSalonReview(dbConnection, salonId, rid);
					}
				}else{
					//スタイリストのレビューに対するcomment
					commentInfo.setCommentUserName(stylistInfo.getStylistName());
					CommentDao commentDao = new CommentDao();
					int commentId = commentDao.setComment(dbConnection, commentInfo);					
					if(commentId>0){
						//reviewにcommentIdを紐付け
						result = reviewDao.setReviewCommentId(dbConnection, commentInfo.getCommentReviewId(), commentId);
					}
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("result", result);

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
	
	private HttpServletResponse resultError(HttpServletResponse response, String reason){
		try{
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("result", false);
	    	jsonObject.put("reason", reason);
		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
}
