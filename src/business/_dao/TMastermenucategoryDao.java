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

import common._model.TMasterMenuCategoryInfo;
import common.util.DBConnection;

public abstract class TMasterMenuCategoryDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasterMenuCategoryInfo createTMasterMenuCategoryInfo(ResultSet rs) throws SQLException {
		
		TMasterMenuCategoryInfo info = new TMasterMenuCategoryInfo();
		info.setTMenuCategoryCategoryId(rs.getInt("t_menuCategory_categoryId"));
		info.setTMenuCategoryName(rs.getString("t_menuCategory_name"));
		return info;
		
	}
	
	public TMasterMenuCategoryInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterMenuCategoryInfo> list = getByColumn(dbConnection, "t_menuCategory_categoryId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasterMenuCategoryInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasterMenuCategoryInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<TMasterMenuCategoryInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_masterMenuCategory` ";
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
		
		List<TMasterMenuCategoryInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterMenuCategoryInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_menuCategory_categoryId`) count from `t_masterMenuCategory` ";
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
	
	public int save(DBConnection dbConnection, TMasterMenuCategoryInfo info) throws SQLException {
		
		String sql = "insert into `t_masterMenuCategory` "
			+ " ( "
			+ " `t_menuCategory_name` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTMenuCategoryName());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMasterMenuCategoryInfo info) throws SQLException {
		
		String sql = "update `t_masterMenuCategory` set "

			+ " `t_menuCategory_name` = ? "
			+ " where "
			+ " `t_menuCategory_categoryId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTMenuCategoryName());

		preparedStatement.setObject(2, info.getTMenuCategoryCategoryId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterMenuCategory` set "
			+ " where "
			+ " `t_menuCategory_categoryId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
