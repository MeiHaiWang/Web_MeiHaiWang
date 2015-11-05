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

import common._model.TMastercountryInfo;
import common.util.DBConnection;

public abstract class TMastercountryDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private TMastercountryInfo createTMastercountryInfo(ResultSet rs) throws SQLException {
		
		TMastercountryInfo info = new TMastercountryInfo();
		info.setTCountryCountryId(rs.getInt("t_country_countryId"));
		info.setTCountryCountryName(rs.getString("t_country_countryName"));
		return info;
		
	}
	
	public TMastercountryInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TMastercountryInfo> list = getByColumn(dbConnection, "t_country_countryId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TMastercountryInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TMastercountryInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_masterCountry` ";
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
		
		List<TMastercountryInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTMastercountryInfo(rs));
		}
		return list;
	}
	
	public int save(DBConnection dbConnection, TMastercountryInfo info) throws SQLException {
		
		String sql = "insert into `t_masterCountry` "
			+ " ( "
			+ " `t_country_countryName` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTCountryCountryName());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, TMastercountryInfo info) throws SQLException {
		
		String sql = "update `t_masterCountry` set "

			+ " `t_country_countryName` = ? "
			+ " where "
			+ " `t_country_countryId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTCountryCountryName());

		preparedStatement.setObject(2, info.getTCountryCountryId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_masterCountry` set "
			+ " where "
			+ " `t_country_countryId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
