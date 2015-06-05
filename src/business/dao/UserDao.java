package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class UserDao {
	
	public List<UserInfo> getReviewerUserInfo(DBConnection dbConnection, List<Integer> reviewIdList) throws SQLException{
	
		List<UserInfo> UserInfoList = new ArrayList<UserInfo>();
		String sql = "SELECT `t_review_id`, `t_review_userId`, `t_review_postedDate`, `t_review_commentId` , `t_review_text`"
				+ ", `t_user_name`, `t_user_sex`, `t_user_birth`, `t_comment_message` FROM t_review "
				+ "JOIN t_comment ON t_review_commentId = t_comment_reviewId JOIN t_user ON "
				+ "t_review_userId = t_user_Id WHERE t_review_id=";		
		Statement statement = dbConnection.getStatement();
		try {			
			for(int reviewId : reviewIdList){
				ResultSet rs = statement.executeQuery(sql+reviewId);
				UserInfo userInfo = new UserInfo();
				while(rs.next()){
					userInfo.setUserName(rs.getString("t_user_name"));
					userInfo.setUserSex(rs.getInt("t_user_sex"));
					userInfo.setUserBirth(rs.getDate("t_user_birth"));
					UserInfoList.add(userInfo);
				}
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return UserInfoList;
	}
	
	public UserInfo getUserMypageInfo(DBConnection dbConnection, int userId) throws SQLException{
		
		//List<UserInfo> UserInfoList = new ArrayList<UserInfo>();
		UserInfo userInfo = new UserInfo();
		String sql = "SELECT `t_user_id`, `t_user_point` FROM `t_user` WHERE t_user_Id = ";		
		Statement statement = dbConnection.getStatement();
		try {			
			ResultSet rs = statement.executeQuery(sql+userId);
			while(rs.next()){
				userInfo.setUserId(rs.getInt("t_user_id"));
				userInfo.setUserPoint(rs.getInt("t_user_point"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		if(getUserIsStylistInfo(dbConnection, userId)){
			userInfo.setUserIsStylist(1);			
		}
		return userInfo;
	}
	
	public UserInfo getUserProfileInfo(DBConnection dbConnection, int userId) throws SQLException{
		UserInfo userInfo = new UserInfo();
		String sql = "SELECT `t_user_id`, `t_user_name`, `t_user_imagePath`, "
				+ "`t_user_birth`, `t_user_sex` FROM `t_user` WHERE t_user_Id = ";		
		Statement statement = dbConnection.getStatement();
		try {			
			ResultSet rs = statement.executeQuery(sql+userId);
			while(rs.next()){
				userInfo.setUserId(rs.getInt("t_user_id"));
				userInfo.setUserName(rs.getString("t_user_name"));
				userInfo.setUserImagePath(rs.getString("t_user_imagePath"));
				userInfo.setUserBirth(rs.getDate("t_user_birth"));
				userInfo.setUserSex(rs.getInt("t_user_sex"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		if(getUserIsStylistInfo(dbConnection, userId)){
			userInfo.setUserIsStylist(1);			
		}
		return userInfo;
	}

	
	public boolean getUserIsStylistInfo(DBConnection dbConnection, int userId) throws SQLException {
		//UserInfo userInfo = new UserInfo();
		boolean isStylist = false;
		String sql = "SELECT `t_stylist_userId` FROM `t_stylist`";		
		Statement statement = dbConnection.getStatement();
		try {			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				if(rs.getInt("t_stylist_userId")==userId) isStylist = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return isStylist;
	}

}