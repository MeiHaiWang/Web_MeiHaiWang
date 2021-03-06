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

import common._model.TStylistInfo;
import common.util.DBConnection;

public abstract class TStylistDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TStylistInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TStylistInfo createTStylistInfo(ResultSet rs) throws SQLException {
		
		TStylistInfo info = new TStylistInfo();
		info.setTStylistId(rs.getInt("t_stylist_Id"));
		info.setTStylistName(rs.getString("t_stylist_name"));
		info.setTStylistSex(rs.getInt("t_stylist_sex"));
		info.setTStylistDetailText(rs.getString("t_stylist_detailText"));
		info.setTStylistUserId(rs.getInt("t_stylist_userId"));
		info.setTStylistImagePath(rs.getString("t_stylist_imagePath"));
		info.setTStylistPosition(rs.getString("t_stylist_position"));
		info.setTStylistMessage(rs.getString("t_stylist_message"));
		info.setTStylistExperienceYear(rs.getInt("t_stylist_experienceYear"));
		info.setTStylistSpecialMenu(rs.getString("t_stylist_specialMenu"));
		info.setTStylistGoodNumber(rs.getInt("t_stylist_goodNumber"));
		info.setTStylistViewNumber(rs.getInt("t_stylist_viewNumber"));
		info.setTStylistMail(rs.getString("t_stylist_mail"));
		info.setTStylistPhoneNumber(rs.getString("t_stylist_phoneNumber"));
		info.setTStylistBirth(rs.getDate("t_stylist_birth"));
		info.setTStylistMenuId(rs.getString("t_stylist_menuId"));
		info.setTStylistHairStyleId(rs.getString("t_stylist_hairStyleId"));
		info.setTStylistSalonId(rs.getInt("t_stylist_salonId"));
		info.setTStylistFavoriteNumber(rs.getInt("t_stylist_favoriteNumber"));
		info.setTStylistIsNetReservation(rs.getInt("t_stylist_isNetReservation"));
		info.setTStylistSearchConditionId(rs.getString("t_stylist_searchConditionId"));
		info.setTStylistAreaId(rs.getString("t_stylist_areaId"));
		info.setTStylistRestday(rs.getString("t_stylist_restday"));
		info.setTStylistResttime(rs.getString("t_stylist_resttime"));
		info.setTStylistReservationId(rs.getString("t_stylist_reservationId"));
		return info;
		
	}
	
	/**
	 * {@link TStylistInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TStylistInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TStylistInfo> list = getByColumn(dbConnection, "t_stylist_Id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TStylistInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TStylistInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TStylistInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TStylistInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TStylistInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TStylistInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		return getByColumns(dbConnection, map, offset, count, null, null);
	}

	/**
	 * {@link TStylistInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @param orderColumn
	 * @param direction
	 * @return
	 * @throws SQLException
	 */
	public List<TStylistInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count, String orderColumn, String direction) throws SQLException {
		
		String sql = "select * from `t_stylist` ";
		String where = " where ";

		for (String columnName : map.keySet()) {
			
			where += " `" + columnName + "` = ? AND ";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -4);
			sql += where;
		}
		
		String order = " order by ";
		if (orderColumn != null) {
			
			order += " " + orderColumn + " ";
			
			if (direction != null) {
			
				order += " " + direction + " ";
			}
			sql += order;
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
		
		List<TStylistInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTStylistInfo(rs));
		}
		return list;
	}

	/**
	 * 件数をカウントします。
	 * @param dbConnection
	 * @return
	 * @throws SQLException
	 */
	public int count(DBConnection dbConnection) throws SQLException {

		return count(dbConnection, new HashMap<>());
	}

	/**
	 * 件数をカウントします。
	 * @param dbConnection
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int count(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = " select count(`t_stylist_Id`) count from `t_stylist` ";
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
	
	/**
	 * 新規作成します。
	 *
	 * @param dbConnection
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	public int save(DBConnection dbConnection, TStylistInfo info) throws SQLException {
		
		String sql = "insert into `t_stylist` "
			+ " ( "
			+ " `t_stylist_name`, "
			+ " `t_stylist_sex`, "
			+ " `t_stylist_detailText`, "
			+ " `t_stylist_userId`, "
			+ " `t_stylist_imagePath`, "
			+ " `t_stylist_position`, "
			+ " `t_stylist_message`, "
			+ " `t_stylist_experienceYear`, "
			+ " `t_stylist_specialMenu`, "
			+ " `t_stylist_goodNumber`, "
			+ " `t_stylist_viewNumber`, "
			+ " `t_stylist_mail`, "
			+ " `t_stylist_phoneNumber`, "
			+ " `t_stylist_birth`, "
			+ " `t_stylist_menuId`, "
			+ " `t_stylist_hairStyleId`, "
			+ " `t_stylist_salonId`, "
			+ " `t_stylist_favoriteNumber`, "
			+ " `t_stylist_isNetReservation`, "
			+ " `t_stylist_searchConditionId`, "
			+ " `t_stylist_areaId`, "
			+ " `t_stylist_restday`, "
			+ " `t_stylist_resttime`, "
			+ " `t_stylist_reservationId` "
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
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTStylistName());
		preparedStatement.setObject(2, info.getTStylistSex());
		preparedStatement.setObject(3, info.getTStylistDetailText());
		preparedStatement.setObject(4, info.getTStylistUserId());
		preparedStatement.setObject(5, info.getTStylistImagePath());
		preparedStatement.setObject(6, info.getTStylistPosition());
		preparedStatement.setObject(7, info.getTStylistMessage());
		preparedStatement.setObject(8, info.getTStylistExperienceYear());
		preparedStatement.setObject(9, info.getTStylistSpecialMenu());
		preparedStatement.setObject(10, info.getTStylistGoodNumber());
		preparedStatement.setObject(11, info.getTStylistViewNumber());
		preparedStatement.setObject(12, info.getTStylistMail());
		preparedStatement.setObject(13, info.getTStylistPhoneNumber());
		preparedStatement.setObject(14, info.getTStylistBirth());
		preparedStatement.setObject(15, info.getTStylistMenuId());
		preparedStatement.setObject(16, info.getTStylistHairStyleId());
		preparedStatement.setObject(17, info.getTStylistSalonId());
		preparedStatement.setObject(18, info.getTStylistFavoriteNumber());
		preparedStatement.setObject(19, info.getTStylistIsNetReservation());
		preparedStatement.setObject(20, info.getTStylistSearchConditionId());
		preparedStatement.setObject(21, info.getTStylistAreaId());
		preparedStatement.setObject(22, info.getTStylistRestday());
		preparedStatement.setObject(23, info.getTStylistResttime());
		preparedStatement.setObject(24, info.getTStylistReservationId());
		logger.debug(sql);
		
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			return rs.getInt(1);
		}
		
		return -1;
	}
	
	/**
	 * 更新を行ないます。
	 * 
	 * @param dbConnection
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	public int update(DBConnection dbConnection, TStylistInfo info) throws SQLException {
		
		String sql = "update `t_stylist` set "

			+ " `t_stylist_name` = ?, "
			+ " `t_stylist_sex` = ?, "
			+ " `t_stylist_detailText` = ?, "
			+ " `t_stylist_userId` = ?, "
			+ " `t_stylist_imagePath` = ?, "
			+ " `t_stylist_position` = ?, "
			+ " `t_stylist_message` = ?, "
			+ " `t_stylist_experienceYear` = ?, "
			+ " `t_stylist_specialMenu` = ?, "
			+ " `t_stylist_goodNumber` = ?, "
			+ " `t_stylist_viewNumber` = ?, "
			+ " `t_stylist_mail` = ?, "
			+ " `t_stylist_phoneNumber` = ?, "
			+ " `t_stylist_birth` = ?, "
			+ " `t_stylist_menuId` = ?, "
			+ " `t_stylist_hairStyleId` = ?, "
			+ " `t_stylist_salonId` = ?, "
			+ " `t_stylist_favoriteNumber` = ?, "
			+ " `t_stylist_isNetReservation` = ?, "
			+ " `t_stylist_searchConditionId` = ?, "
			+ " `t_stylist_areaId` = ?, "
			+ " `t_stylist_restday` = ?, "
			+ " `t_stylist_resttime` = ?, "
			+ " `t_stylist_reservationId` = ? "
			+ " where "
			+ " `t_stylist_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTStylistName());
		preparedStatement.setObject(2, info.getTStylistSex());
		preparedStatement.setObject(3, info.getTStylistDetailText());
		preparedStatement.setObject(4, info.getTStylistUserId());
		preparedStatement.setObject(5, info.getTStylistImagePath());
		preparedStatement.setObject(6, info.getTStylistPosition());
		preparedStatement.setObject(7, info.getTStylistMessage());
		preparedStatement.setObject(8, info.getTStylistExperienceYear());
		preparedStatement.setObject(9, info.getTStylistSpecialMenu());
		preparedStatement.setObject(10, info.getTStylistGoodNumber());
		preparedStatement.setObject(11, info.getTStylistViewNumber());
		preparedStatement.setObject(12, info.getTStylistMail());
		preparedStatement.setObject(13, info.getTStylistPhoneNumber());
		preparedStatement.setObject(14, info.getTStylistBirth());
		preparedStatement.setObject(15, info.getTStylistMenuId());
		preparedStatement.setObject(16, info.getTStylistHairStyleId());
		preparedStatement.setObject(17, info.getTStylistSalonId());
		preparedStatement.setObject(18, info.getTStylistFavoriteNumber());
		preparedStatement.setObject(19, info.getTStylistIsNetReservation());
		preparedStatement.setObject(20, info.getTStylistSearchConditionId());
		preparedStatement.setObject(21, info.getTStylistAreaId());
		preparedStatement.setObject(22, info.getTStylistRestday());
		preparedStatement.setObject(23, info.getTStylistResttime());
		preparedStatement.setObject(24, info.getTStylistReservationId());

		preparedStatement.setObject(25, info.getTStylistId());
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
	
	/**
	 * 論理削除を行ないます。
	 * 
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public int logicalDelete(DBConnection dbConnection, int id) throws SQLException {
		
		String sql = "update `t_stylist` set "
			+ " where "
			+ " `t_stylist_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
