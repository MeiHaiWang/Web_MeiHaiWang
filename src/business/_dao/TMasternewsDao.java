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

import common._model.TMasternewsInfo;
import common.util.DBConnection;

public abstract class TMasternewsDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasternewsInfo createTMasternewsInfo(ResultSet rs) throws SQLException {
		
		TMasternewsInfo info = new TMasternewsInfo();
		info.setTMasterNewsId(rs.getInt("t_masterNewsId"));
		info.setTMasterNewsName(rs.getString("t_masterNewsName"));
		info.setTMasterNewImagePath(rs.getString("t_masterNewImagePath"));
		info.setTMasterNewsURL(rs.getString("t_masterNewsURL"));
		info.setTMasterNewsUpdateDate(rs.getDate("t_masterNewsUpdateDate"));
		return info;
		
	}
	
	public TMasternewsInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasternewsInfo> list = getByColumn(dbConnection, "t_masterNewsId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasternewsInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasternewsInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_masterNews` ";
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
		
		List<TMasternewsInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasternewsInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMasternewsInfo info) throws SQLException {
		
		String sql = "insert into `t_masterNews` "
			+ " ( "
			+ " `t_masterNewsName`, "
			+ " `t_masterNewImagePath`, "
			+ " `t_masterNewsURL`, "
			+ " `t_masterNewsUpdateDate` "
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
		
		preparedStatement.setObject(1, info.getTMasterNewsName());
		preparedStatement.setObject(2, info.getTMasterNewImagePath());
		preparedStatement.setObject(3, info.getTMasterNewsURL());
		preparedStatement.setObject(4, info.getTMasterNewsUpdateDate());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMasternewsInfo info) throws SQLException {
		
		String sql = "update `t_masterNews` set "

			+ " `t_masterNewsName` = ?, "
			+ " `t_masterNewImagePath` = ?, "
			+ " `t_masterNewsURL` = ?, "
			+ " `t_masterNewsUpdateDate` = ? "
			+ " where "
			+ " `t_masterNewsId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTMasterNewsName());
		preparedStatement.setObject(2, info.getTMasterNewImagePath());
		preparedStatement.setObject(3, info.getTMasterNewsURL());
		preparedStatement.setObject(4, info.getTMasterNewsUpdateDate());

		preparedStatement.setObject(5, info.getTMasterNewsId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterNews` set "
			+ " where "
			+ " `t_masterNewsId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
