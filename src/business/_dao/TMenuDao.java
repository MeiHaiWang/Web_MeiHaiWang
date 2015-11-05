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

import common._model.TMenuInfo;
import common.util.DBConnection;

public abstract class TMenuDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMenuInfo createTMenuInfo(ResultSet rs) throws SQLException {
		
		TMenuInfo info = new TMenuInfo();
		info.setTMenuMenuId(rs.getInt("t_menu_menuId"));
		info.setTMenuName(rs.getString("t_menu_name"));
		info.setTMenuPrice(rs.getInt("t_menu_price"));
		info.setTMenuCategoryId(rs.getInt("t_menu_categoryId"));
		info.setTMenuDetailText(rs.getString("t_menu_detailText"));
		info.setTMenuImagePath(rs.getString("t_menu_imagePath"));
		info.setTMenuTime(rs.getString("t_menu_time"));
		return info;
		
	}
	
	public TMenuInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMenuInfo> list = getByColumn(dbConnection, "t_menu_menuId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMenuInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMenuInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_menu` ";
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
		
		List<TMenuInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMenuInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMenuInfo info) throws SQLException {
		
		String sql = "insert into `t_menu` "
			+ " ( "
			+ " `t_menu_name`, "
			+ " `t_menu_price`, "
			+ " `t_menu_categoryId`, "
			+ " `t_menu_detailText`, "
			+ " `t_menu_imagePath`, "
			+ " `t_menu_time` "
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
		
		preparedStatement.setObject(1, info.getTMenuName());
		preparedStatement.setObject(2, info.getTMenuPrice());
		preparedStatement.setObject(3, info.getTMenuCategoryId());
		preparedStatement.setObject(4, info.getTMenuDetailText());
		preparedStatement.setObject(5, info.getTMenuImagePath());
		preparedStatement.setObject(6, info.getTMenuTime());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMenuInfo info) throws SQLException {
		
		String sql = "update `t_menu` set "

			+ " `t_menu_name` = ?, "
			+ " `t_menu_price` = ?, "
			+ " `t_menu_categoryId` = ?, "
			+ " `t_menu_detailText` = ?, "
			+ " `t_menu_imagePath` = ?, "
			+ " `t_menu_time` = ? "
			+ " where "
			+ " `t_menu_menuId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTMenuName());
		preparedStatement.setObject(2, info.getTMenuPrice());
		preparedStatement.setObject(3, info.getTMenuCategoryId());
		preparedStatement.setObject(4, info.getTMenuDetailText());
		preparedStatement.setObject(5, info.getTMenuImagePath());
		preparedStatement.setObject(6, info.getTMenuTime());

		preparedStatement.setObject(7, info.getTMenuMenuId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_menu` set "
			+ " where "
			+ " `t_menu_menuId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
