package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public List<ConditionTitleInfo> getConditionTitleInfo(DBConnection dbConnection, int ConditionTitleType) throws SQLException{
		String sql = "SELECT `t_masterSearchConditionTitle_id`, "
				+ "`t_masterSearchConditionTitle_name` FROM `t_masterSearchConditionTitle`";

		ArrayList<ConditionTitleInfo> ConditionTitleInfoList = new ArrayList<ConditionTitleInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				ConditionTitleInfo ConditionTitleInfo = new ConditionTitleInfo();
				String ConditionTitleName = "";

				switch(ConditionTitleType){
				case 1:
					ConditionTitleName = rs.getString("t_masterSearchConditionTitle_name");
					if(ConditionTitleName.compareTo("時間")==0 
					|| ConditionTitleName.compareTo("サービス")==0
					|| ConditionTitleName.compareTo("設備")==0){
						ConditionTitleInfo.setConditionTitleId(rs.getInt("t_masterSearchConditionTitle_id"));
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
				case 2:
					ConditionTitleName = rs.getString("t_masterSearchConditionTitle_name");
					if(ConditionTitleName.compareTo("対応日時")==0 
					|| ConditionTitleName.compareTo("得意メニュー")==0
					|| ConditionTitleName.compareTo("得意なスタイル")==0){
						ConditionTitleInfo.setConditionTitleId(rs.getInt("t_masterSearchConditionTitle_id"));
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
					
				case 3:
					ConditionTitleName = rs.getString("t_masterSearchConditionTitle_name");
					if(ConditionTitleName.compareTo("性別")==0 
					|| ConditionTitleName.compareTo("年齢")==0){
						ConditionTitleInfo.setConditionTitleId(rs.getInt("t_masterSearchConditionTitle_id"));
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
				case 4:
					ConditionTitleName = rs.getString("t_masterSearchConditionTitle_name");
					if(ConditionTitleName.compareTo("カラー")==0 
					|| ConditionTitleName.compareTo("イメージ")==0){
						ConditionTitleInfo.setConditionTitleId(rs.getInt("t_masterSearchConditionTitle_id"));
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
				case 5:
					ConditionTitleName = rs.getString("t_masterSearchConditionTitle_name");
					if(ConditionTitleName.compareTo("顔型")==0){
						ConditionTitleInfo.setConditionTitleId(rs.getInt("t_masterSearchConditionTitle_id"));
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
				default:						
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ConditionTitleInfoList;
	}	

	
}
