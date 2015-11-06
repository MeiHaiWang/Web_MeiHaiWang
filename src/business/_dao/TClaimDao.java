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

import common._model.TClaimInfo;
import common.util.DBConnection;

public abstract class TClaimDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TClaimInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TClaimInfo createTClaimInfo(ResultSet rs) throws SQLException {
		
		TClaimInfo info = new TClaimInfo();
		info.setTClaimId(rs.getInt("t_claim_id"));
		info.setTClaimUserId(rs.getInt("t_claim_userId"));
		info.setTClaimSalonId(rs.getInt("t_claim_salonId"));
		info.setTClaimReserveId(rs.getInt("t_claim_reserveId"));
		info.setTClaimMenuId(rs.getInt("t_claim_menuId"));
		info.setTClaimMessage(rs.getString("t_claim_message"));
		info.setTClaimDate(rs.getString("t_claim_date"));
		return info;
		
	}
	
	/**
	 * {@link TClaimInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TClaimInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TClaimInfo> list = getByColumn(dbConnection, "t_claim_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TClaimInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TClaimInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TClaimInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TClaimInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TClaimInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TClaimInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_claim` ";
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
		
		List<TClaimInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTClaimInfo(rs));
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
		
		String sql = " select count(`t_claim_id`) count from `t_claim` ";
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
	public int save(DBConnection dbConnection, TClaimInfo info) throws SQLException {
		
		String sql = "insert into `t_claim` "
			+ " ( "
			+ " `t_claim_userId`, "
			+ " `t_claim_salonId`, "
			+ " `t_claim_reserveId`, "
			+ " `t_claim_menuId`, "
			+ " `t_claim_message`, "
			+ " `t_claim_date` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTClaimUserId());
		preparedStatement.setObject(2, info.getTClaimSalonId());
		preparedStatement.setObject(3, info.getTClaimReserveId());
		preparedStatement.setObject(4, info.getTClaimMenuId());
		preparedStatement.setObject(5, info.getTClaimMessage());
		preparedStatement.setObject(6, info.getTClaimDate());
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
	public int update(DBConnection dbConnection, TClaimInfo info) throws SQLException {
		
		String sql = "update `t_claim` set "

			+ " `t_claim_userId` = ?, "
			+ " `t_claim_salonId` = ?, "
			+ " `t_claim_reserveId` = ?, "
			+ " `t_claim_menuId` = ?, "
			+ " `t_claim_message` = ?, "
			+ " `t_claim_date` = ? "
			+ " where "
			+ " `t_claim_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTClaimUserId());
		preparedStatement.setObject(2, info.getTClaimSalonId());
		preparedStatement.setObject(3, info.getTClaimReserveId());
		preparedStatement.setObject(4, info.getTClaimMenuId());
		preparedStatement.setObject(5, info.getTClaimMessage());
		preparedStatement.setObject(6, info.getTClaimDate());

		preparedStatement.setObject(7, info.getTClaimId());
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
		
		String sql = "update `t_claim` set "
			+ " where "
			+ " `t_claim_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
