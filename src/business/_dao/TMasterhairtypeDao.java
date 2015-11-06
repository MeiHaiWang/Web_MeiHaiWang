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

import common._model.TMasterHairTypeInfo;
import common.util.DBConnection;

public abstract class TMasterHairTypeDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasterHairTypeInfo createTMasterHairTypeInfo(ResultSet rs) throws SQLException {
		
		TMasterHairTypeInfo info = new TMasterHairTypeInfo();
		info.setTHairTypeId(rs.getInt("t_hairType_id"));
		info.setTHairTypeName(rs.getString("t_hairType_name"));
		info.setTHairTypeSex(rs.getString("t_hairType_sex"));
		info.setTHairTypeImagePath(rs.getString("t_hairType_ImagePath"));
		info.setTHairTypeSearchConditionId(rs.getInt("t_hairType_searchConditionId"));
		return info;
		
	}
	
	public TMasterHairTypeInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterHairTypeInfo> list = getByColumn(dbConnection, "t_hairType_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasterHairTypeInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasterHairTypeInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<TMasterHairTypeInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_masterHairType` ";
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
		
		List<TMasterHairTypeInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterHairTypeInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_hairType_id`) count from `t_masterHairType` ";
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
	
	public int save(DBConnection dbConnection, TMasterHairTypeInfo info) throws SQLException {
		
		String sql = "insert into `t_masterHairType` "
			+ " ( "
			+ " `t_hairType_name`, "
			+ " `t_hairType_sex`, "
			+ " `t_hairType_ImagePath`, "
			+ " `t_hairType_searchConditionId` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTHairTypeName());
		preparedStatement.setObject(2, info.getTHairTypeSex());
		preparedStatement.setObject(3, info.getTHairTypeImagePath());
		preparedStatement.setObject(4, info.getTHairTypeSearchConditionId());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMasterHairTypeInfo info) throws SQLException {
		
		String sql = "update `t_masterHairType` set "

			+ " `t_hairType_name` = ?, "
			+ " `t_hairType_sex` = ?, "
			+ " `t_hairType_ImagePath` = ?, "
			+ " `t_hairType_searchConditionId` = ? "
			+ " where "
			+ " `t_hairType_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTHairTypeName());
		preparedStatement.setObject(2, info.getTHairTypeSex());
		preparedStatement.setObject(3, info.getTHairTypeImagePath());
		preparedStatement.setObject(4, info.getTHairTypeSearchConditionId());

		preparedStatement.setObject(5, info.getTHairTypeId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterHairType` set "
			+ " where "
			+ " `t_hairType_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
