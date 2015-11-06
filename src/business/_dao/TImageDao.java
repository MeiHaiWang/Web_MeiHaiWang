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

import common._model.TImageInfo;
import common.util.DBConnection;

public abstract class TImageDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TImageInfo createTImageInfo(ResultSet rs) throws SQLException {
		
		TImageInfo info = new TImageInfo();
		info.setTImageId(rs.getInt("t_image_id"));
		info.setTImageName(rs.getString("t_image_name"));
		info.setTImageFilepath(rs.getString("t_image_filepath"));
		info.setTImageSize(rs.getString("t_image_size"));
		info.setTImageSalonId(rs.getInt("t_image_salonId"));
		info.setTImageHash(rs.getString("t_image_hash"));
		return info;
		
	}
	
	public TImageInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TImageInfo> list = getByColumn(dbConnection, "t_image_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TImageInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TImageInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<TImageInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_image` ";
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
		
		List<TImageInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTImageInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_image_id`) count from `t_image` ";
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
	
	public int save(DBConnection dbConnection, TImageInfo info) throws SQLException {
		
		String sql = "insert into `t_image` "
			+ " ( "
			+ " `t_image_name`, "
			+ " `t_image_filepath`, "
			+ " `t_image_size`, "
			+ " `t_image_salonId`, "
			+ " `t_image_hash` "
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
		
		preparedStatement.setObject(1, info.getTImageName());
		preparedStatement.setObject(2, info.getTImageFilepath());
		preparedStatement.setObject(3, info.getTImageSize());
		preparedStatement.setObject(4, info.getTImageSalonId());
		preparedStatement.setObject(5, info.getTImageHash());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TImageInfo info) throws SQLException {
		
		String sql = "update `t_image` set "

			+ " `t_image_name` = ?, "
			+ " `t_image_filepath` = ?, "
			+ " `t_image_size` = ?, "
			+ " `t_image_salonId` = ?, "
			+ " `t_image_hash` = ? "
			+ " where "
			+ " `t_image_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTImageName());
		preparedStatement.setObject(2, info.getTImageFilepath());
		preparedStatement.setObject(3, info.getTImageSize());
		preparedStatement.setObject(4, info.getTImageSalonId());
		preparedStatement.setObject(5, info.getTImageHash());

		preparedStatement.setObject(6, info.getTImageId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_image` set "
			+ " where "
			+ " `t_image_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
