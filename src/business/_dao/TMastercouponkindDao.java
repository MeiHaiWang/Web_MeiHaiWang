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

import common._model.TMastercouponkindInfo;
import common.util.DBConnection;

public abstract class TMastercouponkindDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMastercouponkindInfo createTMastercouponkindInfo(ResultSet rs) throws SQLException {
		
		TMastercouponkindInfo info = new TMastercouponkindInfo();
		info.setTCouponKindId(rs.getInt("t_couponKind_id"));
		info.setTCouponKindName(rs.getString("t_couponKind_name"));
		return info;
		
	}
	
	public TMastercouponkindInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMastercouponkindInfo> list = getByColumn(dbConnection, "t_couponKind_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMastercouponkindInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMastercouponkindInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_masterCouponKind` ";
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
		
		List<TMastercouponkindInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMastercouponkindInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMastercouponkindInfo info) throws SQLException {
		
		String sql = "insert into `t_masterCouponKind` "
			+ " ( "
			+ " `t_couponKind_name` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTCouponKindName());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMastercouponkindInfo info) throws SQLException {
		
		String sql = "update `t_masterCouponKind` set "

			+ " `t_couponKind_name` = ? "
			+ " where "
			+ " `t_couponKind_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTCouponKindName());

		preparedStatement.setObject(2, info.getTCouponKindId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterCouponKind` set "
			+ " where "
			+ " `t_couponKind_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
