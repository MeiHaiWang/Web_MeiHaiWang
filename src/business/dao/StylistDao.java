package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;
import common.constant.Constant;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;
import common.util.ListUtilities;

public class StylistDao {
	public StylistDao() throws Exception{
		
	}
	
	public List<Integer> getStylistHistoryIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_latestViewStylistId` FROM `t_user` WHERE t_user_Id=" + user_id;
		List<Integer> stylistIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_latestViewStylistId");
			/*
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				stylistIdList.add(Integer.parseInt(temp));
			}
			*/
			ListUtilities listUtilities = new ListUtilities();
			List<String> stylistStrList = listUtilities.separateData(str_id_list);
			stylistIdList = listUtilities.convertList_str_int(stylistStrList);


		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistIdList;
	}

	public List<StylistInfo> getStylistHistoryInfo(DBConnection dbConnection, List<Integer> stylist_id_list) throws SQLException{
		String sql = 
				"SELECT `t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_imagePath`, `t_stylist_favoriteNumber`, `t_stylist_salonId` FROM `t_stylist` WHERE t_stylist_Id=";
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		
		/* 履歴にまだ登録されていない＝null */
		if(stylist_id_list.isEmpty()){
	 		stylistInfoList.add(retNull());
	 		return stylistInfoList;
		}
		
		Statement statement = dbConnection.getStatement();
		for(int index: stylist_id_list){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					StylistInfo stylistInfo = new StylistInfo();
					stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
					stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
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
	
	public StylistInfo getStylistDetailInfo(DBConnection dbConnection, Integer stylistId) throws SQLException{
		String sql = "SELECT `t_stylist_Id`, `t_stylist_salonId`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_imagePath`, `t_stylist_message`, `t_stylist_experienceYear`, `t_stylist_favoriteNumber`, `t_stylist_isNetReservation` FROM `t_stylist` WHERE t_stylist_Id =" + stylistId.toString();
		StylistInfo stylistInfo = new StylistInfo();
		Statement statement = dbConnection.getStatement();
		
