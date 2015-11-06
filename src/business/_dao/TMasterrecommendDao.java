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

import common._model.TMasterRecommendInfo;
import common.util.DBConnection;

public abstract class TMasterRecommendDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TMasterRecommendInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TMasterRecommendInfo createTMasterRecommendInfo(ResultSet rs) throws SQLException {
		
		TMasterRecommendInfo info = new TMasterRecommendInfo();
		info.setTMasterRecommendId(rs.getInt("t_masterRecommend_Id"));
		info.setTMasterRecommendSalonId(rs.getInt("t_masterRecommend_salonId"));
		info.setTMasterRecommendHairStyleId(rs.getInt("t_masterRecommend_hairStyleId"));
		info.setTMasterRecommendUpdateDate(rs.getDate("t_masterRecommend_updateDate"));
		info.setTMasterRecommendStylistId(rs.getInt("t_masterRecommend_stylistId"));
		return info;
		
	}
	
	/**
	 * {@link TMasterRecommendInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TMasterRecommendInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterRecommendInfo> list = getByColumn(dbConnection, "t_masterRecommend_Id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TMasterRecommendInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TMasterRecommendInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TMasterRecommendInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TMasterRecommendInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TMasterRecommendInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TMasterRecommendInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_masterRecommend` ";
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
		
		List<TMasterRecommendInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterRecommendInfo(rs));
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
		
		String sql = " select count(`t_masterRecommend_Id`) count from `t_masterRecommend` ";
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
	public int save(DBConnection dbConnection, TMasterRecommendInfo info) throws SQLException {
		
		String sql = "insert into `t_masterRecommend` "
			+ " ( "
			+ " `t_masterRecommend_salonId`, "
			+ " `t_masterRecommend_hairStyleId`, "
			+ " `t_masterRecommend_updateDate`, "
			+ " `t_masterRecommend_stylistId` "
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
		
		preparedStatement.setObject(1, info.getTMasterRecommendSalonId());
		preparedStatement.setObject(2, info.getTMasterRecommendHairStyleId());
		preparedStatement.setObject(3, info.getTMasterRecommendUpdateDate());
		preparedStatement.setObject(4, info.getTMasterRecommendStylistId());
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
	public int update(DBConnection dbConnection, TMasterRecommendInfo info) throws SQLException {
		
		String sql = "update `t_masterRecommend` set "

			+ " `t_masterRecommend_salonId` = ?, "
			+ " `t_masterRecommend_hairStyleId` = ?, "
			+ " `t_masterRecommend_updateDate` = ?, "
			+ " `t_masterRecommend_stylistId` = ? "
			+ " where "
			+ " `t_masterRecommend_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTMasterRecommendSalonId());
		preparedStatement.setObject(2, info.getTMasterRecommendHairStyleId());
		preparedStatement.setObject(3, info.getTMasterRecommendUpdateDate());
		preparedStatement.setObject(4, info.getTMasterRecommendStylistId());

		preparedStatement.setObject(5, info.getTMasterRecommendId());
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
		
		String sql = "update `t_masterRecommend` set "
			+ " where "
			+ " `t_masterRecommend_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
