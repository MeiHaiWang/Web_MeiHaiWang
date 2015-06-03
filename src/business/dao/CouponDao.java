package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		String sql= "SELECT `t_coupon_Id`, `t_coupon_name`, (SELECT `t_couponKind_name` FROM `t_masterCouponKind` WHERE `t_couponKind_id` = `t_coupon_couponKindId`) AS CKINDNAME, `t_coupon_detailText`, `t_coupon_useCondition`, `t_coupon_price`, `t_coupon_deadLine`, `t_coupon_isFirstFlag`, `t_coupon_presentationCondition` FROM `t_coupon` WHERE `t_coupon_Id` IN(" + couponIdList + ")";
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
	
	
}
