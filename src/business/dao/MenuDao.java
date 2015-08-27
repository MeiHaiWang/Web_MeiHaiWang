package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import common.model.MenuInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class MenuDao {
	public MenuDao(){
	}
	
	/**
	 * 
	 * @param dbConnection
	 * @param AreaType: 
	 * @return
	 * @throws SQLException
	 */
	public List<MenuInfo> getMenuListInfo(DBConnection dbConnection, List<Integer> menuIdList) throws SQLException{
		String sql1= "SELECT `t_menu_menuId`, `t_menu_name` FROM `t_menu` WHERE `t_menu_menuId` = ";
		List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();
		Statement statement = dbConnection.getStatement();

		for(int menuId : menuIdList){
			try {
				ResultSet rs = statement.executeQuery(sql1 + menuId);
				//debug
				System.out.println(sql1 + menuId);
				while(rs.next()){
					MenuInfo menuInfo = new MenuInfo();
					menuInfo.setMenuId(rs.getInt("t_menu_menuId"));
					menuInfo.setMenuName(rs.getString("t_menu_name"));
					menuInfoList.add(menuInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return menuInfoList;
	}

	/**
	 * 
	 * @param dbConnection
	 * @param menuIdList
	 * @return
	 * @throws SQLException
	 * 
	 *     {
		      menu:[
		        {
		          t_menu_categoryId,
		          t_menu_menuId,
		          t_menu_name,
		          t_menu_price,
		          t_menu_detailText,
		          t_menu_imagePath,
		        },
		      ]
		    }
	 */
	public List<MenuInfo> getMenuInfoForMaster(DBConnection dbConnection,
			List<Integer> menuIdList) throws SQLException{

		String sql1= "SELECT `t_menu_categoryId`,`t_menu_menuId`, `t_menu_name`, `t_menu_price`,"
				+ "`t_menu_detailText`,`t_menu_imagePath` FROM `t_menu` WHERE `t_menu_menuId` = ";
		List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();
		Statement statement = dbConnection.getStatement();

		for(int menuId : menuIdList){
			try {
				ResultSet rs = statement.executeQuery(sql1 + menuId);
				while(rs.next()){
					MenuInfo menuInfo = new MenuInfo();
					menuInfo.setMenuCategoryId(rs.getInt("t_menu_categoryId"));
					menuInfo.setMenuId(rs.getInt("t_menu_menuId"));
					menuInfo.setMenuName(rs.getString("t_menu_name"));
					menuInfo.setMenuPrice(rs.getInt("t_menu_price"));
					menuInfo.setMenuDetailText(rs.getString("t_menu_detailText"));
					menuInfo.setMenuImagePath(rs.getString("t_menu_imagePath"));
					menuInfoList.add(menuInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return menuInfoList;
	}

	
	public int setMenuInfoForMaster(DBConnection dbConnection, int salonId,
			MenuInfo menuInfo) throws SQLException{
		
		int menuId = menuInfo.getMenuId();
		boolean result = false;
		
		//Unexpected value
		if(menuInfo.getMenuPrice()<0) return -1;
		
		/**
		 * menuId からmenu情報があるかどうか確認。
		 * idがテーブルに存在したらx
		 * idが存在しなければinsertする
		 * 
		 *     {
			      t_hairSalonMaster_salonId,
			      t_menu_categoryId,
			      t_menu_name,
			      t_menu_price,
			      t_menu_detailText,
			      t_menu_imagePath,
			    }
		 */

		/**
		 * SQL 例:
		 * 
		 * SELECT * FROM `t_menu` WHERE `t_menu_Id` = 1
		 * 
			INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_menu` (`t_menu_menuId`, `t_menu_name`, `t_menu_price`, `t_menu_categoryId`, 
			`t_menu_detailText`, `t_menu_imagePath`) VALUES ('2', 'name', '1000', '1', 'detail', 'imagePath');		 * 
		*/
		
		String sql_before = "SELECT * FROM `t_menu` WHERE `t_menu_menuId` = "; // menuId 
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_menu` ("
				+ "`t_menu_menuId`, `t_menu_name`, `t_menu_price`, `t_menu_categoryId`, `t_menu_detailText`, "
				+ "`t_menu_imagePath`) VALUES ('";
		String sql2 = "', '";
		//String sql3 = "NULL";
		//String sql4 = "0";
		String sql_end = "');";
		
		//update
		String sql_update = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_menu` SET "
				+ "`t_menu_menuId` = '" +  menuInfo.getMenuId()  + "', "
				+ "`t_menu_name` = '" +  menuInfo.getMenuName()  + "',"
				+ "`t_menu_price` = '" +  menuInfo.getMenuPrice()  + "',"
				+ "`t_menu_categoryId` = '" + menuInfo.getMenuCategoryId()  + "',"
				+ "`t_menu_detailText` = '" +  menuInfo.getMenuDetailText() + "',"
				+ "`t_menu_imagePath` = '" +  menuInfo.getMenuImagePath()  + "'"
				+ " WHERE `t_menu`.`t_menu_menuId` = " + menuId;

		Statement statement = dbConnection.getStatement();
		
		if(menuId<0){
			for(int i=1; i<Integer.MAX_VALUE; i++){
				try {
					ResultSet rs = statement.executeQuery(sql_before+Integer.toString(i));
					if(!rs.next()){
						menuId = i;
						break;
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			String sql = sql1 +menuId + sql2
					+ menuInfo.getMenuName() + sql2
					+ menuInfo.getMenuPrice()  + sql2
					+ menuInfo.getMenuCategoryId()  + sql2
					+ menuInfo.getMenuDetailText() +sql2
					+ menuInfo.getMenuImagePath()
					+ sql_end;
	
			//debug
			System.out.println(sql);
			
			try {
				int result_int = statement.executeUpdate(sql);
				if(result_int >= 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//* menu をsalon　に足さなきゃ
			String salon_sql1 = "SELECT `t_hairSalonMaster_menuId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
			String salon_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_menuId` = '";
			String salon_sql2_middle = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = ";
			String salon_sql2_after = ";";
			
			ResultSet rs;
			String menuIdList = "";
			try {
				rs = statement.executeQuery(salon_sql1+salonId);
				while(rs.next()){
					menuIdList = rs.getString("t_hairSalonMaster_menuId");
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			if(menuId != -1){
				String salon_sql = null;
				if(menuIdList!="" && menuIdList!=null){
					salon_sql =  salon_sql2_before + menuIdList + "," + menuId + salon_sql2_middle + salonId + salon_sql2_after;				
				}else{
					salon_sql =  salon_sql2_before + menuId + salon_sql2_middle + salonId + salon_sql2_after;								
				}
				System.out.println(salon_sql);
				try {
					int result_int = statement.executeUpdate(salon_sql);
					if(result_int < 0) menuId = -1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					menuId = -1;
				}
			}		
		//update
		}else{
			//debug
			System.out.println("sql_update :" + sql_update);			
			try {
				int result_int = statement.executeUpdate(sql_update);
				if(result_int >= 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				menuId = -1;
			}
		}

		return menuId;
	}

	public boolean DeleteMenuInfoForMaster(DBConnection dbConnection,
			String t_menu_menuId,int salonId) {
		boolean result = false;
		
		/**
		 * stylistId からstylist情報があるかどうか確認。
		 * idがテーブルに存在したらupdate
		 * idが存在しなければx
		 */

		/**
		 * SQL 例:
		 *DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_menu` WHERE `t_menu`.`t_menu_menuId` = 2
		 * 
		*/
		
		String sql = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_menu` WHERE `t_menu`.`t_menu_menuId` = ";
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql + t_menu_menuId);
			if(result_int > 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//* menuId をsalon　から消さなきゃ
		//SQL
		String salon_sql1 = "SELECT `t_hairSalonMaster_menuId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
		String salon_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_hairSalonMaster` SET `t_hairSalonMaster_menuId` = '";
		String salon_sql2_middle = "' WHERE `t_hairSalonMaster`.`t_hairSalonMaster_salonId` = ";
		String salon_sql2_after = ";";
		
		ResultSet rs;
		String idListStr = "";
		try {
			rs = statement.executeQuery(salon_sql1+salonId);
			while(rs.next()){
				idListStr = rs.getString("t_hairSalonMaster_menuId");
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
            if(name.equals(t_menu_menuId)){
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
		
		//stylist からもmenuIdを削除
		String salon_stylist_sql = "SELECT `t_hairSalonMaster_stylistId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = " + salonId;
		String stylist_sql1 = "SELECT `t_stylist_menuId` FROM `t_stylist` WHERE `t_stylist_Id` = ";
		String stylist_sql2_before = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_stylist` SET `t_stylist_menuId` = '";
		String stylist_sql2_middle = "' WHERE `t_stylist`.`t_stylist_Id` = ";
		String stylist_sql2_after = ";";
		
		//ResultSet rs;
		String s_idListStr = "";
		String stylistIdListStr = "";
		try {
			rs = statement.executeQuery(salon_stylist_sql);
			while(rs.next()){
				stylistIdListStr = rs.getString("t_hairSalonMaster_stylistId");
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> stylistIdList = new LinkedList<String>(Arrays.asList(stylistIdListStr.split(",")));
		
		for(String stId : stylistIdList){
			try {
				rs = statement.executeQuery(stylist_sql1+stId);
				while(rs.next()){
					s_idListStr = rs.getString("t_stylist_menuId");
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<String> s_idList = new LinkedList<String>(Arrays.asList(s_idListStr.split(",")));
			Iterator<String> s_i = s_idList.iterator();
	        while(s_i.hasNext()){
	            String name = s_i.next();
	            if(name.equals(t_menu_menuId)){
	                s_i.remove();
	            }
	        }
	        String s_newIdList = "";
			for (String id : s_idList){
				s_newIdList += id + ",";
			}
			s_newIdList = s_newIdList.substring(0, s_newIdList.lastIndexOf(','));
			//debug
			//System.out.println("stylist_NewIdList?:" + s_newIdList);
			
			String stylist_sql;
			stylist_sql =  stylist_sql2_before + s_newIdList + stylist_sql2_middle + stId + stylist_sql2_after;								
			
			//debug
			System.out.println(stylist_sql);
			try {
				int result_int = statement.executeUpdate(stylist_sql);
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

}
