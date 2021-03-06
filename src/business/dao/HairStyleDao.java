package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import business._dao.THairStyleDao;
import common.model.HairStyleInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;
import common.util.ListUtilities;

public class HairStyleDao extends THairStyleDao{
	
	/*
	public List<Integer> getHairStyleHistoryIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_latestViewHairStyleId` FROM `t_user` WHERE t_user_Id = " + user_id;
		List<Integer> idList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_latestViewHairStyleId");
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
		
		// 履歴にまだ何も登録されていない＝null 
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
	
	public List<Integer> getHairStyleFavoriteIdList(DBConnection dbConnection, int user_id) throws SQLException{
		String sql = "SELECT `t_user_favoriteHairStyleId` FROM `t_user` WHERE t_user_Id =" + user_id;
		List<Integer> HairStyleIdList = new ArrayList<Integer>();
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String str_id_list = rs.getString("t_user_favoriteHairStyleId");
			
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
		
		// お気に入りにまだ何も登録されていない＝null 
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
	*/
	
	//アプリ側：ヘアスタイル情報の詳細を取得
	/**
	{
		   id: 1,
		   image1: "http://exsample.com/minibobex.png",
		   image2: "http://exsample.com/minibobex.png",
		   image3: "http://exsample.com/minibobex.png",
		   isgood: 1,
		   stylistName: "aaaa",
		   message: "aaaaaa",
		   area: "北京",
		   good_count: 144,
		   stylistID: 1
		}
	*/
	/*
	public HairStyleInfo getHairStyleDetailInfo(DBConnection dbConnection, int cataloglistID) throws SQLException{
		String sql = 
				"SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_stylistId`, "
				+ "`t_hairStyle_message`, `t_hairStyle_areaId`,  `t_hairStyle_goodNumber`, "
				+ "`t_hairStyle_favoriteNumber` FROM `t_hairStyle` WHERE t_hairStyle_id="+cataloglistID;
				
		Statement statement = dbConnection.getStatement();
		HairStyleInfo hairStyleInfo = new HairStyleInfo();

		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_id"));
				hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
				hairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
				hairStyleInfo.setHairStyleMessage(rs.getString("t_hairStyle_message"));
				hairStyleInfo.setHairStyleAreaId(rs.getString("t_hairStyle_areaId"));
				hairStyleInfo.setHairStyleGoodNumber(rs.getInt("t_hairStyle_goodNumber"));
				hairStyleInfo.setFavoriteNumber(rs.getInt("t_hairStyle_favoriteNumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return hairStyleInfo;
	}
	
	//空っぽのデータをつっこむ
	public HairStyleInfo retNull(){
		HairStyleInfo hairStyleInfo = new HairStyleInfo();
		hairStyleInfo.setHairStyleId(Integer.MIN_VALUE);
		hairStyleInfo.setHairStyleImagePath("");
		hairStyleInfo.setStylistId(Integer.MIN_VALUE);
		hairStyleInfo.setFavoriteNumber(Integer.MIN_VALUE);

		return hairStyleInfo;

	}
	*/

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
		          t_hairStyle_areaId,
		          t_hairStyle_areaName,
		          t_hairStyle_message
		        },
		      ]
		    }
	 */
	/*
	public List<HairStyleInfo> getHairStyleInfoForMaster(
			DBConnection dbConnection, List<Integer> hairStyleIdList, int salonId) {
		
		String sql = 
				"SELECT `t_hairStyle_id`, `t_hairStyle_imagePath`, `t_hairStyle_hairTypeId`, `t_hairStyle_searchConditionId`, "
				+ "`t_hairStyle_name`, `t_hairStyle_stylistId`, `t_hairStyle_areaId`, `t_hairStyle_message`"
				+ "FROM `t_hairStyle` WHERE t_hairStyle_id=";
				
		Statement statement = dbConnection.getStatement();
		List<HairStyleInfo> HairStyleInfoList = new ArrayList<HairStyleInfo>();

		for(int hId : hairStyleIdList){
			try {
				ResultSet rs = statement.executeQuery(sql + hId);
				//debug
				System.out.println(sql + hId);
				while(rs.next()){
					HairStyleInfo hairStyleInfo = new HairStyleInfo();
					hairStyleInfo.setHairStyleId(rs.getInt("t_hairStyle_id"));
					hairStyleInfo.setStylistId(rs.getInt("t_hairStyle_stylistId"));
					hairStyleInfo.setHairStyleImagePath(rs.getString("t_hairStyle_imagePath"));
					hairStyleInfo.setSalonId(salonId);
					hairStyleInfo.setHairTypeId(rs.getInt("t_hairStyle_hairTypeId"));
					hairStyleInfo.setHairStyleName(rs.getString("t_hairStyle_name"));
					hairStyleInfo.setHairStyleSearchConditionId(rs.getString("t_hairStyle_searchConditionId"));
					hairStyleInfo.setHairStyleAreaId(rs.getString("t_hairStyle_areaId"));
					hairStyleInfo.setHairStyleMessage(rs.getString("t_hairStyle_message"));
					HairStyleInfoList.add(hairStyleInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return HairStyleInfoList;
	}


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
		      t_hairStyle_areaId,
		      t_hairStyle_message,
		      t_hairStyle_updateDate
		    }
		 */

		/**
		 * SQL 例:
		 * 
		 * SELECT * FROM `t_hairStyle` WHERE `t_hairStyle_Id` = 1
		 * 
			INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_hairStyle` (`t_hairStyle_id`, `t_hairStyle_name`, `t_hairStyle_hairTypeId`, 
			`t_hairStyle_goodNumber`, `t_hairStyle_viewNumber`, `t_hairStyle_stylistId`, `t_hairStyle_areaId`, 
			`t_hairStyle_imagePath`, `t_hairStyle_salonId`, `t_hairStyle_updateDate`, `t_hairStyle_favoriteNumber`) VALUES ('
			10', NULL, NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, '0');	
		*/
	/*
	public int setAlbumInfoForMaster(DBConnection dbConnection, int salonId,
			HairStyleInfo hairStyleInfo) throws SQLException{
		
		int hairStyleId = hairStyleInfo.getHairStyleId();
		boolean result = false;
		
		//String sql_before = "SELECT * FROM `t_hairStyle` WHERE `t_hairStyle_id` = "; // hairStyleId 
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_hairStyle` ("
				+ "`t_hairStyle_name`, `t_hairStyle_hairTypeId`, `t_hairStyle_stylistId`, "
				+ "`t_hairStyle_goodNumber`, `t_hairStyle_viewNumber`, `t_hairStyle_salonId`, `t_hairStyle_areaId`,"
				+ "`t_hairStyle_updateDate`, `t_hairStyle_imagePath`, `t_hairStyle_searchConditionId`, "
				+ "`t_hairStyle_message`) VALUES ('";
		String sql2 = "', '";
		//String sql3 = "NULL";
		String sql4 = "0";
		String sql_end = "');";
		//String sql_after = "SELECT `t_hairStyle_id` FROM `t_hairStyle` WHERE `t_hairStyle_updateDate`='"+hairStyleInfo.getUpdateTime()+"'";
		
		/*
		//TODO: Update time?
		//Date oneDate = new Date(0);
		Date oneDate = new Date(hairStyleInfo.getUpdateTime());
		DateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String updateTime = sdf.format(oneDate);
		System.out.println("updateTime:"+updateTime);
		*/

		/*
		//update
		String sql_update = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairStyle` SET "
				+ "`t_hairStyle_id` = '" +  hairStyleInfo.getHairStyleId()  + "', "
				+ "`t_hairStyle_name` = '" +  hairStyleInfo.getHairStyleName()  + "',"
				+ "`t_hairStyle_hairTypeId` = '" +  hairStyleInfo.getHairTypeId()  + "',"
				+ "`t_hairStyle_stylistId` = '" + hairStyleInfo.getStylistId()  + "',"
				+ "`t_hairStyle_salonId` = '" +  salonId  + "',"
				+ "`t_hairStyle_updateDate` = '" +  hairStyleInfo.getUpdateTime()  + "',"
				+ "`t_hairStyle_imagePath` = '" +  hairStyleInfo.getHairStyleImagePathStr()  + "',"
				+ "`t_hairStyle_searchConditionId` = '" + hairStyleInfo.getHairStyleSearchConditionId() + "',"
				+ "`t_hairStyle_areaId` = '" +  hairStyleInfo.getHairStyleAreaId()  + "',"
				+ "`t_hairStyle_message` = '" + hairStyleInfo.getHairStyleMessage() + "'"
				+ " WHERE `t_hairStyle`.`t_hairStyle_id` = " + hairStyleId;

		
		Statement statement = dbConnection.getStatement();
		
		if(hairStyleInfo.getHairStyleId()<0){
			//combine insert sentence
			String sql = sql1 
					+ hairStyleInfo.getHairStyleName()  + sql2
					+ hairStyleInfo.getHairTypeId() +sql2
					+ hairStyleInfo.getStylistId()  + sql2
					+ sql4 + sql2 
					+ sql4 + sql2 
					+ salonId + sql2
					+ hairStyleInfo.getHairStyleAreaId() + sql2
					+ hairStyleInfo.getUpdateTime() + sql2 				
					+ hairStyleInfo.getHairStyleImagePathStr() + sql2
					+ hairStyleInfo.getHairStyleSearchConditionId() + sql2
					+ hairStyleInfo.getHairStyleMessage()
					+ sql_end;
			//debug
			System.out.println(sql);
			
			try {
				int result_int = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				if(result_int >= 0){
					//更新したidをget
					ResultSet rs = statement.getGeneratedKeys();
			        if (rs.next()){
			        	hairStyleId = rs.getInt(1);
		        	}
			        rs.close();
			        result = true;
				}
			} catch (SQLException e) {
							// TODO Auto-generated catch block
				e.printStackTrace();
				hairStyleId = -1;
			}
		
			// hairStyle をsalon　に足さなきゃ
			String salon_sql1 = "SELECT `t_hairSalonMaster_hairStyleId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
			String salon_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_hairStyleId` = '";
			String salon_sql2_middle = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = ";
			String salon_sql2_after = ";";
			
			ResultSet rs;
			String hairStyleIdList = "";
			try {
				rs = statement.executeQuery(salon_sql1+salonId);
				while(rs.next()){
					hairStyleIdList = rs.getString("t_hairSalonMaster_hairStyleId");
					//debug
					//System.out.println("hairStyleIdList:"+hairStyleIdList);
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String salon_sql = null;
			if(hairStyleId > 0 ){
				if(hairStyleIdList!="" && hairStyleIdList!=null){
					salon_sql =  salon_sql2_before + hairStyleIdList + "," + hairStyleId + salon_sql2_middle + salonId + salon_sql2_after;				
				}else{
					salon_sql =  salon_sql2_before + hairStyleId + salon_sql2_middle + salonId + salon_sql2_after;								
				}
				System.out.println(salon_sql);
				try {
					int result_int = statement.executeUpdate(salon_sql);
					if(result_int < 0) hairStyleId = -1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					hairStyleId = -1;
				}
			}else{
				//debug
				System.out.println("hairStyle-regist error.");
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
				hairStyleId = -1;
			}
		}

		return hairStyleId;
	}
	*/

		/**
		 * SQL 例:
		 *DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_hairStyle` WHERE `t_hairStyle`.`t_hairStyle_hairStyleid` = 2
		 * 
		*/
		/*
	public boolean DeleteHairStyleInfoForMaster(DBConnection dbConnection,
			String t_hairStyle_Id, int salonId) {
		boolean result = false;
		
		String sql = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_hairStyle` WHERE `t_hairStyle`.`t_hairStyle_id` = ";
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

		
		//* hairStyleId をsalon　から消さなきゃ
		//SQL
		String salon_sql1 = "SELECT `t_hairSalonMaster_hairStyleId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
		String salon_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_hairStyleId` = '";
		String salon_sql2_middle = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = ";
		String salon_sql2_after = ";";
		
		ResultSet rs;
		String idListStr = "";
		try {
			rs = statement.executeQuery(salon_sql1+salonId);
			while(rs.next()){
				idListStr = rs.getString("t_hairSalonMaster_hairStyleId");
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> idList = new LinkedList<String>(Arrays.asList(idListStr.split(",")));
		Iterator<String> i = idList.iterator();
        while(i.hasNext()){
            String name = i.next();
            if(name.equals(t_hairStyle_Id)){
                i.remove();
            }
        }
        String newIdList = "";
		for (String id : idList){
			newIdList += id + ",";
		}
		if(newIdList.lastIndexOf(',')!=-1){
			newIdList = newIdList.substring(0, newIdList.lastIndexOf(','));
		}
		//debug
		//System.out.println("NewIdList?:" + newIdList);
		
		String salon_sql;
		salon_sql =  salon_sql2_before + newIdList + salon_sql2_middle + salonId + salon_sql2_after;								
		
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
		
		
		return result;
	}	
		 */

}
