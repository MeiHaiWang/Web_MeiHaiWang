package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import common.constant.Constant;
import common.model.BeautyNewsInfo;
import common.model.HairStyleInfo;
import common.model.HairTypeInfo;
import common.util.DBConnection;


public class HairTypeDao {
	public HairTypeDao() throws Exception{
		
	}
	
	public HairTypeInfo getHairTypeInfo(DBConnection dbConnection, int categoryId) throws SQLException{
		String sql = "SELECT `t_hairType_id`, `t_hairType_name` FROM `t_masterHairType` WHERE t_hairType_id="+categoryId;
		HairTypeInfo hairTypeInfo = new HairTypeInfo();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				hairTypeInfo.setHairTypeId(rs.getInt("t_hairType_id"));
				hairTypeInfo.setHairTypeName(rs.getString("t_hairType_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return hairTypeInfo;
	}	

	//Category一覧取得
	public List<HairTypeInfo> getHairTypeCategoryInfo(DBConnection dbConnection, int gender) throws SQLException{
		String sql = "SELECT `t_hairType_id`, `t_hairType_name`, `t_hairType_ImagePath` FROM `t_masterHairType` WHERE t_hairType_sex="+gender;

		List<HairTypeInfo> hairTypeInfoList = new ArrayList<HairTypeInfo>();
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				HairTypeInfo hairTypeInfo = new HairTypeInfo();
				hairTypeInfo.setHairTypeId(rs.getInt("t_hairType_id"));
				hairTypeInfo.setHairTypeName(rs.getString("t_hairType_name"));
				hairTypeInfo.setHairTypeImagePath(rs.getString("t_hairType_ImagePath"));
				hairTypeInfoList.add(hairTypeInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return hairTypeInfoList;
	}	

	
	
	public List<HairStyleInfo> getHairTypeOrderNewInfo(DBConnection dbConnection, int stylistId, int pageNumber, JSONObject jsonObject, List<String> searchConditionIdList) throws SQLException{
		String sql = "";
		String sql2="";
		List<HairStyleInfo> infoList = new ArrayList<HairStyleInfo>();
		
		/**
		 * SELECT `t_hairStyle_id`, `t_hairStyle_goodNumber`, `t_hairStyle_viewNumber`, `t_hairStyle_areaId`, 
		 * `t_hairStyle_imagePath`, `t_hairStyle_favoriteNumber` 
		 * FROM `t_hairStyle` 
		 * WHERE `t_hairStyle_stylistId` = 1 AND `t_hairStyle_salonId` = 1
		 */
		if(stylistId>=0){
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_goodNumber`, "
					+ "`t_hairStyle_viewNumber`, `t_hairStyle_areaId`, `t_hairStyle_favoriteNumber` "
					+ "FROM `t_hairStyle` " 
					+ "WHERE t_hairStyle_stylistId="+ stylistId;
					//+ " AND t_hairStyle_salonId ="+salonId;
					//+ " AND FIND_IN_SET(";
			sql2=   " ORDER BY `t_hairStyle_updateDate` DESC " 
					+ " limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " 
					+ String.valueOf(pageNumber * Constant.ONE_PAGE_NUM);
					
			int hitNum = 0;
			Statement statement = dbConnection.getStatement();
			try {
				//debug
				System.out.println(sql+sql2);
				ResultSet rs = statement.executeQuery(sql+sql2);
				while(rs.next()){
					HairStyleInfo hairStyleInfo = new HairStyleInfo();
					hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
					hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
					hairStyleInfo.setHairStyleGoodNumber(rs.getInt("t_hairStyle_goodNumber"));
					hairStyleInfo.setHairStyleViewNumber(rs.getInt("t_hairStyle_viewNumber"));
					hairStyleInfo.setHairStyleAreaId(rs.getString("t_hairStyle_areaId"));
					hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
					infoList.add(hairStyleInfo);
					hitNum++;
				}
				if(Constant.ONE_PAGE_NUM * pageNumber + Constant.ONE_PAGE_NUM >= hitNum){
					jsonObject.put("next", 0);
				}
				else{
					jsonObject.put("next", 1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}

		}else{
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_goodNumber`, "
					+ "`t_hairStyle_viewNumber`, `t_hairStyle_areaId`, `t_hairStyle_favoriteNumber` "
					+ "FROM `t_hairStyle` " 
					+ "WHERE FIND_IN_SET(";
			sql2= ",`t_hairStyle_searchConditionId`) "
					+" ORDER BY `t_hairStyle_updateDate` DESC " 
					+ "limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " 
					+ String.valueOf(pageNumber * Constant.ONE_PAGE_NUM);
					
			int hitNum = 0;
			Statement statement = dbConnection.getStatement();
			try {
				for(String conditionId : searchConditionIdList){
					//debug
					System.out.println(sql+conditionId+sql2);
					ResultSet rs = statement.executeQuery(sql+conditionId+sql2);
					while(rs.next()){
						HairStyleInfo hairStyleInfo = new HairStyleInfo();
						hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
						hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
						hairStyleInfo.setHairStyleGoodNumber(rs.getInt("t_hairStyle_goodNumber"));
						hairStyleInfo.setHairStyleViewNumber(rs.getInt("t_hairStyle_viewNumber"));
						hairStyleInfo.setHairStyleAreaId(rs.getString("t_hairStyle_areaId"));
						hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
						infoList.add(hairStyleInfo);
						hitNum++;
					}
					if(Constant.ONE_PAGE_NUM * pageNumber + Constant.ONE_PAGE_NUM >= hitNum){
						jsonObject.put("next", 0);
					}
					else{
						jsonObject.put("next", 1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		return infoList;
	}	

	public List<HairStyleInfo> getHairTypeOrderGoodInfo(DBConnection dbConnection, int stylistId, 
			int pageNumber, JSONObject jsonObject, List<String> searchConditionIdList) throws SQLException{
		String sql = "";
		String sql2="";
		List<HairStyleInfo> infoList = new ArrayList<HairStyleInfo>();
		/*
		if(categoryId>=0){
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath` FROM `t_hairStyle` "
					+ "WHERE t_hairStyle_hairTypeId="+categoryId;
			sql2= " AND FIND_IN_SET(";
			sql3= ",`t_hairStyle_searchConditionId`) "
					+" ORDER BY `t_hairStyle_goodNumber` DESC "
					+ "limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " 
					+ String.valueOf(pageNumber * Constant.ONE_PAGE_NUM);
		}else
		*/
		/**
		 * SELECT `t_hairStyle_id`, `t_hairStyle_goodNumber`, `t_hairStyle_viewNumber`, `t_hairStyle_areaId`, 
		 * `t_hairStyle_imagePath`, `t_hairStyle_favoriteNumber` 
		 * FROM `t_hairStyle` 
		 * WHERE `t_hairStyle_stylistId` = 1 AND `t_hairStyle_salonId` = 1
		 */
		if(stylistId>=0){
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_goodNumber`, "
					+ "`t_hairStyle_viewNumber`, `t_hairStyle_areaId`, `t_hairStyle_favoriteNumber` "
					+ "FROM `t_hairStyle` " 
					+ "WHERE t_hairStyle_stylistId="+ stylistId;
					//+ " AND t_hairStyle_salonId ="+salonId;
					//+ " AND FIND_IN_SET(";
			sql2= " ORDER BY `t_hairStyle_goodNumber` DESC " 
					+" limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " 
					+ String.valueOf(pageNumber * Constant.ONE_PAGE_NUM);
					
			int hitNum = 0;
			Statement statement = dbConnection.getStatement();
			try {
				//debug
				System.out.println(sql+sql2);
				ResultSet rs = statement.executeQuery(sql+sql2);
				while(rs.next()){
					HairStyleInfo hairStyleInfo = new HairStyleInfo();
					hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
					hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
					hairStyleInfo.setHairStyleGoodNumber(rs.getInt("t_hairStyle_goodNumber"));
					hairStyleInfo.setHairStyleViewNumber(rs.getInt("t_hairStyle_viewNumber"));
					hairStyleInfo.setHairStyleAreaId(rs.getString("t_hairStyle_areaId"));
					hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
					infoList.add(hairStyleInfo);
					hitNum++;
				}
				if(Constant.ONE_PAGE_NUM * pageNumber + Constant.ONE_PAGE_NUM >= hitNum){
					jsonObject.put("next", 0);
				}
				else{
					jsonObject.put("next", 1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}

		}else{
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_goodNumber`, "
					+ "`t_hairStyle_viewNumber`, `t_hairStyle_areaId`, `t_hairStyle_favoriteNumber` "
					+ "FROM `t_hairStyle` " 
					+ "WHERE FIND_IN_SET(";
			sql2= ",`t_hairStyle_searchConditionId`) "
					+" ORDER BY `t_hairStyle_goodNumber` DESC " 
					+ "limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " 
					+ String.valueOf(pageNumber * Constant.ONE_PAGE_NUM);
					
			int hitNum = 0;
			Statement statement = dbConnection.getStatement();
			try {
				for(String conditionId : searchConditionIdList){
					//debug
					System.out.println(sql+conditionId+sql2);
					ResultSet rs = statement.executeQuery(sql+conditionId+sql2);
					while(rs.next()){
						HairStyleInfo hairStyleInfo = new HairStyleInfo();
						hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
						hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
						hairStyleInfo.setHairStyleGoodNumber(rs.getInt("t_hairStyle_goodNumber"));
						hairStyleInfo.setHairStyleViewNumber(rs.getInt("t_hairStyle_viewNumber"));
						hairStyleInfo.setHairStyleAreaId(rs.getString("t_hairStyle_areaId"));
						hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
						infoList.add(hairStyleInfo);
						hitNum++;
					}
					if(Constant.ONE_PAGE_NUM * pageNumber + Constant.ONE_PAGE_NUM >= hitNum){
						jsonObject.put("next", 0);
					}
					else{
						jsonObject.put("next", 1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		return infoList;
	}	

	public String getHairTypeImage(DBConnection dbConnection, int conditionId) throws SQLException {
		String sql = "SELECT `t_hairType_imagePath` FROM `t_masterHairType` WHERE `t_hairType_searchConditionId` ="+conditionId;

		String hairTypeImage = "";
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				hairTypeImage=rs.getString("t_hairType_ImagePath");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return hairTypeImage;
	}

	
	/*空っぽのデータをつっこむ*/
	public HairStyleInfo retNull(){
		HairStyleInfo hairStyleInfo = new HairStyleInfo();
		hairStyleInfo.setHairStyleId(Integer.MIN_VALUE);
		hairStyleInfo.setHairStyleImagePath("");
		hairStyleInfo.setStylistId(Integer.MIN_VALUE);
		hairStyleInfo.setFavoriteNumber(Integer.MIN_VALUE);
		return hairStyleInfo;

	}

	
}
