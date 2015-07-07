package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.MenuCategoryInfo;
import common.model.MenuInfo;
import common.util.DBConnection;

public class MenuCategoryDao {
	public MenuCategoryDao(){
	}
	
	/**
	 * 
	 * @param dbConnection
	 * @param AreaType: 
	 * @return
	 * @throws SQLException
	 */
	public List<MenuCategoryInfo> getMenuCategoryListInfo(DBConnection dbConnection) throws SQLException{
		String sql= "SELECT * FROM `t_masterMenuCategory`";
		List<MenuCategoryInfo> menuCatInfoList = new ArrayList<MenuCategoryInfo>();
		Statement statement = dbConnection.getStatement();

		try {
			ResultSet rs = statement.executeQuery(sql);
			//debug
			System.out.println(sql);
			while(rs.next()){
				MenuCategoryInfo menuCatInfo = new MenuCategoryInfo();
				menuCatInfo.setMenuCategoryId(rs.getInt("t_menuCategory_categoryId"));
				menuCatInfo.setMenuCategoryName(rs.getString("t_menuCategory_name"));
				menuCatInfoList.add(menuCatInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return menuCatInfoList;
	}	

}
