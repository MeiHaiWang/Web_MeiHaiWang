package business._dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.dao.BaseDao;

import common._model.TMasterSearchConditionInfo;
import common.util.DBConnection;

public abstract class TMasterSearchConditionDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasterSearchConditionInfo createTMasterSearchConditionInfo(ResultSet rs) throws SQLException {
		
		TMasterSearchConditionInfo info = new TMasterSearchConditionInfo();
		info.setTMasterSearchConditionId(rs.getInt("t_masterSearchCondition_id"));
		info.setTMasterSearchConditionName(rs.getString("t_masterSearchCondition_name"));
		info.setTMasterSearchConditionTitleId(rs.getInt("t_masterSearchCondition_titleId"));
		return info;
		
	}
	
	public TMasterSearchConditionInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterSearchConditionInfo> list = getByColumn(dbConnection, "t_masterSearchCondition_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasterSearchConditionInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasterSearchConditionInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<TMasterSearchConditionInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_masterSearchCondition` ";
		String where = " where ";

		for (String columnName : map.keySet()) {
			
			where += " `" + columnName + "` = ? AND ";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -4);
			sql += where;
		}
		
		String limit = " limit ";
		if (offset != null) {
		
			limit += offset + " , ";
		}
		
		if (count != null) {
			
			limit += count;
			sql += limit;
		}

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		for (Object value : map.values()) {
			int index = 1;
			preparedStatement.setObject(index, value);
			index++;
		}
		
		ResultSet rs = preparedStatement.executeQuery();
		logger.debug(sql.toString());
		
		List<TMasterSearchConditionInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterSearchConditionInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_masterSearchCondition_id`) count from `t_masterSearchCondition` ";
		String where = " where ";
		
		for (String columnName : map.keySet()) {
			
			where += " `" + columnName + "` = ? AND ";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -4);
			sql += where;
		}

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		for (Object value : map.values()) {
			int index = 1;
			preparedStatement.setObject(index, value);
			index++;
		}
		
		ResultSet rs = preparedStatement.executeQuery();
		logger.debug(sql.toString());
		
		while (rs.next()) {
			return rs.getInt("count");
		}
		return 0;
	}
	
	public int save(DBConnection dbConnection, TMasterSearchConditionInfo info) throws SQLException {
		
		String sql = "insert into `t_masterSearchCondition` "
			+ " ( "
			+ " `t_masterSearchCondition_name`, "
			+ " `t_masterSearchCondition_titleId` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTMasterSearchConditionName());
		preparedStatement.setObject(2, info.getTMasterSearchConditionTitleId());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMasterSearchConditionInfo info) throws SQLException {
		
		String sql = "update `t_masterSearchCondition` set "

			+ " `t_masterSearchCondition_name` = ?, "
			+ " `t_masterSearchCondition_titleId` = ? "
			+ " where "
			+ " `t_masterSearchCondition_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTMasterSearchConditionName());
		preparedStatement.setObject(2, info.getTMasterSearchConditionTitleId());

		preparedStatement.setObject(3, info.getTMasterSearchConditionId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterSearchCondition` set "
			+ " where "
			+ " `t_masterSearchCondition_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
