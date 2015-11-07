package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import business._dao.TReviewDao;
import net.sf.json.JSONObject;
import common.constant.Constant;
import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class ReviewDao extends TReviewDao{
	/*
	String sql = "SELECT `t_review_id`, `t_review_userId`, `t_review_postedDate`, `t_review_commentId` , "
			+ "`t_review_text`, `t_review_evaluation_point`, `t_review_userId`"
			+ "FROM t_review WHERE t_review_id = ";
			 */
	/*
	public List<ReviewInfo> getReviewDetailInfo(DBConnection dbConnection, List<Integer> idList, int pageNum, JSONObject jsonObject) throws SQLException{
		List<ReviewInfo> ReviewInfoList = new ArrayList<ReviewInfo>();
		String sql = "SELECT `t_review_id`, `t_review_userId`, `t_review_postedDate`, `t_review_commentId` , "
				+ "`t_review_text`, `t_review_evaluation_point`, `t_user_name`, `t_user_sex`, `t_user_birth` "
				+ "FROM t_review JOIN t_user ON t_review_userId = t_user_Id WHERE t_review_id = ";
				//+ "LIMIT 1 OFFSET 0";		
		Statement statement = dbConnection.getStatement();
		
		//ページ対応
		int searchNum_start = pageNum * Constant.ONE_PAGE_NUM;
		int searchNum_end = (pageNum+1) * Constant.ONE_PAGE_NUM;

		List<Integer> reviewIdList = new ArrayList<Integer>();
		for(int i=searchNum_start; i<searchNum_end; i++){
			if(idList.size()<=i) break;
			reviewIdList.add(idList.get(i));			
		}
		if(Constant.ONE_PAGE_NUM * pageNum + Constant.ONE_PAGE_NUM >= idList.size()){
			jsonObject.put("next", 0);
		}
		else{
			jsonObject.put("next", 1);
		}

		//int hitNum=0;
		
		try {			
			for(int reviewId : reviewIdList){
				ResultSet rs = statement.executeQuery(sql+reviewId);
				//Debug
				System.out.println(sql+reviewId);
				ReviewInfo reviewInfo = new ReviewInfo();
				while(rs.next()){
					reviewInfo.setReviewId(rs.getInt("t_review_id"));
					reviewInfo.setReviewUserId(rs.getInt("t_review_userId"));
					reviewInfo.setReviewPostedDate(rs.getDate("t_review_postedDate"));
					reviewInfo.setReviewCommentId(rs.getString("t_review_commentId"));
					reviewInfo.setReviewText(rs.getString("t_review_text"));
					reviewInfo.setReviewPoint(rs.getString("t_review_evaluation_point"));
					//reviewInfo.setReviewUserId(rs.getInt("t_review_userId"));
					reviewInfo.setReviewUserName(rs.getString("t_user_name"));
					reviewInfo.setReviewUserSex(rs.getInt("t_user_sex"));
					reviewInfo.setReviewUserBirth(rs.getDate("t_user_birth"));
					ReviewInfoList.add(reviewInfo);
					//hitNum++;
				}
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return ReviewInfoList;
	}

	/**
	 * INSERT INTO `MEIHAIWAN_TEST`.`t_review` 
	 * (`t_review_id`, `t_review_userId`, `t_review_postedDate`, `t_review_commentId`, 
	 * `t_review_text`, `t_review_evaluation_point`) 
	 * VALUES ('5', '1', '2015-10-01 00:00:00', 'pdf', 'asdf', '1');
	 */
	
	/*
	public int setReview(DBConnection dbConnection, ReviewInfo reviewInfo) {
		boolean result = false;
		int reviewId = -1;

		
		String sql = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_review` "
				+ "(`t_review_id`, `t_review_userId`, `t_review_postedDate`, `t_review_commentId`,`t_review_text`, `t_review_evaluation_point`)"
				+ "VALUES (NULL,"; //);
		Statement statement = dbConnection.getStatement();

		//現在日時を取得する
        Calendar c = Calendar.getInstance();
        //フォーマットパターンを指定して表示する
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(sdf.format(c.getTime()));
		
		sql += "'"+reviewInfo.getReviewUserId()+"',"
				+"'"+sdf.format(c.getTime())+"',"
				+"'"+reviewInfo.getReviewCommentId()+"'," //初期値""
				+"'"+reviewInfo.getReviewText()+"',"
				+"'"+reviewInfo.getReviewPoint()+"');";
		
		try {
			//debug
			System.out.println(sql);
			int result_int = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if(result_int >= 0){
				//更新したidをget
				ResultSet rs = statement.getGeneratedKeys();
		        if (rs.next()){
		        	reviewId = rs.getInt(1);
	        	}
		        rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reviewId;
	}

	/**
	 * UPDATE `MEIHAIWAN_TEST`.`t_review` SET `t_review_commentId` = '1' WHERE `t_review`.`t_review_id` = 10;
	 */
	/*
	public boolean setReviewCommentId(DBConnection dbConnection,
			int commentReviewId, int commentId) {
		boolean result = false;
		String sql_before = "SELECT `t_review_commentId` FROM `t_review` WHERE `t_review_id`="+ commentReviewId;
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_review` SET `t_review_commentId` = '" ;
		String sql2 ="' WHERE `t_review`.`t_review_id` = " +commentReviewId;

		Statement statement = dbConnection.getStatement();
		
		try {
			String reviewCommentId = "";
			//debug
			System.out.println(sql_before);
			ResultSet rs = statement.executeQuery(sql_before);
			while(rs.next()){
				reviewCommentId = rs.getString("t_review_commentId");
			}
			if(reviewCommentId!=null && !reviewCommentId.equals("")){
				reviewCommentId += "," + commentId;
			}else{
				reviewCommentId = "" + commentId;
			}
			
			String sql = sql1 + reviewCommentId + sql2;
			//debug
			System.out.println(sql);
			int result_int = statement.executeUpdate(sql);
			if(result_int >= 0){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * DELETE FROM `MEIHAIWAN_TEST`.`t_review` WHERE `t_review`.`t_review_id` = 26
	 */
	/*
	public boolean DeleteReviewInfo(DBConnection dbConnection, String t_reviewId) {
		boolean result = false;
		String sql = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_review` WHERE `t_review`.`t_review_id` = "+t_reviewId;
		Statement statement = dbConnection.getStatement();

		try{
			//debug
			System.out.println(sql);
			int result_int = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if(result_int >= 0){
				result = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<String> getReviewCommentIdList(DBConnection dbConnection, String t_reviewId) {
		String sql = "SELECT `t_review_commentId` FROM `t_review` WHERE `t_review_id`="+t_reviewId;
		Statement statement = dbConnection.getStatement();
		String commentId = "";
		try{
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				commentId = rs.getString("t_review_commentId");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return Arrays.asList(commentId.split(","));
	}

	public int getReviewUserId(DBConnection dbConnection, String t_reviewId) {
		int userId = -1;
		String sql = "SELECT `t_review_userId` FROM `t_review` WHERE `t_review_id`="+t_reviewId;
		Statement statement = dbConnection.getStatement();
		try{
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				userId = rs.getInt("t_review_userId");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	/**
	 * SELECT `t_review_commentId` FROM `t_review` WHERE `t_review_id`=
	 * UPDATE `MEIHAIWAN_TEST`.`t_review` SET `t_review_commentId` = '5,6,7' WHERE `t_review`.`t_review_id` = 22;
	 */
	/*
	public boolean DeleteCommentId(DBConnection dbConnection, int reviewId,
			String t_commentId) {
		boolean result = false;
		String sql_before = "SELECT `t_review_commentId` FROM `t_review` WHERE `t_review_id`="+reviewId;
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_review` SET `t_review_commentId` = '";
		String sql2 = "' WHERE `t_review`.`t_review_id` = "+reviewId;
		Statement statement = dbConnection.getStatement();

		try{
			String commentIds = "";
			//debug
			System.out.println(sql_before);
			ResultSet rs = statement.executeQuery(sql_before);
			while(rs.next()){
				commentIds = rs.getString("t_review_commentId");
			}
			List<String> commentIdList = Arrays.asList(commentIds.split(","));
			commentIds = "";
			for(int i=0; i<commentIdList.size();i++){
				if(!commentIdList.get(i).equals(t_commentId)){
					commentIds += commentIdList.get(i)+",";
				}
			}
			if(commentIds.length()>0) commentIds=commentIds.substring(0,commentIds.length()-1);
			
			String sql = sql1 + commentIds + sql2;
			//debug
			System.out.println(sql);
			int result_int = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if(result_int >= 0){
				result = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	*/
	
}
