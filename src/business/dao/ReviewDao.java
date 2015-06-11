package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import common.constant.Constant;
import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class ReviewDao {
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
		/*
		int searchNum_start = (pageNum-1) * 2;
		int searchNum_end = pageNum * 2;
		 */

		List<Integer> reviewIdList = new ArrayList<Integer>();
		for(int i=searchNum_start; i<searchNum_end; i++){
			if(idList.size()<=i) break;
			reviewIdList.add(idList.get(i));			
		}
		
		int hitNum=0;
		
		try {			
			for(int reviewId : reviewIdList){
				ResultSet rs = statement.executeQuery(sql+reviewId);
				//Debug
				System.out.println("sql="+sql+reviewId);

				ReviewInfo reviewInfo = new ReviewInfo();
				while(rs.next()){
					reviewInfo.setReviewId(rs.getInt("t_review_id"));
					reviewInfo.setReviewUserId(rs.getInt("t_review_userId"));
					reviewInfo.setReviewPostedDate(rs.getDate("t_review_postedDate"));
					reviewInfo.setReviewCommentId(rs.getString("t_review_commentId"));
					reviewInfo.setReviewText(rs.getString("t_review_text"));
					reviewInfo.setReviewPoint(rs.getDouble("t_review_evaluation_point"));
					ReviewInfoList.add(reviewInfo);
					hitNum++;
				}
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		

		if(Constant.ONE_PAGE_NUM * pageNum + Constant.ONE_PAGE_NUM >= hitNum){
			jsonObject.put("next", 0);
		}
		else{
			jsonObject.put("next", 1);
		}
		
		return ReviewInfoList;
	}
	
}
