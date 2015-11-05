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

import common._model.TMastersearchconditionInfo;
import common.util.DBConnection;

public abstract class TMastersearchconditionDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMastersearchconditionInfo createTMastersearchconditionInfo(ResultSet rs) throws SQLException {
		
		TMastersearchconditionInfo info = new TMastersearchconditionInfo();
		info.setTMasterSearchConditionId(rs.getInt("t_masterSearchCondition_id"));
		info.setTMasterSearchConditionName(rs.getString("t_masterSearchCondition_name"));
		info.setTMasterSearchConditionTitleId(rs.getInt("t_masterSearchCondition_titleId"));
		return info;
		
	}
	
	public TMastersearchconditionInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMastersearchconditionInfo> list = getByColumn(dbConnection, "t_masterSearchCondition_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMastersearchconditionInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMastersearchconditionInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
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
		
		List<TMastersearchconditionInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMastersearchconditionInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMastersearchconditionInfo info) throws SQLException {
		
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
	
	public int update(DBConnection dbConnection, TMastersearchconditionInfo info) throws SQLException {
		
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
