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

import common._model.TReservationInfo;
import common.util.DBConnection;

public abstract class TReservationDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TReservationInfo createTReservationInfo(ResultSet rs) throws SQLException {
		
		TReservationInfo info = new TReservationInfo();
		info.setTReservationId(rs.getInt("t_reservation_id"));
		info.setTReservationUserId(rs.getInt("t_reservation_userId"));
		info.setTReservationSalonId(rs.getInt("t_reservation_salonId"));
		info.setTReservationStylistId(rs.getInt("t_reservation_stylistId"));
		info.setTReservationDate(rs.getDate("t_reservation_Date"));
		info.setTReservationIsFinished(rs.getInt("t_reservation_isFinished"));
		info.setTReservationMenuId(rs.getString("t_reservation_menuId"));
		info.setTReservationSeatId(rs.getInt("t_reservation_seatId"));
		info.setTReservationMemo(rs.getString("t_reservation_memo"));
		info.setTReservationAppoint(rs.getInt("t_reservation_appoint"));
		return info;
		
	}
	
	public TReservationInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TReservationInfo> list = getByColumn(dbConnection, "t_reservation_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TReservationInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TReservationInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<TReservationInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_reservation` ";
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
		
		List<TReservationInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTReservationInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_reservation_id`) count from `t_reservation` ";
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
	
	public int save(DBConnection dbConnection, TReservationInfo info) throws SQLException {
		
		String sql = "insert into `t_reservation` "
			+ " ( "
			+ " `t_reservation_userId`, "
			+ " `t_reservation_salonId`, "
			+ " `t_reservation_stylistId`, "
			+ " `t_reservation_Date`, "
			+ " `t_reservation_isFinished`, "
			+ " `t_reservation_menuId`, "
			+ " `t_reservation_seatId`, "
			+ " `t_reservation_memo`, "
			+ " `t_reservation_appoint` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTReservationUserId());
		preparedStatement.setObject(2, info.getTReservationSalonId());
		preparedStatement.setObject(3, info.getTReservationStylistId());
		preparedStatement.setObject(4, info.getTReservationDate());
		preparedStatement.setObject(5, info.getTReservationIsFinished());
		preparedStatement.setObject(6, info.getTReservationMenuId());
		preparedStatement.setObject(7, info.getTReservationSeatId());
		preparedStatement.setObject(8, info.getTReservationMemo());
		preparedStatement.setObject(9, info.getTReservationAppoint());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TReservationInfo info) throws SQLException {
		
		String sql = "update `t_reservation` set "

			+ " `t_reservation_userId` = ?, "
			+ " `t_reservation_salonId` = ?, "
			+ " `t_reservation_stylistId` = ?, "
			+ " `t_reservation_Date` = ?, "
			+ " `t_reservation_isFinished` = ?, "
			+ " `t_reservation_menuId` = ?, "
			+ " `t_reservation_seatId` = ?, "
			+ " `t_reservation_memo` = ?, "
			+ " `t_reservation_appoint` = ? "
			+ " where "
			+ " `t_reservation_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTReservationUserId());
		preparedStatement.setObject(2, info.getTReservationSalonId());
		preparedStatement.setObject(3, info.getTReservationStylistId());
		preparedStatement.setObject(4, info.getTReservationDate());
		preparedStatement.setObject(5, info.getTReservationIsFinished());
		preparedStatement.setObject(6, info.getTReservationMenuId());
		preparedStatement.setObject(7, info.getTReservationSeatId());
		preparedStatement.setObject(8, info.getTReservationMemo());
		preparedStatement.setObject(9, info.getTReservationAppoint());

		preparedStatement.setObject(10, info.getTReservationId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_reservation` set "
			+ " where "
			+ " `t_reservation_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
