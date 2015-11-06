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

import common._model.TMasterSearchConditionTitleInfo;
import common.util.DBConnection;

public abstract class TMasterSearchConditionTitleDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasterSearchConditionTitleInfo createTMasterSearchConditionTitleInfo(ResultSet rs) throws SQLException {
		
		TMasterSearchConditionTitleInfo info = new TMasterSearchConditionTitleInfo();
		info.setTMasterSearchConditionTitleId(rs.getInt("t_masterSearchConditionTitle_id"));
		info.setTMasterSearchConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
		info.setTMasterSearchConditionTitleTypeId(rs.getInt("t_masterSearchConditionTitle_typeId"));
		return info;
		
	}
	
	public TMasterSearchConditionTitleInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterSearchConditionTitleInfo> list = getByColumn(dbConnection, "t_masterSearchConditionTitle_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasterSearchConditionTitleInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasterSearchConditionTitleInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<TMasterSearchConditionTitleInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_masterSearchConditionTitle` ";
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
		
		List<TMasterSearchConditionTitleInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterSearchConditionTitleInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_masterSearchConditionTitle_id`) count from `t_masterSearchConditionTitle` ";
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
	
	public int save(DBConnection dbConnection, TMasterSearchConditionTitleInfo info) throws SQLException {
		
		String sql = "insert into `t_masterSearchConditionTitle` "
			+ " ( "
			+ " `t_masterSearchConditionTitle_name`, "
			+ " `t_masterSearchConditionTitle_typeId` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTMasterSearchConditionTitleName());
		preparedStatement.setObject(2, info.getTMasterSearchConditionTitleTypeId());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMasterSearchConditionTitleInfo info) throws SQLException {
		
		String sql = "update `t_masterSearchConditionTitle` set "

			+ " `t_masterSearchConditionTitle_name` = ?, "
			+ " `t_masterSearchConditionTitle_typeId` = ? "
			+ " where "
			+ " `t_masterSearchConditionTitle_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTMasterSearchConditionTitleName());
		preparedStatement.setObject(2, info.getTMasterSearchConditionTitleTypeId());

		preparedStatement.setObject(3, info.getTMasterSearchConditionTitleId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterSearchConditionTitle` set "
			+ " where "
			+ " `t_masterSearchConditionTitle_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
