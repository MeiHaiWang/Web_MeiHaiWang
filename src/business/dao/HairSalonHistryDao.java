package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.model.HairSalonInfo;
import common.util.DBConnection;

public class HairSalonHistryDao {
	public HairSalonHistryDao() throws Exception{
		
	}
	
	/*  *
	 *  SELECT `t_user_latestViewStylistId` FROM `t_user` WHERE t_user_Id=?
		SELECT `t_stylist_Id`, `t_stylist_name`, `t_stylist_imagePath`, `t_stylist_goodNumber`, `t_stylist_salonId` FROM `t_stylist` WHERE t_stylist_Id=?
	 * */
	
	public List<Integer> getHairSalonIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_latestViewSalonId` FROM `t_user` WHERE t_user_Id = " + user_id;
		List<Integer> idList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_latestViewSalonId");
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				idList.add(Integer.parseInt(temp));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return idList;
	}

	public List<HairSalonInfo> getHairSalonHistryInfo(DBConnection dbConnection, List<Integer> idList) throws SQLException{
		String sql = 
				"SELECT `t_hairSalonMaster_salonID`, `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, `t_area_areaName` FROM `t_hairSalonMaster` JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId WHERE t_hairSalonMaster_salonId =";
		List<HairSalonInfo> infoList = new ArrayList<HairSalonInfo>();
		
		Statement statement = dbConnection.getStatement();
		for(int index: idList){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					HairSalonInfo hairSalonInfo = new HairSalonInfo();
					hairSalonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonID"));
					hairSalonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
					hairSalonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
					hairSalonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
					hairSalonInfo.setAreaNameList(Arrays.asList(new String[]{rs.getString("t_area_areaName")}));
					infoList.add(hairSalonInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return infoList;
	}	

}
