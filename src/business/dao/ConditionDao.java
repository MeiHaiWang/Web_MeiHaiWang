package business.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.constant.Constant;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.ConditionTypeInfo;
import common.util.CommonUtil;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class ConditionDao {
	public ConditionDao() throws Exception {

	}

	/**
	 * 
	 * @param dbConnection
	 * @param ConditionType
	 *            : サロン検索条件：１ スタイリスト検索条件：2 スタイリスト検索好み：3 ヘアスタイル検索メニュー：4
	 *            ヘアスタイル検索顔型：5
	 * @return
	 * @throws SQLException
	 */
	public List<ConditionInfo> getConditionInfo(DBConnection dbConnection,
			List<ConditionTitleInfo> ConditionTitleInfoList)
			throws SQLException {
		String sql = "SELECT `t_masterSearchCondition_id`, `t_masterSearchCondition_name` ,`t_masterSearchCondition_titleId`"
				+ "FROM `t_masterSearchCondition` WHERE `t_masterSearchCondition_titleId`=";
		ArrayList<ConditionInfo> ConditionInfoList = new ArrayList<ConditionInfo>();

		Statement statement = dbConnection.getStatement();
		try {
			for (ConditionTitleInfo conditionTitleInfo : ConditionTitleInfoList) {
				ResultSet rs = statement.executeQuery(sql
						+ conditionTitleInfo.getConditionTitleId());
				while (rs.next()) {
					ConditionInfo ConditionInfo = new ConditionInfo();
					ConditionInfo.setConditionName(rs
							.getString("t_masterSearchCondition_name"));
					ConditionInfo.setConditionId(rs
							.getInt("t_masterSearchCondition_Id"));
					ConditionInfo.setConditionTitleId(rs
							.getInt("t_masterSearchCondition_titleId"));
					ConditionInfoList.add(ConditionInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ConditionInfoList;
	}

	public List<ConditionTitleInfo> getConditionTitleInfo(
			DBConnection dbConnection, String conditionType)
			throws SQLException, UnsupportedEncodingException {
		String typeStr = "";
		int conditionTypeId = -1;
		ArrayList<ConditionTitleInfo> ConditionTitleInfoList = new ArrayList<ConditionTitleInfo>();

		/*
		 * try{
		 * if(ConditionTitleType.compareTo(Constant.TYPE_FOR_SALON_CONDITION
		 * )==0) typeStr = new
		 * String(Constant.TITLE_NAME_LIST_FOR_SALON.getBytes("UTF-8"),"UTF-8");
		 * else
		 * if(ConditionTitleType.compareTo(Constant.TITLE_FOR_STYLIST_CODITION
		 * )==0) typeStr = new
		 * String(Constant.TITLE_NAME_LIST_FOR_STYLIST_CODITION
		 * .getBytes("UTF-8"),"UTF-8"); else
		 * if(ConditionTitleType.compareTo(Constant.TITLE_FOR_STYLIST_LIKE)==0)
		 * typeStr = new
		 * String(Constant.TITLE_NAME_LIST_FOR_STYLIST_LIKE.getBytes
		 * ("UTF-8"),"UTF-8"); else
		 * if(ConditionTitleType.compareTo(Constant.TITLE_FOR_HAIRSTYLE_MENU
		 * )==0) typeStr = new
		 * String(Constant.TITLE_NAME_LIST_FOR_HAIRSTYLE_MENU
		 * .getBytes("UTF-8"),"UTF-8"); else
		 * if(ConditionTitleType.compareTo(Constant
		 * .TITLE_FOR_HAIRSTYLE_FACE)==0) typeStr = new
		 * String(Constant.TITLE_NAME_LIST_FOR_HAIRSTYLE_FACE
		 * .getBytes("UTF-8"),"UTF-8"); else typeStr = "";
		 * }catch(UnsupportedEncodingException e){ e.printStackTrace(); throw e;
		 * }
		 */

		String sql_1 = "SELECT * FROM `t_masterSearchConditionType` WHERE `t_masterSearchConditionType_name` = '"
				+ conditionType + "'";
		String sql_2 = "SELECT * FROM `t_masterSearchConditionTitle` WHERE `t_masterSearchConditionTitle_typeId` = ";
		/*
		 * sql =
		 * "SELECT `t_masterSearchConditionTitle_id`,`t_masterSearchConditionTitle_name` FROM "
		 * +
		 * "`t_masterSearchConditionTitle` WHERE `t_masterSearchConditionTitle_name` "
		 * + "IN(" + typeStr + ")";
		 */
		Statement statement = dbConnection.getStatement();
		try {
			// debug
			System.out.println(sql_1);

			ResultSet rs = statement.executeQuery(sql_1);
			while (rs.next()) {
				conditionTypeId = rs.getInt("t_masterSearchConditionType_id");
			}

			rs = statement.executeQuery(sql_2 + conditionTypeId);
			// debug
			System.out.println(sql_2 + conditionTypeId);

			while (rs.next()) {
				ConditionTitleInfo conditionTitleInfo = new ConditionTitleInfo();
				conditionTitleInfo.setConditionTitleId(rs
						.getInt("t_masterSearchConditionTitle_id"));
				conditionTitleInfo.setConditionTitleName(rs
						.getString("t_masterSearchConditionTitle_name"));
				ConditionTitleInfoList.add(conditionTitleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ConditionTitleInfoList;
	}

	public int setHairStyleCondition(DBConnection dbConnection,
			int hairStyleId, List<String> searchConditionIdList) {

		/**
		 * ヘアスタイルの検索条件を追加するAPI
		 * 現在の検索条件を取得し、異なるIDだけを追加する
		 */
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairStyle` SET `t_hairStyle_searchConditionId` = '";
		String sql2 = "' WHERE `t_hairStyle`.`t_hairStyle_id` = "+hairStyleId+";";
		String searchsql = "SELECT `t_hairStyle_searchConditionId` FROM `t_hairStyle` WHERE `t_hairStyle_id` = "+hairStyleId;
		int result_int = -1;

		Statement statement = dbConnection.getStatement();
		try {
			String condIdStr = null;
			List<String> condIdList = new ArrayList<String>();
			ResultSet rs = statement.executeQuery(searchsql);
			// debug
			System.out.println(searchsql);
			while (rs.next()) {
				condIdStr = rs.getString("t_hairStyle_searchConditionId");
			}
			if(condIdStr!=null){
				condIdList = Arrays.asList(condIdStr.split(","));
			}
			
			String searchConditionIdListStr = "";
			if(searchConditionIdList.size()>0){
				for(String id : searchConditionIdList){
					if(!condIdList.contains(id) && CommonUtil.isNum(id)){
						searchConditionIdListStr += id + ",";
					}
				}
				if(searchConditionIdListStr.length()>0){
					searchConditionIdListStr = searchConditionIdListStr.substring(0,searchConditionIdListStr.length()-1);
				}
			}
			
			if(condIdStr!=null && searchConditionIdListStr.length()>0){
				searchConditionIdListStr = condIdStr + ","+ searchConditionIdListStr;
			}else{
				searchConditionIdListStr = condIdStr;
			}
			String sql = sql1 + searchConditionIdListStr + sql2;
			//debug
			System.out.println(sql);
			result_int = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return result_int;
	}

	/**
	 * 
	 * @param dbConnection
	 * @param stylistId
	 * @param searchConditionIdList
	 * @return
	 * 
	 * スタイリストの検索条件を登録されているものに追加
	 */
	public int setStylistCondition(DBConnection dbConnection, int stylistId,
			String searchConditionIdList) {
		/**
		 * UPDATE `MEIHAIWAN_TEST`.`t_stylist` SET `t_stylist_searchConditionId` = '1,2,3' WHERE `t_stylist`.`t_stylist_Id` = 1;
		 */
		String search = "SELECT `t_stylist_searchConditionId` FROM `t_stylist` WHERE `t_stylist_Id` = "+stylistId;
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_searchConditionId` = '";
		String sql2 = "' WHERE `t_stylist`.`t_stylist_Id` = "+stylistId+";";

		//既存の検索条件リストを取得
		String conditionlist = "";
		//debug
		System.out.println(search);
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(search);
			while (rs.next()) {
				conditionlist = rs.getString("t_stylist_searchConditionId");
				if(conditionlist.equals("-1")) conditionlist="";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		//重複してない検索条件だけを詰め込む追加listを作成
		String addlist = "";
		if(conditionlist!=null && !conditionlist.equals("")){
			List<String> existIdList = Arrays.asList(conditionlist.split(","));
			List<String> addIdList = Arrays.asList(searchConditionIdList.split(","));
			for(String a: addIdList){
				if(!existIdList.contains(a)){
					addlist+=a+",";
				}
			}
			if(addlist.length()>0){
				//追加する項目あり。
				addlist = addlist.substring(0,addlist.length()-1);
				addlist = conditionlist+","+addlist;
			}else{
				//追加する項目なし。そのまま。
				addlist = conditionlist;
			}
		}else{
			//新規登録
			addlist = searchConditionIdList;
		}
		//いざ検索条件を追加update
		//debug
		System.out.println(sql1+addlist+sql2);
		String sql = sql1+addlist+sql2;
		int result_int = -1;
		try {
			result_int = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result_int;
	}

	public List<ConditionTypeInfo> getConditionTypeInfo(
			DBConnection dbConnection) {
		List<ConditionTypeInfo> condTypeList = new ArrayList<ConditionTypeInfo>();

		String sql = "SELECT * FROM `t_masterSearchConditionType`";
		Statement statement = dbConnection.getStatement();
		try {
			// debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ConditionTypeInfo cond = new ConditionTypeInfo();
				cond.setConditionTypeId( rs.getInt("t_masterSearchConditionType_id"));
				cond.setConditionTypeName( rs.getString("t_masterSearchConditionType_name"));
				condTypeList.add(cond);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return condTypeList;
	}

	public String getConditionTypeName(DBConnection dbConnection,
			String type) {
		String sql = "SELECT `t_masterSearchConditionType_name` FROM `t_masterSearchConditionType` WHERE `t_masterSearchConditionType_id` = "+type;
		Statement statement = dbConnection.getStatement();
		String typeName = "";
		try {
			// debug
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				typeName = rs.getString("t_masterSearchConditionType_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return typeName;
	}

}
