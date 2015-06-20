package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.AreaInfo;
import common.model.CountryInfo;
import common.util.DBConnection;

public class CountryDao {
	public CountryDao() throws Exception{
		
	}
	
	/**
	 * 
	 * @param dbConnection
	 * @param AreaType: 
	 * @return
	 * @throws SQLException
	 */
	public List<CountryInfo> getCountryListInfo(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT * FROM `t_masterCountry` WHERE 1";
		ArrayList<CountryInfo> CountryInfoList = new ArrayList<CountryInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs;
			rs = statement.executeQuery(sql);
			
			while(rs.next()){
				CountryInfo countryInfo = new CountryInfo();
				countryInfo.setCountryId(rs.getInt("t_country_countryId"));
				countryInfo.setCountryName(rs.getString("t_country_countryName"));
				CountryInfoList.add(countryInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return CountryInfoList;
	}	

}
