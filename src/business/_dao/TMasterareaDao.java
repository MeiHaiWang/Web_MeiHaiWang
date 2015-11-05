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

import common._model.TMasterareaInfo;
import common.util.DBConnection;

public abstract class TMasterareaDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasterareaInfo createTMasterareaInfo(ResultSet rs) throws SQLException {
		
		TMasterareaInfo info = new TMasterareaInfo();
		info.setTAreaAreaId(rs.getInt("t_area_areaId"));
		info.setTAreaAreaName(rs.getString("t_area_areaName"));
		info.setTAreaLevel(rs.getInt("t_area_level"));
		info.setTAreaCountryId(rs.getInt("t_area_countryId"));
		info.setTAreaIsDetailFlag(rs.getInt("t_area_isDetailFlag"));
		info.setTAreaParentAreaId(rs.getInt("t_area_parentAreaId"));
		return info;
		
	}
	
	public TMasterareaInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterareaInfo> list = getByColumn(dbConnection, "t_area_areaId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasterareaInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasterareaInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
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
		
		List<TMasterareaInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterareaInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMasterareaInfo info) throws SQLException {
		
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
	
	public int update(DBConnection dbConnection, TMasterareaInfo info) throws SQLException {
		
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
