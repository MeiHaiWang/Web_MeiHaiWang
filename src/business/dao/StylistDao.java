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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;
import common.constant.Constant;
import common.constant.TableConstant;
import common.constant.TableConstant;
import common.model.IBaseInfo;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.CommonUtil;
import common.util.ConfigUtil;
import common.util.DBConnection;
import common.util.ListUtilities;

public class StylistDao extends BaseDao{
	private static Logger logger = LogManager.getLogger();

	public StylistDao() throws Exception{
	}

	public int getStylistIntData(DBConnection dbConnection ,String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{
		return getIntValue(dbConnection, Constant.TABLE_STYLIST, targetColumnName, sourceColumnName, sourceColumnValue );
	}
	public String getStylistStringData(DBConnection dbConnection , String targetColumnName, String sourceColumnName, String sourceColumnValue) throws SQLException{
		return getStringValue(dbConnection, Constant.TABLE_STYLIST, targetColumnName, sourceColumnName, sourceColumnValue);
	}
	public int getStylistIntData(DBConnection dbConnection, String columnName, IBaseInfo info) throws SQLException{
		return getIntValue(dbConnection, Constant.TABLE_STYLIST, columnName, TableConstant.COLUMN_STYLIST_ID, info);
	}
	public int setStylistIntData(DBConnection dbConnection , String columnName, int targetColumValue, IBaseInfo info) throws SQLException{
		return setIntValue(dbConnection, Constant.TABLE_STYLIST, columnName,  targetColumValue, TableConstant.COLUMN_STYLIST_ID, info);
	}
	public String getStylistStringData(DBConnection dbConnection , String columnName,  IBaseInfo info) throws SQLException{
		return getStringValue(dbConnection, Constant.TABLE_STYLIST, columnName, TableConstant.COLUMN_STYLIST_ID, info);
	}
	public int setStylistStringData(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		return setStringValue(dbConnection, Constant.TABLE_STYLIST, targetColumnName, value, TableConstant.COLUMN_STYLIST_ID, info);
	}
	public int appendId(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		int result = -1;
		String data = getStylistStringData(dbConnection, targetColumnName, info);
		if(data!=null){
			data = data + ", "+ value;
			result = setStylistStringData(dbConnection, targetColumnName, data, info);
		}else{
			result = setStylistStringData(dbConnection, targetColumnName, value, info);
		}
		return result;
	}
	public int removeId(DBConnection dbConnection ,String targetColumnName, String value, IBaseInfo info) throws SQLException{
		int result = -1;
		String idlist = getStylistStringData(dbConnection, targetColumnName, info);
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
			result = setStylistStringData(dbConnection, targetColumnName, retIdlist, info);
		}
		return result;
	}

