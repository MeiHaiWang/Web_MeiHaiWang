package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.util.DBConnection;

public class RecommendDao {
	
	public RecommendDao() throws Exception{
		
	}
	
	public List<HairSalonInfo> getRecommendSalonInfo(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_hairSalonMaster_salonID`, `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, `t_area_areaName` FROM t_hairSalonMaster JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId WHERE `t_hairSalonMaster_salonId` IN (SELECT `t_masterRecommend_salonId` FROM `t_masterRecommend`)";
		List<HairSalonInfo> hairSalonInfoList = new ArrayList<HairSalonInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				HairSalonInfo hairSalonInfo = new HairSalonInfo();
				hairSalonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonID"));
				hairSalonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				hairSalonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				hairSalonInfo.setAreaNameList(Arrays.asList(new String[]{rs.getString("t_area_areaName")}));
				hairSalonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
				hairSalonInfoList.add(hairSalonInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return hairSalonInfoList;
	}

	public List<HairStyleInfo> getRecommendHairStyleInfo(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_hairStyle_Id`, `t_hairStyle_name`, `t_hairStyle_imagePath`, `t_hairStyle_salonId`, `t_hairStyle_stylistId` FROM `t_hairStyle` WHERE `t_hairStyle_Id` IN (SELECT `t_masterRecommend_hairStyleId` FROM `t_masterRecommend`)";
		List<HairStyleInfo> hairStyleInfoList = new ArrayList<HairStyleInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				HairStyleInfo hairStyleInfo = new HairStyleInfo();
				hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
				hairStyleInfo.setHairStyleName(rs.getString("t_hairStyle_name"));
				hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
				hairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
				hairStyleInfo.setSalonId(rs.getInt("t_hairStyle_salonId"));
				hairStyleInfoList.add(hairStyleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return hairStyleInfoList;
	}	
	
	public Date getRecommendSalonLastUpdate(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_masterRecommend_updateDate` FROM 't_masterRecommend' WHERE ('t_masterRecommend_salonId'　!=-1 AND 't_masterRecommend_hairStyleId' == -1) ORDER BY 't_masterRecommend_updateDate' DESC";
		Statement statement = dbConnection.getStatement();
		Date lastUpdate = new Date(0);
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				//更新日の降順で取得しているので一番最初に取得されたのが最終更新日のはず
				lastUpdate = rs.getDate("t_masterRecommend_updateDate");
				if(lastUpdate.compareTo(new Date(0)) != 0 ){
					break;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
		return lastUpdate;
	}
	
	public Date getRecommendHairLastUpdate(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_masterRecommend_updateDate` FROM 't_masterRecommend' WHERE ('t_masterRecommend_salonId'　==-1 AND 't_masterRecommend_hairStyleId' != -1) ORDER BY t_masterRecommend_updateDate DESC";
		Statement statement = dbConnection.getStatement();
		Date lastUpdate = new Date(0);
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				lastUpdate = rs.getDate("t_masterRecommend_updateDate");
				if(lastUpdate.compareTo(new Date(0)) != 0){
					break;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
		return lastUpdate;
	}
}
