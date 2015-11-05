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
	
	public TClaimInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TClaimInfo> list = getByColumn(dbConnection, "t_claim_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TClaimInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TClaimInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
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
		
		List<TClaimInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTClaimInfo(rs));
		}
		return list;
	}
	
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
