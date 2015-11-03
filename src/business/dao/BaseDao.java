package business.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.constant.Constant;
import common.model.AreaInfo;
import common.model.BeautyNewsInfo;
import common.model.ClaimInfo;
import common.model.CommentInfo;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.ConditionTypeInfo;
import common.model.CountryInfo;
import common.model.CouponInfo;
import common.model.CouponKindInfo;
import common.model.EvaluationInfo;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.model.HairTypeInfo;
import common.model.IBaseInfo;
import common.model.MenuCategoryInfo;
import common.model.MenuInfo;
import common.model.ReservationInfo;
import common.model.ReviewInfo;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class BaseDao {
	private static Logger logger = LogManager.getLogger();

	public String getStringValue(DBConnection dbConnection , String tableName , String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(Constant.SPACE);
		sql.append(targetColumnName);
		sql.append(Constant.SPACE);
		sql.append("FROM");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(sourceColumnValue);
		sql.append(Constant.SINGLEQ);
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				return rs.getString(targetColumnName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	public int getIntValue(DBConnection dbConnection , String tableName , String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(Constant.SPACE);
		sql.append(targetColumnName);
		sql.append(Constant.SPACE);
		sql.append("FROM");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(sourceColumnValue);
		sql.append(Constant.SINGLEQ);
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				return rs.getInt(targetColumnName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return Integer.MIN_VALUE;
	}
	
	public String getStringValue(DBConnection dbConnection , String tableName , String targetColumnName, String sourceColumnId, IBaseInfo info) throws SQLException{

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(Constant.SPACE);
		sql.append(targetColumnName);
		sql.append(Constant.SPACE);
		sql.append("FROM");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnId);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(info.getObjectId());
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				return rs.getString(targetColumnName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	public int getIntValue(DBConnection dbConnection , String tableName , String targetColumnName, String sourceColumnId, IBaseInfo info) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(Constant.SPACE);
		sql.append(targetColumnName);
		sql.append(Constant.SPACE);
		sql.append("FROM");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnId);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(info.getObjectId());
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		try {			
			logger.debug(sql.toString());
			rs = statement.executeQuery(sql.toString());
			
			while(rs.next()){
				return rs.getInt(targetColumnName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return Integer.MIN_VALUE;
	}

	public Date getDateValue(DBConnection dbConnection , String tableName , String targetColumnName, String sourceColumnId,IBaseInfo info) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(Constant.SPACE);
		sql.append(targetColumnName);
		sql.append(Constant.SPACE);
		sql.append("FROM");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnId);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(info.getObjectId());
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				return rs.getDate(targetColumnName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	public int setStringValue(DBConnection dbConnection , String tableName , String targetColumnName,String value , String sourceColumnId, IBaseInfo info) throws SQLException{
		
		int result = -1;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(ConfigUtil.getConfig("dbname"));
		sql.append(Constant.BACKQ);
		sql.append(Constant.DOT);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("SET");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(targetColumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(value);
		sql.append(Constant.SINGLEQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.DOT);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnId);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(info.getObjectId());
		
		Statement statement = dbConnection.getStatement();
		try {			
			result = statement.executeUpdate(sql.toString());
			logger.debug(sql.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public int setIntValue(DBConnection dbConnection , String tableName , String targetColumnName, int value, String sourceColumnId,IBaseInfo info) throws SQLException{
		
		int result = -1;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(ConfigUtil.getConfig("dbname"));
		sql.append(Constant.BACKQ);
		sql.append(Constant.DOT);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("SET");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(targetColumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(value);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.DOT);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnId);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(info.getObjectId());
		
		Statement statement = dbConnection.getStatement();
		try {			
			result = statement.executeUpdate(sql.toString());
			logger.debug(sql.toString());
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int setDateValue(DBConnection dbConnection , String tableName , String targetColumnName, String sourceColumnId,IBaseInfo info,Date value) throws SQLException{
		
		int result = -1;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(ConfigUtil.getConfig("dbname"));
		sql.append(Constant.BACKQ);
		sql.append(Constant.DOT);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("SET");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(targetColumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(value);
		sql.append(Constant.SINGLEQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.DOT);
		sql.append(Constant.BACKQ);
		sql.append(sourceColumnId);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(info.getObjectId());
		
		Statement statement = dbConnection.getStatement();
		try {			
			result = statement.executeUpdate(sql.toString());
			logger.debug(sql.toString());
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	public IBaseInfo createObject(DBConnection dbConnection,java.sql.Connection conn , String tableName) throws SQLException{
		StringBuilder sql_insert = new StringBuilder();
		sql_insert.append("INSERT");
		sql_insert.append(Constant.SPACE);
		sql_insert.append("INTO");
		sql_insert.append(Constant.SPACE);
		sql_insert.append(Constant.BACKQ);
		sql_insert.append(ConfigUtil.getConfig("dbname"));
		sql_insert.append(Constant.BACKQ);
		sql_insert.append(Constant.DOT);
		sql_insert.append(Constant.BACKQ);
		sql_insert.append(tableName);
		sql_insert.append(Constant.BACKQ);
		sql_insert.append(Constant.SPACE);
		sql_insert.append("()");
		sql_insert.append(Constant.SPACE);
		sql_insert.append("VALUES");
		sql_insert.append(Constant.SPACE);
		sql_insert.append("()");
		sql_insert.append(Constant.SEMICOLON);
		 
		int objectId = -1;
		IBaseInfo info = getInfo(tableName);
		ResultSet rs = null;
		PreparedStatement statement = null;
		try {	
			logger.debug(sql_insert.toString());
			statement = conn.prepareStatement(sql_insert.toString(),java.sql.Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			//auto-incrementの値取得
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				objectId = rs.getInt(1);
		    }
			info.setObjectId(objectId);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return info;
	}
	
	public IBaseInfo getObject(DBConnection dbConnection, String tableName, String columnId, String columnName , int id) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(tableName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("WHERE");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(columnId);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(id);
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				IBaseInfo info = getInfo(tableName);
				info.setObjectId(rs.getInt(columnId));
				info.setName(rs.getString(columnName));
				return info;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	//TODO infoを増やした場合はgetInfoに追加
	private IBaseInfo getInfo(String tableName){
		IBaseInfo info = null;
		if(Constant.TABLE_AREA.equals(tableName)){
			info = new AreaInfo();
		}
		else if(Constant.TABLE_BEUTY_NEWS.equals(tableName)){
			info = new BeautyNewsInfo();
		}
		else if(Constant.TABLE_CLAIM.equals(tableName)){
			info = new ClaimInfo();
		}
		else if(Constant.TABLE_COMMENT.equals(tableName)){
			info = new CommentInfo();
		}
		else if(Constant.TABLE_SEARCH_CONDITION.equals(tableName)){
			info = new ConditionInfo();
		}
		else if(Constant.TABLE_SEARCH_CONDITION_TITLE.equals(tableName)){
			info = new ConditionTitleInfo();
		}
		else if(Constant.TABLE_SEARCH_CONDITION_TYPE.equals(tableName)){
			info = new ConditionTypeInfo();
		}
		else if(Constant.TABLE_COUNTRY.equals(tableName)){
			info = new CountryInfo();
		}
		else if(Constant.TABLE_COUPON.equals(tableName)){
			info = new CouponInfo();
		}
		else if(Constant.TABLE_COUPON_KIND.equals(tableName)){
			info = new CouponKindInfo();
		}
		else if(Constant.TABLE_EVALUATION.equals(tableName)){
			info = new EvaluationInfo();
		}
		else if(Constant.TABLE_SALON.equals(tableName)){
			info = new HairSalonInfo();
		}
		else if(Constant.TABLE_HAIR_STYLE.equals(tableName)){
			info = new HairStyleInfo();
		}
		else if(Constant.TABLE_HAIR_TYPE.equals(tableName)){
			info = new HairTypeInfo();
		}
		else if(Constant.TABLE_MENU_CATEGORY.equals(tableName)){
			info = new MenuCategoryInfo();
		}
		else if(Constant.TABLE_MENU.equals(tableName)){
			info = new MenuInfo();
		}
		else if(Constant.TABLE_RESERVATION.equals(tableName)){
			info = new ReservationInfo();
		}
		else if(Constant.TABLE_REVIEW.equals(tableName)){
			info = new ReviewInfo();
		}
		else if(Constant.TABLE_STYLIST.equals(tableName)){
			info = new StylistInfo();
		}
		else if(Constant.TABLE_USER.equals(tableName)){
			info = new UserInfo();
		}
		
		return info;
	}

}
