package business.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.constant.Constant;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.util.DBConnection;

public class ConditionDao {
	public ConditionDao() throws Exception{
		
	}
	
	/**
	 * 
	 * @param dbConnection
	 * @param ConditionType: 
	 *  サロン検索条件：１
		スタイリスト検索条件：2
		スタイリスト検索好み：3
		ヘアスタイル検索メニュー：4
		ヘアスタイル検索顔型：5
	 * @return
	 * @throws SQLException
	 */
	public List<ConditionInfo> getConditionInfo(DBConnection dbConnection, List<ConditionTitleInfo> ConditionTitleInfoList) throws SQLException{
		String sql = "SELECT `t_masterSearchCondition_id`, `t_masterSearchCondition_name` ,`t_masterSearchCondition_titleId`"
				+ "FROM `t_masterSearchCondition` WHERE `t_masterSearchCondition_titleId`=";
		ArrayList<ConditionInfo> ConditionInfoList = new ArrayList<ConditionInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			for(ConditionTitleInfo conditionTitleInfo: ConditionTitleInfoList){
				ResultSet rs = statement.executeQuery(sql+conditionTitleInfo.getConditionTitleId());
				while(rs.next()){
					ConditionInfo ConditionInfo = new ConditionInfo();
						ConditionInfo.setConditionName(rs.getString("t_masterSearchCondition_name"));
						ConditionInfo.setConditionId(rs.getInt("t_masterSearchCondition_Id"));
						ConditionInfo.setConditionTitleId(rs.getInt("t_masterSearchCondition_titleId"));
						ConditionInfoList.add(ConditionInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ConditionInfoList;
	}	

	public List<ConditionTitleInfo> getConditionTitleInfo(DBConnection dbConnection, String conditionType) throws SQLException,UnsupportedEncodingException{
		String typeStr =  "";
		int conditionTypeId = -1;
		ArrayList<ConditionTitleInfo> ConditionTitleInfoList = new ArrayList<ConditionTitleInfo>();		

		/*
		try{
			if(ConditionTitleType.compareTo(Constant.TYPE_FOR_SALON_CONDITION)==0)
				typeStr = new String(Constant.TITLE_NAME_LIST_FOR_SALON.getBytes("UTF-8"),"UTF-8");
			else if(ConditionTitleType.compareTo(Constant.TITLE_FOR_STYLIST_CODITION)==0)
				typeStr = new String(Constant.TITLE_NAME_LIST_FOR_STYLIST_CODITION.getBytes("UTF-8"),"UTF-8");
			else if(ConditionTitleType.compareTo(Constant.TITLE_FOR_STYLIST_LIKE)==0)
				typeStr = new String(Constant.TITLE_NAME_LIST_FOR_STYLIST_LIKE.getBytes("UTF-8"),"UTF-8");
			else if(ConditionTitleType.compareTo(Constant.TITLE_FOR_HAIRSTYLE_MENU)==0)
				typeStr = new String(Constant.TITLE_NAME_LIST_FOR_HAIRSTYLE_MENU.getBytes("UTF-8"),"UTF-8");
			else if(ConditionTitleType.compareTo(Constant.TITLE_FOR_HAIRSTYLE_FACE)==0)
				typeStr = new String(Constant.TITLE_NAME_LIST_FOR_HAIRSTYLE_FACE.getBytes("UTF-8"),"UTF-8");
			else typeStr = "";
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			throw e;
		}
		*/

		String sql_1 = "SELECT * FROM `t_masterSearchConditionType` WHERE `t_masterSearchConditionType_name` = '"+conditionType+"'";
		String sql_2 = "SELECT * FROM `t_masterSearchConditionTitle` WHERE `t_masterSearchConditionTitle_typeId` = ";
		/*
		sql = "SELECT `t_masterSearchConditionTitle_id`,`t_masterSearchConditionTitle_name` FROM "
			 		+ "`t_masterSearchConditionTitle` WHERE `t_masterSearchConditionTitle_name` "
			 		+ "IN(" + typeStr + ")";
			 		*/
		Statement statement = dbConnection.getStatement();
		try {
			//debug 
			System.out.println(sql_1);
			
			ResultSet rs = statement.executeQuery(sql_1);
			while(rs.next()){
				conditionTypeId = rs.getInt("t_masterSearchConditionType_id");
			}
			
			rs = statement.executeQuery(sql_2+conditionTypeId);
			//debug 
			System.out.println(sql_2+conditionTypeId);

			while(rs.next()){
				ConditionTitleInfo conditionTitleInfo = new ConditionTitleInfo();
				conditionTitleInfo.setConditionTitleId(rs.getInt("t_masterSearchConditionTitle_id"));
				conditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
				ConditionTitleInfoList.add(conditionTitleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ConditionTitleInfoList;
	}	

	
}
