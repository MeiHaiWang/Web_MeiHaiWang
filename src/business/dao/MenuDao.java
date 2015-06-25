package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import common.model.MenuInfo;
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
		
		int menuId = -1;
		boolean result = false;
		
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
			INSERT INTO `MEIHAIWAN_TEST`.`t_menu` (`t_menu_menuId`, `t_menu_name`, `t_menu_price`, `t_menu_categoryId`, 
			`t_menu_detailText`, `t_menu_imagePath`) VALUES ('2', 'name', '1000', '1', 'detail', 'imagePath');		 * 
		*/
		
		String sql_before = "SELECT * FROM `t_menu` WHERE `t_menu_menuId` = "; // menuId 
		String sql1 = "INSERT INTO `MEIHAIWAN_TEST`.`t_menu` ("
				+ "`t_menu_menuId`, `t_menu_name`, `t_menu_price`, `t_menu_categoryId`, `t_menu_detailText`, "
				+ "`t_menu_imagePath`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "NULL";
		String sql4 = "0";
		String sql_end = "');";

		Statement statement = dbConnection.getStatement();
		
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
		String salon_sql2_before = "UPDATE `MEIHAIWAN_TEST`.`t_hairSalonMaster` SET `t_hairSalonMaster_menuId` = '";
		String salon_sql2_middle = "' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = ";
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
			String salon_sql =  salon_sql2_before + menuIdList + "," + menuId + salon_sql2_middle + salonId + salon_sql2_after;
			System.out.println(salon_sql);
			try {
				int result_int = statement.executeUpdate(salon_sql);
				if(result_int > 0) menuId = -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				menuId = -1;
			}
		}		
		return menuId;
	}

	public boolean DeleteMenuInfoForMaster(DBConnection dbConnection,
			String t_menu_menuId) {
		boolean result = false;
		
		/**
		 * stylistId からstylist情報があるかどうか確認。
		 * idがテーブルに存在したらupdate
		 * idが存在しなければx
		 */

		/**
		 * SQL 例:
		 *DELETE FROM `MEIHAIWAN_TEST`.`t_menu` WHERE `t_menu`.`t_menu_menuId` = 2
		 * 
		*/
		
		String sql = "DELETE FROM `MEIHAIWAN_TEST`.`t_menu` WHERE `t_menu`.`t_menu_menuId` = ";
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
	
		return result;
	}	

}