		try{
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
				stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
				stylistInfo.setStylistName(rs.getString("t_stylist_name"));
				stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
				stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
				stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
				stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
				stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
				stylistInfo.setIsNetReservation(rs.getInt("t_stylist_isNetReservation"));
			}
	
		}catch (SQLException e) {
				e.printStackTrace();
				throw e;
		}
		return stylistInfo;		
	}
	
	/*
	 * Favorite
	 * */
	public List<Integer> getStylistFavoriteIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_favoriteStylistId` FROM `t_user` WHERE t_user_Id =" + user_id;
		List<Integer> stylistIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_favoriteStylistId");
			/*
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				stylistIdList.add(Integer.parseInt(temp));
			}
			*/
			ListUtilities listUtilities = new ListUtilities();
			List<String> stylistStrList = listUtilities.separateData(str_id_list);
			stylistIdList = listUtilities.convertList_str_int(stylistStrList);


		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistIdList;
	}

	public List<StylistInfo> getStylistFavoriteInfo(DBConnection dbConnection, List<Integer> stylist_id_list) throws SQLException{
		String sql = 
				"SELECT `t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_imagePath`, `t_stylist_goodNumber`, `t_stylist_salonId`, `t_stylist_message`, `t_stylist_experienceYear` FROM `t_stylist` WHERE t_stylist_Id=";
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		
		/* お気に入りにまだ何も登録されていない＝null */
	 	if(stylist_id_list.isEmpty()) {
	 		stylistInfoList.add(retNull());
	 		return stylistInfoList;
	 	}
		
		Statement statement = dbConnection.getStatement();
		for(int index: stylist_id_list){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					StylistInfo stylistInfo = new StylistInfo();
					stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
					stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
					stylistInfo.setStylistName(rs.getString("t_stylist_name"));
					stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
					stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
					stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
					stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
					stylistInfo.setIsGood(rs.getInt("t_stylist_goodNumber"));
					stylistInfoList.add(stylistInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return stylistInfoList;
	}	
	
	/*空っぽのデータをつっこむ*/
	public StylistInfo retNull(){
		StylistInfo stylistInfo = new StylistInfo();
		stylistInfo.setStylistId(Integer.MIN_VALUE);
		stylistInfo.setSalonId(Integer.MIN_VALUE);
		stylistInfo.setStylistName("");
		stylistInfo.setStylistGender(Integer.MIN_VALUE);
		stylistInfo.setStylistImagePath("");
		stylistInfo.setStylistYears(Integer.MIN_VALUE);
		stylistInfo.setStylistMessage("");
		stylistInfo.setIsGood(Integer.MIN_VALUE);

		return stylistInfo;

	}

	public List<StylistInfo> getStylistInfoList(DBConnection dbConnection, int salonId, List<String> areaIdList , List<String> searchConditionIdList , int pageNum,JSONObject jsonObject) throws SQLException{
		String sql = "SELECT `t_stylist_Id`,`t_stylist_areaId`,`t_stylist_searchConditionId`,`t_stylist_salonId` FROM `t_stylist` ORDER BY `t_stylist_Id` ASC";
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		Statement statement = dbConnection.getStatement();
		List<String> styListAreaIdList = new ArrayList<String>();
		List<String> stylistSearchConditionList = new ArrayList<String>();
		Map<Integer,List<String>> stylistAreaMap = new HashMap<Integer, List<String>>();
		Map<Integer,List<String>> stylistConditionMap = new HashMap<Integer, List<String>>();
		Map<Integer,Integer> stylistSalonMap = new HashMap<Integer, Integer>();
		
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String searchCondtionIds = rs.getString("t_stylist_searchConditionId");
				String areaIds = rs.getString("t_stylist_areaId");
				int stylistSalonId = rs.getInt("t_stylist_salonId");
				
				if(searchCondtionIds != null){
					String[] searchCondtionArray = searchCondtionIds.split(",");
					for(String condId : searchCondtionArray){
						stylistSearchConditionList.add(condId);
					}
				}
				
				if(areaIds != null){
					String[] areaIdsArray = areaIds.split(",");
					for(String areaId : areaIdsArray){
						styListAreaIdList.add(areaId);
					}
				}
				stylistConditionMap.put(rs.getInt("t_stylist_Id"), stylistSearchConditionList);
				stylistAreaMap.put(rs.getInt("t_stylist_Id"), styListAreaIdList);
				stylistSalonMap.put(rs.getInt("t_stylist_Id"),stylistSalonId);
			}
			List<Integer> retStylistIdList = new ArrayList<Integer>();
			for (Iterator<Entry<Integer, List<String>>> it = stylistConditionMap.entrySet().iterator(); it.hasNext();) {
			    Entry entry = it.next();
			    boolean searchCondFlag = false;
			    boolean areaFlag = false;
			    boolean salonFlag = false;
			    int stylistId = Integer.parseInt(entry.getKey().toString());
			    List<String> condList = stylistConditionMap.get(stylistId);
			    List<String> areaList = stylistAreaMap.get(stylistId);
			    int stylistSalonId = stylistSalonMap.get(stylistId);
			 
			    for(String condId : searchConditionIdList){
			    	if(condList.contains(condId)){
			    		searchCondFlag = true;
			    		break;
			    	}
			    }
			    for(String id :areaIdList ){
			    	if(areaList.contains(id)){
			    		areaFlag = true;
			    		break;
			    	}
			    }
			    
			    if(salonId == stylistSalonId){
			    	salonFlag = true;
			    }
			    if(searchCondFlag || areaFlag || salonFlag) retStylistIdList.add(stylistId);
			}
			
			String innerSQL ="SELECT `t_stylist_Id` ,`t_stylist_salonId`,`t_stylist_name`,`t_stylist_sex`,`t_stylist_imagePath`,`t_stylist_message`,`t_stylist_experienceYear`,`t_stylist_favoriteNumber` FROM `t_stylist` WHERE `t_stylist_Id` =";
			int hitCount=0;
			int offset = pageNum * Constant.ONE_PAGE_NUM;
			for(int retStylistId : retStylistIdList){
				if(Constant.ONE_PAGE_NUM > hitCount ){
					ResultSet innerRs = statement.executeQuery(innerSQL + String.valueOf(retStylistIdList.get(offset)) );
					while(innerRs.next()){
						StylistInfo stylistInfo = new StylistInfo();
						stylistInfo.setStylistId(innerRs.getInt("t_stylist_Id"));
						stylistInfo.setSalonId(innerRs.getInt("t_stylist_salonId"));
						stylistInfo.setStylistName(innerRs.getString("t_stylist_name"));
						stylistInfo.setStylistGender(innerRs.getInt("t_stylist_sex"));
						stylistInfo.setStylistImagePath(innerRs.getString("t_stylist_imagePath"));
						stylistInfo.setStylistMessage(innerRs.getString("t_stylist_message"));
						stylistInfo.setStylistYears(innerRs.getInt("t_stylist_experienceYear"));
						stylistInfo.setFavoriteNumber(innerRs.getInt("t_stylist_favoriteNumber"));
						stylistInfoList.add(stylistInfo);
						offset++;
						hitCount++;
					}
				}
				else{
					break;
				}
			}
			
			if(Constant.ONE_PAGE_NUM * pageNum + Constant.ONE_PAGE_NUM >= retStylistIdList.size()){
				jsonObject.put("next", 0);
			}
			else{
				jsonObject.put("next", 1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistInfoList;
	}	

	public List<StylistInfo> getStylistListInfo(DBConnection dbConnection, List<Integer> stylist_id_list) throws SQLException{
		String sql = 
				"SELECT `t_stylist_Id`, `t_stylist_name` FROM `t_stylist` WHERE t_stylist_Id=";
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		
	 	if(stylist_id_list.isEmpty()) {
	 		stylistInfoList.add(retNull());
	 		return stylistInfoList;
	 	}
		
		Statement statement = dbConnection.getStatement();
		for(int index: stylist_id_list){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					StylistInfo stylistInfo = new StylistInfo();
					stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
					stylistInfo.setStylistName(rs.getString("t_stylist_name"));
					stylistInfoList.add(stylistInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return stylistInfoList;
	}	
	
	public List<StylistInfo> getStylistInfoForMaster(DBConnection dbConnection, List<Integer> stylist_id_list) throws SQLException{
		/**
		 *
		    {
		      stylist:[
		        {
		          t_stylist_Id,
		          t_stylist_name,
		          t_stylist_sex,
		          t_stylist_phoneNumber,
		          t_stylist_mail,
		          t_stylist_imagePath,
		          t_stylist_birth,
		          t_stylist_position,
		          t_stylist_experienceYear,
		          t_stylist_specialMenu,
		          t_stylist_message,
		          t_menu_t_menu_id:カンマ区切りの文字列,
		        },
		        ...
		      ]
		    }
		 */
		
		String sql = 
				"SELECT `t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_phoneNumber`, "
				+ "`t_stylist_mail`, `t_stylist_imagePath`, `t_stylist_birth`,"
				+ "`t_stylist_position`, `t_stylist_experienceYear`, `t_stylist_specialMenu`, `t_stylist_message`, "
				+ "`t_stylist_menuId` FROM `t_stylist` WHERE t_stylist_Id="; 
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		
	 	if(stylist_id_list.isEmpty()) {
	 		stylistInfoList.add(retNull());
	 		return stylistInfoList;
	 	}
	 		 	
		Statement statement = dbConnection.getStatement();
		for(int index: stylist_id_list){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				//debug
			 	System.out.println(sql+Integer.toString(index));
				while(rs.next()){
					StylistInfo stylistInfo = new StylistInfo();
					stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
					stylistInfo.setStylistName(rs.getString("t_stylist_name"));
					stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
					stylistInfo.setPhoneNumber(rs.getString("t_stylist_phoneNumber"));
					stylistInfo.setMail(rs.getString("t_stylist_mail"));
					stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
					stylistInfo.setBirth(rs.getDate("t_stylist_birth"));
					stylistInfo.setPosition(rs.getString("t_stylist_position"));
					stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
					stylistInfo.setSpecialMenu(rs.getString("t_stylist_SpecialMenu"));
					stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
					stylistInfo.setMenuId(rs.getString("t_stylist_menuId"));
					stylistInfoList.add(stylistInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return stylistInfoList;
		
		

	}

	public int  setStylistInfoForMaster(DBConnection dbConnection, int salonId,
			StylistInfo stylistInfo) {
		int stylistId = -1;

		boolean result = false;
		
		/**
		 * stylistId からstylist情報があるかどうか確認。
		 * idがテーブルに存在したらx
		 * idが存在しなければinsertする
		 */

		/**
		 * SQL 例:
		 * 
		 * SELECT * FROM `t_stylist` WHERE `t_stylist_Id` = 1
		 * 
		 * INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` (`t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_detailText`, `t_stylist_userId`, `t_stylist_imagePath`, `t_stylist_position`, `t_stylist_message`, `t_stylist_experienceYear`, `t_stylist_specialMenu`, `t_stylist_goodNumber`, `t_stylist_viewNumber`, `t_stylist_mail`, `t_stylist_phoneNumber`, `t_stylist_birth`, `t_stylist_menuId`, `t_stylist_hairStyleId`, `t_stylist_salonId`, `t_stylist_favoriteNumber`, `t_stylist_isNetReservation`, `t_stylist_searchConditionId`, `t_stylist_areaId`) VALUES ('
		 * 10', 'name', '1', NULL, NULL, 'imagePath', 'position', 'message', '2', 'sp', NULL, NULL, 'mail.com', '090-', '2015-06-15 00:00:00', NULL, NULL, NULL, '0', '1', NULL, NULL);
		 * 
		 * SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = 1
		 * UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_stylistId` = '1,2,3,4' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = 1;
		 * 
		*/
		
		String sql_before = "SELECT * FROM `t_stylist` WHERE `t_stylist_Id` = "; // stylistId 
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` ("
				+ "`t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_detailText`, "
				+ "`t_stylist_userId`, `t_stylist_imagePath`, `t_stylist_position`, `t_stylist_message`, "
				+ "`t_stylist_experienceYear`, `t_stylist_specialMenu`, `t_stylist_goodNumber`, `t_stylist_viewNumber`, "
				+ "`t_stylist_mail`, `t_stylist_phoneNumber`, `t_stylist_birth`, `t_stylist_menuId`, "
				+ "`t_stylist_hairStyleId`, `t_stylist_salonId`, `t_stylist_favoriteNumber`, `t_stylist_isNetReservation`, "
				+ "`t_stylist_searchConditionId`, `t_stylist_areaId`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "NULL";
		String sql4 = "0";
		String sql_end = "');";
		
		String salon_sql1 = "SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
		String salon_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_stylistId` = '";
		String salon_sql2_middle = "' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = ";
		String salon_sql2_after = ";";

		Statement statement = dbConnection.getStatement();
		
		for(int i=1; i<Integer.MAX_VALUE; i++){
			try {
				ResultSet rs = statement.executeQuery(sql_before+Integer.toString(i));
				if(!rs.next()){
					stylistId = i;
					break;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		//date '2015-06-03 00:00:00' format sample.
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(stylistInfo.getBirth());
		
		String sql = sql1 +stylistId + sql2
				+ stylistInfo.getStylistName() + sql2
				+ stylistInfo.getStylistGender()  + sql2
				+ sql3 + sql2 //detail
				+ sql4 + sql2 //userId
				+ stylistInfo.getStylistImagePathStr()  + sql2
				+ stylistInfo.getPosition()  + sql2
				+ stylistInfo.getStylistMessage()  + sql2
				+ stylistInfo.getStylistYearsNumber().charAt(0) + sql2
				+ stylistInfo.getSpecialMenu() + sql2
				+ sql4 + sql2 //goodNum
				+ sql4 + sql2 //viewNum
				+ stylistInfo.getMail() + sql2
				+ stylistInfo.getPhoneNumber() + sql2
				+ birth + sql2
				+ sql3 + sql2 //MENU
				+ sql4 + sql2 //hairStyleId
				+ salonId + sql2 //salonId
				+ sql4 + sql2 //favorite
				+ sql4 + sql2 //isNetReserv
				+ sql4 + sql2 //search
				+ sql4 //areaId
				+ sql_end;

		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql);
			if(result_int >= 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//* stylist をsalon　に足さなきゃ
		ResultSet rs;
		String stylistIdList = "";
		try {
			rs = statement.executeQuery(salon_sql1+salonId);
			while(rs.next()){
				stylistIdList = rs.getString("t_hairSalonMaster_stylistId");
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(stylistId != -1){
			String salon_sql =  salon_sql2_before + stylistIdList + "," + stylistId + salon_sql2_middle + salonId + salon_sql2_after;
			System.out.println(salon_sql);
			try {
				int result_int = statement.executeUpdate(salon_sql);
				if(result_int > 0) stylistId = -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				stylistId = -1;
			}
		}		
		return stylistId;
	}

	public boolean setStylistMenuForMaster(DBConnection dbConnection,
			String t_stylist_Id, String t_menu_t_menu_id) {
		boolean result = false;
		
		/**
		 * stylistId からstylist情報があるかどうか確認。
		 * idがテーブルに存在したらupdate
		 * idが存在しなければx
		 */

		/**
		 * SQL 例:
		 * UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_menuId` = '1,2,3' WHERE `t_stylist`.`t_stylist_Id` = 13;
		 * 
		*/
		
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_menuId` = '";
		String sql2 = "' WHERE `t_stylist`.`t_stylist_Id` = ";
		String sql3 = ";";
		Statement statement = dbConnection.getStatement();
		
		String sql = sql1 + t_menu_t_menu_id + sql2 + t_stylist_Id + sql3;

		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql);
			if(result_int >= 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
	}

	public boolean DeleteStylistMenuForMaster(DBConnection dbConnection,
			String t_stylist_Id) {
		boolean result = false;
		
		/**
		 * stylistId からstylist情報があるかどうか確認。
		 * idがテーブルに存在したらupdate
		 * idが存在しなければx
		 */

		/**
		 * SQL 例:
		 *UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_menuId` = '' WHERE `t_stylist`.`t_stylist_Id` = 13;
		 * 
		*/
		
		String sql = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_menuId` = '' WHERE `t_stylist`.`t_stylist_Id` = ";
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql + t_stylist_Id);
			if(result_int > 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
	}
	
	public boolean DeleteStylistInfoForMaster(DBConnection dbConnection,
			String t_stylist_Id) {
			boolean result = false;
			
			/**
			 * stylistId からstylist情報があるかどうか確認。
			 * idがテーブルに存在したらupdate
			 * idが存在しなければx
			 */

			/**
			 * SQL 例:
			 * DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` WHERE `t_stylist`.`t_stylist_Id` = 13
			 * 
			*/
			
			String sql = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` WHERE `t_stylist`.`t_stylist_Id` = ";
			Statement statement = dbConnection.getStatement();
			
			//debug
			System.out.println(sql);
			
			try {
				int result_int = statement.executeUpdate(sql + t_stylist_Id);
				System.out.println(result_int);
				if(result_int > 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return result;	
		}
	
	
}
