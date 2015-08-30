package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import common.model.BeautyNewsInfo;
import common.util.DBConnection;

public class NewsDao {
	
	public NewsDao() throws Exception{
		
	}

	public List<BeautyNewsInfo> getBeautyNewsInfo(DBConnection dbConnection) throws SQLException{
		String sql = "SELECT `t_masterNewsName`, `t_masterNewImagePath`, `t_masterNewsURL` FROM `t_masterNews` ORDER BY t_masterNewsId DESC";
		List<BeautyNewsInfo> BeautyNewsInfoList = new ArrayList<BeautyNewsInfo>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				BeautyNewsInfo BeautyNewsInfo = new BeautyNewsInfo();
				//BeautyNewsInfo.setBeautyNewsId(rs.getInt("t_masterNewsId"));
				BeautyNewsInfo.setbeautyNewsName(rs.getString("t_masterNewsName"));
				BeautyNewsInfo.setbeautyNewsImagePath(rs.getString("t_masterNewImagePath"));
				BeautyNewsInfo.setbeautyNewsURL(rs.getString("t_masterNewsURL"));
				BeautyNewsInfoList.add(BeautyNewsInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return BeautyNewsInfoList;
	}	
	
}
