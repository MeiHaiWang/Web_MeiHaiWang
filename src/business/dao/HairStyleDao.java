package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.HairStyleInfo;
import common.model.HairStyleInfo;
import common.util.DBConnection;

public class HairStyleDao {
	public HairStyleDao() throws Exception{
		
	}
	
	public List<Integer> getHairStyleHistoryIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_latestViewHairStyleId` FROM `t_user` WHERE t_user_Id = " + user_id;
		List<Integer> idList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_latestViewHairStyleId");
			/*
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				idList.add(Integer.parseInt(temp));
			}
			*/
	    	String str2 = str_id_list;
	    	int i = 0;
	    	while(i<=str_id_list.length()){
	    		int idx = str_id_list.indexOf(',', i);
	    		if(idx>0){
			    	str2 = str_id_list.substring(i, idx);		    			
	    		}
		    	//jsonOneData.put("image"+Integer.toString(num++), str2);
	    		idList.add(Integer.parseInt(str2));
		    	i+=(str2.length()+1);
	    	}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return idList;
	}

	public List<HairStyleInfo> getHairStyleHistoryInfo(DBConnection dbConnection, List<Integer> idList) throws SQLException{
		String sql = 
				"SELECT `t_hairStyle_Id`, `t_hairStyle_imagePath`, `t_hairStyle_stylistId`, `t_hairStyle_favoriteNumber` FROM `t_hairStyle` WHERE `t_hairStyle_Id` =";
		List<HairStyleInfo> infoList = new ArrayList<HairStyleInfo>();
		
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
					HairStyleInfo hairStyleInfo = new HairStyleInfo();
					hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_Id"));
					hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
					hairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
					hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
					infoList.add(hairStyleInfo);
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
	public List<Integer> getHairStyleFavoriteIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_favoriteHairStyleId` FROM `t_user` WHERE t_user_Id =" + user_id;
		List<Integer> HairStyleIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_favoriteHairStyleId");
			
			/*
			for(int i=0; i<=str_id_list.length(); i+=2){
				String temp = str_id_list.substring(i, i+1);
				HairStyleIdList.add(Integer.parseInt(temp));
			}
			*/
			
	    	String str2 = str_id_list;
	    	int i = 0;
	    	while(i<=str_id_list.length()){
	    		int idx = str_id_list.indexOf(',', i);
	    		if(idx>0){
			    	str2 = str_id_list.substring(i, idx);		    			
	    		}
		    	//jsonOneData.put("image"+Integer.toString(num++), str2);
	    		HairStyleIdList.add(Integer.parseInt(str2));
		    	i+=(str2.length()+1);
	    	}


		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return HairStyleIdList;
	}

	public List<HairStyleInfo> getHairStyleFavoriteInfo(DBConnection dbConnection, List<Integer> HairStyle_id_list) throws SQLException{
		String sql = 
				"SELECT `t_hairStyle_id`, `t_hairStyle_stylistId`, `t_hairStyle_imagePath`,"
				+ " `t_hairStyle_favoriteNumber` FROM `t_hairStyle` WHERE t_hairStyle_id=";
		List<HairStyleInfo> HairStyleInfoList = new ArrayList<HairStyleInfo>();
		
		/* お気に入りにまだ何も登録されていない＝null */
	 	if(HairStyle_id_list.isEmpty()) {
	 		HairStyleInfoList.add(retNull());
	 		return HairStyleInfoList;
	 	}
		
		Statement statement = dbConnection.getStatement();
		for(int index: HairStyle_id_list){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(index));
				while(rs.next()){
					HairStyleInfo HairStyleInfo = new HairStyleInfo();
					HairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_id"));
					HairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
					HairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
					HairStyleInfo.setIsGood(rs.getInt("t_hairStyle_favoriteNumber"));
					HairStyleInfoList.add(HairStyleInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return HairStyleInfoList;
	}
	
	//Detail
	public List<HairStyleInfo> getHairStyleDetailInfo(DBConnection dbConnection, int cataloglistID) throws SQLException{
		String sql = 
				"SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, "
				+ "`t_hairStyle_favoriteNumber`, `t_hairStyle_stylistId` FROM `t_hairStyle` WHERE t_hairStyle_id="+cataloglistID;
				
		Statement statement = dbConnection.getStatement();
		List<HairStyleInfo> HairStyleInfoList = new ArrayList<HairStyleInfo>();

		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				HairStyleInfo hairStyleInfo = new HairStyleInfo();
				hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_id"));
				hairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
				hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
				hairStyleInfo.setIsGood(rs.getInt("t_hairStyle_favoriteNumber"));
				HairStyleInfoList.add(hairStyleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return HairStyleInfoList;
	}
	
	/*空っぽのデータをつっこむ*/
	public HairStyleInfo retNull(){
		HairStyleInfo hairStyleInfo = new HairStyleInfo();
		hairStyleInfo.setHairStyleId(Integer.MIN_VALUE);
		hairStyleInfo.setHairStyleImagePath("");
		hairStyleInfo.setStylistId(Integer.MIN_VALUE);
		hairStyleInfo.setFavoriteNumber(Integer.MIN_VALUE);

		return hairStyleInfo;

	}

}
