package business.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import common._model.TCommentInfo;
import common._model.THairSalonMasterInfo;
import common._model.TReviewInfo;
import common._model.TStylistInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.CommentInfo;
import common.model.ReviewInfo;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class SetReviewService implements IServiceExcuter{
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
				
		TReviewInfo reviewInfo = new TReviewInfo();
		if(comment!=null&&!comment.equals("")) reviewInfo.setTReviewText(comment);
		//if(evaluation!=null&&evaluation.equals("")) reviewInfo.setReviewPoint(Double.parseDouble(evaluation));
		if(evaluation!=null) reviewInfo.setTReviewEvaluationPoint(evaluation);
		reviewInfo.setTReviewUserId(userId);
		//reviewInfo.setReviewPostedDate();
		TCommentInfo commentInfo = new TCommentInfo();
		if(reviewId!=null&&!reviewId.equals("")) commentInfo.setTCommentReviewId(Integer.parseInt(reviewId));
		if(comment!=null&&!comment.equals("")) commentInfo.setTCommentMessage(comment);
		commentInfo.setTCommentUserId(userId);
		//commentInfo.setCommentUserName(userName);
		
		if((salonId==null || salonId.equals("")) || evaluation==null || comment==null){
			if(commentInfo.getTCommentReviewId()<0){
				return resultError(response, "Invalid Parameter.");
			}
		}
		
		boolean result = false;
		int rid = -1;
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			
			if(conn!=null){
				List<TStylistInfo> stylistInfoList = new ArrayList<TStylistInfo>();
				StylistDao stylistDao = new StylistDao();
				//stylistInfo = stylistDao.getStylistInfoByUserId(dbConnection,userId);
				stylistInfoList = stylistDao.getByColumn(dbConnection, TableConstant.COLUMN_STYLIST_USERID, Integer.toString(userId));
				ReviewDao reviewDao = new ReviewDao();
				if(commentInfo.getTCommentReviewId()<0){
					//ユーザーレビュー
					//レビューを登録
					//rid = reviewDao.setReview(dbConnection, reviewInfo);
					rid = reviewDao.save(dbConnection, reviewInfo);
					if(rid>0){
						//サロンにレビューidを追加
						SalonDao salonDao = new SalonDao();
						THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
						salonInfo = salonDao.get(dbConnection, Integer.parseInt(salonId));
						String reviewIds = salonInfo.getTHairSalonMasterStylistId();
						List<String> reviewIdList = Arrays.asList(reviewIds.split(","));
						if(!reviewIdList.contains(rid)) {
							reviewIds="";
							for(int index=0;index<reviewIdList.size();index++){
								reviewIds += reviewIdList.get(index)+",";
							}
							reviewIds += rid;
							salonInfo.setTHairSalonMasterStylistId(reviewIds);
							int resultInt = salonDao.update(dbConnection, salonInfo);
							if(resultInt > 0) result = true;
						}
						//result = salonDao.setSalonReview(dbConnection, salonId, rid);
					}
				}else{
					//スタイリストのレビューに対するcomment
					//commentInfo.setTCommentUserId(tCommentUserId);//.setTCommentUserName(stylistInfoList.get(0).getName());
					CommentDao commentDao = new CommentDao();
					//int commentId = commentDao.setTComment(dbConnection, commentInfo);	
					int commentId = commentDao.save(dbConnection, commentInfo);	
					if(commentId>0){
						//reviewにcommentIdを紐付け
						TReviewInfo _reviewInfo = new TReviewInfo();
						_reviewInfo = reviewDao.get(dbConnection, commentInfo.getTCommentReviewId());
						String commentIds = _reviewInfo.getTReviewCommentId();
						List<String> commentIdList = Arrays.asList(commentIds.split(","));
						if(!commentIdList.contains(Integer.toString(commentInfo.getTCommentReviewId()))) {
							commentIds="";
							for(int index=0;index<commentIdList.size();index++){
								commentIds += commentIdList.get(index)+",";
							}
							commentIds += rid;
							_reviewInfo.setTReviewCommentId(commentIds);
							int resultInt = reviewDao.update(dbConnection, _reviewInfo);
							if(resultInt > 0) result = true;
						}
						//result = reviewDao.setReviewCommentId(dbConnection, commentInfo.getTCommentReviewId(), commentId);
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
