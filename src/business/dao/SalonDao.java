package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import common.model.BeautyNewsInfo;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class SalonDao {
	
	public SalonDao(){
		
	}
	
	public List<HairSalonInfo> getSalonDetailInfo(Integer salonId ,DBConnection dbConnection) throws SQLException{

		List<HairSalonInfo> salonInfoList = new ArrayList<HairSalonInfo>();
		String sql = "SELECT `t_hairSalonMaster_name`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_evaluationId`, `t_hairSalonMaster_message`, `t_hairSalonMaster_phoneNumber`, `t_hairSalonMaster_address`, `t_hairSalonMaster_openTime`, `t_hairSalonMaster_closeTime`, `t_hairSalonMaster_closeDay`, `t_hairSalonMaster_reviewId`, `t_hairSalonMaster_favoriteNumber` , `t_hairSalonMaster_availableCountryId` FROM `t_hairSalonMaster` WHERE `t_hairSalonMaster_salonId` =" + salonId.toString();		
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				HairSalonInfo salonInfo = new HairSalonInfo();
				salonInfo.setHairSalonId(salonId);
				salonInfo.setHairSalonName(rs.getString("t_hairSalonMaster_name"));
				salonInfo.setHairSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
				salonInfo.setMessage(rs.getString("t_hairSalonMaster_message"));
				salonInfo.setTelNumber(rs.getString("t_hairSalonMaster_phoneNumber"));
				salonInfo.setAddress(rs.getString("t_hairSalonMaster_address"));
				Date openTime = rs.getDate("t_hairSalonMaster_openTime") !=null ?
						rs.getDate("t_hairSalonMaster_openTime"): new Date(0);
				Date closeTime = rs.getDate("t_hairSalonMaster_closeTime") !=null ?
						rs.getDate("t_hairSalonMaster_closeTime") : new Date(0);
				salonInfo.setBusinessHour(sdf.format(openTime).toString() + "〜"  + sdf.format(closeTime));
				salonInfo.setRegularHoliday(rs.getString("t_hairSalonMaster_closeDay"));
				salonInfo.setFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
				salonInfo.setIsNetReservation(rs.getInt("t_hairSalonMaster_ isNetReservation"));
				
				List<String> reviewIdList = rs.getString("t_hairSalonMaster_reviewId") != null ?
						Arrays.asList(rs.getString("t_hairSalonMaster_reviewId").split(",")) : new ArrayList<String>();
				salonInfo.setWordOfMonth(reviewIdList.size());
						
				String innerSql = "SELECT `t_evaluation_point` FROM `t_evaluation` WHERE FIND_IN_SET(t_evaluation_evaluationId," + rs.getString("t_hairSalonMaster_evaluationId") + ")";
				ResultSet innerRs = statement.executeQuery(innerSql);
				Double evaluationPoint = 0.0;
				double i= 0.0;
				while(innerRs.next()){
					evaluationPoint += innerRs.getDouble("t_evaluation_point");
					i++;
				}
				salonInfo.setEvaluationPointMid(evaluationPoint / i);
				
				innerSql ="SELECT t_country_countryName FROM t_masterCountry WHERE FIND_IN_SET(t_country_countryId,"+ rs.getString("t_hairSalonMaster_availableCountryId") +")";
				innerRs = statement.executeQuery(innerSql);
				List<String> availableCountryName = new ArrayList<String>();
				while(innerRs.next()){
					availableCountryName.add(innerRs.getString("t_country_countryName"));
				}
				salonInfo.setMultiLingual(String.join(",", availableCountryName.toArray(new String[0])));
				salonInfoList.add(salonInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return salonInfoList;
	}
	
}
