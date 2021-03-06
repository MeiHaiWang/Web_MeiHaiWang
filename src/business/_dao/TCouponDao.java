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

import common._model.TCouponInfo;
import common.util.DBConnection;

public abstract class TCouponDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TCouponInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TCouponInfo createTCouponInfo(ResultSet rs) throws SQLException {
		
		TCouponInfo info = new TCouponInfo();
		info.setTCouponId(rs.getInt("t_coupon_Id"));
		info.setTCouponName(rs.getString("t_coupon_name"));
		info.setTCouponCouponKindId(rs.getInt("t_coupon_couponKindId"));
		info.setTCouponDetailText(rs.getString("t_coupon_detailText"));
		info.setTCouponUseCondition(rs.getString("t_coupon_useCondition"));
		info.setTCouponImagePath(rs.getString("t_coupon_imagePath"));
		info.setTCouponPrice(rs.getInt("t_coupon_price"));
		info.setTCouponDeadLine(rs.getString("t_coupon_deadLine"));
		info.setTCouponIsFirstFlag(rs.getInt("t_coupon_isFirstFlag"));
		info.setTCouponPresentationCondition(rs.getString("t_coupon_presentationCondition"));
		return info;
		
	}
	
	/**
	 * {@link TCouponInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TCouponInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TCouponInfo> list = getByColumn(dbConnection, "t_coupon_Id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TCouponInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TCouponInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TCouponInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TCouponInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TCouponInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TCouponInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		return getByColumns(dbConnection, map, offset, count, null, null);
	}

	/**
	 * {@link TCouponInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @param orderColumn
	 * @param direction
	 * @return
	 * @throws SQLException
	 */
	public List<TCouponInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count, String orderColumn, String direction) throws SQLException {
		
		String sql = "select * from `t_coupon` ";
		String where = " where ";

		for (String columnName : map.keySet()) {
			
			where += " `" + columnName + "` = ? AND ";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -4);
			sql += where;
		}
		
		String order = " order by ";
		if (orderColumn != null) {
			
			order += " " + orderColumn + " ";
			
			if (direction != null) {
			
				order += " " + direction + " ";
			}
			sql += order;
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
		
		List<TCouponInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTCouponInfo(rs));
		}
		return list;
	}

	/**
	 * 件数をカウントします。
	 * @param dbConnection
	 * @return
	 * @throws SQLException
	 */
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	/**
	 * 件数をカウントします。
	 * @param dbConnection
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_coupon_Id`) count from `t_coupon` ";
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
	
	/**
	 * 新規作成します。
	 *
	 * @param dbConnection
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	public int save(DBConnection dbConnection, TCouponInfo info) throws SQLException {
		
		String sql = "insert into `t_coupon` "
			+ " ( "
			+ " `t_coupon_name`, "
			+ " `t_coupon_couponKindId`, "
			+ " `t_coupon_detailText`, "
			+ " `t_coupon_useCondition`, "
			+ " `t_coupon_imagePath`, "
			+ " `t_coupon_price`, "
			+ " `t_coupon_deadLine`, "
			+ " `t_coupon_isFirstFlag`, "
			+ " `t_coupon_presentationCondition` "
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
		
		preparedStatement.setObject(1, info.getTCouponName());
		preparedStatement.setObject(2, info.getTCouponCouponKindId());
		preparedStatement.setObject(3, info.getTCouponDetailText());
		preparedStatement.setObject(4, info.getTCouponUseCondition());
		preparedStatement.setObject(5, info.getTCouponImagePath());
		preparedStatement.setObject(6, info.getTCouponPrice());
		preparedStatement.setObject(7, info.getTCouponDeadLine());
		preparedStatement.setObject(8, info.getTCouponIsFirstFlag());
		preparedStatement.setObject(9, info.getTCouponPresentationCondition());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	/**
	 * 更新を行ないます。
	 * 
	 * @param dbConnection
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	public int update(DBConnection dbConnection, TCouponInfo info) throws SQLException {
		
		String sql = "update `t_coupon` set "

			+ " `t_coupon_name` = ?, "
			+ " `t_coupon_couponKindId` = ?, "
			+ " `t_coupon_detailText` = ?, "
			+ " `t_coupon_useCondition` = ?, "
			+ " `t_coupon_imagePath` = ?, "
			+ " `t_coupon_price` = ?, "
			+ " `t_coupon_deadLine` = ?, "
			+ " `t_coupon_isFirstFlag` = ?, "
			+ " `t_coupon_presentationCondition` = ? "
			+ " where "
			+ " `t_coupon_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTCouponName());
		preparedStatement.setObject(2, info.getTCouponCouponKindId());
		preparedStatement.setObject(3, info.getTCouponDetailText());
		preparedStatement.setObject(4, info.getTCouponUseCondition());
		preparedStatement.setObject(5, info.getTCouponImagePath());
		preparedStatement.setObject(6, info.getTCouponPrice());
		preparedStatement.setObject(7, info.getTCouponDeadLine());
		preparedStatement.setObject(8, info.getTCouponIsFirstFlag());
		preparedStatement.setObject(9, info.getTCouponPresentationCondition());

		preparedStatement.setObject(10, info.getTCouponId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	/**
	 * 論理削除を行ないます。
	 * 
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_coupon` set "
			+ " where "
			+ " `t_coupon_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
