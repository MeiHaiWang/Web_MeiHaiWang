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

import common._model.TMastersearchconditiontypeInfo;
import common.util.DBConnection;

public abstract class TMastersearchconditiontypeDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMastersearchconditiontypeInfo createTMastersearchconditiontypeInfo(ResultSet rs) throws SQLException {
		
		TMastersearchconditiontypeInfo info = new TMastersearchconditiontypeInfo();
		info.setTMasterSearchConditionTypeId(rs.getInt("t_masterSearchConditionType_id"));
		info.setTMasterSearchConditionTypeName(rs.getString("t_masterSearchConditionType_name"));
		return info;
		
	}
	
	public TMastersearchconditiontypeInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMastersearchconditiontypeInfo> list = getByColumn(dbConnection, "t_masterSearchConditionType_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMastersearchconditiontypeInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMastersearchconditiontypeInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_user` ";
		String where = " where ";

		for (String columnName : map.keySet()) {
			
			where += "`" + columnName + "` = ? AND";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -3);
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
		
		List<TMastersearchconditiontypeInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMastersearchconditiontypeInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMastersearchconditiontypeInfo info) throws SQLException {
		
		String sql = "insert into `t_masterSearchConditionType` "
			+ " ( "
			+ " `t_masterSearchConditionType_name` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTMasterSearchConditionTypeName());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMastersearchconditiontypeInfo info) throws SQLException {
		
		String sql = "update `t_masterSearchConditionType` set "

			+ " `t_masterSearchConditionType_name` = ? "
			+ " where "
			+ " `t_masterSearchConditionType_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTMasterSearchConditionTypeName());

		preparedStatement.setObject(2, info.getTMasterSearchConditionTypeId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterSearchConditionType` set "
			+ " where "
			+ " `t_masterSearchConditionType_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
