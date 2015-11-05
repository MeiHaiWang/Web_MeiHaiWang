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

import common._model.TMasterrecommendInfo;
import common.util.DBConnection;

public abstract class TMasterrecommendDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMasterrecommendInfo createTMasterrecommendInfo(ResultSet rs) throws SQLException {
		
		TMasterrecommendInfo info = new TMasterrecommendInfo();
		info.setTMasterRecommendId(rs.getInt("t_masterRecommend_Id"));
		info.setTMasterRecommendSalonId(rs.getInt("t_masterRecommend_salonId"));
		info.setTMasterRecommendHairStyleId(rs.getInt("t_masterRecommend_hairStyleId"));
		info.setTMasterRecommendUpdateDate(rs.getDate("t_masterRecommend_updateDate"));
		info.setTMasterRecommendStylistId(rs.getInt("t_masterRecommend_stylistId"));
		return info;
		
	}
	
	public TMasterrecommendInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMasterrecommendInfo> list = getByColumn(dbConnection, "t_masterRecommend_Id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMasterrecommendInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMasterrecommendInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_masterRecommend` ";
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
		
		List<TMasterrecommendInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMasterrecommendInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMasterrecommendInfo info) throws SQLException {
		
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
	
	public int update(DBConnection dbConnection, TMasterrecommendInfo info) throws SQLException {
		
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
