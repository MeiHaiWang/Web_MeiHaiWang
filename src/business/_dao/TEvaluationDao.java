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

import common._model.TEvaluationInfo;
import common.util.DBConnection;

public abstract class TEvaluationDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TEvaluationInfo createTEvaluationInfo(ResultSet rs) throws SQLException {
		
		TEvaluationInfo info = new TEvaluationInfo();
		info.setTEvaluationEvaluationId(rs.getInt("t_evaluation_evaluationId"));
		info.setTEvaluationName(rs.getString("t_evaluation_name"));
		info.setTEvaluationPoint(rs.getInt("t_evaluation_point"));
		info.setTEvaluationUserId(rs.getInt("t_evaluation_userId"));
		return info;
		
	}
	
	public TEvaluationInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TEvaluationInfo> list = getByColumn(dbConnection, "t_evaluation_evaluationId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TEvaluationInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TEvaluationInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_evaluation` ";
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
		
		List<TEvaluationInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTEvaluationInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TEvaluationInfo info) throws SQLException {
		
		String sql = "insert into `t_evaluation` "
			+ " ( "
			+ " `t_evaluation_name`, "
			+ " `t_evaluation_point`, "
			+ " `t_evaluation_userId` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTEvaluationName());
		preparedStatement.setObject(2, info.getTEvaluationPoint());
		preparedStatement.setObject(3, info.getTEvaluationUserId());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TEvaluationInfo info) throws SQLException {
		
		String sql = "update `t_evaluation` set "

			+ " `t_evaluation_name` = ?, "
			+ " `t_evaluation_point` = ?, "
			+ " `t_evaluation_userId` = ? "
			+ " where "
			+ " `t_evaluation_evaluationId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTEvaluationName());
		preparedStatement.setObject(2, info.getTEvaluationPoint());
		preparedStatement.setObject(3, info.getTEvaluationUserId());

		preparedStatement.setObject(4, info.getTEvaluationEvaluationId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_evaluation` set "
			+ " where "
			+ " `t_evaluation_evaluationId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
