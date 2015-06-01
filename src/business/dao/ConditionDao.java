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
	public List<ConditionInfo> getConditionInfo(DBConnection dbConnection, int ConditionType) throws SQLException{
		String sql = "SELECT `t_masterSearchCondition_id`, `t_masterSearchCondition_name`, `t_masterSearchCondition_titleId` "
				+ "FROM `t_masterSearchCondition`";
		ArrayList<ConditionInfo> ConditionInfoList = new ArrayList<ConditionInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				ConditionInfo ConditionInfo = new ConditionInfo();
				int ConditionId = -1, ConditionTitleId = -1;

				switch(ConditionType){
				case 1:
					ConditionId = rs.getInt("t_masterSearchCondition_id");
					if(ConditionId>=111 && ConditionId <=199){
						ConditionInfo.setConditionId(ConditionId % 10);
						ConditionInfo.setConditionName(rs.getString("t_masterSearchCondition_name"));
						ConditionTitleId = rs.getInt("t_masterSearchCondition_titleId");
						ConditionInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionInfoList.add(ConditionInfo);
					}
					break;
				case 2:
					ConditionId = rs.getInt("t_masterSearchCondition_id");
					if(ConditionId>=211 && ConditionId <=299){
						ConditionInfo.setConditionId(ConditionId % 10);
						ConditionInfo.setConditionName(rs.getString("t_masterSearchCondition_name"));
						ConditionTitleId = rs.getInt("t_masterSearchCondition_titleId");
						ConditionInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionInfoList.add(ConditionInfo);
					}
					break;
					
				case 3:
					ConditionId = rs.getInt("t_masterSearchCondition_id");
					if(ConditionId>=311 && ConditionId <=399){
						ConditionInfo.setConditionId(ConditionId % 10);
						ConditionInfo.setConditionName(rs.getString("t_masterSearchCondition_name"));
						ConditionTitleId = rs.getInt("t_masterSearchCondition_titleId");
						ConditionInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionInfoList.add(ConditionInfo);
					}
					break;
					
				case 4:
					ConditionId = rs.getInt("t_masterSearchCondition_id");
					if(ConditionId>=411 && ConditionId <=499){
						ConditionInfo.setConditionId(ConditionId % 10);
						ConditionInfo.setConditionName(rs.getString("t_masterSearchCondition_name"));
						ConditionTitleId = rs.getInt("t_masterSearchCondition_titleId");
						ConditionInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionInfoList.add(ConditionInfo);
					}
					break;
					
				case 5:
					ConditionId = rs.getInt("t_masterSearchCondition_id");
					if(ConditionId>=511 && ConditionId <=599){
						ConditionInfo.setConditionId(ConditionId % 10);
						ConditionInfo.setConditionName(rs.getString("t_masterSearchCondition_name"));
						ConditionTitleId = rs.getInt("t_masterSearchCondition_titleId");
						ConditionInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionInfoList.add(ConditionInfo);
					}				
					break;

				default:						
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
				int ConditionTitleId = -1;

				switch(ConditionTitleType){
				case 1:
					ConditionTitleId = rs.getInt("t_masterSearchConditionTitle_id");
					if(ConditionTitleId>=11 && ConditionTitleId <=19){
						ConditionTitleInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
				case 2:
					ConditionTitleId = rs.getInt("t_masterSearchConditionTitle_id");
					if(ConditionTitleId>=21 && ConditionTitleId <=29){
						ConditionTitleInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
					
				case 3:
					ConditionTitleId = rs.getInt("t_masterSearchConditionTitle_id");
					if(ConditionTitleId>=31 && ConditionTitleId <=39){
						ConditionTitleInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}					
					break;
				case 4:
					ConditionTitleId = rs.getInt("t_masterSearchConditionTitle_id");
					if(ConditionTitleId>=41 && ConditionTitleId <=49){
						ConditionTitleInfo.setConditionTitleId(ConditionTitleId % 10);
						ConditionTitleInfo.setConditionTitleName(rs.getString("t_masterSearchConditionTitle_name"));
						ConditionTitleInfoList.add(ConditionTitleInfo);
					}
					break;
					
				case 5:
					ConditionTitleId = rs.getInt("t_masterSearchConditionTitle_id");
					if(ConditionTitleId>=51 && ConditionTitleId <=59){
						ConditionTitleInfo.setConditionTitleId(ConditionTitleId % 10);
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
