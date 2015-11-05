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

import common._model.THairstyleInfo;
import common.util.DBConnection;

public abstract class THairstyleDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private THairstyleInfo createTHairstyleInfo(ResultSet rs) throws SQLException {
		
		THairstyleInfo info = new THairstyleInfo();
		info.setTHairStyleId(rs.getInt("t_hairStyle_id"));
		info.setTHairStyleName(rs.getString("t_hairStyle_name"));
		info.setTHairStyleHairTypeId(rs.getInt("t_hairStyle_hairTypeId"));
		info.setTHairStyleGoodNumber(rs.getInt("t_hairStyle_goodNumber"));
		info.setTHairStyleViewNumber(rs.getInt("t_hairStyle_viewNumber"));
		info.setTHairStyleStylistId(rs.getInt("t_hairStyle_stylistId"));
		info.setTHairStyleAreaId(rs.getString("t_hairStyle_areaId"));
		info.setTHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
		info.setTHairStyleSalonId(rs.getInt("t_hairStyle_salonId"));
		info.setTHairStyleUpdateDate(rs.getDate("t_hairStyle_updateDate"));
		info.setTHairStyleFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
		info.setTHairStyleSearchConditionId(rs.getString("t_hairStyle_searchConditionId"));
		info.setTHairStyleMessage(rs.getString("t_hairStyle_message"));
		return info;
		
	}
	
	public THairstyleInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<THairstyleInfo> list = getByColumn(dbConnection, "t_hairStyle_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<THairstyleInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<THairstyleInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
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
		
		List<THairstyleInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTHairstyleInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, THairstyleInfo info) throws SQLException {
		
		String sql = "insert into `t_hairStyle` "
			+ " ( "
			+ " `t_hairStyle_name`, "
			+ " `t_hairStyle_hairTypeId`, "
			+ " `t_hairStyle_goodNumber`, "
			+ " `t_hairStyle_viewNumber`, "
			+ " `t_hairStyle_stylistId`, "
			+ " `t_hairStyle_areaId`, "
			+ " `t_hairStyle_imagePath`, "
			+ " `t_hairStyle_salonId`, "
			+ " `t_hairStyle_updateDate`, "
			+ " `t_hairStyle_favoriteNumber`, "
			+ " `t_hairStyle_searchConditionId`, "
			+ " `t_hairStyle_message` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTHairStyleName());
		preparedStatement.setObject(2, info.getTHairStyleHairTypeId());
		preparedStatement.setObject(3, info.getTHairStyleGoodNumber());
		preparedStatement.setObject(4, info.getTHairStyleViewNumber());
		preparedStatement.setObject(5, info.getTHairStyleStylistId());
		preparedStatement.setObject(6, info.getTHairStyleAreaId());
		preparedStatement.setObject(7, info.getTHairStyleImagePath());
		preparedStatement.setObject(8, info.getTHairStyleSalonId());
		preparedStatement.setObject(9, info.getTHairStyleUpdateDate());
		preparedStatement.setObject(10, info.getTHairStyleFavoriteNumber());
		preparedStatement.setObject(11, info.getTHairStyleSearchConditionId());
		preparedStatement.setObject(12, info.getTHairStyleMessage());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, THairstyleInfo info) throws SQLException {
		
		String sql = "update `t_hairStyle` set "

			+ " `t_hairStyle_name` = ?, "
			+ " `t_hairStyle_hairTypeId` = ?, "
			+ " `t_hairStyle_goodNumber` = ?, "
			+ " `t_hairStyle_viewNumber` = ?, "
			+ " `t_hairStyle_stylistId` = ?, "
			+ " `t_hairStyle_areaId` = ?, "
			+ " `t_hairStyle_imagePath` = ?, "
			+ " `t_hairStyle_salonId` = ?, "
			+ " `t_hairStyle_updateDate` = ?, "
			+ " `t_hairStyle_favoriteNumber` = ?, "
			+ " `t_hairStyle_searchConditionId` = ?, "
			+ " `t_hairStyle_message` = ? "
			+ " where "
			+ " `t_hairStyle_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTHairStyleName());
		preparedStatement.setObject(2, info.getTHairStyleHairTypeId());
		preparedStatement.setObject(3, info.getTHairStyleGoodNumber());
		preparedStatement.setObject(4, info.getTHairStyleViewNumber());
		preparedStatement.setObject(5, info.getTHairStyleStylistId());
		preparedStatement.setObject(6, info.getTHairStyleAreaId());
		preparedStatement.setObject(7, info.getTHairStyleImagePath());
		preparedStatement.setObject(8, info.getTHairStyleSalonId());
		preparedStatement.setObject(9, info.getTHairStyleUpdateDate());
		preparedStatement.setObject(10, info.getTHairStyleFavoriteNumber());
		preparedStatement.setObject(11, info.getTHairStyleSearchConditionId());
		preparedStatement.setObject(12, info.getTHairStyleMessage());

		preparedStatement.setObject(13, info.getTHairStyleId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_hairStyle` set "
			+ " where "
			+ " `t_hairStyle_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
