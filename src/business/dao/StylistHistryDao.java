package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import common.model.StylistInfo;
import common.util.DBConnection;

public class StylistHistryDao {
	public StylistHistryDao() throws Exception{
		
	}
	
	public List<Integer> getstylistIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_latestViewStylistId` FROM `t_user` WHERE t_user_Id=" + user_id;
		List<Integer> stylistIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_latestViewStylistId");
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				stylistIdList.add(Integer.parseInt(temp));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistIdList;
	}

	public List<StylistInfo> getStylistHistryInfo(DBConnection dbConnection, List<Integer> stylist_id_list) throws SQLException{
		String sql = 
				"SELECT `t_stylist_Id`, `t_stylist_name`, `t_stylist_imagePath`, `t_stylist_favoriteNumber`, `t_stylist_salonId` FROM `t_stylist` WHERE t_stylist_Id=";
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		
		Statement statement = dbConnection.getStatement();
		for(int index: stylist_id_list){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					StylistInfo stylistInfo = new StylistInfo();
					stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
					stylistInfo.setStylistName(rs.getString("t_stylist_name"));
					stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
					stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
					stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
					stylistInfoList.add(stylistInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return stylistInfoList;
	}	
	
}
