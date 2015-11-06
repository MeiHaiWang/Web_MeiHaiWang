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

import common._model.TSeatInfo;
import common.util.DBConnection;

public abstract class TSeatDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TSeatInfo createTSeatInfo(ResultSet rs) throws SQLException {
		
		TSeatInfo info = new TSeatInfo();
		info.setTSeatId(rs.getInt("t_seat_id"));
		info.setTSeatName(rs.getString("t_seat_name"));
		info.setTSeatSalonId(rs.getInt("t_seat_salonId"));
		info.setTSeatMemo(rs.getString("t_seat_memo"));
		return info;
		
	}
	
	public TSeatInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TSeatInfo> list = getByColumn(dbConnection, "t_seat_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TSeatInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TSeatInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<TSeatInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_seat` ";
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
		
		List<TSeatInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTSeatInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_seat_id`) count from `t_seat` ";
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
	
	public int save(DBConnection dbConnection, TSeatInfo info) throws SQLException {
		
		String sql = "insert into `t_seat` "
			+ " ( "
			+ " `t_seat_name`, "
			+ " `t_seat_salonId`, "
			+ " `t_seat_memo` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTSeatName());
		preparedStatement.setObject(2, info.getTSeatSalonId());
		preparedStatement.setObject(3, info.getTSeatMemo());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TSeatInfo info) throws SQLException {
		
		String sql = "update `t_seat` set "

			+ " `t_seat_name` = ?, "
			+ " `t_seat_salonId` = ?, "
			+ " `t_seat_memo` = ? "
			+ " where "
			+ " `t_seat_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTSeatName());
		preparedStatement.setObject(2, info.getTSeatSalonId());
		preparedStatement.setObject(3, info.getTSeatMemo());

		preparedStatement.setObject(4, info.getTSeatId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_seat` set "
			+ " where "
			+ " `t_seat_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
