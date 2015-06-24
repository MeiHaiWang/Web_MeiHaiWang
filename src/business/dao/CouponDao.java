package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.model.AreaInfo;
import common.model.CouponInfo;
import common.util.DBConnection;

public class CouponDao {
	public CouponDao(){
	}
	
	/**
	 * 
	 * @param dbConnection
	 * @param AreaType: 
	 * @return
	 * @throws SQLException
	 */
	public List<CouponInfo> getCouponInfo(DBConnection dbConnection, String couponIdList,int salonId) throws SQLException{
		String sql= "SELECT `t_coupon_Id`, `t_coupon_name`, (SELECT `t_couponKind_name` FROM `t_masterCouponKind` WHERE `t_couponKind_id` = `t_coupon_couponKindId`) AS CKINDNAME, `t_coupon_couponKindId`, `t_coupon_imagePath`, `t_coupon_detailText`, `t_coupon_useCondition`, `t_coupon_price`, `t_coupon_deadLine`, `t_coupon_isFirstFlag`, `t_coupon_presentationCondition` FROM `t_coupon` WHERE `t_coupon_Id` IN(" + couponIdList + ")";
		System.out.println(sql);
		List<CouponInfo> couponInfoList = new ArrayList<CouponInfo>();
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				CouponInfo couponInfo = new CouponInfo();
				couponInfo.setCouponId(rs.getInt("t_coupon_Id"));
				couponInfo.setSalonId(salonId);
				couponInfo.setCouponName(rs.getString("t_coupon_name"));
				couponInfo.setCouponDetailText(rs.getString("t_coupon_detailText"));
				couponInfo.setCouponCategoryName(rs.getString("CKINDNAME"));
				couponInfo.setPrice(rs.getInt("t_coupon_price"));
				couponInfo.setPresentationCondition(rs.getString("t_coupon_presentationCondition"));
				couponInfo.setDeadLine(rs.getString("t_coupon_deadLine"));
				couponInfo.setUseCondition(rs.getString("t_coupon_useCondition"));
				couponInfo.setIsFirstFlag(rs.getInt("t_coupon_isFirstFlag"));
				couponInfo.setCouponKindId(rs.getInt("t_coupon_couponKindId"));
				couponInfo.setImagePath(rs.getString("t_coupon_imagePath"));
				couponInfoList.add(couponInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return couponInfoList;
	}	

	/**
	 * 
	 * @param dbConnection
	 * @param AreaType: 
	 * @return
	 * @throws SQLException
	 */
	public String getCouponId(DBConnection dbConnection, Integer salonId) throws SQLException{
		String sql= "SELECT `t_hairSalonMaster_couponId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` =" + salonId.toString();
		String couponIds="";
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				couponIds = rs.getString("t_hairSalonMaster_couponId");
				/*if(couponIds != null){
					couponIdList = Arrays.asList(couponIds.split(","));
				}*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return couponIds;
	}

	public int setCouponInfoForMaster(DBConnection dbConnection, int salonId,
			CouponInfo couponInfo) {
		int couponId = -1;

		boolean result = false;
		
		/**
		 * couponId からcoupon情報があるかどうか確認。
		 * idがテーブルに存在したらx
		 * idが存在しなければinsertする
		 */

		/**
		 * SQL 例:
		 * 
		 * SELECT * FROM `t_coupon` WHERE `t_coupon_Id` = 1
		 * 
			INSERT INTO `MEIHAIWAN_TEST`.`t_coupon` (`t_coupon_Id`, `t_coupon_name`, `t_coupon_couponKindId`, `t_coupon_detailText`, `t_coupon_useCondition`, `t_coupon_imagePath`, `t_coupon_price`, `t_coupon_deadLine`, `t_coupon_isFirstFlag`, `t_coupon_presentationCondition`) VALUES ('10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL);		 * 
		*/
		
		String sql_before = "SELECT * FROM `t_coupon` WHERE `t_coupon_Id` = "; // couponId 
		String sql1 = "INSERT INTO `MEIHAIWAN_TEST`.`t_coupon` ("
				+ "`t_coupon_Id`, `t_coupon_name`, `t_coupon_couponKindId`, `t_coupon_detailText`, "
				+ "`t_coupon_useCondition`, `t_coupon_imagePath`, `t_coupon_price`, `t_coupon_deadLine`, "
				+ "`t_coupon_isFirstFlag`, `t_coupon_presentationCondition`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "NULL";
		String sql4 = "0";
		String sql_end = "');";
		
		String salon_sql1 = "SELECT `t_hairSalonMaster_couponId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` = ";
		String salon_sql2_before = "UPDATE `MEIHAIWAN_TEST`.`t_hairSalonMaster` SET `t_hairSalonMaster_couponId` = '";
		String salon_sql2_middle = "' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = ";
		String salon_sql2_after = ";";

		Statement statement = dbConnection.getStatement();
		
		for(int i=1; i<Integer.MAX_VALUE; i++){
			try {
				ResultSet rs = statement.executeQuery(sql_before+Integer.toString(i));
				if(!rs.next()){
					couponId = i;
					break;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		String sql = sql1 +couponId + sql2
				+ couponInfo.getCouponName() + sql2
				+ couponInfo.getCouponKindId()  + sql2
				+ couponInfo.getCouponDetailText()  + sql2
				+ couponInfo.getUseCondition()  + sql2
				+ couponInfo.getImagePath()  + sql2
				+ couponInfo.getPrice()  + sql2
				+ couponInfo.getDeadLine() + sql2
				+ sql4 + sql2 //isFirst
				+ couponInfo.getPresentationCondition()
				+ sql_end;

		//debug
		System.out.println("sql= "+ sql);
		
		try {
			int result_int = statement.executeUpdate(sql);
			if(result_int <= 0) couponId = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			couponId = -1;
		}
	
		//* coupon をsalon　に足さなきゃ
		ResultSet rs;
		String couponIdList = "";
		try {
			rs = statement.executeQuery(salon_sql1+salonId);
			while(rs.next()){
				couponIdList = rs.getString("t_hairSalonMaster_couponId");
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(couponId != -1){
			String salon_sql =  salon_sql2_before + couponIdList + "," + couponId + salon_sql2_middle + salonId + salon_sql2_after;
			System.out.println(salon_sql);
			try {
				int result_int = statement.executeUpdate(salon_sql);
				if(result_int > 0) couponId = -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				couponId = -1;
			}
		}
		return couponId;
	}

	public boolean DeleteCouponInfoForMaster(DBConnection dbConnection,
			String t_coupon_Id) {
		boolean result = false;
		
		/**
		 * couponId からcoupon情報があるかどうか確認。
		 * idがテーブルに存在したらupdate
		 * idが存在しなければx
		 */

		/**
		 * SQL 例:
		 * DELETE FROM `MEIHAIWAN_TEST`.`t_coupon` WHERE `t_coupon`.`t_coupon_Id` = 13
		 * 
		*/
		
		String sql = "DELETE FROM `MEIHAIWAN_TEST`.`t_coupon` WHERE `t_coupon`.`t_coupon_Id` = ";
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql + t_coupon_Id);
			if(result_int > 0) result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;	
	}
	
	
}
