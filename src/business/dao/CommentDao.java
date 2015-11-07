package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import business._dao.TCommentDao;
import common.model.CommentInfo;
import common.model.ReviewInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class CommentDao extends TCommentDao{

	/*
	public CommentInfo getCommentInfo(DBConnection dbConnection, int commentId) throws SQLException{

		CommentInfo commentInfo = new CommentInfo();
		String sql = "SELECT `t_comment_userId`, `t_comment_message`, `t_comment_reviewId`, `t_user_name` "
				+ "FROM `t_comment` JOIN t_user ON t_comment_id = t_user_id WHERE t_comment_id = ";		
		Statement statement = dbConnection.getStatement();
		
		try {			
			ResultSet rs = statement.executeQuery(sql+commentId);
			//Debug
			//System.out.println("sql="+sql+commentId);

			while(rs.next()){
				//commentInfo.setCommentId(rs.getInt("t_comment_id"));
				commentInfo.setCommentUserId(rs.getInt("t_comment_userId"));
				commentInfo.setCommentMessage(rs.getString("t_comment_message"));
				commentInfo.setCommentReviewId(rs.getInt("t_comment_reviewId"));
				commentInfo.setCommentUserName(rs.getString("t_user_name"));
				//commentInfo.setCommentDate(rs.getDate("t_comment_date"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return commentInfo;
	}

		/**
		 * INSERT INTO `MEIHAIWAN_TEST`.`t_comment` 
		 * (`t_comment_id`, `t_comment_userId`, `t_comment_message`, `t_comment_reviewId`, `t_comment_date`) 
		 * VALUES (NULL, '1', '1', '1', '2015-10-27 00:00:00');
		 */
	/*
	public int setComment(DBConnection dbConnection, CommentInfo commentInfo) {
		//boolean result = false;
		int commentId = -1;
		String sql_before = "SELECT * FROM `t_review` WHERE `t_review_id`="+commentInfo.getCommentReviewId();
		String sql = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_comment` "
				+ "(`t_comment_id`, `t_comment_userId`, `t_comment_message`, `t_comment_reviewId`, `t_comment_date`) VALUES (NULL, ";

		Statement statement = dbConnection.getStatement();

		//現在日時を取得する
        Calendar c = Calendar.getInstance();
        //フォーマットパターンを指定して表示する
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(sdf.format(c.getTime()));
		
		sql += "'"+commentInfo.getCommentUserId()+"',"
				+"'"+commentInfo.getCommentMessage()+"',"
				+"'"+commentInfo.getCommentReviewId()+"'," 
				+"'"+sdf.format(c.getTime())+"');";
		
		try {
			//コメントするレビューが存在しているかをかくにん
			//debug
			System.out.println(sql_before);
			ResultSet before_rs = statement.executeQuery(sql_before);
			if(!before_rs.next()){
				return commentId;
			}
			
			//debug
			System.out.println(sql);
			int result_int = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if(result_int >= 0){
				//更新したidをget
				ResultSet rs = statement.getGeneratedKeys();
		        if (rs.next()){
		        	commentId = rs.getInt(1);
	        	}
		        rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commentId;
	}

	/**
	 * DELETE FROM `MEIHAIWAN_TEST`.`t_comment` WHERE `t_comment`.`t_comment_id` = 6
	 */
	/*
	public boolean DeleteCommentId(DBConnection dbConnection, String commentId) {
		boolean result = false;
		String sql = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_comment` WHERE `t_comment`.`t_comment_id` = "+commentId;
		Statement statement = dbConnection.getStatement();
		try{
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

	public int getReviewId(DBConnection dbConnection, String t_commentId) throws SQLException {
		int reviewId = -1;
		String sql = "SELECT `t_comment_reviewId` FROM `t_comment` WHERE t_comment_id = " + t_commentId;		
		Statement statement = dbConnection.getStatement();
		try {			
			//Debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				reviewId = rs.getInt("t_comment_reviewId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return reviewId;
	}
	*/
	
}
