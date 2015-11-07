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

import common._model.TMasterAreaInfo;
import common.util.DBConnection;

public abstract class TMasterAreaDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TMasterAreaInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TMasterAreaInfo createTMasterAreaInfo(ResultSet rs) throws SQLException {
		
		TMasterAreaInfo info = new TMasterAreaInfo();
		info.setTAreaAreaId(rs.getInt("t_area_areaId"));
		info.setTAreaAreaName(rs.getString("t_area_areaName"));
		info.setTAreaLevel(rs.getInt("t_area_level"));
		info.setTAreaCountryId(rs.getInt("t_area_countryId"));
		info.setTAreaIsDetailFlag(rs.getInt("t_area_isDetailFlag"));
		info.setTAreaParentAreaId(rs.getInt("t_area_parentAreaId"));
		return info;
		
	}
	
	/**
	 * {@link TMasterAreaInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TMasterAreaInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterAreaInfo> list = getByColumn(dbConnection, "t_area_areaId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TMasterAreaInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TMasterAreaInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TMasterAreaInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TMasterAreaInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TMasterAreaInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TMasterAreaInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		return getByColumns(dbConnection, map, offset, count, null, null);
	}

	/**
	 * {@link TMasterAreaInfo} 一覧を取得します
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
	public List<TMasterAreaInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count, String orderColumn, String direction) throws SQLException {
		
		String sql = "select * from `t_masterArea` ";
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
		
		List<TMasterAreaInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterAreaInfo(rs));
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
		
		String sql = " select count(`t_area_areaId`) count from `t_masterArea` ";
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
	public int save(DBConnection dbConnection, TMasterAreaInfo info) throws SQLException {
		
		String sql = "insert into `t_masterArea` "
			+ " ( "
			+ " `t_area_areaName`, "
			+ " `t_area_level`, "
			+ " `t_area_countryId`, "
			+ " `t_area_isDetailFlag`, "
			+ " `t_area_parentAreaId` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTAreaAreaName());
		preparedStatement.setObject(2, info.getTAreaLevel());
		preparedStatement.setObject(3, info.getTAreaCountryId());
		preparedStatement.setObject(4, info.getTAreaIsDetailFlag());
		preparedStatement.setObject(5, info.getTAreaParentAreaId());
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
	public int update(DBConnection dbConnection, TMasterAreaInfo info) throws SQLException {
		
		String sql = "update `t_masterArea` set "

			+ " `t_area_areaName` = ?, "
			+ " `t_area_level` = ?, "
			+ " `t_area_countryId` = ?, "
			+ " `t_area_isDetailFlag` = ?, "
			+ " `t_area_parentAreaId` = ? "
			+ " where "
			+ " `t_area_areaId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTAreaAreaName());
		preparedStatement.setObject(2, info.getTAreaLevel());
		preparedStatement.setObject(3, info.getTAreaCountryId());
		preparedStatement.setObject(4, info.getTAreaIsDetailFlag());
		preparedStatement.setObject(5, info.getTAreaParentAreaId());

		preparedStatement.setObject(6, info.getTAreaAreaId());
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
		
		String sql = "update `t_masterArea` set "
			+ " where "
			+ " `t_area_areaId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
