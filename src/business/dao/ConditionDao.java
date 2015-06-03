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

	public List<ConditionTitleInfo> getConditionTitleInfo(DBConnection dbConnection, int ConditionTitleType) throws SQLException,UnsupportedEncodingException{
		String sql = "";
		ArrayList<ConditionTitleInfo> ConditionTitleInfoList = new ArrayList<ConditionTitleInfo>();		
		try{
			 sql = "SELECT `t_masterSearchConditionTitle_id`,`t_masterSearchConditionTitle_name` FROM `t_masterSearchConditionTitle` WHERE `t_masterSearchConditionTitle_name` IN(" + new String(Constant.TITLE_NAME_LIST_FOR_SALON.getBytes("UTF-8"),"UTF-8") + ")";
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			throw e;
		}
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			System.out.println(sql);
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
