package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class ReviewDao {

	public List<ReviewInfo> getReviewDetailInfo(DBConnection dbConnection, List<Integer> reviewIdList) throws SQLException{

		List<ReviewInfo> ReviewInfoList = new ArrayList<ReviewInfo>();
		String sql = "SELECT `t_review_id`, `t_review_userId`, `t_review_postedDate`, `t_review_commentId` , `t_review_text`"
				+ ", `t_user_name`, `t_user_sex`, `t_user_birth`, `t_comment_message` FROM t_review "
				+ "JOIN t_comment ON t_review_commentId = t_comment_reviewId JOIN t_user ON "
				+ "t_review_userId = t_user_Id WHERE t_review_id=";		
		Statement statement = dbConnection.getStatement();
		try {			
			for(int reviewId : reviewIdList){
				ResultSet rs = statement.executeQuery(sql+reviewId);
				ReviewInfo reviewInfo = new ReviewInfo();
				while(rs.next()){
					reviewInfo.setReviewId(rs.getInt("t_review_id"));
					reviewInfo.setReviewUserId(rs.getInt("t_review_userId"));
					reviewInfo.setReviewPostedDate(rs.getDate("t_review_postedDate"));
					reviewInfo.setReviewCommentId(rs.getNString("t_review_commentId"));
					reviewInfo.setReviewText(rs.getString("t_review_text"));
					ReviewInfoList.add(reviewInfo);
				}
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ReviewInfoList;
	}
	
}
