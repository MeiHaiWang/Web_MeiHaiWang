package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
			sql = "SELECT `t_hairStyle_id`,`t_hairStyle_searchConditionId` FROM `t_hairStyle` ORDER BY `t_hairStyle_updateDate` DESC";
			/*
					+ "WHERE FIND_IN_SET(";
			sql2= ",`t_hairStyle_searchConditionId`) "
					+" ORDER BY `t_hairStyle_goodNumber` DESC " 
					+ "limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " 
					+ String.valueOf(pageNumber * Constant.ONE_PAGE_NUM);
					*/
					
			//int hitNum = 0;
			Map<Integer,List<String>> hairStyleConditionMap = new LinkedHashMap<Integer, List<String>>();
			Statement statement = dbConnection.getStatement();
			try {
				//debug
				System.out.println(sql);
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()){
					List<String> searchConditionList = new ArrayList<String>();
					String searchCondtionIds = rs.getString("t_hairStyle_searchConditionId");
					if(searchCondtionIds != null){
						String[] searchCondtionArray = searchCondtionIds.split(",");
						for(String condId : searchCondtionArray){
							searchConditionList.add(condId);
						}
					}
					//debug
					//System.out.println("["+rs.getInt("t_hairStyle_id")+"]:sL:"+searchConditionList);
					hairStyleConditionMap.put(rs.getInt("t_hairStyle_id"), searchConditionList);
					//debug
					//System.out.println("map:"+hairStyleConditionMap);
				}
				
				List<Integer> retHairStyleIdList = new ArrayList<Integer>();
				for (Iterator<Entry<Integer, List<String>>> it = hairStyleConditionMap.entrySet().iterator(); it.hasNext();) {
				    Entry entry = it.next();
				    boolean searchCondFlag = false;
				    int hairStyleId = Integer.parseInt(entry.getKey().toString());
				    List<String> condList = hairStyleConditionMap.get(hairStyleId);
				 
				    /*
				    //OR検索
				    for(String condId : searchConditionIdList){
				    	if(condList.contains(condId)){
				    		//debug
				    		//System.out.println("Stylist "+stylistId+" contains condId:"+condList+","+searchConditionIdList);
				    		searchCondFlag = true;
				    		break;
				    	}
				    }
				    */
				    //AND検索			    
				    for(String condId : searchConditionIdList){
				    	if(condList.contains(condId)){
				    		//debug
				    		//System.out.println("Stylist "+salonId+" contains condId:"+condList+","+searchConditionIdList);
				    		searchCondFlag = true;
				    	}else{
				    		searchCondFlag = false;
				    	}
				    }
				    //debug
				    //System.out.println("["+hairStyleId+"]:sL:"+retHairStyleIdList);
				    if(searchCondFlag)  retHairStyleIdList.add(hairStyleId);
				}
			
				sql2 = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_goodNumber`, "
						+ "`t_hairStyle_viewNumber`, `t_hairStyle_areaId`, `t_hairStyle_favoriteNumber` "
						+ "FROM `t_hairStyle` WHERE `t_hairStyle_id`=";
				int hitCount=0;
				int offset = pageNumber * Constant.ONE_PAGE_NUM;
				for(int id : retHairStyleIdList){
					if(Constant.ONE_PAGE_NUM > hitCount ){
						if(offset>=retHairStyleIdList.size()) break;
						//debug
						System.out.println(sql2+String.valueOf(retHairStyleIdList.get(offset)));
						ResultSet rs2 = statement.executeQuery(sql2+String.valueOf(retHairStyleIdList.get(offset)));
						while(rs2.next()){
							HairStyleInfo hairStyleInfo = new HairStyleInfo();
							hairStyleInfo.setHairStyleId(rs2.getInt("t_hairStyle_Id"));
							hairStyleInfo.setHairStyleImagePath(rs2.getString("t_hairStyle_imagePath"));
							hairStyleInfo.setHairStyleGoodNumber(rs2.getInt("t_hairStyle_goodNumber"));
							hairStyleInfo.setHairStyleViewNumber(rs2.getInt("t_hairStyle_viewNumber"));
							hairStyleInfo.setHairStyleAreaId(rs2.getString("t_hairStyle_areaId"));
							hairStyleInfo.setFavoriteNumber(rs2.getInt("t_hairStyle_favoriteNumber"));
							infoList.add(hairStyleInfo);
							offset++;
							hitCount++;
						}
					}
					else{
						break;
					}
				}

				if(Constant.ONE_PAGE_NUM * pageNumber + Constant.ONE_PAGE_NUM >= retHairStyleIdList.size()){
					jsonObject.put("next", 0);
				}
				else{
					jsonObject.put("next", 1);
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
			sql = "SELECT `t_hairStyle_id`,`t_hairStyle_searchConditionId` FROM `t_hairStyle` ORDER BY `t_hairStyle_goodNumber` DESC";
			/*
					+ "WHERE FIND_IN_SET(";
			sql2= ",`t_hairStyle_searchConditionId`) "
					+" ORDER BY `t_hairStyle_goodNumber` DESC " 
					+ "limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " 
					+ String.valueOf(pageNumber * Constant.ONE_PAGE_NUM);
					*/
					
			//int hitNum = 0;
			Map<Integer,List<String>> stylistConditionMap = new LinkedHashMap<Integer, List<String>>();
			Statement statement = dbConnection.getStatement();
			try {
				//debug
				System.out.println(sql);
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()){
					List<String> searchConditionList = new ArrayList<String>();
					String searchCondtionIds = rs.getString("t_hairStyle_searchConditionId");
					if(searchCondtionIds != null){
						String[] searchCondtionArray = searchCondtionIds.split(",");
						for(String condId : searchCondtionArray){
							searchConditionList.add(condId);
						}
					}
					stylistConditionMap.put(rs.getInt("t_hairStyle_id"), searchConditionList);
				}
				
				List<Integer> retHairStyleIdList = new ArrayList<Integer>();
				for (Iterator<Entry<Integer, List<String>>> it = stylistConditionMap.entrySet().iterator(); it.hasNext();) {
				    Entry entry = it.next();
				    boolean searchCondFlag = false;
				    int hairStyleId = Integer.parseInt(entry.getKey().toString());
				    List<String> condList = stylistConditionMap.get(hairStyleId);
				 
				    /*
				    //OR検索
				    for(String condId : searchConditionIdList){
				    	if(condList.contains(condId)){
				    		//debug
				    		//System.out.println("Stylist "+stylistId+" contains condId:"+condList+","+searchConditionIdList);
				    		searchCondFlag = true;
				    		break;
				    	}
				    }
				    */
				    //AND検索			    
				    for(String condId : searchConditionIdList){
				    	if(condList.contains(condId)){
				    		//debug
				    		//System.out.println("Stylist "+salonId+" contains condId:"+condList+","+searchConditionIdList);
				    		searchCondFlag = true;
				    	}else{
				    		searchCondFlag = false;
				    	}
				    }
				    if(searchCondFlag)  retHairStyleIdList.add(hairStyleId);
				}
			
				sql2 = "SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_goodNumber`, "
						+ "`t_hairStyle_viewNumber`, `t_hairStyle_areaId`, `t_hairStyle_favoriteNumber` "
						+ "FROM `t_hairStyle` WHERE `t_hairStyle_id`=";
				int hitCount=0;
				int offset = pageNumber * Constant.ONE_PAGE_NUM;
				for(int id : retHairStyleIdList){
					if(Constant.ONE_PAGE_NUM > hitCount ){
						if(offset>=retHairStyleIdList.size()) break;
						//debug
						System.out.println(sql2+String.valueOf(retHairStyleIdList.get(offset)));
						ResultSet rs2 = statement.executeQuery(sql2+String.valueOf(retHairStyleIdList.get(offset)));
						while(rs2.next()){
							HairStyleInfo hairStyleInfo = new HairStyleInfo();
							hairStyleInfo.setHairStyleId(rs2.getInt("t_hairStyle_Id"));
							hairStyleInfo.setHairStyleImagePath(rs2.getString("t_hairStyle_imagePath"));
							hairStyleInfo.setHairStyleGoodNumber(rs2.getInt("t_hairStyle_goodNumber"));
							hairStyleInfo.setHairStyleViewNumber(rs2.getInt("t_hairStyle_viewNumber"));
							hairStyleInfo.setHairStyleAreaId(rs2.getString("t_hairStyle_areaId"));
							hairStyleInfo.setFavoriteNumber(rs2.getInt("t_hairStyle_favoriteNumber"));
							infoList.add(hairStyleInfo);
							offset++;
							hitCount++;
						}
					}
					else{
						break;
					}
				}

				if(Constant.ONE_PAGE_NUM * pageNumber + Constant.ONE_PAGE_NUM >= retHairStyleIdList.size()){
					jsonObject.put("next", 0);
				}
				else{
					jsonObject.put("next", 1);
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
