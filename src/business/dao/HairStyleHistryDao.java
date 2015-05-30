package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.HairStyleInfo;
import common.util.DBConnection;

public class HairStyleHistryDao {
	public HairStyleHistryDao() throws Exception{
		
	}
	
	public List<Integer> getHairStyleIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_latestViewHairStyleId` FROM `t_user` WHERE t_user_Id = " + user_id;
		List<Integer> idList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_latestViewHairStyleId");
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

	public List<HairStyleInfo> getHairStyleHistryInfo(DBConnection dbConnection, List<Integer> idList) throws SQLException{
		String sql = 
				"SELECT `t_hairStyle_Id`, `t_hairStyle_imagePath`, `t_hairStyle_stylistId`, `t_hairStyle_favoriteNumber` FROM `t_hairStyle` WHERE `t_hairStyle_Id` =";
		List<HairStyleInfo> infoList = new ArrayList<HairStyleInfo>();
		
		Statement statement = dbConnection.getStatement();
		for(int index: idList){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					HairStyleInfo hairStyleInfo = new HairStyleInfo();
					hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
					hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
					hairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
					hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
					infoList.add(hairStyleInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return infoList;
	}	

}
