package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.model.HairSalonInfo;
import common.model.ReservationInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class ReservationDao {
	
	public ReservationDao() throws Exception{
		
	}

	/*
	public List<ReservationInfo> getReservationInfo(DBConnection dbConnection, int userId) throws SQLException{
		String sql = "SELECT `t_masterReservation_id`, `t_masterReservation_salonId`, `t_hairSalonMaster_name`, `t_masterReservation_stylistId`, "
				+ "`t_stylist_name`, `t_masterReservation_Date` FROM `t_masterReservation` JOIN t_hairSalonMaster ON "
				+ "t_masterReservation_salonId = t_hairSalonMaster_salonId"
				+ " JOIN t_stylist ON t_masterReservation_stylistId = t_stylist_Id WHERE t_masterReservation_userId=";
		List<ReservationInfo> ReservationInfoList = new ArrayList<ReservationInfo>();
		System.out.println(sql+userId);
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql+userId);
			while(rs.next()){
				ReservationInfo reservationInfo = new ReservationInfo();
				reservationInfo.setReservationId(rs.getInt("t_masterReservation_id"));
				reservationInfo.setReservationSalonId(rs.getInt("t_masterReservation_salonid"));
				reservationInfo.setReservationSalonName(rs.getString("t_hairSalonMaster_name"));
				reservationInfo.setReservationStylistId(rs.getInt("t_masterReservation_stylistid"));				
				reservationInfo.setReservationStylistName(rs.getString("t_stylist_name"));
				ReservationInfoList.add(reservationInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ReservationInfoList;
	}
	*/

	public List<ReservationInfo> getReservationInfo(DBConnection dbConnection, int userId) throws SQLException{
		String sql = "SELECT `t_reservation_id`, `t_reservation_salonId`, `t_hairSalonMaster_name`, `t_reservation_menuId`, "
				+ " `t_reservation_stylistId`, `t_reservation_isFinished`, `t_reservation_memo`, `t_stylist_name`, `t_reservation_date` "
				+ "FROM `t_reservation` "
				+ "JOIN t_hairSalonMaster ON t_reservation_salonId = t_hairSalonMaster_salonId "
				+ "JOIN t_stylist ON t_reservation_stylistId = t_stylist_Id "
				+ "WHERE t_reservation_userId=";
		List<ReservationInfo> ReservationInfoList = new ArrayList<ReservationInfo>();
		System.out.println(sql+userId);
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql+userId);
			while(rs.next()){
				ReservationInfo reservationInfo = new ReservationInfo();
				reservationInfo.setReservationId(rs.getInt("t_reservation_id"));
				reservationInfo.setReservationSalonId(rs.getInt("t_reservation_salonId"));
				reservationInfo.setReservationSalonName(rs.getString("t_hairSalonMaster_name"));
				reservationInfo.setReservationMenuId(rs.getString("t_reservation_menuId"));				
				reservationInfo.setReservationStylistId(rs.getInt("t_reservation_stylistId"));				
				reservationInfo.setisFinished(rs.getInt("t_reservation_isFinished"));
				reservationInfo.setReservationMemo(rs.getString("t_reservation_memo"));				
				reservationInfo.setReservationStylistName(rs.getString("t_stylist_name"));
				ReservationInfoList.add(reservationInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ReservationInfoList;
	}

	
	public boolean setReservationInfo(DBConnection dbConnection,
			ReservationInfo reservationInfo) {
		/**
		 * INSERT INTO `MEIHAIWAN_TEST`.`t_reservation` (`t_reservation_userId`, `t_reservation_salonId`, 
		 * `t_reservation_stylistId`, `t_reservation_Date`, `t_reservation_isFinished`, `t_reservation_menuId`, 
		 * `t_reservation_seatId`, `t_reservation_memo`) VALUES ('
		 * 1', '1', '1', '1', '2015-09-09 00:00:00', '0', '1', '1', '1');
		 */
		int result = -1;
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_reservation` "
				+ "(`t_reservation_userId`, `t_reservation_salonId`, `t_reservation_stylistId`, `t_reservation_Date`, "
				+ "`t_reservation_isFinished`, `t_reservation_menuId`, `t_reservation_seatId`, `t_reservation_memo`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "');";
		
		String sql = sql1 + reservationInfo.getReservationUserId() + sql2
				+ reservationInfo.getReservationSalonId() + sql2
				+ reservationInfo.getReservationStylistId() + sql2
				+ reservationInfo.getReservationDate() + sql2
				+"0" + sql2
				+ reservationInfo.getReservationMenuId() + sql2
				+ reservationInfo.getReservationSeatId() + sql2
				+ reservationInfo.getReservationMemo() + sql3;

		Statement statement = dbConnection.getStatement();
		//debug
		System.out.println(sql);		
		try {						
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean ret = true;
		if(result < 0) ret = false;

		return ret;
	}

	public List<ReservationInfo> getReservationInfoList(
			DBConnection dbConnection, String t_reservation_salonId,
			String t_reservation_date) {
		/**
		 *     t_reservation_id,
			    t_reservation_date,
			    t_reservation_time,
			    t_user_name,
			    t_user_gender,
			    t_stylist_name,
			    t_menu_name,
			    t_seat_name,
			    t_reservation_memo
		 */
		String sql = "SELECT `t_reservation_id`, `t_user_name`, `t_user_sex`, `t_stylist_name`, "
				+ "`t_seat_name`, `t_reservation_date`, `t_reservation_isFinished`, `t_reservation_memo` "
				+ "FROM `t_reservation` "
				+ "JOIN t_user ON t_reservation_userId = t_user_id "
				+ "JOIN t_stylist ON t_reservation_stylistId = t_stylist_Id "
				+ "JOIN t_seat ON t_reservation_seatId = t_seat_id "
				+ "WHERE `t_reservation_salonId` = "+t_reservation_salonId+" AND `t_reservation_Date` LIKE '%"+t_reservation_date.substring(0,10)+"%'";
		String sql_menu1 = "SELECT `t_reservation_menuId` FROM `t_reservation` WHERE `t_reservation_salonId` = "+t_reservation_salonId;
		
		List<ReservationInfo> ReservationInfoList = new ArrayList<ReservationInfo>();
		System.out.println(sql);
		
		Statement statement = dbConnection.getStatement();
		try {
			List<String> menuNameList = new ArrayList<String>();
			int ope_time = 0;
			ResultSet rs = statement.executeQuery(sql_menu1);
			while(rs.next()){
				String sql_menu2 = "SELECT `t_menu_name`, `t_menu_time` FROM `t_menu` WHERE `t_menu_menuId` IN ("+rs.getString("t_reservation_menuId")+")";
				rs = statement.executeQuery(sql_menu2);
				while(rs.next()){
					//menuNames = Arrays.asList(rs.getString("t_menu_name").split(","));
					menuNameList.add(rs.getString("t_menu_name"));
					ope_time += rs.getInt("t_menu_time");
				}
			}
			rs = statement.executeQuery(sql);
			while(rs.next()){
				ReservationInfo reservationInfo = new ReservationInfo();
				reservationInfo.setReservationId(rs.getInt("t_reservation_id"));
				String r_date = rs.getString("t_reservation_date");
				if(r_date.contains(".")){
					r_date = r_date.substring(0, r_date.lastIndexOf("."));
				}
				reservationInfo.setReservationDate(r_date);
				//reservationInfo.setReservationStylistId(rs.getInt("t_reservation_stylistId"));				
				reservationInfo.setReservationStylistName(rs.getString("t_stylist_name"));
				reservationInfo.setReservationUserName(rs.getString("t_user_name"));
				reservationInfo.setReservationUserSex(rs.getString("t_user_sex"));				
				String menuNames = "";
				for(String menuName : menuNameList) menuNames+=menuName+",";
				menuNames = menuNames.substring(0, menuNames.lastIndexOf(","));
				reservationInfo.setReservationMenuName(menuNames);
				reservationInfo.setReservationSeatName(rs.getString("t_seat_name"));
				reservationInfo.setisFinished(rs.getInt("t_reservation_isFinished"));
				reservationInfo.setReservationMemo(rs.getString("t_reservation_memo"));
				reservationInfo.setReservationTime(ope_time);
				ReservationInfoList.add(reservationInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReservationInfoList;
	}

	
	public List<ReservationInfo> getUserReservationInfoList(
			DBConnection dbConnection, String t_reservation_salonId,
			String t_reservation_userId) {
		/**
		 * 
		reservation_lists:[
		  {
		    t_reservation_id,
		    t_reservation_isFinished
		  },
		  {
		  }
		]
		 */

		String sql1 = "SELECT * FROM `t_reservation` WHERE `t_reservation_userId` = ";
		String sql2 = " AND `t_reservation_salonId` = ";
		String sql = sql1 + t_reservation_userId + sql2 + t_reservation_salonId;
		
		List<ReservationInfo> ReservationInfoList = new ArrayList<ReservationInfo>();
		System.out.println(sql);
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				ReservationInfo reservationInfo = new ReservationInfo();
				reservationInfo.setReservationId(rs.getInt("t_reservation_id"));
				reservationInfo.setisFinished(rs.getInt("t_reservation_isFinished"));
				ReservationInfoList.add(reservationInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReservationInfoList;
	}

	public boolean setReservationFinish(DBConnection dbConnection,
			String t_reservation_id, String t_reservation_isFinished) {
		/**
		 * UPDATE `MEIHAIWAN_TEST`.`t_reservation` SET `t_reservation_isFinished` = '1' WHERE `t_reservation`.`t_reservation_id` = 1;
		 */
		int result = -1;
		String sql1 = "UPDATE `"+ConfigUtil.getConfig("dbname")+"`.`t_reservation` SET `t_reservation_isFinished` = '";
		String sql2 = "' WHERE `t_reservation`.`t_reservation_id` = ";
		String sql3 = ";";
		
		String sql = sql1 + t_reservation_isFinished + sql2 + t_reservation_id + sql3;

		Statement statement = dbConnection.getStatement();

		//debug
		System.out.println(sql);		
		try {						
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean ret = true;
		if(result < 0) ret = false;

		return ret;
	}

	public List<Integer> getReservationUserList(DBConnection dbConnection,
			String t_reservation_salonId) {
		/**
		 * SELECT `t_reservation_userId` FROM `t_reservation` WHERE `t_reservation_salonId` = 1
		 */
		String sql1 = "SELECT `t_reservation_userId` FROM `t_reservation` WHERE `t_reservation_salonId` = ";
		String sql = sql1 + t_reservation_salonId;
		
		List<Integer> userIdList = new ArrayList<Integer>();
		System.out.println(sql);
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				if(!userIdList.contains(rs.getInt("t_reservation_userId"))){
					userIdList.add(rs.getInt("t_reservation_userId"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userIdList;
	}

	public ReservationInfo getReservationInfo(DBConnection dbConnection,
			String t_reservation_id) {
		ReservationInfo reservationInfo = new ReservationInfo();
		/**
		 * SELECT `t_reservation_userId` FROM `t_reservation` WHERE `t_reservation_salonId` = 1
		 */
		String sql = "SELECT `t_user_tel`, `t_reservation_salonId`, `t_reservation_stylistId`, "
				+ "`t_reservation_Date`, `t_reservation_isFinished`, `t_reservation_menuId`, "
				+ "`t_reservation_seatId`, `t_reservation_memo` "
				+ "FROM `t_reservation` "
				+ "JOIN t_user ON t_reservation_userId = t_user_id "
				+ "WHERE `t_reservation_id` = " + t_reservation_id;
		System.out.println(sql);
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				reservationInfo.setReservationUserTel(rs.getString("t_user_tel"));
				reservationInfo.setReservationSalonId(rs.getInt("t_reservation_salonId"));
				reservationInfo.setReservationStylistId(rs.getInt("t_reservation_stylistId"));
				reservationInfo.setReservationDate(rs.getString("t_reservation_Date"));
				reservationInfo.setisFinished(rs.getInt("t_reservation_isFinished"));
				reservationInfo.setReservationMenuId(rs.getString("t_reservation_menuId"));
				reservationInfo.setReservationSeatId(rs.getInt("t_reservation_seatId"));
				reservationInfo.setReservationMemo(rs.getString("t_reservation_memo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationInfo;
	}	
	
}
