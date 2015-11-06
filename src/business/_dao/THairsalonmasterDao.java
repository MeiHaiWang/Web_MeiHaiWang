package business._dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.dao.BaseDao;

import common._model.THairSalonMasterInfo;
import common.util.DBConnection;

public abstract class THairSalonMasterDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private THairSalonMasterInfo createTHairSalonMasterInfo(ResultSet rs) throws SQLException {
		
		THairSalonMasterInfo info = new THairSalonMasterInfo();
		info.setTHairSalonMasterSalonId(rs.getInt("t_hairSalonMaster_salonId"));
		info.setTHairSalonMasterName(rs.getString("t_hairSalonMaster_name"));
		info.setTHairSalonMasterViewNumber(rs.getInt("t_hairSalonMaster_viewNumber"));
		info.setTHairSalonMasterGoodNumber(rs.getInt("t_hairSalonMaster_goodNumber"));
		info.setTHairSalonMasterDisplayOrder(rs.getInt("t_hairSalonMaster_displayOrder"));
		info.setTHairSalonMasterAreaId(rs.getString("t_hairSalonMaster_areaId"));
		info.setTHairSalonMasterMenuId(rs.getString("t_hairSalonMaster_menuId"));
		info.setTHairSalonMasterDisableFlag(rs.getInt("t_hairSalonMaster_disableFlag"));
		info.setTHairSalonMasterDetailText(rs.getString("t_hairSalonMaster_detailText"));
		info.setTHairSalonMasterCouponId(rs.getString("t_hairSalonMaster_couponId"));
		info.setTHairSalonMasterStylistId(rs.getString("t_hairSalonMaster_stylistId"));
		info.setTHairSalonMasterSalonImagePath(rs.getString("t_hairSalonMaster_salonImagePath"));
		info.setTHairSalonMasterReviewId(rs.getString("t_hairSalonMaster_reviewId"));
		info.setTHairSalonMasterHairStyleId(rs.getString("t_hairSalonMaster_hairStyleId"));
		info.setTHairSalonMasterContactUserName(rs.getString("t_hairSalonMaster_contactUserName"));
		info.setTHairSalonMasterAddress(rs.getString("t_hairSalonMaster_address"));
		info.setTHairSalonMasterPhoneNumber(rs.getString("t_hairSalonMaster_phoneNumber"));
		info.setTHairSalonMasterMail(rs.getString("t_hairSalonMaster_mail"));
		info.setTHairSalonMasterPassward(rs.getString("t_hairSalonMaster_passward"));
		info.setTHairSalonMasterOpenTime(rs.getString("t_hairSalonMaster_openTime"));
		info.setTHairSalonMasterCloseTime(rs.getString("t_hairSalonMaster_closeTime"));
		info.setTHairSalonMasterCloseDay(rs.getString("t_hairSalonMaster_closeDay"));
		info.setTHairSalonMasterCreditAvailable(rs.getInt("t_hairSalonMaster_creditAvailable"));
		info.setTHairSalonMasterCarParkAvailable(rs.getInt("t_hairSalonMaster_carParkAvailable"));
		info.setTHairSalonMasterMapUrl(rs.getString("t_hairSalonMaster_mapUrl"));
		info.setTHairSalonMasterMapImagePath(rs.getString("t_hairSalonMaster_mapImagePath"));
		info.setTHairSalonMasterMessage(rs.getString("t_hairSalonMaster_message"));
		info.setTHairSalonMasterMapLatitude(rs.getDouble("t_hairSalonMaster_mapLatitude"));
		info.setTHairSalonMasterMapLongitude(rs.getDouble("t_hairSalonMaster_mapLongitude"));
		info.setTHairSalonMasterMapInfoText(rs.getString("t_hairSalonMaster_mapInfoText"));
		info.setTHairSalonMasterAvailableCountryId(rs.getString("t_hairSalonMaster_availableCountryId"));
		info.setTHairSalonMasterFavoriteNumber(rs.getInt("t_hairSalonMaster_favoriteNumber"));
		info.setTHairSalonMasterIsNetReservation(rs.getInt("t_hairSalonMaster_isNetReservation"));
		info.setTHairSalonMasterSearchConditionId(rs.getString("t_hairSalonMaster_searchConditionId"));
		info.setTHairSalonMasterReservationId(rs.getString("t_hairSalonMaster_reservationId"));
		info.setTHairSalonMasterSeatId(rs.getString("t_hairSalonMaster_seatId"));
		return info;
		
	}
	
	public THairSalonMasterInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<THairSalonMasterInfo> list = getByColumn(dbConnection, "t_hairSalonMaster_salonId", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<THairSalonMasterInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<THairSalonMasterInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	public List<THairSalonMasterInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `t_hairSalonMaster` ";
		String where = " where ";

		for (String columnName : map.keySet()) {
			
			where += " `" + columnName + "` = ? AND ";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -4);
			sql += where;
		}
		
		String limit = " limit ";
		if (offset != null) {
		
			limit += offset + " , ";
		}
		
		if (count != null) {
			
			limit += count;
			sql += limit;
		}

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		for (Object value : map.values()) {
			int index = 1;
			preparedStatement.setObject(index, value);
			index++;
		}
		
		ResultSet rs = preparedStatement.executeQuery();
		logger.debug(sql.toString());
		
		List<THairSalonMasterInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTHairSalonMasterInfo(rs));
		}
		return list;
	}
	
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_hairSalonMaster_salonId`) count from `t_hairSalonMaster` ";
		String where = " where ";
		
		for (String columnName : map.keySet()) {
			
			where += " `" + columnName + "` = ? AND ";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -4);
			sql += where;
		}

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		for (Object value : map.values()) {
			int index = 1;
			preparedStatement.setObject(index, value);
			index++;
		}
		
		ResultSet rs = preparedStatement.executeQuery();
		logger.debug(sql.toString());
		
		while (rs.next()) {
			return rs.getInt("count");
		}
		return 0;
	}
	
	public int save(DBConnection dbConnection, THairSalonMasterInfo info) throws SQLException {
		
		String sql = "insert into `t_hairSalonMaster` "
			+ " ( "
			+ " `t_hairSalonMaster_name`, "
			+ " `t_hairSalonMaster_viewNumber`, "
			+ " `t_hairSalonMaster_goodNumber`, "
			+ " `t_hairSalonMaster_displayOrder`, "
			+ " `t_hairSalonMaster_areaId`, "
			+ " `t_hairSalonMaster_menuId`, "
			+ " `t_hairSalonMaster_disableFlag`, "
			+ " `t_hairSalonMaster_detailText`, "
			+ " `t_hairSalonMaster_couponId`, "
			+ " `t_hairSalonMaster_stylistId`, "
			+ " `t_hairSalonMaster_salonImagePath`, "
			+ " `t_hairSalonMaster_reviewId`, "
			+ " `t_hairSalonMaster_hairStyleId`, "
			+ " `t_hairSalonMaster_contactUserName`, "
			+ " `t_hairSalonMaster_address`, "
			+ " `t_hairSalonMaster_phoneNumber`, "
			+ " `t_hairSalonMaster_mail`, "
			+ " `t_hairSalonMaster_passward`, "
			+ " `t_hairSalonMaster_openTime`, "
			+ " `t_hairSalonMaster_closeTime`, "
			+ " `t_hairSalonMaster_closeDay`, "
			+ " `t_hairSalonMaster_creditAvailable`, "
			+ " `t_hairSalonMaster_carParkAvailable`, "
			+ " `t_hairSalonMaster_mapUrl`, "
			+ " `t_hairSalonMaster_mapImagePath`, "
			+ " `t_hairSalonMaster_message`, "
			+ " `t_hairSalonMaster_mapLatitude`, "
			+ " `t_hairSalonMaster_mapLongitude`, "
			+ " `t_hairSalonMaster_mapInfoText`, "
			+ " `t_hairSalonMaster_availableCountryId`, "
			+ " `t_hairSalonMaster_favoriteNumber`, "
			+ " `t_hairSalonMaster_isNetReservation`, "
			+ " `t_hairSalonMaster_searchConditionId`, "
			+ " `t_hairSalonMaster_reservationId`, "
			+ " `t_hairSalonMaster_seatId` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTHairSalonMasterName());
		preparedStatement.setObject(2, info.getTHairSalonMasterViewNumber());
		preparedStatement.setObject(3, info.getTHairSalonMasterGoodNumber());
		preparedStatement.setObject(4, info.getTHairSalonMasterDisplayOrder());
		preparedStatement.setObject(5, info.getTHairSalonMasterAreaId());
		preparedStatement.setObject(6, info.getTHairSalonMasterMenuId());
		preparedStatement.setObject(7, info.getTHairSalonMasterDisableFlag());
		preparedStatement.setObject(8, info.getTHairSalonMasterDetailText());
		preparedStatement.setObject(9, info.getTHairSalonMasterCouponId());
		preparedStatement.setObject(10, info.getTHairSalonMasterStylistId());
		preparedStatement.setObject(11, info.getTHairSalonMasterSalonImagePath());
		preparedStatement.setObject(12, info.getTHairSalonMasterReviewId());
		preparedStatement.setObject(13, info.getTHairSalonMasterHairStyleId());
		preparedStatement.setObject(14, info.getTHairSalonMasterContactUserName());
		preparedStatement.setObject(15, info.getTHairSalonMasterAddress());
		preparedStatement.setObject(16, info.getTHairSalonMasterPhoneNumber());
		preparedStatement.setObject(17, info.getTHairSalonMasterMail());
		preparedStatement.setObject(18, info.getTHairSalonMasterPassward());
		preparedStatement.setObject(19, info.getTHairSalonMasterOpenTime());
		preparedStatement.setObject(20, info.getTHairSalonMasterCloseTime());
		preparedStatement.setObject(21, info.getTHairSalonMasterCloseDay());
		preparedStatement.setObject(22, info.getTHairSalonMasterCreditAvailable());
		preparedStatement.setObject(23, info.getTHairSalonMasterCarParkAvailable());
		preparedStatement.setObject(24, info.getTHairSalonMasterMapUrl());
		preparedStatement.setObject(25, info.getTHairSalonMasterMapImagePath());
		preparedStatement.setObject(26, info.getTHairSalonMasterMessage());
		preparedStatement.setObject(27, info.getTHairSalonMasterMapLatitude());
		preparedStatement.setObject(28, info.getTHairSalonMasterMapLongitude());
		preparedStatement.setObject(29, info.getTHairSalonMasterMapInfoText());
		preparedStatement.setObject(30, info.getTHairSalonMasterAvailableCountryId());
		preparedStatement.setObject(31, info.getTHairSalonMasterFavoriteNumber());
		preparedStatement.setObject(32, info.getTHairSalonMasterIsNetReservation());
		preparedStatement.setObject(33, info.getTHairSalonMasterSearchConditionId());
		preparedStatement.setObject(34, info.getTHairSalonMasterReservationId());
		preparedStatement.setObject(35, info.getTHairSalonMasterSeatId());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	public int update(DBConnection dbConnection, THairSalonMasterInfo info) throws SQLException {
		
		String sql = "update `t_hairSalonMaster` set "

			+ " `t_hairSalonMaster_name` = ?, "
			+ " `t_hairSalonMaster_viewNumber` = ?, "
			+ " `t_hairSalonMaster_goodNumber` = ?, "
			+ " `t_hairSalonMaster_displayOrder` = ?, "
			+ " `t_hairSalonMaster_areaId` = ?, "
			+ " `t_hairSalonMaster_menuId` = ?, "
			+ " `t_hairSalonMaster_disableFlag` = ?, "
			+ " `t_hairSalonMaster_detailText` = ?, "
			+ " `t_hairSalonMaster_couponId` = ?, "
			+ " `t_hairSalonMaster_stylistId` = ?, "
			+ " `t_hairSalonMaster_salonImagePath` = ?, "
			+ " `t_hairSalonMaster_reviewId` = ?, "
			+ " `t_hairSalonMaster_hairStyleId` = ?, "
			+ " `t_hairSalonMaster_contactUserName` = ?, "
			+ " `t_hairSalonMaster_address` = ?, "
			+ " `t_hairSalonMaster_phoneNumber` = ?, "
			+ " `t_hairSalonMaster_mail` = ?, "
			+ " `t_hairSalonMaster_passward` = ?, "
			+ " `t_hairSalonMaster_openTime` = ?, "
			+ " `t_hairSalonMaster_closeTime` = ?, "
			+ " `t_hairSalonMaster_closeDay` = ?, "
			+ " `t_hairSalonMaster_creditAvailable` = ?, "
			+ " `t_hairSalonMaster_carParkAvailable` = ?, "
			+ " `t_hairSalonMaster_mapUrl` = ?, "
			+ " `t_hairSalonMaster_mapImagePath` = ?, "
			+ " `t_hairSalonMaster_message` = ?, "
			+ " `t_hairSalonMaster_mapLatitude` = ?, "
			+ " `t_hairSalonMaster_mapLongitude` = ?, "
			+ " `t_hairSalonMaster_mapInfoText` = ?, "
			+ " `t_hairSalonMaster_availableCountryId` = ?, "
			+ " `t_hairSalonMaster_favoriteNumber` = ?, "
			+ " `t_hairSalonMaster_isNetReservation` = ?, "
			+ " `t_hairSalonMaster_searchConditionId` = ?, "
			+ " `t_hairSalonMaster_reservationId` = ?, "
			+ " `t_hairSalonMaster_seatId` = ? "
			+ " where "
			+ " `t_hairSalonMaster_salonId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTHairSalonMasterName());
		preparedStatement.setObject(2, info.getTHairSalonMasterViewNumber());
		preparedStatement.setObject(3, info.getTHairSalonMasterGoodNumber());
		preparedStatement.setObject(4, info.getTHairSalonMasterDisplayOrder());
		preparedStatement.setObject(5, info.getTHairSalonMasterAreaId());
		preparedStatement.setObject(6, info.getTHairSalonMasterMenuId());
		preparedStatement.setObject(7, info.getTHairSalonMasterDisableFlag());
		preparedStatement.setObject(8, info.getTHairSalonMasterDetailText());
		preparedStatement.setObject(9, info.getTHairSalonMasterCouponId());
		preparedStatement.setObject(10, info.getTHairSalonMasterStylistId());
		preparedStatement.setObject(11, info.getTHairSalonMasterSalonImagePath());
		preparedStatement.setObject(12, info.getTHairSalonMasterReviewId());
		preparedStatement.setObject(13, info.getTHairSalonMasterHairStyleId());
		preparedStatement.setObject(14, info.getTHairSalonMasterContactUserName());
		preparedStatement.setObject(15, info.getTHairSalonMasterAddress());
		preparedStatement.setObject(16, info.getTHairSalonMasterPhoneNumber());
		preparedStatement.setObject(17, info.getTHairSalonMasterMail());
		preparedStatement.setObject(18, info.getTHairSalonMasterPassward());
		preparedStatement.setObject(19, info.getTHairSalonMasterOpenTime());
		preparedStatement.setObject(20, info.getTHairSalonMasterCloseTime());
		preparedStatement.setObject(21, info.getTHairSalonMasterCloseDay());
		preparedStatement.setObject(22, info.getTHairSalonMasterCreditAvailable());
		preparedStatement.setObject(23, info.getTHairSalonMasterCarParkAvailable());
		preparedStatement.setObject(24, info.getTHairSalonMasterMapUrl());
		preparedStatement.setObject(25, info.getTHairSalonMasterMapImagePath());
		preparedStatement.setObject(26, info.getTHairSalonMasterMessage());
		preparedStatement.setObject(27, info.getTHairSalonMasterMapLatitude());
		preparedStatement.setObject(28, info.getTHairSalonMasterMapLongitude());
		preparedStatement.setObject(29, info.getTHairSalonMasterMapInfoText());
		preparedStatement.setObject(30, info.getTHairSalonMasterAvailableCountryId());
		preparedStatement.setObject(31, info.getTHairSalonMasterFavoriteNumber());
		preparedStatement.setObject(32, info.getTHairSalonMasterIsNetReservation());
		preparedStatement.setObject(33, info.getTHairSalonMasterSearchConditionId());
		preparedStatement.setObject(34, info.getTHairSalonMasterReservationId());
		preparedStatement.setObject(35, info.getTHairSalonMasterSeatId());

		preparedStatement.setObject(36, info.getTHairSalonMasterSalonId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_hairSalonMaster` set "
			+ " `t_hairSalonMaster_disableFlag` = ? "
			+ " where "
			+ " `t_hairSalonMaster_salonId` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
