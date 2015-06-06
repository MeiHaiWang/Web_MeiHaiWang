package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import common.model.HairSalonInfo;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.util.DBConnection;
import common.constant.Constant;
public class RecommendDao {
	
	public RecommendDao() throws Exception{
		
	}
	//TODO ユーザがお気に入りしているかどうかを保持するisGoodはセットしていない。呼び出し側でセットすること
	public List<HairSalonInfo> getRecommendSalonInfo(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_hairSalonMaster_salonID`, `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, `t_area_areaName`,`t_hairSalonMaster_favoriteNumber` FROM t_hairSalonMaster JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId WHERE `t_hairSalonMaster_salonId` IN (SELECT `t_masterRecommend_salonId` FROM `t_masterRecommend`)";
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
				hairSalonInfo.setFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
				hairSalonInfoList.add(hairSalonInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return hairSalonInfoList;
	}
	//TODO ユーザがお気に入りしているかどうかを保持するisGoodはセットしていない。呼び出し側でセットすること
	public List<HairStyleInfo> getRecommendHairStyleInfo(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_hairStyle_Id`, `t_hairStyle_name`, `t_hairStyle_imagePath`, `t_hairStyle_salonId`, `t_hairStyle_stylistId` , `t_hairStyle_favoriteNumber` FROM `t_hairStyle` WHERE `t_hairStyle_Id` IN (SELECT `t_masterRecommend_hairStyleId` FROM `t_masterRecommend`)";
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
				hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
				hairStyleInfoList.add(hairStyleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return hairStyleInfoList;
	}	

	//TODO ユーザがお気に入りしているかどうかを保持するisGoodはセットしていない。呼び出し側でセットすること
	public List<StylistInfo> getRecommendStylistInfo(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_stylist_Id`, `t_stylist_name`, `t_stylist_imagePath`, `t_stylist_favoriteNumber`, `t_stylist_salonId` FROM `t_stylist` WHERE t_stylist_Id IN (SELECT `t_masterRecommend_stylistId` FROM `t_masterRecommend`)";
		List<StylistInfo> styListInfoList = new ArrayList<StylistInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				StylistInfo stylistInfo = new StylistInfo();
				stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
				stylistInfo.setStylistName(rs.getString("t_stylist_name"));
				stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
				stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
				stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
				styListInfoList.add(stylistInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return styListInfoList;
	}		
	
	public Date getRecommendSalonLastUpdate(DBConnection dbConnection) throws SQLException{

		String sql = "SELECT `t_masterRecommend_updateDate` FROM `t_masterRecommend` WHERE (t_masterRecommend_salonId!=-1 AND t_masterRecommend_hairStyleId = -1 AND t_masterRecommend_stylistId = -1)  ORDER BY `t_masterRecommend_updateDate` DESC";
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
		String sql = "SELECT `t_masterRecommend_updateDate` FROM `t_masterRecommend` WHERE (t_masterRecommend_salonId=-1 AND t_masterRecommend_hairStyleId !=-1 AND t_masterRecommend_stylistId = -1)  ORDER BY `t_masterRecommend_updateDate` DESC";
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

	public Date getRecommendStylistLastUpdate(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_masterRecommend_updateDate` FROM `t_masterRecommend` WHERE (t_masterRecommend_salonId=-1 AND t_masterRecommend_hairStyleId =-1 AND t_masterRecommend_stylistId != -1)  ORDER BY `t_masterRecommend_updateDate` DESC";
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
		
	
	public void setIsFavoriteSalon(Integer userId,List<HairSalonInfo> infoList , DBConnection dbConnection) throws SQLException{
		if(userId == Constant.USER_NOT_LOGIN){
			return;
		}
		String sql = "SELECT `t_user_favoriteSalonId` FROM `t_user` WHERE `t_user_Id` =" + userId.toString() ;
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			String favoriteSalons ="";
			while(rs.next()){
				favoriteSalons = rs.getString("t_user_favoriteSalonId");
			}
			String[]  favoriteSalonIdArray = favoriteSalons != null ?
					favoriteSalons.split(",") : null;
			List<String> favoriteSalonIdList = favoriteSalonIdArray != null  ?
					Arrays.asList(favoriteSalonIdArray) : new ArrayList<String>() ;
			
			for(HairSalonInfo salonInfo : infoList){
				if(favoriteSalonIdList.contains(String.valueOf(salonInfo.getHairSalonId()))){
					salonInfo.setIsGood(Constant.FLAG_ON);
				}
				else{
					salonInfo.setIsGood(Constant.FLAG_OFF);
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
	}	
	
	public void setIsFavoriteHair(Integer userId,List<HairStyleInfo> infoList , DBConnection dbConnection) throws SQLException{
		if(userId == Constant.USER_NOT_LOGIN){
			return;
		}
		String sql = "SELECT `t_user_favoriteHairStyleId` FROM `t_user` WHERE `t_user_Id` =" + userId.toString() ;
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			String favoriteHairs ="";
			while(rs.next()){
				favoriteHairs = rs.getString("t_user_favoriteHairStyleId");
			}
			String[]  favoriteHairIdArray = favoriteHairs != null ?
					favoriteHairs.split(",") : null;
			List<String> favoriteHairIdList = favoriteHairIdArray != null  ?
					Arrays.asList(favoriteHairIdArray) : new ArrayList<String>() ;
			
			for(HairStyleInfo hairInfo : infoList){
				if(favoriteHairIdList.contains(String.valueOf(hairInfo.getHairStyleId()))){
					hairInfo.setIsGood(Constant.FLAG_ON);
				}
				else{
					hairInfo.setIsGood(Constant.FLAG_OFF);
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
	}	

	public void setIsFavoriteStylist(Integer userId,List<StylistInfo> infoList , DBConnection dbConnection) throws SQLException{
		if(userId == Constant.USER_NOT_LOGIN){
			return;
		}
		String sql = "SELECT `t_user_favoriteStylistId` FROM `t_user` WHERE `t_user_Id` =" + userId.toString() ;
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			String favoriteStylists ="";
			while(rs.next()){
				favoriteStylists = rs.getString("t_user_favoriteStylistId");
			}
			String[]  favoriteStylistIdArray = favoriteStylists != null ?
					favoriteStylists.split(",") : null;
			List<String> favoriteHairIdList = favoriteStylistIdArray != null  ?
					Arrays.asList(favoriteStylistIdArray) : new ArrayList<String>() ;
			
			for(StylistInfo stylistInfo : infoList){
				if(favoriteHairIdList.contains(String.valueOf(stylistInfo.getStylistId()))){
					stylistInfo.setIsGood(Constant.FLAG_ON);
				}
				else{
					stylistInfo.setIsGood(Constant.FLAG_OFF);
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
	}
}