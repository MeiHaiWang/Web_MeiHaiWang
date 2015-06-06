package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.media.jfxmedia.logging.Logger;

import common.model.BeautyNewsInfo;
import common.model.HairSalonInfo;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class SalonDao {
	
	public SalonDao(){
		
	}
	
	public List<HairSalonInfo> getSalonDetailInfo(Integer salonId ,DBConnection dbConnection) throws SQLException{

		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		String sql = "SELECT `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_evaluationId`, `t_hairSalonMaster_message`, `t_hairSalonMaster_phoneNumber`, `t_hairSalonMaster_address`, `t_hairSalonMaster_openTime`, `t_hairSalonMaster_closeTime`, `t_hairSalonMaster_closeDay`, `t_hairSalonMaster_reviewId`, `t_hairSalonMaster_favoriteNumber` , `t_hairSalonMaster_availableCountryId` , `t_hairSalonMaster_isNetReservation` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` =" + salonId.toString();		
		Statement statement = dbConnection.getStatement();
		try {
			List<String> reviewIdList =  new ArrayList<String>();
			List<String> evaluationIdList = new ArrayList<String>();
			List<String> availabuleCountryIdList = new ArrayList<String>();
			
			ResultSet rs = statement.executeQuery(sql);
			HairSalonInfo salonInfo = new HairSalonInfo();
			while(rs.next()){
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
				
				evaluationIdList = rs.getString("t_hairSalonMaster_evaluationId") != null?
						Arrays.asList(rs.getString("t_hairSalonMaster_evaluationId").split(",")) : new ArrayList<String>();
						
				availabuleCountryIdList = rs.getString("t_hairSalonMaster_availableCountryId") != null?
						Arrays.asList(rs.getString("t_hairSalonMaster_availableCountryId").split(",")) : new ArrayList<String>();
			}	

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
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				SalonIdList.add(Integer.parseInt(temp));
			}

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
	
	
	public List<Integer> getHairSalonEvaluationIdList(DBConnection dbConnection, int salonId) throws SQLException{
		String sql = 
				"SELECT `t_hairSalonMaster_evaluationId`, `t_hairSalonMaster_reviewId` "
				+ "FROM `t_hairSalonMaster` WHERE t_hairSalonMaster_salonId=" + salonId;
		
		Statement statement = dbConnection.getStatement();
		List<Integer> evalList = new ArrayList<Integer>();
		
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String evalStr = rs.getString("t_hairSalonMaster_evaluationId");
				String str2 = evalStr;
		    	int i = 0;
		    	while(i<=evalStr.length()){
		    		int idx = evalStr.indexOf(',', i);
		    		if(idx>0){
				    	str2 = evalStr.substring(i, idx);		    			
		    		}
			    	//jsonOneData.put("image"+Integer.toString(num++), str2);
		    		evalList.add(Integer.parseInt(str2));
			    	i+=(str2.length()+1);
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return evalList;
	}

	public List<Integer> getHairSalonReviewIdList(DBConnection dbConnection, int salonId) throws SQLException{
		String sql = 
				"SELECT `t_hairSalonMaster_evaluationId`, `t_hairSalonMaster_reviewId` "
				+ "FROM `t_hairSalonMaster` WHERE t_hairSalonMaster_salonId=" + salonId;
		
		Statement statement = dbConnection.getStatement();
		List<Integer> reviewIdList = new ArrayList<Integer>();
		
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String reviewIdStr = rs.getString("t_hairSalonMaster_evaluationId");
				String str2 = reviewIdStr;
		    	int i = 0;
		    	while(i<=reviewIdStr.length()){
		    		int idx = reviewIdStr.indexOf(',', i);
		    		if(idx>0){
				    	str2 = reviewIdStr.substring(i, idx);		    			
		    		}
			    	//jsonOneData.put("image"+Integer.toString(num++), str2);
		    		reviewIdList.add(Integer.parseInt(str2));
			    	i+=(str2.length()+1);
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
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

	public List<HairSalonInfo> getSalonInfoListByArea(DBConnection dbConnection, List<String> areaIdList){
		return null;
	}
	
}
