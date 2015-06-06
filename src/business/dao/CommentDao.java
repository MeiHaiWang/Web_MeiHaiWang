package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.CommentInfo;
import common.model.ReviewInfo;
import common.util.DBConnection;

public class CommentDao {

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
	
}
