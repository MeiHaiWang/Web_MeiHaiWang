package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql+userId);
			while(rs.next()){
				ReservationInfo reservationInfo = new ReservationInfo();
				/*
				BeautyNewsInfo.setbeautyNewsName(rs.getString("t_masterNewsName"));
				BeautyNewsInfo.setbeautyNewsImagePath(rs.getString("t_masterNewImagePath"));
				BeautyNewsInfo.setbeautyNewsURL(rs.getString("t_masterNewsURL"));
				BeautyNewsInfoList.add(BeautyNewsInfo);
				*/
				ReservationInfoList.add(reservationInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return ReservationInfoList;
	}	
	
}
