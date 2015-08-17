package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mysql.jdbc.PreparedStatement;

import common.constant.Constant;
import common.model.HairSalonInfo;
import common.model.ReviewInfo;
import common.model.UserInfo;
import common.util.ConfigUtil;
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
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_user` SET `t_user_favoriteSalonId` = '";
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
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_user` SET `t_user_favoriteStylistId` = '";
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
	
	public int addFavoriteHairStyle(DBConnection dbConnection, int userId, int hairStyleId) throws SQLException {
		int result = -1, status = -1;
		String sql_before = "SELECT `t_user_favoriteHairStyleId` FROM `t_user` WHERE t_user_id = ";
		String sql_after="";
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_user` SET `t_user_favoriteHairStyleId` = '";
		String sql2 = "' WHERE `t_user`.`t_user_Id` = " + userId + ";";
		
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql_before+userId);		

		String before = "";
		String after = "";
		try {						
			ResultSet rs = statement.executeQuery(sql_before + userId);
			while(rs.next()){
				before = rs.getString("t_user_favoriteHairStyleId");
				if(before.compareTo("")!=0){
				    after = before + "," + hairStyleId; 
				}else{
					after = "" + hairStyleId; 				
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
	
	public UserInfo getUserInfoByLoginInfo(DBConnection dbConnection, String pw, String tel) throws SQLException { 
		UserInfo userInfo = null;
		String sql = "SELECT `t_user_Id` FROM `t_user` WHERE `t_user_tel` =" + tel + " AND `t_user_passward` =" + pw ;		
		Statement statement = dbConnection.getStatement();
		try {			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("t_user_Id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfo;		
	}
	
	public UserInfo getUserInfoByTel(DBConnection dbConnection, String tel) throws SQLException { 
		UserInfo userInfo = null;
		String sql = "SELECT `t_user_Id` FROM `t_user` WHERE `t_user_tel` =" + tel;		
		Statement statement = dbConnection.getStatement();
		try {			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("t_user_Id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfo;		
	}
	public UserInfo getUserInfoByHash(DBConnection dbConnection, String hash) throws SQLException { 
		UserInfo userInfo = null;
		String sql = "SELECT `t_user_Id` FROM `t_user` WHERE `t_user_cookie` = '" + hash + "'";	
		//debug
		System.out.println("Check Auto Login: " +sql);
		Statement statement = dbConnection.getStatement();
		try {			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("t_user_Id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfo;		
	}
	
	public int updateUserHash(DBConnection dbConnection, Integer userId, String userHash) throws SQLException { 
		int result = -1;
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_user` SET `t_user_cookie` = '" + userHash;
		String sql2 = "' WHERE `t_user`.`t_user_Id` = " + userId.toString() + ";";
		Statement statement = dbConnection.getStatement();
		//debug
		System.out.println(sql1 + sql2);		
		try {						
			result = statement.executeUpdate(sql1 + sql2);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public int getCheckLoginInfo(DBConnection dbConnection, String mail, String password){
		String sql1 = "SELECT `t_hairSalonMaster_salonId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_mail` ='";
		String sql2 = "' AND `t_hairSalonMaster_passward` ='";
	    String sql3 = "'";
	    int retSalonId = -1;
	    
	    String sql = sql1 + mail + sql2 + password + sql3;
	    //debug
	    System.out.println("Manual-Login: " + sql);
	    
		Statement statement = dbConnection.getStatement();
		HairSalonInfo salonInfo = new HairSalonInfo();
		
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			while(rs.next()){
				salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				retSalonId = salonInfo.getHairSalonId();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retSalonId;
	}
	

	public int getMsterSalonId(DBConnection dbConnection, int userId){
		int retSalonId = -1;
		String sql = "SELECT `t_user_MasterSalonId` FROM `t_user` WHERE `t_user_Id` = " + userId;

		Statement statement = dbConnection.getStatement();
		UserInfo userInfo = null;

		//debug
		System.out.println("Master-SalonId for Master-userId : " + sql);
		
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setUserMasterSalonId(rs.getInt("t_user_MasterSalonId"));
				retSalonId = rs.getInt("t_user_MasterSalonId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retSalonId;
	}

	
	public boolean setUserAcount(DBConnection dbConnection, String tel , String pw){

		String sql = "INSERT INTO t_user(t_user_tel,t_user_passward) values(";
		
		Statement statement = dbConnection.getStatement();
		boolean result = false;
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql + "'"+ tel + "'" + "," + "'" + pw + "'" + ")");
			while(rs.next()){
				result = true;
				
			}
		} catch (SQLException e) {
			result = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}