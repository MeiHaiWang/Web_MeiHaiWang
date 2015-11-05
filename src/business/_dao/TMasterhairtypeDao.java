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

import common._model.TMasterhairtypeInfo;
import common.util.DBConnection;

public abstract class TMasterhairtypeDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasterhairtypeInfo createTMasterhairtypeInfo(ResultSet rs) throws SQLException {
		
		TMasterhairtypeInfo info = new TMasterhairtypeInfo();
		info.setTHairTypeId(rs.getInt("t_hairType_id"));
		info.setTHairTypeName(rs.getString("t_hairType_name"));
		info.setTHairTypeSex(rs.getString("t_hairType_sex"));
		info.setTHairTypeImagePath(rs.getString("t_hairType_ImagePath"));
		info.setTHairTypeSearchConditionId(rs.getInt("t_hairType_searchConditionId"));
		return info;
		
	}
	
	public TMasterhairtypeInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterhairtypeInfo> list = getByColumn(dbConnection, "t_hairType_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasterhairtypeInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasterhairtypeInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
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
		
		List<TMasterhairtypeInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterhairtypeInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMasterhairtypeInfo info) throws SQLException {
		
		String sql = "insert into `t_masterHairType` "
			+ " ( "
			+ " `t_hairType_name`, "
			+ " `t_hairType_sex`, "
			+ " `t_hairType_ImagePath`, "
			+ " `t_hairType_searchConditionId` "
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
		
		preparedStatement.setObject(1, info.getTHairTypeName());
		preparedStatement.setObject(2, info.getTHairTypeSex());
		preparedStatement.setObject(3, info.getTHairTypeImagePath());
		preparedStatement.setObject(4, info.getTHairTypeSearchConditionId());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMasterhairtypeInfo info) throws SQLException {
		
		String sql = "update `t_masterHairType` set "

			+ " `t_hairType_name` = ?, "
			+ " `t_hairType_sex` = ?, "
			+ " `t_hairType_ImagePath` = ?, "
			+ " `t_hairType_searchConditionId` = ? "
			+ " where "
			+ " `t_hairType_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTHairTypeName());
		preparedStatement.setObject(2, info.getTHairTypeSex());
		preparedStatement.setObject(3, info.getTHairTypeImagePath());
		preparedStatement.setObject(4, info.getTHairTypeSearchConditionId());

		preparedStatement.setObject(5, info.getTHairTypeId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterHairType` set "
			+ " where "
			+ " `t_hairType_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
