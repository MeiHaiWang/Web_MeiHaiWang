package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

	
	
	public List<HairStyleInfo> getHairTypeOrderNewInfo(DBConnection dbConnection, int categoryId, int stylistId) throws SQLException{
		String sql = "";
		List<HairStyleInfo> infoList = new ArrayList<HairStyleInfo>();
		
		if(categoryId>=0){
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath` FROM `t_hairStyle` "
					+ "WHERE t_hairStyle_hairTypeId="+categoryId+" ORDER BY `t_hairStyle_updateDate` DESC";
		}else if(stylistId>=0){
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath` FROM `t_hairStyle` "
					+ "WHERE t_hairStyle_stylistId="+ stylistId +" ORDER BY `t_hairStyle_updateDate` DESC";
		}else{
			infoList.add(retNull());
	 		return infoList;
		}
				
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				HairStyleInfo hairStyleInfo = new HairStyleInfo();
				hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
				hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
				infoList.add(hairStyleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return infoList;
	}	

	public List<HairStyleInfo> getHairTypeOrderGoodInfo(DBConnection dbConnection, int categoryId, int stylistId) throws SQLException{
		String sql = "";
		List<HairStyleInfo> infoList = new ArrayList<HairStyleInfo>();
		
		if(categoryId>=0){
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath` FROM `t_hairStyle` "
					+ "WHERE t_hairStyle_hairTypeId="+categoryId+" ORDER BY `t_hairStyle_goodNumber` DESC";
		}else if(stylistId>=0){
			sql = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath` FROM `t_hairStyle` "
					+ "WHERE t_hairStyle_stylistId="+ stylistId +" ORDER BY `t_hairStyle_goodNumber` DESC";
		}else{
			infoList.add(retNull());
	 		return infoList;
		}
				
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				HairStyleInfo hairStyleInfo = new HairStyleInfo();
				hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
				hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
				infoList.add(hairStyleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return infoList;
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
