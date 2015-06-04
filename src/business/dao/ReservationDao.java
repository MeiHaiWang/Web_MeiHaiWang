package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.HairSalonInfo;
import common.model.ReservationInfo;
import common.util.DBConnection;

public class ReservationDao {
	
	public ReservationDao() throws Exception{
		
	}

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
	
}