	public StylistInfo getStylistObject(DBConnection dbConnection, int id) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_STYLIST);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.WHERE);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_STYLIST_ID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.EQUAL);
		sql.append(Constant.SPACE);
		sql.append(id);
		
		Statement statement = dbConnection.getStatement();
		StylistInfo stylistInfo = null;

		try {			
			ResultSet rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				stylistInfo = new StylistInfo();
				stylistInfo.setObjectId(rs.getInt("t_stylist_Id"));
				stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
				stylistInfo.setName(rs.getString("t_stylist_name"));
				stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
				stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
				stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
				stylistInfo.setIsNetReservation(rs.getInt("t_stylist_isNetReservation"));
				stylistInfo.setUserId(rs.getInt("t_stylist_userId"));
				stylistInfo.setPhoneNumber(rs.getString("t_stylist_phoneNumber"));
				stylistInfo.setMail(rs.getString("t_stylist_mail"));
				stylistInfo.setBirth(rs.getDate("t_stylist_birth"));
				stylistInfo.setPosition(rs.getString("t_stylist_position"));
				stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
				stylistInfo.setSpecialMenu(rs.getString("t_stylist_SpecialMenu"));
				stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
				stylistInfo.setMenuId(rs.getString("t_stylist_menuId"));
				stylistInfo.setStylistSearchConditionId(rs.getString("t_stylist_searchConditionId"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistInfo;
	}
	
	public StylistInfo getStylistObjectByColumn(DBConnection dbConnection, String culumnName , String value) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_STYLIST);
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
		StylistInfo stylistInfo = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				stylistInfo = new StylistInfo();
				stylistInfo.setObjectId(rs.getInt("t_stylist_Id"));
				stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
				stylistInfo.setName(rs.getString("t_stylist_name"));
				stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
				stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
				stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
				stylistInfo.setIsNetReservation(rs.getInt("t_stylist_isNetReservation"));
				stylistInfo.setUserId(rs.getInt("t_stylist_userId"));
				stylistInfo.setPhoneNumber(rs.getString("t_stylist_phoneNumber"));
				stylistInfo.setMail(rs.getString("t_stylist_mail"));
				stylistInfo.setBirth(rs.getDate("t_stylist_birth"));
				stylistInfo.setPosition(rs.getString("t_stylist_position"));
				stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
				stylistInfo.setSpecialMenu(rs.getString("t_stylist_SpecialMenu"));
				stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
				stylistInfo.setMenuId(rs.getString("t_stylist_menuId"));
				stylistInfo.setStylistSearchConditionId(rs.getString("t_stylist_searchConditionId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistInfo;
	}
	
	public List<StylistInfo> getStylistObjectListByColumn(DBConnection dbConnection, String culumnName , String value) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_STYLIST);
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
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				StylistInfo stylistInfo = new StylistInfo();
				stylistInfo.setObjectId(rs.getInt("t_stylist_Id"));
				stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
				stylistInfo.setName(rs.getString("t_stylist_name"));
				stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
				stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
				stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
				stylistInfo.setIsNetReservation(rs.getInt("t_stylist_isNetReservation"));
				stylistInfo.setUserId(rs.getInt("t_stylist_userId"));
				stylistInfo.setPhoneNumber(rs.getString("t_stylist_phoneNumber"));
				stylistInfo.setMail(rs.getString("t_stylist_mail"));
				stylistInfo.setBirth(rs.getDate("t_stylist_birth"));
				stylistInfo.setPosition(rs.getString("t_stylist_position"));
				stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
				stylistInfo.setSpecialMenu(rs.getString("t_stylist_SpecialMenu"));
				stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
				stylistInfo.setMenuId(rs.getString("t_stylist_menuId"));
				stylistInfo.setStylistSearchConditionId(rs.getString("t_stylist_searchConditionId"));
				stylistInfoList.add(stylistInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistInfoList;
	}
	
	public StylistInfo getStylistObjectByColumnMap(DBConnection dbConnection, Map<String, String> sourceData) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_STYLIST);
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
		StylistInfo stylistInfo = null;
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				stylistInfo = new StylistInfo();
				stylistInfo.setObjectId(rs.getInt("t_stylist_Id"));
				stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
				stylistInfo.setName(rs.getString("t_stylist_name"));
				stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
				stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
				stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
				stylistInfo.setIsNetReservation(rs.getInt("t_stylist_isNetReservation"));
				stylistInfo.setUserId(rs.getInt("t_stylist_userId"));
				stylistInfo.setPhoneNumber(rs.getString("t_stylist_phoneNumber"));
				stylistInfo.setMail(rs.getString("t_stylist_mail"));
				stylistInfo.setBirth(rs.getDate("t_stylist_birth"));
				stylistInfo.setPosition(rs.getString("t_stylist_position"));
				stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
				stylistInfo.setSpecialMenu(rs.getString("t_stylist_SpecialMenu"));
				stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
				stylistInfo.setMenuId(rs.getString("t_stylist_menuId"));
				stylistInfo.setStylistSearchConditionId(rs.getString("t_stylist_searchConditionId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistInfo;
	}
	
	public List<StylistInfo> getStylistObjectListByColumnMap(DBConnection dbConnection, Map<String, String> sourceData) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECTALL);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_STYLIST);
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
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		try {			
			rs = statement.executeQuery(sql.toString());
			logger.debug(sql.toString());
			
			while(rs.next()){
				StylistInfo stylistInfo = new StylistInfo();
				stylistInfo.setObjectId(rs.getInt("t_stylist_Id"));
				stylistInfo.setSalonId(rs.getInt("t_stylist_salonId"));
				stylistInfo.setName(rs.getString("t_stylist_name"));
				stylistInfo.setStylistGender(rs.getInt("t_stylist_sex"));
				stylistInfo.setStylistImagePath(rs.getString("t_stylist_imagePath"));
				stylistInfo.setFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
				stylistInfo.setIsNetReservation(rs.getInt("t_stylist_isNetReservation"));
				stylistInfo.setUserId(rs.getInt("t_stylist_userId"));
				stylistInfo.setPhoneNumber(rs.getString("t_stylist_phoneNumber"));
				stylistInfo.setMail(rs.getString("t_stylist_mail"));
				stylistInfo.setBirth(rs.getDate("t_stylist_birth"));
				stylistInfo.setPosition(rs.getString("t_stylist_position"));
				stylistInfo.setStylistYears(rs.getInt("t_stylist_experienceYear"));
				stylistInfo.setSpecialMenu(rs.getString("t_stylist_SpecialMenu"));
				stylistInfo.setStylistMessage(rs.getString("t_stylist_message"));
				stylistInfo.setMenuId(rs.getString("t_stylist_menuId"));
				stylistInfo.setStylistSearchConditionId(rs.getString("t_stylist_searchConditionId"));
				stylistInfoList.add(stylistInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistInfoList;
	}

	public List<StylistInfo> getStylistInfoList(DBConnection dbConnection, int salonId, List<String> areaIdList , List<String> searchConditionIdList , int pageNum,JSONObject jsonObject) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(Constant.SELECT);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_STYLIST_ID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.COMMA);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_STYLIST_AREAID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.COMMA);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_STYLIST_CONDITIONID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.COMMA);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_STYLIST_SALONID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.FROM);
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(Constant.TABLE_STYLIST);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append("ORDER BY");
		sql.append(Constant.SPACE);
		sql.append(Constant.BACKQ);
		sql.append(TableConstant.COLUMN_STYLIST_ID);
		sql.append(Constant.BACKQ);
		sql.append(Constant.SPACE);
		sql.append(Constant.SORT_BY_ASC);
		
		/*
		String sql = "SELECT `t_stylist_Id`,`t_stylist_areaId`,`t_stylist_searchConditionId`,`t_stylist_salonId` "
				+ "FROM `t_stylist` ORDER BY `t_stylist_Id` ASC";
				*/
		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
		Statement statement = dbConnection.getStatement();
		Map<Integer,List<String>> stylistAreaMap = new HashMap<Integer, List<String>>();
		Map<Integer,List<String>> stylistConditionMap = new HashMap<Integer, List<String>>();
		Map<Integer,Integer> stylistSalonMap = new HashMap<Integer, Integer>();
		
		//salonId, area, searchConditionIdが空ならreturn
		if(salonId==-1 && areaIdList.get(0).equals("") && searchConditionIdList.get(0).equals("")) return stylistInfoList;
		
		try {
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql.toString());
			while(rs.next()){
				List<String> styListAreaIdList = new ArrayList<String>();
				List<String> stylistSearchConditionList = new ArrayList<String>();
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
			    		break;
			    	}
			    }
			    
			    for(String id :areaIdList ){
			    	if(areaList.contains(id)){
			    		//debug
			    		//System.out.println("Stylist "+stylistId+" contains areaId:"+areaList+","+areaIdList);
			    		areaFlag = true;
			    		break;
			    	}
			    }
			    
			    if(salonId == stylistSalonId){
			    	//debug
			    	//System.out.println("Stylist "+stylistId+" contains salonId:"+salonId);
			    	salonFlag = true;
			    }
			    
			    boolean addFlag = false;
			    if(salonFlag && searchCondFlag) addFlag=true;
			    if(salonFlag && searchConditionIdList.get(0).equals("")) addFlag=true;
			    if(areaFlag && searchCondFlag) addFlag=true;
			    if(areaFlag && searchConditionIdList.get(0).equals("")) addFlag=true;
			    if(addFlag) retStylistIdList.add(stylistId);
			    /*
			    if(searchCondFlag && salonFlag){
		    		retStylistIdList.add(stylistId);
		    	}else if(searchCondFlag && areaFlag){
		    		retStylistIdList.add(stylistId);
		    	}
		    	if(searchCondFlag && salonFlag && areaFlag) retStylistIdList.add(stylistId);
		    	*/
			}
			
			//String innerSQL ="SELECT `t_stylist_Id` ,`t_stylist_salonId`,`t_stylist_name`,`t_stylist_sex`,`t_stylist_imagePath`,`t_stylist_message`,`t_stylist_experienceYear`,`t_stylist_favoriteNumber` FROM `t_stylist` WHERE `t_stylist_Id` =";
			StringBuilder innerSQL = new StringBuilder();
			innerSQL.append(Constant.SELECTALL);
			innerSQL.append(Constant.SPACE);
			innerSQL.append(Constant.BACKQ);
			innerSQL.append(Constant.TABLE_STYLIST);
			innerSQL.append(Constant.BACKQ);
			innerSQL.append(Constant.SPACE);
			innerSQL.append(Constant.WHERE);
			innerSQL.append(Constant.SPACE);
			innerSQL.append(Constant.BACKQ);
			innerSQL.append(TableConstant.COLUMN_STYLIST_ID);
			innerSQL.append(Constant.BACKQ);
			innerSQL.append(Constant.SPACE);
			innerSQL.append(Constant.EQUAL);
			innerSQL.append(Constant.SPACE);
			
			int hitCount=0;
			int offset = pageNum * Constant.ONE_PAGE_NUM;
			for(int retStylistId : retStylistIdList){
				if(Constant.ONE_PAGE_NUM > hitCount ){
					if(offset>=retStylistIdList.size()) break;
					//debug
					//System.out.println(innerSQL+String.valueOf("ArraySize["+retStylistIdList.size()+"]:"+retStylistIdList.get(offset)));
					ResultSet innerRs = statement.executeQuery(innerSQL.toString() + String.valueOf(retStylistIdList.get(offset)) );
					while(innerRs.next()){
						StylistInfo stylistInfo = new StylistInfo();
						stylistInfo.setObjectId(innerRs.getInt("t_stylist_Id"));
						stylistInfo.setSalonId(innerRs.getInt("t_stylist_salonId"));
						stylistInfo.setName(innerRs.getString("t_stylist_name"));
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

	
	public int setStylistInfoInsert(DBConnection dbConnection, StylistInfo stylistInfo){

		//DBステートメント
		Statement statement = dbConnection.getStatement();
		//返り値id
		int retId = -1;

		//Insertする情報をMapで整理
		Map<String, String> source = new HashMap<String, String>();
		source.put(TableConstant.COLUMN_STYLIST_NAME, stylistInfo.getName());
		source.put(TableConstant.COLUMN_STYLIST_SEX, Integer.toString(stylistInfo.getStylistGender()));
		source.put(TableConstant.COLUMN_STYLIST_DETAILTEXT, stylistInfo.getStylistDetailText());
		source.put(TableConstant.COLUMN_STYLIST_USERID, Integer.toString(stylistInfo.getUserId()));
		source.put(TableConstant.COLUMN_STYLIST_IMAGE, stylistInfo.getImagePath());
		source.put(TableConstant.COLUMN_STYLIST_POSITION, stylistInfo.getPosition());
		source.put(TableConstant.COLUMN_STYLIST_MESSAGE, stylistInfo.getStylistMessage());
		source.put(TableConstant.COLUMN_STYLIST_EXPERIENCEYEAR, stylistInfo.getStylistYearsNumber());
		source.put(TableConstant.COLUMN_STYLIST_SPECIALMENU, stylistInfo.getSpecialMenu());
		source.put(TableConstant.COLUMN_STYLIST_GOODNUMBER, "");
		source.put(TableConstant.COLUMN_STYLIST_VIEWNUMBER, "");
		source.put(TableConstant.COLUMN_STYLIST_MAIL, stylistInfo.getMail());
		source.put(TableConstant.COLUMN_STYLIST_PHONE, stylistInfo.getPhoneNumber());
		source.put(TableConstant.COLUMN_STYLIST_BIRTH, ""); //birth
		source.put(TableConstant.COLUMN_STYLIST_MENUID, stylistInfo.getMenuId());
		source.put(TableConstant.COLUMN_STYLIST_HAIRSTYLEID, "");
		source.put(TableConstant.COLUMN_STYLIST_SALONID, Integer.toString(stylistInfo.getSalonId()));
		source.put(TableConstant.COLUMN_STYLIST_FAVORITE_NUMBER, Integer.toString(stylistInfo.getFavoriteNumber()));
		source.put(TableConstant.COLUMN_STYLIST_ISNETRESERVATION,Integer.toString(stylistInfo.getIsNetReservation()));
		source.put(TableConstant.COLUMN_STYLIST_CONDITIONID, stylistInfo.getStylistSearchConditionId());
		source.put(TableConstant.COLUMN_STYLIST_AREAID, stylistInfo.getStylistAreaId());
				
		//DATETIME -> string 処理
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(stylistInfo.getBirth());
		source.replace(TableConstant.COLUMN_STYLIST_BIRTH, birth);
		
		//Insert SQL 
		StringBuilder insertSql = new StringBuilder();
		insertSql.append(Constant.INSERT);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.BACKQ);
		insertSql.append(ConfigUtil.getConfig("dbname"));
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.DOT);
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.TABLE_STYLIST);
		insertSql.append(Constant.BACKQ);
		insertSql.append(Constant.BRACKET_1);
		int index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()) {
			if(index == 0){
				insertSql.append(Constant.BACKQ);
				insertSql.append(entry.getKey());
				insertSql.append(Constant.BACKQ);
				index++;
			}else{
				insertSql.append(Constant.COMMA);
				insertSql.append(Constant.SPACE);
				insertSql.append(Constant.BACKQ);
				insertSql.append(entry.getKey());
				insertSql.append(Constant.BACKQ);
				index++;
			}
		}
		insertSql.append(Constant.BRACKET_2);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.VALUES);
		insertSql.append(Constant.SPACE);
		insertSql.append(Constant.BRACKET_1);
		index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()) {
			if(index == 0){
				insertSql.append(Constant.SINGLEQ);
				insertSql.append(entry.getValue());
				insertSql.append(Constant.SINGLEQ);
				index++;
			}else{
				insertSql.append(Constant.COMMA);
				insertSql.append(Constant.SPACE);
				insertSql.append(Constant.SINGLEQ);
				insertSql.append(entry.getValue());
				insertSql.append(Constant.SINGLEQ);
				index++;
			}
		}
		insertSql.append(Constant.BRACKET_2);
		insertSql.append(Constant.SEMICOLON);

		//sql成功確認
		int result_int = -1;

		try {
			//debug
			logger.info("{}",insertSql.toString());
			result_int = statement.executeUpdate(insertSql.toString(), Statement.RETURN_GENERATED_KEYS);
			if(result_int > 0){
				//更新したidをget
				ResultSet rs = statement.getGeneratedKeys();
		        if (rs.next()){
		        	retId = rs.getInt(1);
	        	}
		        rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return retId;
	}
	
	public int setStylistInfoUpdate(DBConnection dbConnection, StylistInfo stylistInfo){

		//DBステートメント
		Statement statement = dbConnection.getStatement();
		//返り値id
		int retId = -1;

		//Insertする情報をMapで整理
		Map<String, String> source = new HashMap<String, String>();
		source.put(TableConstant.COLUMN_STYLIST_NAME, stylistInfo.getName());
		source.put(TableConstant.COLUMN_STYLIST_SEX, Integer.toString(stylistInfo.getStylistGender()));
		source.put(TableConstant.COLUMN_STYLIST_DETAILTEXT, stylistInfo.getStylistDetailText());
		source.put(TableConstant.COLUMN_STYLIST_USERID, Integer.toString(stylistInfo.getUserId()));
		source.put(TableConstant.COLUMN_STYLIST_IMAGE, stylistInfo.getImagePath());
		source.put(TableConstant.COLUMN_STYLIST_POSITION, stylistInfo.getPosition());
		source.put(TableConstant.COLUMN_STYLIST_MESSAGE, stylistInfo.getStylistMessage());
		source.put(TableConstant.COLUMN_STYLIST_EXPERIENCEYEAR, stylistInfo.getStylistYearsNumber());
		source.put(TableConstant.COLUMN_STYLIST_SPECIALMENU, stylistInfo.getSpecialMenu());
		source.put(TableConstant.COLUMN_STYLIST_GOODNUMBER, "");
		source.put(TableConstant.COLUMN_STYLIST_VIEWNUMBER, "");
		source.put(TableConstant.COLUMN_STYLIST_MAIL, stylistInfo.getMail());
		source.put(TableConstant.COLUMN_STYLIST_PHONE, stylistInfo.getPhoneNumber());
		source.put(TableConstant.COLUMN_STYLIST_BIRTH, ""); //birth
		source.put(TableConstant.COLUMN_STYLIST_MENUID, stylistInfo.getMenuId());
		source.put(TableConstant.COLUMN_STYLIST_HAIRSTYLEID, "");
		source.put(TableConstant.COLUMN_STYLIST_SALONID, Integer.toString(stylistInfo.getSalonId()));
		source.put(TableConstant.COLUMN_STYLIST_FAVORITE_NUMBER, Integer.toString(stylistInfo.getFavoriteNumber()));
		source.put(TableConstant.COLUMN_STYLIST_ISNETRESERVATION,Integer.toString(stylistInfo.getIsNetReservation()));
		source.put(TableConstant.COLUMN_STYLIST_CONDITIONID, stylistInfo.getStylistSearchConditionId());
		source.put(TableConstant.COLUMN_STYLIST_AREAID, stylistInfo.getStylistAreaId());
				
		//DATETIME -> string 処理
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(stylistInfo.getBirth());
		source.replace(TableConstant.COLUMN_STYLIST_BIRTH, birth);

		//Update SQL 
		StringBuilder updateSql = new StringBuilder();
		updateSql.append(Constant.UPDATE);
		updateSql.append(Constant.BACKQ);
		updateSql.append(ConfigUtil.getConfig("dbname"));
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.DOT);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.TABLE_STYLIST);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.SET);
		updateSql.append(Constant.SPACE);
		
		int index = 0;
		for(Map.Entry<String, String> entry : source.entrySet()){
			if(index==0){ 
				updateSql.append(Constant.BACKQ);
				updateSql.append(entry.getKey());
				updateSql.append(Constant.BACKQ);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.EQUAL);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.SINGLEQ);
				updateSql.append(entry.getValue());
				updateSql.append(Constant.SINGLEQ);
			}else{
				updateSql.append(Constant.COMMA);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.BACKQ);
				updateSql.append(entry.getKey());
				updateSql.append(Constant.BACKQ);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.EQUAL);
				updateSql.append(Constant.SPACE);
				updateSql.append(Constant.SINGLEQ);
				updateSql.append(entry.getValue());
				updateSql.append(Constant.SINGLEQ);
			}
		}
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.WHERE);
		updateSql.append(Constant.SPACE);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.TABLE_STYLIST);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.DOT);
		updateSql.append(Constant.BACKQ);
		updateSql.append(TableConstant.COLUMN_STYLIST_ID);
		updateSql.append(Constant.BACKQ);
		updateSql.append(Constant.EQUAL);
		updateSql.append(Constant.SPACE);
		updateSql.append(stylistInfo.getObjectId());
		updateSql.append(Constant.SEMICOLON);
		
		//sql成功確認
		int result_int = -1;

		try {
			//debug
			logger.info("{}",updateSql.toString());
			result_int = statement.executeUpdate(updateSql.toString(), Statement.RETURN_GENERATED_KEYS);
			if(result_int > 0){
				//更新したidをget
				ResultSet rs = statement.getGeneratedKeys();
		        if (rs.next()){
		        	retId = rs.getInt(1);
	        	}
		        rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return retId;
	}
	
	public boolean DeleteStylistObject(DBConnection dbConnection, String t_stylist_Id) {
		/**
		 * SQL 例:
		 * DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` WHERE `t_stylist`.`t_stylist_Id` = 13
		 * 
		*/
		boolean result = false;
		StringBuilder deleteSql = new StringBuilder();
		deleteSql.append(Constant.DELETE);
		deleteSql.append(Constant.SPACE);
		deleteSql.append(Constant.FROM);
		deleteSql.append(Constant.SPACE);
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(ConfigUtil.getConfig("dbname"));
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(Constant.DOT);
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(Constant.TABLE_STYLIST);
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(Constant.SPACE);
		deleteSql.append(Constant.WHERE);
		deleteSql.append(Constant.SPACE);
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(Constant.TABLE_STYLIST);
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(Constant.DOT);
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(TableConstant.COLUMN_STYLIST_ID);
		deleteSql.append(Constant.BACKQ);
		deleteSql.append(Constant.SPACE);
		deleteSql.append(Constant.EQUAL);
		deleteSql.append(Constant.SPACE);
		deleteSql.append(t_stylist_Id);
		
		Statement statement = dbConnection.getStatement();
		//debug
		logger.info(deleteSql.toString());
		try {
			int result_int = statement.executeUpdate(deleteSql.toString());
			if(result_int > 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	/*
	public boolean DeleteStylistInfoForMaster(DBConnection dbConnection,
			String t_stylist_Id , int salonId) {
			boolean result = false;
			*/
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
			
			/*
			 * SELECT `t_stylist_userId` FROM `t_stylist` WHERE `t_stylist_Id` = 1
			 * DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_user` WHERE `t_user`.`t_user_Id` = 1
			 */
			/*
			String sql = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` WHERE `t_stylist`.`t_stylist_Id` = ";
			Statement statement = dbConnection.getStatement();

			//userテーブルから削除
			String u_select_sql = "SELECT `t_stylist_userId` FROM `t_stylist` WHERE `t_stylist_Id` = "+t_stylist_Id;
			String u_delete_sql = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_user` WHERE `t_user`.`t_user_Id` =";
			int u_Id = -1;
			//debug
			System.out.println(u_select_sql);
			try {
				ResultSet rs = statement.executeQuery(u_select_sql);
				while(rs.next()){
					u_Id = rs.getInt("t_stylist_userId");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			//debug
			System.out.println(u_delete_sql+u_Id);
			try {
				int result_int = statement.executeUpdate(u_delete_sql + u_Id);
				//System.out.println(result_int);
				if(result_int > 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//debug
			System.out.println(sql);
			try {
				int result_int = statement.executeUpdate(sql + t_stylist_Id);
				//System.out.println(result_int);
				if(result_int > 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// stylist をsalon　から消さなきゃ
			//SQL
			String salon_sql1 = "SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
			String salon_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_stylistId` = '";
			String salon_sql2_middle = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = ";
			String salon_sql2_after = ";";
			
			ResultSet rs;
			String stylistIdList = "";
			try {
				rs = statement.executeQuery(salon_sql1+salonId);
				while(rs.next()){
					stylistIdList = rs.getString("t_hairSalonMaster_stylistId");
					//debug
					//System.out.println("stylistIdList = " + stylistIdList);
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<String> idList = new LinkedList<String>(Arrays.asList(stylistIdList.split(",")));
			Iterator<String> i = idList.iterator();
	        while(i.hasNext()){
	            String name = i.next();
	            if(name.equals(t_stylist_Id)){
	                i.remove();
	            }
	        }
	        String newStylistIdList = "";
			for (String id : idList){
				newStylistIdList += id + ",";
			}
			if(newStylistIdList.lastIndexOf(',')!=-1){
				newStylistIdList = newStylistIdList.substring(0, newStylistIdList.lastIndexOf(','));
			}
			//debug
			//System.out.println("NewIdList?:" + newStylistIdList);
			
			String salon_sql;
			salon_sql =  salon_sql2_before + newStylistIdList + salon_sql2_middle + salonId + salon_sql2_after;								
			
			//debug
			System.out.println(salon_sql);
			try {
				int result_int = statement.executeUpdate(salon_sql);
				if(result_int < 0) result = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			}
			
			//stylist のhairStyle を削除
			//SQL
			String hStyle_sql_select = "SELECT `t_hairStyle_id` FROM `t_hairStyle` WHERE `t_hairStyle_stylistId` =" + t_stylist_Id;
			String hStyle_sql_delete = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_hairStyle` WHERE `t_hairstyle`.`t_hairStyle_id` = ";
			
			List<String> hairStyleIdList = new ArrayList<>();
			try {
				rs = statement.executeQuery(hStyle_sql_select);
				while(rs.next()){
					hairStyleIdList.add(rs.getString("t_hairStyle_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(String hairStyleId : hairStyleIdList){
				try {
					//debug
					System.out.println(hStyle_sql_delete+hairStyleId);
					int result_int = statement.executeUpdate(hStyle_sql_delete+hairStyleId);
					if(result_int < 0) result = false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result = false;
					break;
				}
			}
			
			return result;	
		}
		*/
	/*
	public boolean setStylistCondition(DBConnection dbConnection,
			String stylistId, List<String> tagList) {
		boolean result = false;
		
		/**
		 * stylistId のstylistに対して、
		 * conditionタグをインサート。
		 */

		/**
		 * SQL 例:
		 *UPDATE `MEIHAIWAN_TEST`.`t_stylist` SET `t_stylist_searchConditionId` = '1,2,3' WHERE `t_stylist`.`t_stylist_Id` = 31;
		 * 
		*/
		/*
		if(!CommonUtil.isNum(stylistId)){
			return result;
		}
		
		String tags="";
		for(String tag:tagList){
			if(!CommonUtil.isNum(tag)){
				return result;
			}
			tags += tag+",";
		}
		tags = tags.substring(0,tags.length()-1);
		String sql = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_searchConditionId` = '"
		+tags+ "' WHERE `t_stylist`.`t_stylist_Id` = "+stylistId;
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql);
			if(result_int > 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
	}

	public int getStylistSalonId(DBConnection dbConnection, String stylistId) throws SQLException {
		String sql = 
				"SELECT `t_stylist_salonId` FROM `t_stylist` WHERE `t_stylist_Id` = "+stylistId;
		int salonId = -1;
		
		Statement statement = dbConnection.getStatement();
		try {
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				salonId = rs.getInt("t_stylist_salonId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonId;
	}
	
	public StylistInfo getStylistInfoByUserId(DBConnection dbConnection, int userId) throws SQLException {
		StylistInfo stylistInfo = new StylistInfo();
		String sql = 
				"SELECT `t_stylist_Id`,`t_stylist_name` FROM `t_stylist` WHERE `t_stylist_userId` = "+userId;
		
		Statement statement = dbConnection.getStatement();
		try {
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				stylistInfo.setStylistId(rs.getInt("t_stylist_Id"));
				stylistInfo.setStylistName(rs.getString("t_stylist_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return stylistInfo;
	}

	public String getStylistAreaId(DBConnection dbConnection, int stylistId) throws SQLException {
		String areaId = "-1";
		String sql = 
				"SELECT `t_stylist_areaId` FROM `t_stylist` WHERE `t_stylist_Id` = "+stylistId;
		Statement statement = dbConnection.getStatement();
		try {
			//debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				areaId = rs.getString("t_stylist_areaId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return areaId;
	}
		*/

	/*
	public List<Integer> getStylistHistoryIdList(DBConnection dbConnection, int Stylist_id) throws SQLException{
		String sql = "SELECT `t_Stylist_latestViewStylistId` FROM `t_Stylist` WHERE t_Stylist_Id=" + Stylist_id;
		List<Integer> stylistIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_Stylist_latestViewStylistId");
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
		
		// 履歴にまだ登録されていない＝null 
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
		String sql = "SELECT `t_stylist_Id`, `t_stylist_salonId`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_imagePath`, `t_stylist_message`, `t_stylist_experienceYear`, `t_stylist_favoriteNumber`, `t_stylist_isNetReservation`,`t_stylist_userId` FROM `t_stylist` WHERE t_stylist_Id =" + stylistId.toString();
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
				stylistInfo.setUserId(rs.getInt("t_stylist_userId"));
			}
	
		}catch (SQLException e) {
				e.printStackTrace();
				throw e;
		}
		return stylistInfo;		
	}

	public List<Integer> getStylistFavoriteIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_favoriteStylistId` FROM `t_user` WHERE t_user_Id =" + user_id;
		List<Integer> stylistIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_favoriteStylistId");
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
		
		// お気に入りにまだ何も登録されていない＝null 
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
		
	//空っぽのデータをつっこむ
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
	*/

	/*
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
		
		String sql = 
				"SELECT `t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_phoneNumber`, "
				+ "`t_stylist_mail`, `t_stylist_imagePath`, `t_stylist_birth`, `t_stylist_searchConditionId`, "
				+ "`t_stylist_position`, `t_stylist_experienceYear`, `t_stylist_specialMenu`, `t_stylist_message`, "
				+ "`t_stylist_menuId`,`t_stylist_restTime`,`t_stylist_restDay` FROM `t_stylist` WHERE t_stylist_Id="; 

		List<StylistInfo> stylistInfoList = new ArrayList<StylistInfo>();
	 		 	
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
					stylistInfo.setStylistSearchConditionId(rs.getString("t_stylist_searchConditionId"));
					stylistInfo.setStylistRestTime(rs.getString("t_stylist_restTime"));
					stylistInfo.setStylistRestDay(rs.getString("t_stylist_restDay"));
					stylistInfoList.add(stylistInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return stylistInfoList;
	}
	*/
	/*
	public int  setStylistInfoForMaster(DBConnection dbConnection, int salonId,
			StylistInfo stylistInfo, int t_stylist_Id, int userId, String areaId) {
		*/
		/**
		 * stylistId からstylist情報があるかどうか確認。
		 * idがテーブルに存在したらx
		 * idが存在しなければinsertする
		 */

		/**
		 *  stylist情報insert or update
		 *  stylistIdがnullならinsert, not nullならupdate
		 *  stylistIdをsalonMasterに追加
		 *  stylistをuserに登録
		 *  userIdをstylistに追加
		 */
	
		/**
		 * SQL 例:
		 * SELECT * FROM `t_stylist` WHERE `t_stylist_Id` = 1
		 * 
		 * UPDATE `MEIHAIWAN_TEST`.`t_stylist` SET `t_stylist_name` = 'a_', `t_stylist_sex` = '1', `t_stylist_detailText` = '_' WHERE `t_stylist`.`t_stylist_Id` = 6;
		 * 
		 * INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` (`t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_detailText`, `t_stylist_userId`, `t_stylist_imagePath`, `t_stylist_position`, `t_stylist_message`, `t_stylist_experienceYear`, `t_stylist_specialMenu`, `t_stylist_goodNumber`, `t_stylist_viewNumber`, `t_stylist_mail`, `t_stylist_phoneNumber`, `t_stylist_birth`, `t_stylist_menuId`, `t_stylist_hairStyleId`, `t_stylist_salonId`, `t_stylist_favoriteNumber`, `t_stylist_isNetReservation`, `t_stylist_searchConditionId`, `t_stylist_areaId`) VALUES ('
		 * 10', 'name', '1', NULL, NULL, 'imagePath', 'position', 'message', '2', 'sp', NULL, NULL, 'mail.com', '090-', '2015-06-15 00:00:00', NULL, NULL, NULL, '0', '1', NULL, NULL);
		 * 
		 * SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = 1
		 * UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_stylistId` = '1,2,3,4' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = 1;
		 * 
		*/
		/*
		//INSERTメソッド
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` ("
				+ "`t_stylist_name`, `t_stylist_sex`, `t_stylist_detailText`, "
				+ "`t_stylist_userId`, `t_stylist_imagePath`, `t_stylist_position`, `t_stylist_message`, "
				+ "`t_stylist_experienceYear`, `t_stylist_specialMenu`, `t_stylist_goodNumber`, `t_stylist_viewNumber`, "
				+ "`t_stylist_mail`, `t_stylist_phoneNumber`, `t_stylist_birth`, `t_stylist_menuId`, "
				+ "`t_stylist_hairStyleId`, `t_stylist_salonId`, `t_stylist_favoriteNumber`, `t_stylist_isNetReservation`, "
				+ "`t_stylist_searchConditionId`, `t_stylist_areaId`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "";
		String sql4 = "0";
		String sql_end = "');";
		*/
		
		/*
		//処理結果と返り値
		boolean result = false;
		int stylistId = t_stylist_Id;
		
		//Insertする情報をMapで整理
		Map<String, String> source = new HashMap<String, String>();
		source.put(TableConstant.COLUMN_STYLIST_NAME, stylistInfo.getName());
		source.put(TableConstant.COLUMN_STYLIST_SEX, Integer.toString(stylistInfo.getStylistGender()));
		source.put(TableConstant.COLUMN_STYLIST_DETAILTEXT, stylistInfo.getStylistDetailText());
		source.put(TableConstant.COLUMN_STYLIST_USERID, Integer.toString(stylistInfo.getUserId()));
		source.put(TableConstant.COLUMN_STYLIST_IMAGE, stylistInfo.getImagePath());
		source.put(TableConstant.COLUMN_STYLIST_POSITION, stylistInfo.getPosition());
		source.put(TableConstant.COLUMN_STYLIST_MESSAGE, stylistInfo.getStylistMessage());
		source.put(TableConstant.COLUMN_STYLIST_EXPERIENCEYEAR, stylistInfo.getStylistYearsNumber());
		source.put(TableConstant.COLUMN_STYLIST_SPECIALMENU, stylistInfo.getSpecialMenu());
		source.put(TableConstant.COLUMN_STYLIST_GOODNUMBER, "");
		source.put(TableConstant.COLUMN_STYLIST_VIEWNUMBER, "");
		source.put(TableConstant.COLUMN_STYLIST_MAIL, stylistInfo.getMail());
		source.put(TableConstant.COLUMN_STYLIST_PHONE, stylistInfo.getPhoneNumber());
		source.put(TableConstant.COLUMN_STYLIST_BIRTH, ""); //birth
		source.put(TableConstant.COLUMN_STYLIST_MENUID, stylistInfo.getMenuId());
		source.put(TableConstant.COLUMN_STYLIST_HAIRSTYLEID, "");
		source.put(TableConstant.COLUMN_STYLIST_SALONID, Integer.toString(stylistInfo.getSalonId()));
		source.put(TableConstant.COLUMN_STYLIST_FAVORITE_NUMBER, Integer.toString(stylistInfo.getFavoriteNumber()));
		source.put(TableConstant.COLUMN_STYLIST_ISNETRESERVATION,Integer.toString(stylistInfo.getIsNetReservation()));
		source.put(TableConstant.COLUMN_STYLIST_CONDITIONID, stylistInfo.getStylistSearchConditionId());
		source.put(TableConstant.COLUMN_STYLIST_AREAID, "");
				
		//DATETIME -> string 処理
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(stylistInfo.getBirth());
		source.replace(TableConstant.COLUMN_STYLIST_BIRTH, birth);

		//UPDATEメソッド
		String u_sql_update_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_userId` = '";
		String u_sql_update_after = "' WHERE `t_stylist`.`t_stylist_Id` =";
		
		//date '2015-06-03 00:00:00' format sample.
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birth = format.format(stylistInfo.getBirth());
		
		//update
		String sql_update = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET "
				+ "`t_stylist_Id` = '" + stylistId + "', "
				+ "`t_stylist_name` = '" + stylistInfo.getStylistName() + "', "
				+ "`t_stylist_sex` = '" + stylistInfo.getStylistGender() + "', "
				+ "`t_stylist_imagePath` = '" + stylistInfo.getStylistImagePathStr() + "', "
				+ "`t_stylist_position` = '" +  stylistInfo.getPosition()  + "', "
				+ "`t_stylist_message` = '" +  stylistInfo.getStylistMessage()  + "', "
				+ "`t_stylist_experienceYear` = '" +  stylistInfo.getStylistYearsNumber()  + "', "
				+ "`t_stylist_specialMenu` = '" +  stylistInfo.getSpecialMenu()  + "', "
				+ "`t_stylist_mail` = '" +  stylistInfo.getMail()  + "', "
				+ "`t_stylist_phoneNumber` = '" +  stylistInfo.getPhoneNumber()  + "', "
				+ "`t_stylist_birth` = '" +  birth  + "', "
				+ "`t_stylist_searchConditionId` = '" +  stylistInfo.getStylistSearchConditionId()  + "',"
				+ "`t_stylist_areaId` = '" + areaId + "'"
				+ " WHERE `t_stylist`.`t_stylist_Id` = " + stylistId;

		//サロンid追加メソッド
		String salon_sql1 = "SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
		String salon_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_stylistId` = '";
		String salon_sql2_middle = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = ";
		String salon_sql2_after = ";";
		
		//tel -> stylistIdを取得
		String sid_sql = "SELECT `t_stylist_id` FROM `t_stylist` WHERE `t_stylist_phoneNumber` ="; 

		//DBステートメント
		Statement statement = dbConnection.getStatement();

		/**
		 * stylistIdが0より小さい場合は新規スタイリスト登録
		 * stylistIdが0より大きい場合はスタイリスト情報修正
		 */
		/*
		if(stylistId < 0){			
			//INSERTメソッドを形成
			String sql = sql1 
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
					+ stylistInfo.getStylistSearchConditionId() + sql2
					+ areaId //areaId
					+ sql_end;
	
			//debug
			System.out.println(sql);
			
			try {
				int result_int = statement.executeUpdate(sql);
				if(result_int >= 0){
					result = true;
					//debug
					System.out.println("result_int = " + result_int);
				}
				else{
					return -1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//stylistIdを検索
			try {			
				//debug
				System.out.println(sid_sql+"'"+stylistInfo.getPhoneNumber()+"'");
				ResultSet rs = statement.executeQuery(sid_sql+"'"+stylistInfo.getPhoneNumber()+"'");
				while(rs.next()){
					stylistId = rs.getInt("t_stylist_Id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		

			// サロンテーブルにsylistIdを追加
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

			String salon_sql;
			if(stylistId != -1){
				if(stylistIdList!=""&&stylistIdList!=null){ //スタイリスト一人目
					salon_sql =  salon_sql2_before + stylistIdList + "," + stylistId + salon_sql2_middle + salonId + salon_sql2_after;				
				}else{
					salon_sql =  salon_sql2_before + stylistId + salon_sql2_middle + salonId + salon_sql2_after;								
				}
				System.out.println(salon_sql);
				try {
					int result_int = statement.executeUpdate(salon_sql);
					if(result_int < 0){
						stylistId = -1;
						return stylistId;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					stylistId = -1;
				}
			}		

		/*user regist.
			for(int i=1; i<Integer.MAX_VALUE; i++){
				try {
					ResultSet rs2 = statement.executeQuery(u_sql_before+Integer.toString(i));
					if(!rs2.next()){
						u_Id = i;
						break;
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		
			//TODO 初期パスワードはとりあえず0000
			String u_sql = u_sql1 +u_Id + sql2
					+ sql4 + sql2
					+ stylistInfo.getMail() + sql2
					+ "0000" + sql2
					+ sql3 + sql2 //cookie
					+ stylistInfo.getStylistImagePathStr()  + sql2
					+ stylistInfo.getStylistGender()  + sql2
					+ birth + sql2
					+ stylistInfo.getStylistName() + sql2
					+ sql3 + sql2 //favoriteSalon
					+ sql3 + sql2 //favoriteStylist
					+ sql3 + sql2 //latestviewsalon
					+ sql3 + sql2 //latestviewstylist
					+ sql3 + sql2 //favoritehairstyle
					+ sql3 + sql2 //latestviewhairstyle
					+ sql4 + sql2 //point
					+ sql4 + sql2 //historysalon
					+ salonId //salonId
					+ sql_end;
	
			//debug
			System.out.println(u_sql);
			
			try {
				int result_int = statement.executeUpdate(u_sql);
				if(result_int >= 0) result = true;
				else return -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			/*
			//stylistテーブルにuserIdを追加
			String u_sql_update = u_sql_update_before + userId + u_sql_update_after + stylistId;
			//debug
			System.out.println(u_sql_update);
			try {
				int result_int = statement.executeUpdate(u_sql_update);
				if(result_int < 0){
					stylistId = -1;
					return stylistId;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				stylistId = -1;
			}
			
		//update
		}else{
			//debug
			System.out.println(sql_update);			
			try {
				int result_int = statement.executeUpdate(sql_update);
				if(result_int >= 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return stylistId;
	}
	*/

	/*
	public boolean setStylistMenuForMaster(DBConnection dbConnection,
			String t_stylist_Id, String t_menu_t_menu_id) {
		boolean result = false;
				
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
	*/
	
}
