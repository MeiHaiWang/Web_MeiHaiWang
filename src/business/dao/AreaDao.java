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
	public List<AreaInfo> getAreaInfo(DBConnection dbConnection, int AreaId) throws SQLException{
		String sql_1 = "SELECT `t_area_areaId`, `t_area_areaName`, `t_area_isDetailFlag` "
				+ "FROM `t_masterArea` WHERE t_area_level=0";
		String sql_2 = "SELECT `t_area_areaId`, `t_area_areaName`, `t_area_isDetailFlag` "
				+ "FROM `t_masterArea` WHERE t_area_areaId="+AreaId;
		ArrayList<AreaInfo> AreaInfoList = new ArrayList<AreaInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs;
			if(AreaId<0){
				rs = statement.executeQuery(sql_1);
			}else{
				rs = statement.executeQuery(sql_2);				
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


	
}
