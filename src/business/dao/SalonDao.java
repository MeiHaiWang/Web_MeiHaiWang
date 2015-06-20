package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.media.jfxmedia.logging.Logger;

import common.constant.Constant;
import common.model.BeautyNewsInfo;
import common.model.HairSalonInfo;
import common.model.HairSalonInfo;
import common.util.DBConnection;
import common.util.ListUtilities;

public class SalonDao {
	
	public SalonDao(){
		
	}
	
	public List<HairSalonInfo> getSalonDetailInfo(Integer salonId ,DBConnection dbConnection) throws SQLException{

		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		String sql = "SELECT `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, `t_hairSalonMaster_phoneNumber`, `t_hairSalonMaster_address`, `t_hairSalonMaster_openTime`, `t_hairSalonMaster_closeTime`, `t_hairSalonMaster_closeDay`, `t_hairSalonMaster_reviewId`, `t_hairSalonMaster_favoriteNumber` , `t_hairSalonMaster_availableCountryId` , `t_hairSalonMaster_isNetReservation` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` =" + salonId.toString();		
		String sql2 = "SELECT `t_review_evaluation_point` FROM t_review WHERE FIND_IN_SET(";
		String sql3 = ",t_review_id)";
		Statement statement = dbConnection.getStatement();
		try {
			List<String> reviewIdList =  new ArrayList<String>();
			List<String> evaluationIdList = new ArrayList<String>();
			List<String> availabuleCountryIdList = new ArrayList<String>();
			
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			HairSalonInfo salonInfo = new HairSalonInfo();
			while(rs.next()){
				salonInfo = new HairSalonInfo();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				salonInfo.setHairSalonId(salonId);
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				salonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
				salonInfo.setTelNumber(rs.getString("t_hairSalonMaster_phoneNumber"));
				salonInfo.setAddress(rs.getString("t_hairSalonMaster_address"));
				Date openTime = rs.getDate("t_hairSalonMaster_openTime") !=null ?
						rs.getDate("t_hairSalonMaster_openTime"): new Date(0);
				Date closeTime = rs.getDate("t_hairSalonMaster_closeTime") !=null ?
						rs.getDate("t_hairSalonMaster_closeTime") : new Date(0);
				salonInfo.setBusinessHour(sdf.format(openTime).toString() + "〜"  + sdf.format(closeTime));
				salonInfo.setRegularHoliday(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
				salonInfo.setIsNetReservation(rs.getInt("t_hairSalonMaster_isNetReservation"));
				
				reviewIdList = rs.getString("t_hairSalonMaster_reviewId") != null ?
						Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
				salonInfo.setWordOfMonth(reviewIdList.size());
				
				/*
				evaluationIdList = rs.getString("t_hairSalonMaster_evaluationId") != null?
						Arrays.asList(rs.getString("t_hairSalonMaster_evaluationId").split(",")) : new ArrayList<String>();
				*/
						
				availabuleCountryIdList = rs.getString("t_hairSalonMaster_availableCountryId") != null?
						Arrays.asList(rs.getString("t_hairSalonMaster_availableCountryId").split(",")) : new ArrayList<String>();
			}	

			/*
			String innerSql = "SELECT `t_evaluation_point` FROM `t_evaluation` WHERE FIND_IN_SET(t_evaluation_evaluationId,'" + String.join(",", evaluationIdList.toArray(new String[0])).toString() + "')"; 
			System.out.println(innerSql);
			ResultSet innerRs = statement.executeQuery(innerSql);
			Double evaluationPoint = 0.0;
			double i= 0.0;
			while(innerRs.next()){
				evaluationPoint += innerRs.getDouble("t_evaluation_point");
				i++;
			}
			salonInfo.setEvaluationPointMid(evaluationPoint / i);
			*/
			
			double reviewPoint = 0.0;
			int reviewNumber = 0;
			for(String reviewId : reviewIdList){
				ResultSet rsReview = statement.executeQuery(sql2 +reviewId + sql3);
				while(rsReview.next()){
					reviewPoint += rsReview.getDouble("t_review_evaluation_point");
					reviewNumber++;
				}
			}
			if(reviewNumber > 0){ //zero-divide
				salonInfo.setEvaluationPointMid(reviewPoint/reviewNumber);
			}else{
				salonInfo.setEvaluationPointMid(0);	
			}
			salonInfo.setWordOfMonth(reviewNumber);


				
			String innerSql2 ="SELECT t_country_countryName FROM t_masterCountry WHERE FIND_IN_SET(t_country_countryId,'" + String.join(",",availabuleCountryIdList.toArray(new String[0])).toString() +"')";
			ResultSet innerRs2 = statement.executeQuery(innerSql2);
			List<String> availableCountryName = new ArrayList<String>();
			while(innerRs2.next()){
				availableCountryName.add(innerRs2.getString("t_country_countryName"));
			}
			salonInfo.setMultiLingual(String.join(",", availableCountryName.toArray(new String[0])));
			salonInfoList.add(salonInfo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfoList;
	}
	
	public HairSalonInfo getSalonMapInfo(Integer salonId ,DBConnection dbConnection) throws SQLException{
		String sql ="SELECT `t_hairSalonMaster_mapUrl`, `t_hairSalonMaster_mapImagePath`, `t_hairSalonMaster_mapLatitude`, `t_hairSalonMaster_mapLongitude`, `t_hairSalonMaster_mapInfoText` FROM `t_hairSalonMaster` WHERE t_hairSalonMaster_salonId=" + salonId.toString(); 		
		Statement statement = dbConnection.getStatement();
		HairSalonInfo salonInfo = new HairSalonInfo();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				salonInfo.setSalonMapImagePath(rs.getString("t_hairSalonMaster_mapImagePath"));
				/*
				salonInfo.setSalonLatitude(rs.getDouble("t_hairSalonMaster_mapLatitude"));
				salonInfo.setSalonLongitude(rs.getDouble("t_hairSalonMaster_mapLongitude"));
				 */
				Double ido = Double.parseDouble(rs.getString("t_hairSalonMaster_mapLatitude"));
				Double keido = Double.parseDouble(rs.getString("t_hairSalonMaster_mapLongitude"));
				salonInfo.setSalonLatitude(ido);
				salonInfo.setSalonLongitude(keido);

				salonInfo.setSalonMapInfo(rs.getString("t_hairSalonMaster_mapInfoText"));
				
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfo;		
	}
	
	
	/*
	 * History
	 * */
	public List<Integer> getHairSalonHistoryIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_latestViewSalonId` FROM `t_user` WHERE t_user_Id = " + user_id;
		List<Integer> idList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_latestViewSalonId");
			/*
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				idList.add(Integer.parseInt(temp));
			}
			*/
			ListUtilities listUtilities = new ListUtilities();
			List<String> salonStrList = listUtilities.separateData(str_id_list);
			idList = listUtilities.convertList_str_int(salonStrList);

			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return idList;
	}

	public List<HairSalonInfo> getHairSalonHistoryInfo(DBConnection dbConnection, List<Integer> idList) throws SQLException{
		String sql = 
				"SELECT `t_hairSalonMaster_salonID`, `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, `t_area_areaName` FROM `t_hairSalonMaster` JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId WHERE t_hairSalonMaster_salonId =";
		List<HairSalonInfo> infoList = new ArrayList<HairSalonInfo>();
		
		/* 履歴にまだ何も登録されていない＝null */
		if(idList.isEmpty()){
			infoList.add(retNull());
	 		return infoList;
		}
		
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
	
	
	/*
	 * Favorite
	 * */
	public List<Integer> getSalonFavoriteIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_favoriteSalonId` FROM `t_user` WHERE t_user_Id =" + user_id;
		List<Integer> SalonIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_favoriteSalonId");
			/*
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				SalonIdList.add(Integer.parseInt(temp));
			}
			*/
			ListUtilities listUtilities = new ListUtilities();
			List<String> salonStrList = listUtilities.separateData(str_id_list);
			SalonIdList = listUtilities.convertList_str_int(salonStrList);


		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return SalonIdList;
	}

	public List<HairSalonInfo> getSalonFavoriteInfo(DBConnection dbConnection, List<Integer> Salon_id_list) throws SQLException{
		String sql = 
				"SELECT `t_hairSalonMaster_salonId`, `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, "
				+ "`t_area_areaName` FROM t_hairSalonMaster JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId WHERE t_hairSalonMaster_salonId =";
		List<HairSalonInfo> SalonInfoList = new ArrayList<HairSalonInfo>();
		
		/* お気に入りにまだ何も登録されていない＝null */
	 	if(Salon_id_list.isEmpty()) {
	 		SalonInfoList.add(retNull());
	 		return SalonInfoList;
	 	}
		
		Statement statement = dbConnection.getStatement();
		for(int index: Salon_id_list){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					HairSalonInfo SalonInfo = new HairSalonInfo();
					SalonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
					SalonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
					SalonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
					SalonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
					ArrayList<String> areaNameList = new ArrayList<String>();
					areaNameList.add(rs.getString("t_area_areaName"));
					SalonInfo.setAreaNameList(areaNameList);
					SalonInfoList.add(SalonInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return SalonInfoList;
	}	
	
	public List<Integer> getHairSalonReviewIdList(DBConnection dbConnection, int salonId) throws SQLException{
		String sql = 
				"SELECT `t_hairSalonMaster_reviewId` "
				+ "FROM `t_hairSalonMaster` WHERE t_hairSalonMaster_salonId=" + salonId;
		
		Statement statement = dbConnection.getStatement();
		List<Integer> reviewIdList = new ArrayList<Integer>();
		
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String reviewIdStr = rs.getString("t_hairSalonMaster_reviewId");
				ListUtilities listUtilities = new ListUtilities();
				List<String> reviewIdStrList = listUtilities.separateData(reviewIdStr);
				reviewIdList = listUtilities.convertList_str_int(reviewIdStrList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		//Debug
		/*
		for(int reviewId : reviewIdList)
			System.out.println("reviewId: "+reviewId);
			*/
		
		return reviewIdList;
	}	
	
	/*空っぽのデータをつっこむ*/
	public HairSalonInfo retNull(){
		/*
		List<String> reviewIdList =  new ArrayList<String>();
		List<String> evaluationIdList = new ArrayList<String>();
		List<String> availabuleCountryIdList = new ArrayList<String>();
		*/

		HairSalonInfo salonInfo = new HairSalonInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		salonInfo.setHairSalonId(Integer.MIN_VALUE);
		salonInfo.setHairSalonName("");
		salonInfo.setHairSalonImagePath("");
		salonInfo.setMessage("");
		salonInfo.setTelNumber("");
		salonInfo.setAddress("");
		Date openTime = new Date(0);
		Date closeTime = new Date(0);
		salonInfo.setBusinessHour(sdf.format(openTime).toString() + "〜"  + sdf.format(closeTime));
		salonInfo.setRegularHoliday("");
		salonInfo.setFavoriteNumber(Integer.MIN_VALUE);
		salonInfo.setIsNetReservation(Integer.MIN_VALUE);
		salonInfo.setWordOfMonth(Integer.MIN_VALUE);

		/*
		reviewIdList =  new ArrayList<String>();
		
		evaluationIdList = new ArrayList<String>();
		availabuleCountryIdList =  new ArrayList<String>();
		*/
		
		return salonInfo;
	}

	public List<String> getSalonIdListByArea(DBConnection dbConnection, List<String> areaIdList) throws SQLException{
		
		String sql1 = "SELECT t_hairSalonMaster_salonId FROM t_hairSalonMaster WHERE  FIND_IN_SET(";
		String sql2 =  ",t_hairSalonMaster_areaId)";
		
		Statement statement = dbConnection.getStatement();
		List<String> salonIdList = new ArrayList<String>();
		
		try {
			for(String areaId : areaIdList){
				System.out.println(sql1 + areaId+ sql2);
				ResultSet rs = statement.executeQuery(sql1 + areaId+ sql2);
				while(rs.next()){
					if(!salonIdList.contains(rs.getInt("t_hairSalonMaster_salonId"))){
						salonIdList.add(String.valueOf(rs.getInt("t_hairSalonMaster_salonId")));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonIdList;		
	}
	
	public List<HairSalonInfo> getSalonListBySearchCondition(DBConnection dbConnection, List<String> searchConditionIdList,List<String> salonIdList,int pageNumber,JSONObject jsonObject) throws SQLException{
		
		String sql1 = "SELECT `t_hairSalonMaster_salonId` , `t_hairSalonMaster_name` , "
				+ "`t_hairSalonMaster_salonImagePath`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_message`,`t_hairSalonMaster_address`,`t_hairSalonMaster_openTime`,"
				+ "`t_hairSalonMaster_closeTime`,`t_hairSalonMaster_closeDay`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_favoriteNumber` FROM `t_hairSalonMaster` WHERE t_hairSalonMaster_salonId IN(";
		String sql2 =  ") AND FIND_IN_SET(";
		String sql3 = ",`t_hairSalonMaster_searchConditionId`) ORDER BY `t_hairSalonMaster_salonId` limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " + String.valueOf(pageNumber * Constant.ONE_PAGE_NUM) ;
		String sql4 = "SELECT `t_review_evaluation_point` FROM t_review WHERE FIND_IN_SET(";
		String sql5 = ",t_review_id)";
		Statement statement = dbConnection.getStatement();
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		int hitNum = 0;
		try {
			for(String conditionId : searchConditionIdList){
				String sql = sql1 + String.join(",", salonIdList)+ sql2 + conditionId + sql3;
				//debug
				System.out.println(sql);
				ResultSet rs = statement.executeQuery(sql);
				List<String> reviewIdList = new ArrayList<String>();
				HairSalonInfo salonInfo = new HairSalonInfo();
				while(rs.next()){
					salonInfo = new HairSalonInfo();
					salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
					salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
					salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
					reviewIdList = rs.getString("t_hairSalonMaster_reviewId")!= null ?
							Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
					salonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
					salonInfo.setAddress(rs.getString("t_hairSalonMaster_address"));
					Date openTime = rs.getDate("t_hairSalonMaster_openTime") !=null ?
							rs.getDate("t_hairSalonMaster_openTime"): new Date(0);
					Date closeTime = rs.getDate("t_hairSalonMaster_closeTime") !=null ?
							rs.getDate("t_hairSalonMaster_closeTime") : new Date(0);
					salonInfo.setBusinessHour(sdf.format(openTime).toString() + "〜"  + sdf.format(closeTime));
					salonInfo.setRegularHoliday(rs.getString("t_hairSalonMaster_closeDay"));
					salonInfo.setFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
					hitNum++;
				}
				
				double reviewPoint = 0.0;
				int reviewNumber = 0;
				for(String reviewId : reviewIdList){
					ResultSet rsReview = statement.executeQuery(sql4 +reviewId + sql5);
					while(rsReview.next()){
						reviewPoint += rsReview.getDouble("t_review_evaluation_point");
						reviewNumber++;
					}
				}
				if(reviewNumber > 0){ //zero-divide
					salonInfo.setEvaluationPointMid(reviewPoint/reviewNumber);
				}else{
					salonInfo.setEvaluationPointMid(0);	
				}
				salonInfo.setWordOfMonth(reviewNumber);

				//重複確認
				boolean addFlag = true;
				for(HairSalonInfo info : salonInfoList){
					if(info.getHairSalonId() ==  salonInfo.getHairSalonId()){
						addFlag = false;
					}
				}
				if(addFlag) salonInfoList.add(salonInfo);
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
		return salonInfoList;		
	}
	
	//
	public String getCheckSession(DBConnection dbConnection, Integer salonId) throws SQLException{
		String sql ="SELECT `t_hairSalonMaster_contactUserName` FROM `t_hairSalonMaster` WHERE t_hairSalonMaster_salonId = " + salonId; 		
		Statement statement = dbConnection.getStatement();
		HairSalonInfo salonInfo = new HairSalonInfo();
		//contactUserName だけかも?
		String contactUserName = "";
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String salon_contactUserName = rs.getString("t_hairSalonMaster_contactUserName");
				salonInfo.setSalonContactUserName(salon_contactUserName);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		contactUserName = salonInfo.getSalonContactName();
		return contactUserName;		
	}

	public List<Integer> getMenuIdList(DBConnection dbConnection, Integer salonId){
		String sql ="SELECT `t_hairSalonMaster_menuId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = " + salonId; 		
		Statement statement = dbConnection.getStatement();
		List<Integer> menuIdList = new ArrayList<Integer>();
		HairSalonInfo salonInfo = new HairSalonInfo();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				int menuId = rs.getInt("t_hairSalonMaster_menuId");
				//salonInfo.setSalonContactUserName(salon_contactUserName);
				menuIdList.add(menuId);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuIdList;	
	}

	public List<Integer> getStylistIdList(DBConnection dbConnection, Integer salonId){
		String sql ="SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = " + salonId; 		
		Statement statement = dbConnection.getStatement();
		List<Integer> stylistIdList = new ArrayList<Integer>();
		HairSalonInfo salonInfo = new HairSalonInfo();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				int stylistId = rs.getInt("t_hairSalonMaster_stylistId");
				//salonInfo.setSalonContactUserName(salon_contactUserName);
				stylistIdList.add(stylistId);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stylistIdList;	
	}
	
}
