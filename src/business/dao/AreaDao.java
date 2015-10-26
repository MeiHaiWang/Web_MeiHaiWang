package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.AreaInfo;
import common.util.DBConnection;

public class AreaDao {
	public AreaDao() throws Exception{
		
	}
	
	/**
	 * 
	 * @param dbConnection
	 * @param AreaType: 
	 * @return
	 * @throws SQLException
	 */
	public List<AreaInfo> getAreaInfo(DBConnection dbConnection, int areaId) throws SQLException{
		String sql_1 = "SELECT `t_area_areaId`, `t_area_areaName`, `t_area_isDetailFlag` "
				+ "FROM `t_masterArea` WHERE t_area_level=0";
		/*
		String sql_2 = "SELECT `t_area_areaId`, `t_area_areaName`, `t_area_isDetailFlag` "
				+ "FROM `t_masterArea` WHERE t_area_areaId="+AreaId;
				*/
		String sql_2 = "SELECT * FROM `t_masterArea` WHERE `t_area_parentAreaId` = "+areaId;
		ArrayList<AreaInfo> AreaInfoList = new ArrayList<AreaInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs;
			if(areaId<0){
				rs = statement.executeQuery(sql_1);
				//debug
				System.out.println(sql_1);
			}else{
				rs = statement.executeQuery(sql_2);				
				//debug
				System.out.println(sql_2);
			}
			
			while(rs.next()){
				AreaInfo AreaInfo = new AreaInfo();
				AreaInfo.setAreaId(rs.getInt("t_area_areaid"));
				AreaInfo.setAreaName(rs.getString("t_area_areaName"));
				AreaInfo.setisDetailFlag(rs.getInt("t_area_isDetailFlag"));
				AreaInfoList.add(AreaInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return AreaInfoList;
	}	
	
	public List<AreaInfo> getAreaInfoListForCountry(DBConnection dbConnection, int countryId) throws SQLException{
		String sql = "SELECT `t_area_areaId`, `t_area_areaName`, `t_area_level`, `t_area_parentAreaId`, "
				+ "`t_area_isDetailFlag` FROM `t_masterArea` WHERE `t_area_countryId` = " + countryId;

		ArrayList<AreaInfo> AreaInfoList = new ArrayList<AreaInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs;
			rs = statement.executeQuery(sql);
			
			//debug
			System.out.println("AreaInfoList : "+sql);
			
			while(rs.next()){
				AreaInfo AreaInfo = new AreaInfo();
				AreaInfo.setAreaId(rs.getInt("t_area_areaid"));
				AreaInfo.setAreaName(rs.getString("t_area_areaName"));
				AreaInfo.setisDetailFlag(rs.getInt("t_area_isDetailFlag"));
				AreaInfo.setParent(rs.getInt("t_area_parentAreaId"));
				AreaInfoList.add(AreaInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return AreaInfoList;
	}

	/*
	 * 親のidを返す
	 * なかったらnull
	 * */
	public int getAreaParent(DBConnection dbConnection, String areaId) {
		int parent_areaId = 0;
		String sql = "SELECT `t_area_parentAreaId` FROM `t_masterArea` WHERE `t_area_areaId` = " + areaId;
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs;
			rs = statement.executeQuery(sql);			
			//debug
			System.out.println(sql);
			while(rs.next()){
				parent_areaId = rs.getInt("t_area_parentAreaId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parent_areaId;
	}

	/*
	 * areaの名前を返す
	 * */
	public List<String> getAreaName(DBConnection dbConnection, String areaId) {
		List<String> areaNameList = new ArrayList<String>();
		String sql = "SELECT `t_area_areaName` FROM `t_masterArea` WHERE `t_area_areaId` IN (" + areaId + ")";
		Statement statement = dbConnection.getStatement();
		try {
			if(areaId!=""){
				//debug
				System.out.println(sql);
				ResultSet rs = statement.executeQuery(sql);			
				while(rs.next()){
					areaNameList.add(rs.getString("t_area_areaName"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaNameList;
	}
	
	public List<String> getAreaChildren(DBConnection dbConnection, String areaId) {
		String sql = "SELECT * FROM `t_masterArea` WHERE `t_area_parentAreaId` = " + areaId;
		List<String> childrenList = new ArrayList<String>();
		Statement statement = dbConnection.getStatement();
		try {
			//debug
			System.out.println(sql);
			ResultSet rs;
			rs = statement.executeQuery(sql);			
			while(rs.next()){
				childrenList.add(Integer.toString(rs.getInt("t_area_areaId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return childrenList;
	}

	
}
