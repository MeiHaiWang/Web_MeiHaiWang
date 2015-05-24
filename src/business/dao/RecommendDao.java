package business.dao;

import java.io.FileNotFoundException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import common.util.DBConnection;

public class RecommendDao {

	//DBコネクション確立
	private DBConnection dbConnection;
	private Connection conn;
	
	
	public RecommendDao() throws Exception{
		dbConnection = new DBConnection();
		try {
			 conn = dbConnection.connectDB();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Integer> getRecommendSalonList(){
		String sql = "SELECT t_hairSalonMaster.t_hairSalonMaster_salonId,t_hairSalonMaster.t_hairSalonMaster_name,"
				+ "t_hairSalonMaster.t_hairSalonMaster_salonImagePath,t_hairSalonMaster.t_hairSalonMaster_message,t_masterArea.t_area_areaName,t_masterRecommend.t_masterRecommend_displayOrder"
				+" FROM t_hairSalonMaster JOIN t_masterArea"
				+" WHERE t_masterRecommend.t_masterRecommend_type =='サロン' ORDER BY  ";
		if(conn !=null){
			Statement statement = dbConnection.getStatement();
		}
	}
}
