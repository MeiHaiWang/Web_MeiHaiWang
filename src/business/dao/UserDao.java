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
		String sql = "SELECT `t_review_id`, `t_review_userId`, `t_review_postedDate`, `t_review_commentId` , `"
				+ "t_review_text`, `t_user_name`, `t_user_sex`, `t_user_birth` FROM t_review "
				+ "JOIN t_user ON t_review_userId = t_user_Id WHERE t_review_id=";		
		Statement statement = dbConnection.getStatement();
		
		try {			
			for(int reviewId : reviewIdList){
				ResultSet rs = statement.executeQuery(sql+reviewId);
				//debug
				System.out.println(sql+reviewId);

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

	public int addFavoriteSalon(DBConnection dbConnection, int userId, int salonId) throws SQLException {
		int result = -1;
		String sql_before = "SELECT `t_user_favoriteSalonId` FROM `t_user` WHERE t_user_id = ";
		String sql_after="";
		String sql1 = "UPDATE `MEIHAIWAN_TEST`.`t_user` SET `t_user_favoriteSalonId` = '";
		String sql2 = "' WHERE `t_user`.`t_user_Id` = " + userId + ";";
		
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql_before+userId);		

		String before = "";
		String after = "";
		try {						
			ResultSet rs = statement.executeQuery(sql_before + userId);
			while(rs.next()){
				before = rs.getString("t_user_favoriteSalonId");
				if(before.compareTo("")!=0){
				    after = before + "," + salonId; 
				}else{
					after = "" + salonId; 					
				}
				//rs.updateString("t_user_favoriteSalonId", after);
			}
			
			sql_after = sql1 + after + sql2;
			
			//debug
			System.out.println(sql_after);		

			result = statement.executeUpdate(sql_after);
			System.out.println("result: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int addFavoriteStylist(DBConnection dbConnection, int userId, int stylistId) throws SQLException {
		int result = -1, status = -1;
		String sql_before = "SELECT `t_user_favoriteStylistId` FROM `t_user` WHERE t_user_id = ";
		String sql_after="";
		String sql1 = "UPDATE `MEIHAIWAN_TEST`.`t_user` SET `t_user_favoriteStylistId` = '";
		String sql2 = "' WHERE `t_user`.`t_user_Id` = " + userId + ";";
		
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql_before+userId);		

		String before = "";
		String after = "";
		try {						
			ResultSet rs = statement.executeQuery(sql_before + userId);
			while(rs.next()){
				before = rs.getString("t_user_favoriteStylistId");
				if(before.compareTo("")!=0){
				    after = before + "," + stylistId; 
				}else{
					after = "" + stylistId; 					
				}
				//rs.updateString("t_user_favoriteSalonId", after);
			}
			
			sql_after = sql1 + after + sql2;
			
			//debug
			System.out.println(sql_after);		

			result = statement.executeUpdate(sql_after);
			//System.out.println("result: "+result);
			if(result==1) status = 0;
			else status = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return status;
	}

}