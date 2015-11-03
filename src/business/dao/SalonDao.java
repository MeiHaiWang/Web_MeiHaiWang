package business.dao;

import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

import com.mysql.fabric.xmlrpc.base.Array;

import common.constant.Constant;
import common.constant.TableConstant;
import common.model.BeautyNewsInfo;
import common.model.HairSalonInfo;
import common.model.HairSalonInfo;
import common.model.IBaseInfo;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;
import common.util.ListUtilities;

public class SalonDao extends BaseDao{
	private static Logger logger = LogManager.getLogger();

	public SalonDao(){
	}
	
	public int getSalonIntData(DBConnection dbConnection ,String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{
		return getIntValue(dbConnection, Constant.TABLE_SALON, targetColumnName, sourceColumnName, sourceColumnValue );
	}
	public String getSalonStringData(DBConnection dbConnection , String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{
		return getStringValue(dbConnection, Constant.TABLE_SALON, targetColumnName, sourceColumnName, sourceColumnValue);
	}
	public int getSalonIntData(DBConnection dbConnection, String columnName, IBaseInfo info) throws SQLException{
		return getIntValue(dbConnection, Constant.TABLE_SALON, columnName, TableConstant.COLUMN_SALON_ID, info);
	}
	public int setSalonIntData(DBConnection dbConnection , String columnName, int targetColumValue, IBaseInfo info) throws SQLException{
		return setIntValue(dbConnection, Constant.TABLE_SALON, columnName,  targetColumValue, TableConstant.COLUMN_SALON_ID, info);
	}
	public String getSalonStringData(DBConnection dbConnection , String columnName,  IBaseInfo info) throws SQLException{
		return getStringValue(dbConnection, Constant.TABLE_SALON, columnName, TableConstant.COLUMN_SALON_ID, info);
	}
	public int setSalonStringData(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		return setStringValue(dbConnection, Constant.TABLE_SALON, targetColumnName, value, TableConstant.COLUMN_SALON_ID, info);
	}
	public int appendId(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		int result = -1;
		String data = getSalonStringData(dbConnection, targetColumnName, info);
		if(data!=null){
			data = data + ", "+ value;
			result = setSalonStringData(dbConnection, targetColumnName, data, info);
		}else{
			result = setSalonStringData(dbConnection, targetColumnName, value, info);
		}
		return result;
	}
	public int removeId(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		int result = -1;
		String idlist = getSalonStringData(dbConnection, targetColumnName, info);
		if(idlist!=null){
			List<String> idList = Arrays.asList(idlist.split(","));
			String retIdlist = "";
			int index=0;
			for(String id: idList){
				if(id.equals(value)) continue;
				if(index==0){
					retIdlist += id;
					index++;
				}else{
					retIdlist += ","+id;
					index++;
				}
			}
			result = setSalonStringData(dbConnection, targetColumnName, retIdlist, info);
		}
		return result;
	}

	public HairSalonInfo getSalonObject(DBConnection dbConnection, int id) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_SALON);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_SALON_ID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(id);
		
		Statement statement = dbConnection.getStatement();
		HairSalonInfo salonInfo = null;

		try {			
			ResultSet rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setSalonDetailText(rs.getString("t_hairSalonMaster_detailText"));
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				salonInfo.setSalonOpenTime(rs.getString("t_hairSalonMaster_openTime"));
				salonInfo.setSalonCloseTime(rs.getString("t_hairSalonMaster_closeTime"));
				salonInfo.setSalonCloseDay(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setSalonCreditAvailable(rs.getInt("t_hairSalonMaster_creditAvailable"));
				salonInfo.setSalonCarParkAvailable(rs.getInt("t_hairSalonMaster_carParkAvailable"));
				String t_area_areaId = rs.getString("t_hairSalonMaster_areaId");
				salonInfo.setSalonAreaId(t_area_areaId);
				int countryId = rs.getInt("t_hairSalonMaster_availableCountryId");
				salonInfo.setSalonSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
				int japaneseAvailable = 0;
				if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = 1;
				salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				salonInfo.setMail(rs.getString("t_hairSalonMaster_mail"));
				List<String> reviewIdList = rs.getString("t_hairSalonMaster_reviewId") != null ?
						Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
				salonInfo.setWordOfMonth(reviewIdList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfo;
	}
	
	public HairSalonInfo getSalonObjectByColumn(DBConnection dbConnection, String culumnName , String value) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_SALON);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(culumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(value);
		sql.append(Constant.SINGLEQ);
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		HairSalonInfo salonInfo = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setSalonDetailText(rs.getString("t_hairSalonMaster_detailText"));
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				salonInfo.setSalonOpenTime(rs.getString("t_hairSalonMaster_openTime"));
				salonInfo.setSalonCloseTime(rs.getString("t_hairSalonMaster_closeTime"));
				salonInfo.setSalonCloseDay(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setSalonCreditAvailable(rs.getInt("t_hairSalonMaster_creditAvailable"));
				salonInfo.setSalonCarParkAvailable(rs.getInt("t_hairSalonMaster_carParkAvailable"));
				String t_area_areaId = rs.getString("t_hairSalonMaster_areaId");
				salonInfo.setSalonAreaId(t_area_areaId);
				int countryId = rs.getInt("t_hairSalonMaster_availableCountryId");
				salonInfo.setSalonSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
				int japaneseAvailable = 0;
				if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = 1;
				salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				salonInfo.setMail(rs.getString("t_hairSalonMaster_mail"));
				List<String> reviewIdList = rs.getString("t_hairSalonMaster_reviewId") != null ?
						Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
				salonInfo.setWordOfMonth(reviewIdList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfo;
	}
	
	public List<HairSalonInfo> getSalonObjectListByColumn(DBConnection dbConnection, String culumnName , String value) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_SALON);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(culumnName);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(Constant.SINGLEQ);
		sql.append(value);
		sql.append(Constant.SINGLEQ);
		
		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				HairSalonInfo salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setSalonDetailText(rs.getString("t_hairSalonMaster_detailText"));
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				salonInfo.setSalonOpenTime(rs.getString("t_hairSalonMaster_openTime"));
				salonInfo.setSalonCloseTime(rs.getString("t_hairSalonMaster_closeTime"));
				salonInfo.setSalonCloseDay(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setSalonCreditAvailable(rs.getInt("t_hairSalonMaster_creditAvailable"));
				salonInfo.setSalonCarParkAvailable(rs.getInt("t_hairSalonMaster_carParkAvailable"));
				String t_area_areaId = rs.getString("t_hairSalonMaster_areaId");
				salonInfo.setSalonAreaId(t_area_areaId);
				int countryId = rs.getInt("t_hairSalonMaster_availableCountryId");
				salonInfo.setSalonSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
				int japaneseAvailable = 0;
				if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = 1;
				salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				salonInfo.setMail(rs.getString("t_hairSalonMaster_mail"));
				List<String> reviewIdList = rs.getString("t_hairSalonMaster_reviewId") != null ?
						Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
				salonInfo.setWordOfMonth(reviewIdList.size());
				salonInfoList.add(salonInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfoList;
	}
	
	public HairSalonInfo getSalonObjectByColumnMap(DBConnection dbConnection, Map<String, String> sourceData) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_SALON);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		int index = 0;
		for(Map.Entry<String, String> entry : sourceData.entrySet()) {
			//System.out.println(entry.getKey() + " => " + entry.getValue());
			if(index==0){
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}else{
				sql.append(Constant.SPACE);
				sql.append(Constant.AND);
				sql.append(Constant.SPACE);
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}
		}

		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		HairSalonInfo salonInfo = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setSalonDetailText(rs.getString("t_hairSalonMaster_detailText"));
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				salonInfo.setSalonOpenTime(rs.getString("t_hairSalonMaster_openTime"));
				salonInfo.setSalonCloseTime(rs.getString("t_hairSalonMaster_closeTime"));
				salonInfo.setSalonCloseDay(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setSalonCreditAvailable(rs.getInt("t_hairSalonMaster_creditAvailable"));
				salonInfo.setSalonCarParkAvailable(rs.getInt("t_hairSalonMaster_carParkAvailable"));
				String t_area_areaId = rs.getString("t_hairSalonMaster_areaId");
				salonInfo.setSalonAreaId(t_area_areaId);
				int countryId = rs.getInt("t_hairSalonMaster_availableCountryId");
				salonInfo.setSalonSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
				int japaneseAvailable = 0;
				if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = 1;
				salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				salonInfo.setMail(rs.getString("t_hairSalonMaster_mail"));
				List<String> reviewIdList = rs.getString("t_hairSalonMaster_reviewId") != null ?
						Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
				salonInfo.setWordOfMonth(reviewIdList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfo;
	}
	
	public List<HairSalonInfo> getSalonObjectListByColumnMap(DBConnection dbConnection, Map<String, String> sourceData) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_SALON);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		int index = 0;
		for(Map.Entry<String, String> entry : sourceData.entrySet()) {
			//System.out.println(entry.getKey() + " => " + entry.getValue());
			if(index==0){
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}else{
				sql.append(Constant.SPACE);
				sql.append(Constant.AND);
				sql.append(Constant.SPACE);
				sql.append(Constant.BACKQ);
				sql.append(entry.getKey());
				sql.append(Constant.BACKQ);
				sql.append(Constant.SPACE);
				sql.append(Constant.EQUAL);
				sql.append(Constant.SPACE);
				sql.append(Constant.SINGLEQ);
				sql.append(entry.getValue());
				sql.append(Constant.SINGLEQ);
				index++;
			}
		}

		Statement statement = dbConnection.getStatement();
		ResultSet rs = null;
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				HairSalonInfo salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setSalonDetailText(rs.getString("t_hairSalonMaster_detailText"));
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				salonInfo.setSalonOpenTime(rs.getString("t_hairSalonMaster_openTime"));
				salonInfo.setSalonCloseTime(rs.getString("t_hairSalonMaster_closeTime"));
				salonInfo.setSalonCloseDay(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setSalonCreditAvailable(rs.getInt("t_hairSalonMaster_creditAvailable"));
				salonInfo.setSalonCarParkAvailable(rs.getInt("t_hairSalonMaster_carParkAvailable"));
				String t_area_areaId = rs.getString("t_hairSalonMaster_areaId");
				salonInfo.setSalonAreaId(t_area_areaId);
				int countryId = rs.getInt("t_hairSalonMaster_availableCountryId");
				salonInfo.setSalonSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
				int japaneseAvailable = 0;
				if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = 1;
				salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				salonInfo.setMail(rs.getString("t_hairSalonMaster_mail"));
				salonInfoList.add(salonInfo);
				List<String> reviewIdList = rs.getString("t_hairSalonMaster_reviewId") != null ?
						Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
				salonInfo.setWordOfMonth(reviewIdList.size());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfoList;
	}
	
	////////////
	
	
	public boolean registSalonOnDisable(HairSalonInfo salonInfo,DBConnection dbConnection){
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` ("
				+ "`t_hairSalonMaster_contactUserName`, `t_hairSalonMaster_name`, `t_hairSalonMaster_areaId`, `t_hairSalonMaster_address`, "
				+ "`t_hairSalonMaster_phoneNumber`, `t_hairSalonMaster_mail`, `t_hairSalonMaster_passward`, `t_hairSalonMaster_disableFlag`)"
				+ "VALUES ('";
		String sql2 = "', '";
		String sql3 = "1";
		String sql_end = "');";	
		//combine insert sentence
		String sql = sql1 + salonInfo.getSalonContactName() + sql2
				+ salonInfo.getHairSalonName()  + sql2
				+ salonInfo.getSalonAreaId() +sql2
				+ salonInfo.getAddress()  + sql2
				+ salonInfo.getTel()  + sql2
				+ salonInfo.getMail()  + sql2
				+ salonInfo.getPassword()  + sql2
				+ sql3 
				+ sql_end;
		//debug
		System.out.println(sql);
		Statement statement = dbConnection.getStatement();
		boolean result=false;
		try {
			int result_int = statement.executeUpdate(sql);
			if(result_int >= 0) result = true;
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}	
		return result;
	}
	
	public List<HairSalonInfo> getSalonDetailInfo(Integer salonId ,DBConnection dbConnection) throws SQLException{
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		String sql = "SELECT `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, "
				+ "`t_hairSalonMaster_phoneNumber`, `t_hairSalonMaster_address`, `t_hairSalonMaster_openTime`, "
				+ "`t_hairSalonMaster_closeTime`, `t_hairSalonMaster_closeDay`, `t_hairSalonMaster_reviewId`, "
				+ "`t_hairSalonMaster_favoriteNumber` , `t_hairSalonMaster_availableCountryId` ,"
				+ " `t_hairSalonMaster_isNetReservation`, `t_hairSalonMaster_searchConditionId` "
				+ "FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_disableFlag` = 0 AND `t_hairSalonMaster_salonId` =" + salonId.toString();		
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
				/*
				Date openTime = rs.getDate("t_hairSalonMaster_openTime") !=null ?
						rs.getDate("t_hairSalonMaster_openTime"): new Date(0);
				Date closeTime = rs.getDate("t_hairSalonMaster_closeTime") !=null ?
						rs.getDate("t_hairSalonMaster_closeTime") : new Date(0);
				salonInfo.setBusinessHour(sdf.format(openTime).toString() + "〜"  + sdf.format(closeTime));
				*/
				salonInfo.setBusinessHour(rs.getString("t_hairSalonMaster_openTime") + "〜"  + rs.getString("t_hairSalonMaster_closeTime"));				
				salonInfo.setRegularHoliday(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
				salonInfo.setIsNetReservation(rs.getInt("t_hairSalonMaster_isNetReservation"));
				salonInfo.setSalonSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
				
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
					List<String> reviewPointList = new ArrayList<String>();
					reviewPointList = Arrays.asList(rsReview.getString("t_review_evaluation_point").split(","));
					for(String rp : reviewPointList){
						reviewPoint += Double.parseDouble(rp);
					}
					reviewPoint = reviewPoint/5; //５項目の評価の平均をとる
					//reviewPoint += rsReview.getDouble("t_review_evaluation_point");
					//口コミ数
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
		String sql ="SELECT `t_hairSalonMaster_mapUrl`, `t_hairSalonMaster_mapImagePath`,"
				+ " `t_hairSalonMaster_mapLatitude`, `t_hairSalonMaster_mapLongitude`, `t_hairSalonMaster_mapInfoText` "
				+ "FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_disableFlag` = 0 AND t_hairSalonMaster_salonId=" + salonId.toString(); 		
		Statement statement = dbConnection.getStatement();
		HairSalonInfo salonInfo = new HairSalonInfo();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				salonInfo.setSalonMapUrl(rs.getString("t_hairSalonMaster_mapUrl"));
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
				"SELECT `t_hairSalonMaster_salonID`, `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_message`, `t_area_areaName` FROM `t_hairSalonMaster` JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId WHERE `t_hairSalonMaster_disableFlag` = 0 AND  t_hairSalonMaster_salonId =";
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
				+ "`t_area_areaName` FROM t_hairSalonMaster JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId WHERE `t_hairSalonMaster_disableFlag` = 0 AND t_hairSalonMaster_salonId =";
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
				+ "FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_disableFlag` = 0 AND  t_hairSalonMaster_salonId=" + salonId;
		
		Statement statement = dbConnection.getStatement();
		List<Integer> reviewIdList = new ArrayList<Integer>();
		
		try {
			//debug
			System.out.println(sql);
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

	public List<HairSalonInfo> getSalonListByArea(DBConnection dbConnection, List<String> areaIdList) throws SQLException{
		
		//String sql1 = "SELECT t_hairSalonMaster_salonId FROM t_hairSalonMaster WHERE `t_hairSalonMaster_disableFlag` = 0 AND  FIND_IN_SET(";
		String sql1 = "SELECT `t_hairSalonMaster_salonId` , `t_hairSalonMaster_name` , "
				+ "`t_hairSalonMaster_salonImagePath`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_message`,`t_hairSalonMaster_address`,`t_hairSalonMaster_openTime`,"
				+ "`t_hairSalonMaster_closeTime`,`t_hairSalonMaster_closeDay`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_favoriteNumber` "
				+ "FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_disableFlag` = 0 AND  FIND_IN_SET(";
		String sql2 =  ",t_hairSalonMaster_areaId)";
		
		Statement statement = dbConnection.getStatement();
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		List<String> salonIdList = new ArrayList<String>();
		
		try {
			for(String areaId : areaIdList){
				//debug
				System.out.println(sql1 + areaId+ sql2);
				ResultSet rs = statement.executeQuery(sql1 + areaId+ sql2);
				while(rs.next()){
					if(!salonIdList.contains(rs.getString("t_hairSalonMaster_salonId"))){
						salonIdList.add(rs.getString("t_hairSalonMaster_salonId"));
						HairSalonInfo salonInfo = new HairSalonInfo();
						salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
						salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
						salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
						salonInfo.setSalonReviewIdList(rs.getString("t_hairSalonMaster_reviewId"));
						salonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
						salonInfo.setAddress(rs.getString("t_hairSalonMaster_address"));
						salonInfo.setBusinessHour(rs.getString("t_hairSalonMaster_openTime") + "〜"  + rs.getString("t_hairSalonMaster_closeTime"));
						salonInfo.setRegularHoliday(rs.getString("t_hairSalonMaster_closeDay"));
						salonInfo.setFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
						//重複確認
						boolean add = true;
						for(HairSalonInfo hs : salonInfoList){
							if(hs.getHairSalonId() == salonInfo.getHairSalonId()){
								add = false;
								break;
							}
						}
						if(add) salonInfoList.add(salonInfo);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfoList;		
	}
	
	public List<HairSalonInfo> getSalonListBySearchCondition(DBConnection dbConnection, 
			List<String> searchConditionIdList,List<HairSalonInfo> salonInfoListByArea, 
			int pageNumber,JSONObject jsonObject) throws SQLException{
		
		String sql1 = "SELECT `t_hairSalonMaster_salonId` , `t_hairSalonMaster_name` , "
				+ "`t_hairSalonMaster_salonImagePath`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_message`,`t_hairSalonMaster_address`,`t_hairSalonMaster_openTime`,"
				+ "`t_hairSalonMaster_closeTime`,`t_hairSalonMaster_closeDay`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_favoriteNumber` "
				+ "FROM `t_hairSalonMaster` "
				+ "WHERE `t_hairSalonMaster_disableFlag` = 0 "
				+ "AND FIND_IN_SET(";
		String sql2 = ",`t_hairSalonMaster_searchConditionId`) ORDER BY `t_hairSalonMaster_salonId` limit " + String.valueOf(Constant.ONE_PAGE_NUM) + " offset " + String.valueOf(pageNumber * Constant.ONE_PAGE_NUM) ;
		String sql3 = "SELECT `t_review_evaluation_point` FROM t_review WHERE FIND_IN_SET(";
		String sql4 = ",t_review_id)";
		Statement statement = dbConnection.getStatement();
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		int hitNum = 0;
		try {
			for(String conditionId : searchConditionIdList){
				String sql = sql1 + conditionId + sql2;
				//debug
				System.out.println(sql);
				ResultSet rs = statement.executeQuery(sql);
				List<String> reviewIdList = new ArrayList<String>();
				List<HairSalonInfo> sList = new ArrayList<HairSalonInfo>();
				while(rs.next()){
					HairSalonInfo salonInfo = new HairSalonInfo();
					salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
					salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
					salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
					/*
					reviewIdList = rs.getString("t_hairSalonMaster_reviewId")!= null ?
							Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
							*/
					salonInfo.setSalonReviewIdList(rs.getString("t_hairSalonMaster_reviewId"));
					salonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
					salonInfo.setAddress(rs.getString("t_hairSalonMaster_address"));
					/*
					Date openTime = rs.getDate("t_hairSalonMaster_openTime") !=null ?
							rs.getDate("t_hairSalonMaster_openTime"): new Date(0);
					Date closeTime = rs.getDate("t_hairSalonMaster_closeTime") !=null ?
							rs.getDate("t_hairSalonMaster_closeTime") : new Date(0);
					salonInfo.setBusinessHour(sdf.format(openTime).toString() + "〜"  + sdf.format(closeTime));
					*/
					salonInfo.setBusinessHour(rs.getString("t_hairSalonMaster_openTime") + "〜"  + rs.getString("t_hairSalonMaster_closeTime"));
					salonInfo.setRegularHoliday(rs.getString("t_hairSalonMaster_closeDay"));
					salonInfo.setFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
					//重複確認
					boolean add = true;
					for(HairSalonInfo hs : sList){
						if(hs.getHairSalonId() == salonInfo.getHairSalonId()){
							add = false;
							break;
						}
					}
					if(add) sList.add(salonInfo);
				}

				for(int i=0;i<sList.size();i++){
					HairSalonInfo oneSalon = sList.get(i);
					reviewIdList = oneSalon.getSalonReviewIdList()!= null ?
							Arrays.asList(oneSalon.getSalonReviewIdList()) : new ArrayList<String>();
					double reviewPoint = 0.0;
					int reviewNumber = 0;
					for(String reviewId : reviewIdList){
						String review_sql=sql3+reviewId+sql4;
						//debug
						System.out.println(review_sql);
						ResultSet rsReview = statement.executeQuery(review_sql);
						while(rsReview.next()){
							reviewPoint += rsReview.getDouble("t_review_evaluation_point");
							reviewNumber++;
						}
					}
					if(reviewNumber > 0){ //zero-divide
						oneSalon.setEvaluationPointMid(reviewPoint/reviewNumber);
					}else{
						oneSalon.setEvaluationPointMid(0);	
					}
					oneSalon.setWordOfMonth(reviewNumber);
					sList.set(i, oneSalon);
				}

				//重複確認
				//boolean addFlag = true;
				for(HairSalonInfo info : salonInfoList){
					for(int i=0;i<sList.size();i++){
						HairSalonInfo info2 = sList.get(i);
						if(info.getHairSalonId() ==  info2.getHairSalonId()){
							sList.remove(i);
						}
					}
				}
				hitNum+=sList.size();
				salonInfoList.addAll(sList);
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
	public String getContactUserName(DBConnection dbConnection, Integer salonId) throws SQLException{
		String sql ="SELECT `t_hairSalonMaster_contactUserName` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_disableFlag` = 0 AND t_hairSalonMaster_salonId = " + salonId; 		
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
		String sql ="SELECT `t_hairSalonMaster_menuId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_disableFlag` = 0 AND `t_hairSalonMaster_salonId` = " + salonId; 		
		Statement statement = dbConnection.getStatement();
		List<String> menuIdListStr = new ArrayList<String>();
		List<Integer> menuIdList = new ArrayList<Integer>();

		try {
			ResultSet rs = statement.executeQuery(sql);
			//debug
			System.out.println(sql);
			while(rs.next()){
				String menuId = rs.getString("t_hairSalonMaster_menuId");
				if(menuId != null && menuId != ""){
					menuIdListStr = Arrays.asList(menuId.split(","));				
					for(String mId : menuIdListStr){
						menuIdList.add(Integer.parseInt(mId));
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuIdList;	
	}

	public List<Integer> getStylistIdList(DBConnection dbConnection, Integer salonId){
		String sql ="SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_disableFlag` = 0 AND `t_hairSalonMaster_salonId` = " + salonId; 		
		Statement statement = dbConnection.getStatement();
		List<String> stylistIdListStr = new ArrayList<String>();
		List<Integer> stylistIdList = new ArrayList<Integer>();
		//HairSalonInfo salonInfo = new HairSalonInfo();

		try {
			ResultSet rs = statement.executeQuery(sql);
			//debug 
			System.out.println(sql);
			while(rs.next()){
				String stylistId = rs.getString("t_hairSalonMaster_stylistId");
				if(stylistId!=null && stylistId!=""){
					stylistIdListStr = Arrays.asList(stylistId.split(","));				
					for(String sId : stylistIdListStr){
						stylistIdList.add(Integer.parseInt(sId));
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stylistIdList;	
	}

	public List<HairSalonInfo> getSalonInfo(DBConnection dbConnection, Integer salonId){
		
		/**
		 * 
	     {
		      t_hairSalonMaster_salon_name,
		      t_country_name,
		      t_area_name,
		      t_hairSalonMaster_detailText,
		      t_hairSalonMaster_openTime,
		      t_hairSalonMaster_closeTime,
		      t_hairSalonMaster_closeDay,
		      t_hairSalonMaster_creditAvailable,
		      t_hairSalonMaster_carParkAvailable,
		      t_hairSalonMaster_salonImagePath,
		      t_hairSalonMaster_japaneseAvailable,
		    }
		 */
		/*
		String sql ="SELECT `t_hairSalonMaster_salonId`, `t_hairSalonMaster_name`, `t_hairSalonMaster_detailText`, "
				+ "`t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_openTime`, `t_hairSalonMaster_closeTime`, "
				+ "`t_hairSalonMaster_closeDay`, `t_hairSalonMaster_creditAvailable`, `t_hairSalonMaster_carParkAvailable`, "
				+ "`t_hairSalonMaster_availableCountryId`, "
				+ "`t_area_areaName`, `t_area_areaId`, "
				+ "`t_country_countryName` "
				+ "FROM `t_hairSalonMaster` "
				+ "JOIN t_masterArea ON t_hairSalonMaster_areaId = t_area_areaId "
				+ "JOIN t_masterCountry ON t_area_countryId = t_country_countryId "
				+ "WHERE `t_hairSalonMaster_salonId` = " + salonId; 		
		*/
		String sql1 ="SELECT `t_hairSalonMaster_salonId`, `t_hairSalonMaster_name`, `t_hairSalonMaster_detailText`, "
				+ "`t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_openTime`, `t_hairSalonMaster_closeTime`, "
				+ "`t_hairSalonMaster_closeDay`, `t_hairSalonMaster_creditAvailable`, `t_hairSalonMaster_carParkAvailable`, "
				+ "`t_hairSalonMaster_availableCountryId`, `t_hairSalonMaster_areaId`, `t_hairSalonMaster_searchConditionId`, `t_hairSalonMaster_mail` ";
		String sql3 = 
				 "FROM `t_hairSalonMaster` ";
		String sql5 = 
				 "WHERE `t_hairSalonMaster_salonId` = " + salonId; 		
		String sql = sql1 + sql3 + sql5;

		String t_area_areaId = null;
		String t_country_countryId = null;
		String areaSql = "SELECT `t_area_areaName`, `t_area_countryId` FROM `t_masterArea` WHERE `t_area_areaId` = ";
		String countrySql = "SELECT `t_country_countryName` FROM `t_masterCountry` WHERE `t_country_countryId` = ";
		int countryId = -1;
		
		Statement statement = dbConnection.getStatement();
		List<HairSalonInfo> SalonInfoList = new ArrayList<HairSalonInfo>();
		HairSalonInfo salonInfo = new HairSalonInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		//debug
		System.out.println(sql);
		
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setSalonDetailText(rs.getString("t_hairSalonMaster_detailText"));

				/*
				salonInfo.setSalonCountryName(rs.getString("t_country_countryName"));
				salonInfo.setAreaNameList(Arrays.asList(new String[]{rs.getString("t_area_areaName")}));
				*/
				
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				/*
				Date openTime = rs.getDate("t_hairSalonMaster_openTime") !=null ?
						rs.getDate("t_hairSalonMaster_openTime"): new Date(0);
				Date closeTime = rs.getDate("t_hairSalonMaster_closeTime") !=null ?
						rs.getDate("t_hairSalonMaster_closeTime") : new Date(0);
						*/
				salonInfo.setSalonOpenTime(rs.getString("t_hairSalonMaster_openTime"));
				salonInfo.setSalonCloseTime(rs.getString("t_hairSalonMaster_closeTime"));
				salonInfo.setSalonCloseDay(rs.getString("t_hairSalonMaster_closeDay"));

				//salonInfo.setSalonCreditAvailable(rs.getBoolean("t_hairSalonMaster_creditAvailable"));
				//salonInfo.setSalonCarParkAvailable(rs.getBoolean("t_hairSalonMaster_carParkAvailable"));
				salonInfo.setSalonCreditAvailable(rs.getInt("t_hairSalonMaster_creditAvailable"));
				salonInfo.setSalonCarParkAvailable(rs.getInt("t_hairSalonMaster_carParkAvailable"));

				t_area_areaId = rs.getString("t_hairSalonMaster_areaId");
				salonInfo.setSalonAreaId(t_area_areaId);
				countryId = rs.getInt("t_hairSalonMaster_availableCountryId");
				
				salonInfo.setSalonSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
				int japaneseAvailable = 0;
				if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = 1;
				salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				salonInfo.setMail(rs.getString("t_hairSalonMaster_mail"));

				/*
				boolean japaneseAvailable = false;
				int countryId = rs.getInt("t_hairSalonMaster_availableCountryId");
				if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = true;
				salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				
				*/
				//SalonInfoList.add(salonInfo);
				
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		if(t_area_areaId != "" && t_area_areaId != null){
			List<String> areaIdList = new ArrayList<String>();
			List<String> areaNameList = new ArrayList<String>();
			areaIdList = Arrays.asList(t_area_areaId.split(","));				
			for(String areaId : areaIdList){
				try {
					//debug
					System.out.println(areaSql + areaId);
					ResultSet rs = statement.executeQuery(areaSql + areaId);
					while(rs.next()){
						areaNameList.add(rs.getString("t_area_areaName"));
						t_country_countryId = rs.getString("t_area_countryId");
					}	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			salonInfo.setAreaNameList(areaNameList);
		}

		if(t_country_countryId != null){
			try {
				//debug
				System.out.println(countrySql + t_country_countryId);
				ResultSet rs = statement.executeQuery(countrySql + t_country_countryId);
				while(rs.next()){
					salonInfo.setSalonCountryName(rs.getString("t_country_countryName"));
					//boolean japaneseAvailable = false;
					//int japaneseAvailable = -1;
					//if(countryId == Constant.JAPANESE_COUNTRY_ID) japaneseAvailable = 1;
					//salonInfo.setSalonJapaneseAvailable(japaneseAvailable);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		SalonInfoList.add(salonInfo);
		return SalonInfoList;	
	}

	
	public boolean setSalonInfo(
			DBConnection dbConnection, 
			int salonId,
			HairSalonInfo salonInfo
			/*
		    String t_hairSalonMaster_name,
		    String t_area_id,
		    String t_hairSalonMaster_detailText,
		    String t_hairSalonMaster_openTime,
		    String t_hairSalonMaster_closeTime,
		    String t_hairSalonMaster_closeDay,
		    String t_hairSalonMaster_creditAvailable,
		    String t_hairSalonMaster_carParkAvailable,
		    String t_hairSalonImagePath,
		    String t_hairSalonMaster_japaneseAvailable
		    t_hairSalonMaster_searchConditionId
		    t_hairSalonMaster_mail
		    t_hairSalonMaster_password
		    */
			){

		boolean result = false;
		
		/**
		 * salonId からサロン情報があるかどうか確認。
		 * salonがテーブルに存在したらupdate
		 * salonが存在しなければinsertする
		 */


		/**
		 * SQL 例:
		*/
		
		/**
		 * UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_name` = 'name2', `t_hairSalonMaster_areaId` = '1,2' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = 5;
		 */

		String sql1 = "SELECT * FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = " + salonId; 
		String sql2 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_name` = '";
				//+ "SalonName" +
		String sql3 = "', `t_hairSalonMaster_areaId` = '";
				//+ "AreaId list" +
		String sql4 = "', `t_hairSalonMaster_detailText` = '";
		//+ "detailText" +
		String sql5 = "', `t_hairSalonMaster_openTime` = '";
		//+ "openTime" +
		String sql6 = "', `t_hairSalonMaster_closeTime` = '";
		//+ "closeTime" +
		String sql7 = "', `t_hairSalonMaster_closeDay` = '";
		//+ "closeDay" +
		String sql8 = "', `t_hairSalonMaster_creditAvailable` = '";
		//+ "creditAvailable" +
		String sql9 = "', `t_hairSalonMaster_carParkAvailable` = '";
		//+ "carParkAvailable" +
		String sql10 = "', `t_hairSalonMaster_salonImagePath` = '";
		//+ "salonImagePathList <- string " +
		String sql11 = "', `t_hairSalonMaster_availableCountryId` = '";
		//+ "japaneseAvailable " +
		String sql12 = "', `t_hairSalonMaster_searchConditionId` = '";
		//+
		String sql_mail = "', `t_hairSalonMaster_mail` = '";
		//+
		String sql_pass = "', `t_hairSalonMaster_passward` = '";
		//+
		String sql_end = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = " + salonId + ";";;

		Statement statement = dbConnection.getStatement();
		UserInfo userInfo = null;
		int japaneseAvailable = -1;
		/*
		if(t_hairSalonMaster_japaneseAvailable!=null && t_hairSalonMaster_japaneseAvailable.compareTo("true") == 0){
			japaneseAvailable = Constant.JAPANESE_COUNTRY_ID;
		}
		*/
		//System.out.println(salonInfo.getJapaneseAvailable()+","+Constant.JAPANESE_COUNTRY_ID);
		if(salonInfo.getJapaneseAvailable()==1){
			japaneseAvailable = Constant.JAPANESE_COUNTRY_ID;
		}else{
			japaneseAvailable = 0;
		}

		/*
		//date '2015-06-03 00:00:00' format sample.
 		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		    format.setLenient(false);
		    format.parse(t_hairSalonMaster_openTime); //Exception check
		    format.parse(t_hairSalonMaster_closeTime); //Exception check
		} catch (ParseException e) {
		    //失敗時の処理…
		}
		*/
		/*
		String sql = sql2 + t_hairSalonMaster_name
			 +sql3 + t_area_id
			 +sql4 + t_hairSalonMaster_detailText
			 +sql5 + t_hairSalonMaster_openTime
			 +sql6 + t_hairSalonMaster_closeTime
			 +sql7 + t_hairSalonMaster_closeDay
			 +sql8 + t_hairSalonMaster_creditAvailable
			 +sql9 + t_hairSalonMaster_carParkAvailable
			 +sql10 + t_hairSalonImagePath
			 +sql11 + japaneseAvailable
			 +sql_end;
			 */
		//boolean -> int
//		int creditAvailable =  salonInfo.getSalonCreditAvailable() ? 1 : 0;
//		int carParkAvailable =  salonInfo.getSalonCarParkAvailable() ? 1 : 0;
		int creditAvailable =  salonInfo.getSalonCreditAvailable();
		int carParkAvailable =  salonInfo.getSalonCarParkAvailable();
		
		//debug
		System.out.println("mail&pass:"+salonInfo.getMail()+","+salonInfo.getPassword());
		
		//combine sql sentense.
		String sql = sql2 + salonInfo.getHairSalonName()
				 +sql3 + salonInfo.getSalonAreaId()
				 +sql4 + salonInfo.getSalonDetailText()
				 +sql5 + salonInfo.getSalonOpenTime()
				 +sql6 + salonInfo.getSalonCloseTime()
				 +sql7 + salonInfo.getSalonCloseDay()
				 +sql8 + creditAvailable
				 +sql9 + carParkAvailable
				 +sql10 + salonInfo.getHairSalonImagePathOneLine()
				 +sql11 + japaneseAvailable
				 +sql12 + salonInfo.getSalonSearchConditionId();
		if(salonInfo.getMail()!=""){
			sql += sql_mail + salonInfo.getMail();
		}
		if(salonInfo.getPassword()!=""){
			sql += sql_pass + salonInfo.getPassword();
		}
		sql+=sql_end;
		//debug
		System.out.println(sql);
		
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql1);
			if(rs == null){
				//salon がtableにない = insert? error?
				result = false;
			}else{
				//salon がtableにある = update
				int result_int = statement.executeUpdate(sql);
				if(result_int > 0) result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Integer> getHairStyleIdList(DBConnection dbConnection,
			int salonId) {
		String sql ="SELECT `t_hairSalonMaster_hairStyleId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = " + salonId; 		
		Statement statement = dbConnection.getStatement();
		List<Integer> hairStyleIdList = new ArrayList<Integer>();
		List<String> hairStyleIdListStr = new ArrayList<String>();
		
		try {
			ResultSet rs = statement.executeQuery(sql);
			//debug
			System.out.println(sql);
			while(rs.next()){
				String hairStyleId = rs.getString("t_hairSalonMaster_hairStyleId");
				if(hairStyleId != "" && hairStyleId != null){
					hairStyleIdListStr = Arrays.asList(hairStyleId.split(","));				
					for(String sId : hairStyleIdListStr){
						hairStyleIdList.add(Integer.parseInt(sId));
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hairStyleIdList;	
	}

	public boolean setSalonMapInfo(DBConnection dbConnection, int salonId,
			String t_hairSalonMaster_mapUrl,
			String t_hairSalonMaster_mapImagePath) {
		/**
		 *     UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_mapUrl` = '
		 *     mapUrl', `t_hairSalonMaster_mapImagePath` = 'image' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = 7;
		 */

		//http://tousu.baidu.com/map/add?new_poi=1&x=11605781.17&y=1736159.05&lat=15.506860161245294&lng=104.25537169474694
		
		boolean result = false;
		String sql1 ="UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster`"
				+ " SET `t_hairSalonMaster_mapUrl` = '"; 		
		String sql2 ="', `t_hairSalonMaster_mapLatitude` = '";
		String sql3 ="', `t_hairSalonMaster_mapLongitude` = '";
		String sql4 ="', `t_hairSalonMaster_mapImagePath` = '"; 		
		String sql5 ="' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = "; 		
		String sql6 =";";

		String latitude = "";
		String longitude = "";
		String url = t_hairSalonMaster_mapUrl;
		latitude = url.substring(url.indexOf("lat=")+4,url.lastIndexOf("&"));
		longitude = url.substring(url.indexOf("lng=")+4,url.length());
		
		Statement statement = dbConnection.getStatement();
		
		StringBuilder sql = new StringBuilder(sql1);
		sql.append(url);
		sql.append(sql2);
		sql.append(latitude);
		sql.append(sql3);
		sql.append(longitude);
		sql.append(sql4);
		sql.append(t_hairSalonMaster_mapImagePath);
		sql.append(sql5);
		sql.append(salonId);
		sql.append(sql6);
		//String sql = sql1 + t_hairSalonMaster_mapUrl + sql2 + t_hairSalonMaster_mapImagePath + sql3 + salonId + sql4;
		System.out.println(sql.toString());
		
		try {
			int result_int = statement.executeUpdate(sql.toString());
			if(result_int >= 0) result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;	
	}
	
	public int getCheckLoginInfo(DBConnection dbConnection, String mail, String password){
		String sql1 = "SELECT `t_hairSalonMaster_salonId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_mail` ='";
		String sql2 = "' AND `t_hairSalonMaster_passward` ='";
	    String sql3 = "' AND `t_hairSalonMaster_disableFlag` = 0";
	    int retSalonId = -1;
	    
	    String sql = sql1 + mail + sql2 + password + sql3;
	    //debug
	    //System.out.println("Manual-Login: " + sql);
	    
		Statement statement = dbConnection.getStatement();
		HairSalonInfo salonInfo = new HairSalonInfo();
		
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			while(rs.next()){
				salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(rs.getInt("t_hairSalonMaster_salonId"));
				retSalonId = salonInfo.getHairSalonId();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retSalonId;
	}

	public List<Integer> getCouponIdList(DBConnection dbConnection, int salonId) {
			String sql ="SELECT `t_hairSalonMaster_couponId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = " + salonId; 		
			Statement statement = dbConnection.getStatement();
			List<Integer> couponIdList = new ArrayList<Integer>();
			List<String> couponIdListStr = new ArrayList<String>();
			
			try {
				ResultSet rs = statement.executeQuery(sql);
				//debug
				System.out.println(sql);
				while(rs.next()){
					String couponId = rs.getString("t_hairSalonMaster_couponId");
					if(couponId != "" && couponId != null){
						couponIdListStr = Arrays.asList(couponId.split(","));				
						for(String sId : couponIdListStr){
							couponIdList.add(Integer.parseInt(sId));
						}
					}
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return couponIdList;	
		}
	
	
	public List<HairSalonInfo> getSalonInfoList(DBConnection dbConnection,
			List<String> searchConditionIdList, List<String> areaIdList,
			int pageNum, JSONObject jsonObject) throws SQLException {

		String sql = "SELECT `t_hairSalonMaster_salonId`, `t_hairSalonMaster_areaId`, `t_hairSalonMaster_searchConditionId` "
				+ "FROM `t_hairSalonMaster` ORDER BY `t_hairSalonMaster_salonId` ASC";
		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		Statement statement = dbConnection.getStatement();
		Map<Integer,List<String>> salonAreaMap = new HashMap<Integer, List<String>>();
		Map<Integer,List<String>> salonConditionMap = new HashMap<Integer, List<String>>();

		//area, searchConditionIdが空ならreturn
		if(areaIdList.get(0).equals("") && searchConditionIdList.get(0).equals("")) return salonInfoList;
		
		try {
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				List<String> salonAreaIdList = new ArrayList<String>();
				List<String> salonSearchConditionList = new ArrayList<String>();
				String searchCondtionIds = rs.getString("t_hairSalonMaster_searchConditionId");
				String areaIds = rs.getString("t_hairSalonMaster_areaId");
				
				if(searchCondtionIds != null){
					String[] searchCondtionArray = searchCondtionIds.split(",");
					for(String condId : searchCondtionArray){
						salonSearchConditionList.add(condId);
					}
				}
				
				if(areaIds != null){
					String[] areaIdsArray = areaIds.split(",");
					for(String areaId : areaIdsArray){
						salonAreaIdList.add(areaId);
					}
				}
				salonConditionMap.put(rs.getInt("t_hairSalonMaster_salonId"), salonSearchConditionList);
				salonAreaMap.put(rs.getInt("t_hairSalonMaster_salonId"), salonAreaIdList);
			}
			List<Integer> retSalonIdList = new ArrayList<Integer>();
			for (Iterator<Entry<Integer, List<String>>> it = salonConditionMap.entrySet().iterator(); it.hasNext();) {
			    Entry entry = it.next();
			    boolean searchCondFlag = false;
			    boolean areaFlag = false;
			    int salonId = Integer.parseInt(entry.getKey().toString());
			    List<String> condList = salonConditionMap.get(salonId);
			    List<String> areaList = salonAreaMap.get(salonId);

			    /*
			    //OR検索, ひとつでも合えばtrue
			    for(String condId : searchConditionIdList){
			    	if(condList.contains(condId)){
			    		//debug
			    		//System.out.println("Salon "+salonId+" contains condId:"+condList+","+searchConditionIdList);
			    		searchCondFlag = true;
			    		break;
			    	}
			    }
			    */
			    //AND検索			    
			    for(String condId : searchConditionIdList){
			    	if(condList.contains(condId)){
			    		//debug
			    		//System.out.println("Salon "+salonId+" contains condId:"+condList+","+searchConditionIdList);
			    		searchCondFlag = true;
			    	}else{
			    		searchCondFlag = false;
			    	}
			    }
			    if(searchConditionIdList.get(0).equals("")) searchCondFlag = true;
			    
			    for(String id :areaIdList ){
			    	if(areaList.contains(id)){
			    		//debug
			    		//System.out.println("Salon "+salonId+" contains areaId:"+areaList+","+areaIdList);
			    		areaFlag = true;
			    		break;
			    	}
			    }
			    if(areaIdList.get(0).equals("")) areaFlag = true;
			    
			    if(searchCondFlag && areaFlag) retSalonIdList.add(salonId);
			}
			
			String innerSQL ="SELECT `t_hairSalonMaster_salonId` , `t_hairSalonMaster_name` , "
				+ "`t_hairSalonMaster_salonImagePath`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_message`,`t_hairSalonMaster_address`,`t_hairSalonMaster_openTime`,"
				+ "`t_hairSalonMaster_closeTime`,`t_hairSalonMaster_closeDay`,`t_hairSalonMaster_reviewId`,"
				+ "`t_hairSalonMaster_favoriteNumber` "
				+ "FROM `t_hairSalonMaster` "
				+ "WHERE `t_hairSalonMaster_disableFlag` = 0 "
				+ "AND `t_hairSalonMaster_salonId`=";
			int hitCount=0;
			int offset = pageNum * Constant.ONE_PAGE_NUM;
			for(int retSalonId : retSalonIdList){
				if(Constant.ONE_PAGE_NUM > hitCount ){
					if(offset>=retSalonIdList.size()) break;
					//debug
					System.out.println(innerSQL+String.valueOf(retSalonIdList.get(offset)));
					ResultSet innerRs = statement.executeQuery(innerSQL + String.valueOf(retSalonIdList.get(offset)) );
					while(innerRs.next()){
						HairSalonInfo salonInfo = new HairSalonInfo();
						salonInfo.setHairSalonId(innerRs.getInt("t_hairSalonMaster_salonId"));
						salonInfo.setHairSalonName(innerRs.getString("t_hairSalonMaster_name"));
						salonInfo.setHairSalonImagePath(innerRs.getString("t_hairSalonMaster_salonImagePath"));
						salonInfo.setSalonReviewIdList(innerRs.getString("t_hairSalonMaster_reviewId"));
						salonInfo.setMessage(innerRs.getString("t_hairSalonMaster_message"));
						salonInfo.setAddress(innerRs.getString("t_hairSalonMaster_address"));
						salonInfo.setBusinessHour(innerRs.getString("t_hairSalonMaster_openTime") + "〜"  + innerRs.getString("t_hairSalonMaster_closeTime"));
						salonInfo.setRegularHoliday(innerRs.getString("t_hairSalonMaster_closeDay"));
						salonInfo.setFavoriteNumber(innerRs.getInt("t_hairSalonMaster_favoriteNumber"));
						salonInfoList.add(salonInfo);
						offset++;
						hitCount++;
					}
					
					//評価値を計算して反映
					List<String> reviewIdList = new ArrayList<String>();
					String review_sql1 = "SELECT `t_review_evaluation_point` FROM t_review WHERE FIND_IN_SET(";
					String review_sql2 = ",t_review_id)";
					for(int i=0;i<salonInfoList.size();i++){
						HairSalonInfo oneSalon = salonInfoList.get(i);
						reviewIdList = oneSalon.getSalonReviewIdList()!= null ?
								Arrays.asList(oneSalon.getSalonReviewIdList().split(",")) : new ArrayList<String>();
						int reviewNumber = 0;
						double reviewPoint = 0.0;
						for(String reviewId : reviewIdList){
							String review_sql=review_sql1+reviewId+review_sql2;
							//debug
							System.out.println(review_sql);
							ResultSet rsReview = statement.executeQuery(review_sql);
							while(rsReview.next()){
								//reviewPoint += rsReview.getString("t_review_evaluation_point");
								//口コミ数
								reviewNumber++;
							}
						}
						if(reviewNumber > 0){ //zero-divide
							oneSalon.setEvaluationPointMid(reviewPoint/reviewNumber);
						}else{
							oneSalon.setEvaluationPointMid(0);	
						}
						oneSalon.setWordOfMonth(reviewNumber);
						salonInfoList.set(i, oneSalon);
					}
				}
				else{
					break;
				}
			}
			
			if(Constant.ONE_PAGE_NUM * pageNum + Constant.ONE_PAGE_NUM >= retSalonIdList.size()){
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
	public String getSalonAreaId(DBConnection dbConnection, int salonId) {
		String sql ="SELECT `t_hairSalonMaster_areaId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = " + salonId; 		
		Statement statement = dbConnection.getStatement();
		String areaId = "";
		
		try {
			ResultSet rs = statement.executeQuery(sql);
			//debug
			System.out.println(sql);
			while(rs.next()){
				areaId = rs.getString("t_hairSalonMaster_areaId");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaId;	
	}
	
	public boolean setSalonReview(DBConnection dbConnection, String salonId, int rid) {
		boolean result = false;
		String sql_before = "SELECT `t_hairSalonMaster_reviewId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId`="+salonId;
		String sql ="UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` "
				+ "SET `t_hairSalonMaster_reviewId` = '";
		String sql2 = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = "+salonId+";";
		Statement statement = dbConnection.getStatement();
		
		try {
			String reviewId = "";
			//debug
			System.out.println(sql_before);
			ResultSet rs = statement.executeQuery(sql_before);
			while(rs.next()){
				reviewId = rs.getString("t_hairSalonMaster_reviewId");
			}
			if(reviewId!=null && !reviewId.equals("")){
				reviewId += "," + rid;
			}else{
				reviewId = Integer.toString(rid);
			}

			sql = sql + reviewId + sql2;
			//debug
			System.out.println(sql);
			int result_sql = statement.executeUpdate(sql);
			if(result_sql>0) result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;			
	}
	public int getReviewedSalonId(DBConnection dbConnection, String t_reviewId) {
		String sql ="SELECT `t_hairSalonMaster_salonId`, `t_hairSalonMaster_reviewId` "
				+ "FROM `t_hairSalonMaster` ORDER BY `t_hairSalonMaster_salonId` ASC"; 		
		Statement statement = dbConnection.getStatement();
		int salonId = -1;
		
		try {
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String reviewIds = rs.getString("t_hairSalonMaster_reviewId");
				if(reviewIds!=null && reviewIds.length()>0){
					List<String> reviewIdList = Arrays.asList(reviewIds.split(","));
					if(reviewIdList.contains(t_reviewId)){
						salonId = rs.getInt("t_hairSalonMaster_salonId");
						break;
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return salonId;
	}
	
	public boolean DeleteReviewId(DBConnection dbConnection, String t_reviewId,
			int salonId) {
		boolean result = false;
		String sql_before = "SELECT `t_hairSalonMaster_reviewId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = "+salonId;
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_reviewId` = '";
		String sql2 = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = "+salonId+";";
		Statement statement = dbConnection.getStatement();

		String reviewId="";
		try {
			//debug
			System.out.println(sql_before);
			ResultSet rs = statement.executeQuery(sql_before);
			while(rs.next()){
				String reviewIds = rs.getString("t_hairSalonMaster_reviewId");
				List<String> reviewIdList = Arrays.asList(reviewIds.split(","));
				for(int index=0;index<reviewIdList.size();index++){
					if(!reviewIdList.get(index).equals(t_reviewId)){
						reviewId += reviewIdList.get(index)+",";
					}
				}
				reviewId = reviewId.substring(0,reviewId.length()-1);
			}

			//debug
			String sql = sql1 + reviewId + sql2;
			System.out.println(sql);
			int result_sql = statement.executeUpdate(sql);
			if(result_sql>0) result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
