package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}
