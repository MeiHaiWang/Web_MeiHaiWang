package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.model.HairStyleInfo;
import common.util.DBConnection;
import common.util.ListUtilities;

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
			 */
			ListUtilities listUtilities = new ListUtilities();
			List<String> hairStyleStrList = listUtilities.separateData(str_id_list);
			idList = listUtilities.convertList_str_int(hairStyleStrList);


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

	public List<HairStyleInfo> getHairStyleInfoForMaster(
			DBConnection dbConnection, List<Integer> hairStyleIdList, int salonId) {
		/**
		 *     {
			      album:[
			        {
			          t_hairStyle_id,
			          t_hairSalonMaster_salonId,
			          t_hairStyle_hairTypeId,
			          t_hairStyle_name,
			          t_hairStyle_stylistId,
			          t_hairStyle_imagePath,
			        },
			      ]
			    }
		 */
		
		String sql = 
				"SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_hairTypeId`, "
				+ "`t_hairStyle_name`, `t_hairStyle_stylistId` FROM `t_hairStyle` WHERE t_hairStyle_id=";
				
		Statement statement = dbConnection.getStatement();
		List<HairStyleInfo> HairStyleInfoList = new ArrayList<HairStyleInfo>();

		for(int hId : hairStyleIdList){
			try {
				ResultSet rs = statement.executeQuery(sql + hId);
				while(rs.next()){
					HairStyleInfo hairStyleInfo = new HairStyleInfo();
					hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_id"));
					hairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
					hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
					hairStyleInfo.setSalonId(salonId);
					hairStyleInfo.setHairTypeId(rs.getInt("t_hairStyle_hairTypeId"));
					hairStyleInfo.setHairStyleName(rs.getString("t_hairStyle_name"));
					HairStyleInfoList.add(hairStyleInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return HairStyleInfoList;
	}

	
	public int setAlbumInfoForMaster(DBConnection dbConnection, int salonId,
			HairStyleInfo hairStyleInfo) throws SQLException{
		
		int hairStyleId = -1;
		boolean result = false;
		
		/**
		 * hairStyleId からhairStyle情報があるかどうか確認。
		 * idがテーブルに存在したらx
		 * idが存在しなければinsertする
		 * 
		    {
		      t_hairSalonMaster_salonId,
		      t_hairStyle_hairTypeId,
		      t_hairStyle_name,
		      t_hairStyle_stylistId,
		      t_hairStyle_imagePath,
		    }
		 */

		/**
		 * SQL 例:
		 * 
		 * SELECT * FROM `t_hairStyle` WHERE `t_hairStyle_Id` = 1
		 * 
			INSERT INTO `MEIHAIWAN_TEST`.`t_hairStyle` (`t_hairStyle_id`, `t_hairStyle_name`, `t_hairStyle_hairTypeId`, 
			`t_hairStyle_goodNumber`, `t_hairStyle_viewNumber`, `t_hairStyle_stylistId`, `t_hairStyle_areaId`, 
			`t_hairStyle_imagePath`, `t_hairStyle_salonId`, `t_hairStyle_updateDate`, `t_hairStyle_favoriteNumber`) VALUES ('
			10', NULL, NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, '0');		
		*/
		
		String sql_before = "SELECT * FROM `t_hairStyle` WHERE `t_hairStyle_Id` = "; // hairStyleId 
		String sql1 = "INSERT INTO `MEIHAIWAN_TEST`.`t_hairStyle` ("
				+ "`t_hairStyle_id`, `t_hairStyle_name`, `t_hairStyle_hairTypeId`, `t_hairStyle_stylistId`, "
				+ "`t_hairStyle_goodNumber`, `t_hairStyle_viewNumber`, `t_hairStyle_salonId`, `t_hairStyle_areaId`,"
				+ "`t_hairStyle_updateDate`, `t_hairStyle_imagePath`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "NULL";
		String sql4 = "0";
		String sql_end = "');";

		Statement statement = dbConnection.getStatement();
		
		for(int i=1; i<Integer.MAX_VALUE; i++){
			try {
				ResultSet rs = statement.executeQuery(sql_before+Integer.toString(i));
				if(!rs.next()){
					hairStyleId = i;
					break;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		Date oneDate = new Date(0);
		DateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String sql = sql1 +hairStyleId + sql2
				+ hairStyleInfo.getHairStyleName()  + sql2
				+ hairStyleInfo.getHairTypeId() +sql2
				+ hairStyleInfo.getStylistId()  + sql2
				+ sql4 + sql2 
				+ sql4 + sql2 
				+ salonId + sql2
				+ sql4 + sql2 
				+ sdf.format(oneDate) + sql2 				
				+ hairStyleInfo.getHairStyleImagePath()
				+ sql_end;

		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql);
			if(result_int >= 0) result = true;
		} catch (SQLException e) {
						// TODO Auto-generated catch block
			e.printStackTrace();
			hairStyleId = -1;
		}
	
		//* hairStyle をsalon　に足さなきゃ
		String salon_sql1 = "SELECT `t_hairSalonMaster_hairStyleId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
		String salon_sql2_before = "UPDATE `MEIHAIWAN_TEST`.`t_hairSalonMaster` SET `t_hairSalonMaster_hairStyleId` = '";
		String salon_sql2_middle = "' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = ";
		String salon_sql2_after = ";";
		
		ResultSet rs;
		String hairStyleIdList = "";
		try {
			rs = statement.executeQuery(salon_sql1+salonId);
			while(rs.next()){
				hairStyleIdList = rs.getString("t_hairSalonMaster_hairStyleId");
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(hairStyleId != -1){
			String salon_sql =  salon_sql2_before + hairStyleIdList + "," + hairStyleId + salon_sql2_middle + salonId + salon_sql2_after;
			System.out.println(salon_sql);
			try {
				int result_int = statement.executeUpdate(salon_sql);
				if(result_int < 0) hairStyleId = -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				hairStyleId = -1;
			}
		}		
		System.out.println(hairStyleId);
		return hairStyleId;
	}

	public boolean DeleteHairStyleInfoForMaster(DBConnection dbConnection,
			String t_hairStyle_Id) {
		boolean result = false;
		
		/**
		 * stylistId からstylist情報があるかどうか確認。
		 * idがテーブルに存在したらupdate
		 * idが存在しなければx
		 */

		/**
		 * SQL 例:
		 *DELETE FROM `MEIHAIWAN_TEST`.`t_hairStyle` WHERE `t_hairStyle`.`t_hairStyle_hairStyleid` = 2
		 * 
		*/
		
		String sql = "DELETE FROM `MEIHAIWAN_TEST`.`t_hairStyle` WHERE `t_hairStyle`.`t_hairStyle_id` = ";
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql + t_hairStyle_Id);
			if(result_int > 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
	}	


}
