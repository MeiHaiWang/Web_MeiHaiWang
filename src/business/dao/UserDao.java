package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business._dao.TUserDao;
import common._model.TUserInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.IBaseInfo;
import common.model.UserInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class UserDao extends TUserDao{
	private static Logger logger = LogManager.getLogger();
	/*
	public int getUserIntData(DBConnection dbConnection ,String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{
		return getIntValue(dbConnection, Constant.TABLE_USER, targetColumnName, sourceColumnName, sourceColumnValue );
	}
	public String getUserStringData(DBConnection dbConnection , String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{
		return getStringValue(dbConnection, Constant.TABLE_USER, targetColumnName, sourceColumnName, sourceColumnValue);
	}
	public int getUserIntData(DBConnection dbConnection, String columnName, IBaseInfo info) throws SQLException{
		return getIntValue(dbConnection, Constant.TABLE_USER, columnName, TableConstant.COLUMN_USER_ID, info);
	}
	public int setUserIntData(DBConnection dbConnection , String columnName, int targetColumValue, IBaseInfo info) throws SQLException{
		return setIntValue(dbConnection, Constant.TABLE_USER, columnName,  targetColumValue, TableConstant.COLUMN_USER_ID, info);
	}
	public String getUserStringData(DBConnection dbConnection , String columnName,  IBaseInfo info) throws SQLException{
		return getStringValue(dbConnection, Constant.TABLE_USER, columnName, TableConstant.COLUMN_USER_ID, info);
	}
	public int setUserStringData(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		return setStringValue(dbConnection, Constant.TABLE_USER, targetColumnName, value, TableConstant.COLUMN_USER_ID, info);
	}
	*/
	
	public int appendId(DBConnection dbConnection ,String targetColumnName, String value, int id) throws SQLException{
		int result = -1;
		String idList = null;
		TUserInfo userInfo = get(dbConnection, id);
		String idlist = null;
		switch(targetColumnName){
		case TableConstant.COLUMN_USER_FAVORITE_SALON:
			idlist = userInfo.getTUserFavoriteSalonId();
			if(idlist!=null){
				idlist = idlist + ","+ value;
				userInfo.setTUserFavoriteSalonId(idlist);
				result = update(dbConnection, userInfo);
			}else{
				userInfo.setTUserFavoriteSalonId(value);
				result = update(dbConnection, userInfo);
			}
			break;
		case TableConstant.COLUMN_USER_FAVORITE_HAIR_STYLE:
			idlist = userInfo.getTUserFavoriteHairStyleId();
			if(idlist!=null){
				idlist = idlist + ","+ value;
				userInfo.setTUserFavoriteHairStyleId(idlist);
				result = update(dbConnection, userInfo);
			}else{
				userInfo.setTUserFavoriteHairStyleId(value);
				result = update(dbConnection, userInfo);
			}
			break;
		case TableConstant.COLUMN_USER_FAVORITE_STYLIST:
			idlist = userInfo.getTUserFavoriteStylistId();
			if(idlist!=null){
				idlist = idlist + ","+ value;
				userInfo.setTUserFavoriteStylistId(idlist);
				result = update(dbConnection, userInfo);
			}else{
				userInfo.setTUserFavoriteStylistId(value);
				result = update(dbConnection, userInfo);
			}
			break;
		default:
			break;
		}
		return result;
	}
	
	/*
	public int removeId(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		int result = -1;
		String idlist = getUserStringData(dbConnection, targetColumnName, info);
		if(idlist!=null){
			List<String> idList = Arrays.asList(idlist.split(","));
			String retIdlist = "";
			int index=0;
			for(String id: idList){
				if(id.equals(value)) continue;
				if(index==0){
					retIdlist += id;
					index++;
				}else{
					retIdlist += ","+id;
					index++;
				}
			}
			result = setUserStringData(dbConnection, targetColumnName, retIdlist, info);
		}
		return result;
	}
	
	/*
	public UserInfo getUserObject(DBConnection dbConnection, int id) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_USER);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_USER_ID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(id);
		
		Statement statement = dbConnection.getStatement();
		UserInfo userInfo = null;

		try {			
			ResultSet rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setObjectId(rs.getInt("t_user_id"));
				userInfo.setName(rs.getString("t_user_name"));
				userInfo.setUserSex(rs.getInt("t_user_sex"));
				userInfo.setUserPhoneNumber(rs.getString("t_user_tel"));
				userInfo.setUserBirth(rs.getDate("t_user_birth"));
				userInfo.setUserPoint(rs.getInt("t_user_point"));
				userInfo.setUserImagePath(rs.getString("t_user_imagePath"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfo;
	}
	
	public UserInfo getUserObjectByColumn(DBConnection dbConnection, String culumnName , String value) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_USER);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(culumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(value);
		sql.append(Constant.SINGLEQ);
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		UserInfo userInfo = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setObjectId(rs.getInt("t_user_id"));
				userInfo.setName(rs.getString("t_user_name"));
				userInfo.setUserSex(rs.getInt("t_user_sex"));
				userInfo.setUserPhoneNumber(rs.getString("t_user_tel"));
				userInfo.setUserBirth(rs.getDate("t_user_birth"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfo;
	}
	public List<UserInfo> getUserObjectListByColumn(DBConnection dbConnection, String culumnName , String value) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_USER);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(culumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(value);
		sql.append(Constant.SINGLEQ);
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				UserInfo userInfo = new UserInfo();
				userInfo.setObjectId(rs.getInt("t_user_id"));
				userInfo.setName(rs.getString("t_user_name"));
				userInfo.setUserSex(rs.getInt("t_user_sex"));
				userInfo.setUserPhoneNumber(rs.getString("t_user_tel"));
				userInfo.setUserBirth(rs.getDate("t_user_birth"));
				userInfoList.add(userInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfoList;
	}
	
	public UserInfo getUserObjectByColumnMap(DBConnection dbConnection, Map<String, String> sourceData) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_USER);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		int index = 0;
		for(Map.Entry<String, String> entry : sourceData.entrySet()) {
			//System.out.println(entry.getKey() + " => " + entry.getValue());
			if(index==0){
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}else{
				sql.append(Constant.SPACE);
				sql.append(Constant.AND);
				sql.append(Constant.SPACE);
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}
		}

		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		UserInfo userInfo = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setObjectId(rs.getInt("t_user_id"));
				userInfo.setName(rs.getString("t_user_name"));
				userInfo.setUserSex(rs.getInt("t_user_sex"));
				userInfo.setUserPhoneNumber(rs.getString("t_user_tel"));
				userInfo.setUserBirth(rs.getDate("t_user_birth"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfo;
	}
	
	public List<UserInfo> getUserObjectListByColumnMap(DBConnection dbConnection, Map<String, String> sourceData) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_USER);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		int index = 0;
		for(Map.Entry<String, String> entry : sourceData.entrySet()) {
			//System.out.println(entry.getKey() + " => " + entry.getValue());
			if(index==0){
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}else{
				sql.append(Constant.SPACE);
				sql.append(Constant.AND);
				sql.append(Constant.SPACE);
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}
		}

		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				UserInfo userInfo = new UserInfo();
				userInfo.setObjectId(rs.getInt("t_user_id"));
				userInfo.setName(rs.getString("t_user_name"));
				userInfo.setUserSex(rs.getInt("t_user_sex"));
				userInfo.setUserPhoneNumber(rs.getString("t_user_tel"));
				userInfo.setUserBirth(rs.getDate("t_user_birth"));
				userInfoList.add(userInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return userInfoList;
	}
	
	public int setUserInfoInsert(DBConnection dbConnection, UserInfo userInfo){

		//DBステートメント
		Statement statement = dbConnection.getStatement();
		//返り値id
		int retId = -1;

		//Insertする情報をMapで整理
		Map<String, String> source = new HashMap<String, String>();
		source.put(TableConstant.COLUMN_USER_TEL, userInfo.getUserPhoneNumber());
		source.put(TableConstant.COLUMN_USER_MAIL, userInfo.getUserMail());
		source.put(TableConstant.COLUMN_USER_PASSWORD, userInfo.getUserPass());
		source.put(TableConstant.COLUMN_USER_HASH, "");
		source.put(TableConstant.COLUMN_USER_IMAGE, userInfo.getUserImagePath());
		source.put(TableConstant.COLUMN_USER_SEX, Integer.toString(userInfo.getUserSex()));
		source.put(TableConstant.COLUMN_USER_BIRTH, "");
		source.put(TableConstant.COLUMN_USER_NAME, userInfo.getName());
		source.put(TableConstant.COLUMN_USER_FAVORITE_SALON, "");
		source.put(TableConstant.COLUMN_USER_FAVORITE_STYLIST, "");
		source.put(TableConstant.COLUMN_USER_FAVORITE_HAIR_STYLE, "");
		source.put(TableConstant.COLUMN_USER_LATEST_SALON, "");
		source.put(TableConstant.COLUMN_USER_LATEST_STYLIST, "");
		source.put(TableConstant.COLUMN_USER_LATEST_HAIRSTYLE, "");
		source.put(TableConstant.COLUMN_USER_POINT, "0");
		source.put(TableConstant.COLUMN_USER_HISTORY_SALON, "0");
		source.put(TableConstant.COLUMN_USER_MASTER_SALON, "0");

		//DATETIME -> string 処理
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(userInfo.getUserBirth());
		source.replace(TableConstant.COLUMN_USER_BIRTH, birth);
		
		//Insert SQL 
		StringBuilder insertSql = new StringBuilder();
		insertSql.append(Constant.INSERT);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.BACKQ);
		insertSql.append(ConfigUtil.getConfig("dbname"));
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.DOT);
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.TABLE_USER);
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.BRACKET_1);
		int index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()) {
			if(index == 0){
				insertSql.append(Constant.BACKQ);
				insertSql.append(entry.getKey());
				insertSql.append(Constant.BACKQ);
				index++;
			}else{
				insertSql.append(Constant.COMMA);
				insertSql.append(Constant.SPACE);
				insertSql.append(Constant.BACKQ);
				insertSql.append(entry.getKey());
				insertSql.append(Constant.BACKQ);
				index++;
			}
		}
		insertSql.append(Constant.BRACKET_2);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.VALUES);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.BRACKET_1);
		index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()) {
			if(index == 0){
				insertSql.append(Constant.SINGLEQ);
				insertSql.append(entry.getValue());
				insertSql.append(Constant.SINGLEQ);
				index++;
			}else{
				insertSql.append(Constant.COMMA);
				insertSql.append(Constant.SPACE);
				insertSql.append(Constant.SINGLEQ);
				insertSql.append(entry.getValue());
				insertSql.append(Constant.SINGLEQ);
				index++;
			}
		}
		insertSql.append(Constant.BRACKET_2);
		insertSql.append(Constant.SEMICOLON);

		//sql成功確認
		int result_int = -1;

		try {
			//debug
			logger.info("{}",insertSql.toString());
			result_int = statement.executeUpdate(insertSql.toString(), Statement.RETURN_GENERATED_KEYS);
			if(result_int > 0){
				//更新したidをget
				ResultSet rs = statement.getGeneratedKeys();
		        if (rs.next()){
		        	retId = rs.getInt(1);
	        	}
		        rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return retId;
	}
	
	public int setUserInfoUpdate(DBConnection dbConnection, UserInfo userInfo){

		//DBステートメント
		Statement statement = dbConnection.getStatement();
		//返り値id
		int retId = -1;

		//Insertする情報をMapで整理
		Map<String, String> source = new HashMap<String, String>();
		source.put(TableConstant.COLUMN_USER_TEL, userInfo.getUserPhoneNumber());
		source.put(TableConstant.COLUMN_USER_MAIL, userInfo.getUserMail());
		source.put(TableConstant.COLUMN_USER_PASSWORD, userInfo.getUserPass());
		source.put(TableConstant.COLUMN_USER_HASH, "");
		source.put(TableConstant.COLUMN_USER_IMAGE, userInfo.getUserImagePath());
		source.put(TableConstant.COLUMN_USER_SEX, Integer.toString(userInfo.getUserSex()));
		source.put(TableConstant.COLUMN_USER_BIRTH, ""); 
		source.put(TableConstant.COLUMN_USER_NAME, userInfo.getName());
		source.put(TableConstant.COLUMN_USER_FAVORITE_SALON, "");
		source.put(TableConstant.COLUMN_USER_FAVORITE_STYLIST, "");
		source.put(TableConstant.COLUMN_USER_FAVORITE_HAIR_STYLE, "");
		source.put(TableConstant.COLUMN_USER_LATEST_SALON, "");
		source.put(TableConstant.COLUMN_USER_LATEST_STYLIST, "");
		source.put(TableConstant.COLUMN_USER_LATEST_HAIRSTYLE, "");
		source.put(TableConstant.COLUMN_USER_POINT, "0");
		source.put(TableConstant.COLUMN_USER_HISTORY_SALON, "0");
		source.put(TableConstant.COLUMN_USER_MASTER_SALON, "0");
		
		//DATETIME -> string 処理
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(userInfo.getUserBirth());
		source.replace(TableConstant.COLUMN_USER_BIRTH, birth);

		//Update SQL 
		StringBuilder updateSql = new StringBuilder();
		updateSql.append(Constant.UPDATE);
		updateSql.append(Constant.BACKQ);
		updateSql.append(ConfigUtil.getConfig("dbname"));
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.DOT);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.TABLE_USER);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.SET);
		updateSql.append(Constant.SPACE);
		
		int index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()){
			if(index==0){ 
				updateSql.append(Constant.BACKQ);
				updateSql.append(entry.getKey());
				updateSql.append(Constant.BACKQ);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.EQUAL);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.SINGLEQ);
				updateSql.append(entry.getValue());
				updateSql.append(Constant.SINGLEQ);
			}else{
				updateSql.append(Constant.COMMA);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.BACKQ);
				updateSql.append(entry.getKey());
				updateSql.append(Constant.BACKQ);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.EQUAL);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.SINGLEQ);
				updateSql.append(entry.getValue());
				updateSql.append(Constant.SINGLEQ);
			}
		}
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.WHERE);
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.TABLE_USER);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.DOT);
		updateSql.append(Constant.BACKQ);
		updateSql.append(TableConstant.COLUMN_USER_ID);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.EQUAL);
		updateSql.append(Constant.SPACE);
		updateSql.append(userInfo.getObjectId());
		updateSql.append(Constant.SEMICOLON);
		
		//sql成功確認
		int result_int = -1;

		try {
			//debug
			logger.info("{}",updateSql.toString());
			result_int = statement.executeUpdate(updateSql.toString(), Statement.RETURN_GENERATED_KEYS);
			if(result_int > 0){
				//更新したidをget
				ResultSet rs = statement.getGeneratedKeys();
		        if (rs.next()){
		        	retId = rs.getInt(1);
	        	}
		        rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return retId;
	}
		*/

	
	
	//public int setUserAcount(DBConnection dbConnection, UserInfo userInfo){
		
		/*
		 * user登録を自動で行う userIdを返す
		 * 
		 * INSERT INTO `MEIHAIWAN_TEST`.`t_user` (`t_user_Id`, `t_user_disableFlag`, `t_user_mail`, `t_user_passward`, `t_user_cookie`, `t_user_imagePath`, `t_user_sex`, `t_user_birth`, `t_user_name`, `t_user_favoriteSalonId`, `t_user_favoriteStylistId`, `t_user_latestViewSalonId`, `t_user_latestViewStylistId`, `t_user_favoriteHairStyleId`, `t_user_latestViewHairStyleId`, `t_user_point`, `t_user_historySalonId`, `t_user_MasterSalonId`) VALUES ('1', '0', 'mail', '0000', NULL, NULL, '0', NULL, 'name', NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, '1');
		 * UPDATE `MEIHAIWAN_TEST`.`t_user` SET `t_user_mail` = '111aaa', `t_user_passward` = '0000aaa' WHERE `t_user`.`t_user_Id` = 24;
		 * 
		 */
		/*
		String u_sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_user` ("
				+ "`t_user_disableFlag`, `t_user_tel`, "
				+ "`t_user_mail`, `t_user_passward`, `t_user_cookie`, `t_user_imagePath`, `t_user_sex`, `t_user_birth`, "
				+ "`t_user_name`, `t_user_favoriteSalonId`, `t_user_favoriteStylistId`, `t_user_latestViewSalonId`, "
				+ "`t_user_latestViewStylistId`, `t_user_favoriteHairStyleId`, `t_user_latestViewHairStyleId`, `t_user_point`, "
				+ "`t_user_historySalonId`, `t_user_MasterSalonId`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "";
		String sql4 = "0";
		String sql_end = "');";
		*/
		/*
		//TODO 初期パスワードはとりあえず0000
		String u_sql = u_sql1
				+ sql4 + sql2
				+ userInfo.getUserPhoneNumber() + sql2
				+ userInfo.getUserMail() + sql2
				+ userInfo.getUserPass() + sql2
				+ sql3 + sql2 //cookie
				+ userInfo.getUserImagePath()  + sql2
				+ userInfo.getUserSex()  + sql2
				+ birth + sql2
				+ userInfo.getName() + sql2
				+ sql3 + sql2 //favoriteSalon
				+ sql3 + sql2 //favoriteStylist
				+ sql3 + sql2 //latestviewsalon
				+ sql3 + sql2 //latestviewstylist
				+ sql3 + sql2 //favoritehairstyle
				+ sql3 + sql2 //latestviewhairstyle
				+ sql4 + sql2 //point
				+ sql4 + sql2 //historysalon
				+ sql4 //salonId
				+ sql_end;
		 */
		/*
		//update
		String sql_update = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_user` SET "
				+ "`t_user_name` = '" + userInfo.getUserName() + "', "
				+ "`t_user_sex` = '" + userInfo.getUserSex() + "', "
				+ "`t_user_imagePath` = '" + userInfo.getUserImagePath() + "', "
				+ "`t_user_tel` = '" + userInfo.getUserPhoneNumber() + "', "
				+ "`t_user_mail` = '" +  userInfo.getUserMail()  + "', "
				+ "`t_user_birth` = '" +  birth  + "'"
				+ " WHERE `t_user`.`t_user_Id` = " + userInfo.getUserId();
				*/

		//tel -> uidを取得
		//String uid_sql = "SELECT `t_user_id` FROM `t_user` WHERE `t_user_tel` = "; 
		/*
		try {			
			//debug
			System.out.println(uid_sql+"'"+userInfo.getUserPhoneNumber()+"'");
			ResultSet rs = statement.executeQuery(uid_sql+"'"+userInfo.getUserPhoneNumber()+"'");
			while(rs.next()){
				u_Id = rs.getInt("t_user_Id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
			
		*/
		/*
		//DBステートメント
		Statement statement = dbConnection.getStatement();
		//返り値id
		int retId = -1;

		//Insertする情報をMapで整理
		Map<String, String> source = new HashMap<String, String>();
		source.put(TableConstant.COLUMN_USER_TEL, userInfo.getUserPhoneNumber());
		source.put(TableConstant.COLUMN_USER_MAIL, userInfo.getUserMail());
		source.put(TableConstant.COLUMN_USER_PASSWORD, userInfo.getUserPass());
		source.put(TableConstant.COLUMN_USER_HASH, "");
		source.put(TableConstant.COLUMN_USER_IMAGE, userInfo.getUserImagePath());
		source.put(TableConstant.COLUMN_USER_SEX, Integer.toString(userInfo.getUserSex()));
		source.put(TableConstant.COLUMN_USER_BIRTH, ""); 
		source.put(TableConstant.COLUMN_USER_NAME, userInfo.getName());
		source.put(TableConstant.COLUMN_USER_FAVORITE_SALON, "");
		source.put(TableConstant.COLUMN_USER_FAVORITE_STYLIST, "");
		source.put(TableConstant.COLUMN_USER_FAVORITE_HAIR_STYLE, "");
		source.put(TableConstant.COLUMN_USER_LATEST_SALON, "");
		source.put(TableConstant.COLUMN_USER_LATEST_STYLIST, "");
		source.put(TableConstant.COLUMN_USER_LATEST_HAIRSTYLE, "");
		source.put(TableConstant.COLUMN_USER_POINT, "0");
		source.put(TableConstant.COLUMN_USER_HISTORY_SALON, "0");
		source.put(TableConstant.COLUMN_USER_MASTER_SALON, "0");

		//DATETIME -> string 処理
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(userInfo.getUserBirth());
		source.replace(TableConstant.COLUMN_USER_BIRTH, birth);
		
		//Insert SQL 
		StringBuilder insertSql = new StringBuilder();
		insertSql.append(Constant.INSERT);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.BACKQ);
		insertSql.append(ConfigUtil.getConfig("dbname"));
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.DOT);
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.TABLE_USER);
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.BRACKET_1);
		int index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()) {
			if(index == 0){
				insertSql.append(Constant.BACKQ);
				insertSql.append(entry.getKey());
				insertSql.append(Constant.BACKQ);
				index++;
			}else{
				insertSql.append(Constant.COMMA);
				insertSql.append(Constant.SPACE);
				insertSql.append(Constant.BACKQ);
				insertSql.append(entry.getKey());
				insertSql.append(Constant.BACKQ);
				index++;
			}
		}
		insertSql.append(Constant.BRACKET_2);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.VALUES);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.BRACKET_1);
		index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()) {
			if(index == 0){
				insertSql.append(Constant.SINGLEQ);
				insertSql.append(entry.getValue());
				insertSql.append(Constant.SINGLEQ);
				index++;
			}else{
				insertSql.append(Constant.COMMA);
				insertSql.append(Constant.SPACE);
				insertSql.append(Constant.SINGLEQ);
				insertSql.append(entry.getValue());
				insertSql.append(Constant.SINGLEQ);
				index++;
			}
		}
		insertSql.append(Constant.BRACKET_2);
		insertSql.append(Constant.SEMICOLON);

		//Update SQL 
		StringBuilder updateSql = new StringBuilder();
		updateSql.append(Constant.UPDATE);
		updateSql.append(Constant.BACKQ);
		updateSql.append(ConfigUtil.getConfig("dbname"));
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.DOT);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.TABLE_USER);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.SET);
		updateSql.append(Constant.SPACE);
		index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()){
			if(index==0){ 
				updateSql.append(Constant.BACKQ);
				updateSql.append(entry.getKey());
				updateSql.append(Constant.BACKQ);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.EQUAL);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.SINGLEQ);
				updateSql.append(entry.getValue());
				updateSql.append(Constant.SINGLEQ);
			}else{
				updateSql.append(Constant.COMMA);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.BACKQ);
				updateSql.append(entry.getKey());
				updateSql.append(Constant.BACKQ);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.EQUAL);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.SINGLEQ);
				updateSql.append(entry.getValue());
				updateSql.append(Constant.SINGLEQ);
			}
		}
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.WHERE);
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.TABLE_USER);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.DOT);
		updateSql.append(Constant.BACKQ);
		updateSql.append(TableConstant.COLUMN_USER_ID);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.EQUAL);
		updateSql.append(Constant.SPACE);
		updateSql.append(userInfo.getObjectId());
		updateSql.append(Constant.SEMICOLON);
		
		//sql成功確認
		int result_int = -1;

		try {
			if(userInfo.getObjectId()<0){
				//新規登録
				//debug
				System.out.println(insertSql.toString());
				result_int = statement.executeUpdate(insertSql.toString());
				if(result_int < 0) return -1;
			}else{
				//登録変更
				//debug
				System.out.println(updateSql.toString());
				result_int = statement.executeUpdate(updateSql.toString());
				if(result_int < 0) return -1;
			}
			
			//UserIdを取得して返却
			if(result_int > 0){
				retId = getUserIntData(dbConnection, TableConstant.COLUMN_USER_ID, TableConstant.COLUMN_USER_TEL, userInfo.getUserPhoneNumber()); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return retId;
	}
	*/

	/*
	public List<UserInfo> getReviewerUserInfo(DBConnection dbConnection, List<ReviewInfo> reviewInfoList) throws SQLException{
		List<UserInfo> UserInfoList = new ArrayList<UserInfo>();
		String sql = "SELECT `t_user_name`, `t_user_sex`, `t_user_birth` FROM t_user WHERE t_user_id=";
		Statement statement = dbConnection.getStatement();
		
		try {			
			for(ReviewInfo reviewInfo : reviewInfoList){
				//debug
				System.out.println(sql+reviewInfo.getReviewUserId());
				ResultSet rs = statement.executeQuery(sql+reviewInfo.getReviewUserId());
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

	public UserInfo getUserInfo(DBConnection dbConnection, int userId) throws SQLException{
		UserInfo userInfo = new UserInfo();
		String sql = "SELECT * FROM `t_user` WHERE t_user_Id = ";		
		Statement statement = dbConnection.getStatement();
		try {			
			ResultSet rs = statement.executeQuery(sql+userId);
			while(rs.next()){
				userInfo.setUserId(rs.getInt("t_user_id"));
				userInfo.setUserName(rs.getString("t_user_name"));
				userInfo.setUserSex(rs.getInt("t_user_sex"));
				userInfo.setUserPhoneNumber(rs.getString("t_user_tel"));
				userInfo.setUserBirth(rs.getDate("t_user_birth"));
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
		String sql = "SELECT `t_user_Id` FROM `t_user` WHERE `t_user_tel` =" + "'"+tel+"'" + " AND `t_user_passward` =" + "'"+pw+"'";		
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
		*/


	
	/*
	public UserInfo getUserInfoByTel(DBConnection dbConnection, String tel) throws SQLException { 
		UserInfo userInfo = null;
		String sql = "SELECT `t_user_Id` FROM `t_user` WHERE `t_user_tel` = '" + tel +"'";		
		Statement statement = dbConnection.getStatement();
		try {			
			//debug
			System.out.println(sql);
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
	*/
	/*
	public int setRegistUserInfo(DBConnection dbConnection, int salonId,
			UserInfo userInfo) {
		String u_sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_user` ("
				+ "`t_user_disableFlag`, `t_user_tel`, "
				+ "`t_user_mail`, `t_user_passward`, `t_user_cookie`, `t_user_imagePath`, `t_user_sex`, `t_user_birth`, "
				+ "`t_user_name`, `t_user_favoriteSalonId`, `t_user_favoriteStylistId`, `t_user_latestViewSalonId`, "
				+ "`t_user_latestViewStylistId`, `t_user_favoriteHairStyleId`, `t_user_latestViewHairStyleId`, `t_user_point`, "
				+ "`t_user_historySalonId`, `t_user_MasterSalonId`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "";
		String sql4 = "0";
		String sql_end = "');";
		String uid_sql = "SELECT `t_user_id` FROM `t_user` WHERE `t_user_tel` = "; 

		Statement statement = dbConnection.getStatement();
		int u_Id = -1;

		int age = userInfo.getUserAge();
      Calendar now = Calendar.getInstance(); 
      int y = now.get(now.YEAR);          //年を取得	
      int year_age = y-age;
      String birth = Integer.toString(year_age)+"-01-01 00:00:00";
      //debug
      System.out.println(birth);
      
      //TODO 初期パスワードはとりあえず0000
		String u_sql = u_sql1
				+ sql4 + sql2
				+ userInfo.getUserPhoneNumber() + sql2
				+ userInfo.getUserPhoneNumber() + sql2
				+ sql3 + sql2
				+ sql3 + sql2 //cookie
				+ sql3  + sql2
				+ userInfo.getUserSex()  + sql2
				+ birth + sql2
				+ userInfo.getName() + sql2
				+ sql3 + sql2 //favoriteSalon
				+ sql3 + sql2 //favoriteStylist
				+ sql3 + sql2 //latestviewsalon
				+ sql3 + sql2 //latestviewstylist
				+ sql3 + sql2 //favoritehairstyle
				+ sql3 + sql2 //latestviewhairstyle
				+ sql4 + sql2 //point
				+ sql4 + sql2 //historysalon
				+ sql4 //salonId
				+ sql_end;

		//debug
		System.out.println(u_sql);
		
		int result_int = -1;
		try {
			result_int = statement.executeUpdate(u_sql);
			if(result_int < 0) return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(result_int > 0){
			try {			
				//debug
				System.out.println(uid_sql);
				ResultSet rs = statement.executeQuery(uid_sql+userInfo.getUserPhoneNumber());
				while(rs.next()){
					u_Id = rs.getInt("t_user_Id");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}

		//debug
		// System.out.println("uid:"+u_Id);
		return u_Id;
	}
	*/

	/*
	public UserInfo getUserInfoByName(DBConnection dbConnection, String t_user_name) {
		UserInfo userInfo = null;
		String sql = "SELECT `t_user_Id` FROM `t_user` WHERE `t_user_name` =" + t_user_name;		
		Statement statement = dbConnection.getStatement();
		try {			
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("t_user_Id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;		
	}
	*/
	
}