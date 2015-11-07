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

import common._model.TUserInfo;
import common.util.DBConnection;

public abstract class TUserDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TUserInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TUserInfo createTUserInfo(ResultSet rs) throws SQLException {
		
		TUserInfo info = new TUserInfo();
		info.setTUserId(rs.getInt("t_user_Id"));
		info.setTUserDisableFlag(rs.getInt("t_user_disableFlag"));
		info.setTUserMail(rs.getString("t_user_mail"));
		info.setTUserPassward(rs.getString("t_user_passward"));
		info.setTUserCookie(rs.getString("t_user_cookie"));
		info.setTUserImagePath(rs.getString("t_user_imagePath"));
		info.setTUserSex(rs.getInt("t_user_sex"));
		info.setTUserBirth(rs.getDate("t_user_birth"));
		info.setTUserName(rs.getString("t_user_name"));
		info.setTUserFavoriteSalonId(rs.getString("t_user_favoriteSalonId"));
		info.setTUserFavoriteStylistId(rs.getString("t_user_favoriteStylistId"));
		info.setTUserFavoriteHairStyleId(rs.getString("t_user_favoriteHairStyleId"));
		info.setTUserLatestViewSalonId(rs.getString("t_user_latestViewSalonId"));
		info.setTUserLatestViewStylistId(rs.getString("t_user_latestViewStylistId"));
		info.setTUserLatestViewHairStyleId(rs.getString("t_user_latestViewHairStyleId"));
		info.setTUserPoint(rs.getInt("t_user_point"));
		info.setTUserHistorySalonId(rs.getInt("t_user_historySalonId"));
		info.setTUserMasterSalonId(rs.getInt("t_user_MasterSalonId"));
		info.setTUserTel(rs.getString("t_user_tel"));
		info.setTUserReservationId(rs.getString("t_user_reservationId"));
		return info;
		
	}
	
	/**
	 * {@link TUserInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TUserInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TUserInfo> list = getByColumn(dbConnection, "t_user_Id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TUserInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TUserInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TUserInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TUserInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TUserInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TUserInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		return getByColumns(dbConnection, map, offset, count, null, null);
	}

	/**
	 * {@link TUserInfo} 一覧を取得します
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
	public List<TUserInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count, String orderColumn, String direction) throws SQLException {
		
		String sql = "select * from `t_user` ";
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
		
		List<TUserInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTUserInfo(rs));
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
		
		String sql = " select count(`t_user_Id`) count from `t_user` ";
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
	public int save(DBConnection dbConnection, TUserInfo info) throws SQLException {
		
		String sql = "insert into `t_user` "
			+ " ( "
			+ " `t_user_disableFlag`, "
			+ " `t_user_mail`, "
			+ " `t_user_passward`, "
			+ " `t_user_cookie`, "
			+ " `t_user_imagePath`, "
			+ " `t_user_sex`, "
			+ " `t_user_birth`, "
			+ " `t_user_name`, "
			+ " `t_user_favoriteSalonId`, "
			+ " `t_user_favoriteStylistId`, "
			+ " `t_user_favoriteHairStyleId`, "
			+ " `t_user_latestViewSalonId`, "
			+ " `t_user_latestViewStylistId`, "
			+ " `t_user_latestViewHairStyleId`, "
			+ " `t_user_point`, "
			+ " `t_user_historySalonId`, "
			+ " `t_user_MasterSalonId`, "
			+ " `t_user_tel`, "
			+ " `t_user_reservationId` "
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
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTUserDisableFlag());
		preparedStatement.setObject(2, info.getTUserMail());
		preparedStatement.setObject(3, info.getTUserPassward());
		preparedStatement.setObject(4, info.getTUserCookie());
		preparedStatement.setObject(5, info.getTUserImagePath());
		preparedStatement.setObject(6, info.getTUserSex());
		preparedStatement.setObject(7, info.getTUserBirth());
		preparedStatement.setObject(8, info.getTUserName());
		preparedStatement.setObject(9, info.getTUserFavoriteSalonId());
		preparedStatement.setObject(10, info.getTUserFavoriteStylistId());
		preparedStatement.setObject(11, info.getTUserFavoriteHairStyleId());
		preparedStatement.setObject(12, info.getTUserLatestViewSalonId());
		preparedStatement.setObject(13, info.getTUserLatestViewStylistId());
		preparedStatement.setObject(14, info.getTUserLatestViewHairStyleId());
		preparedStatement.setObject(15, info.getTUserPoint());
		preparedStatement.setObject(16, info.getTUserHistorySalonId());
		preparedStatement.setObject(17, info.getTUserMasterSalonId());
		preparedStatement.setObject(18, info.getTUserTel());
		preparedStatement.setObject(19, info.getTUserReservationId());
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
	public int update(DBConnection dbConnection, TUserInfo info) throws SQLException {
		
		String sql = "update `t_user` set "

			+ " `t_user_disableFlag` = ?, "
			+ " `t_user_mail` = ?, "
			+ " `t_user_passward` = ?, "
			+ " `t_user_cookie` = ?, "
			+ " `t_user_imagePath` = ?, "
			+ " `t_user_sex` = ?, "
			+ " `t_user_birth` = ?, "
			+ " `t_user_name` = ?, "
			+ " `t_user_favoriteSalonId` = ?, "
			+ " `t_user_favoriteStylistId` = ?, "
			+ " `t_user_favoriteHairStyleId` = ?, "
			+ " `t_user_latestViewSalonId` = ?, "
			+ " `t_user_latestViewStylistId` = ?, "
			+ " `t_user_latestViewHairStyleId` = ?, "
			+ " `t_user_point` = ?, "
			+ " `t_user_historySalonId` = ?, "
			+ " `t_user_MasterSalonId` = ?, "
			+ " `t_user_tel` = ?, "
			+ " `t_user_reservationId` = ? "
			+ " where "
			+ " `t_user_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTUserDisableFlag());
		preparedStatement.setObject(2, info.getTUserMail());
		preparedStatement.setObject(3, info.getTUserPassward());
		preparedStatement.setObject(4, info.getTUserCookie());
		preparedStatement.setObject(5, info.getTUserImagePath());
		preparedStatement.setObject(6, info.getTUserSex());
		preparedStatement.setObject(7, info.getTUserBirth());
		preparedStatement.setObject(8, info.getTUserName());
		preparedStatement.setObject(9, info.getTUserFavoriteSalonId());
		preparedStatement.setObject(10, info.getTUserFavoriteStylistId());
		preparedStatement.setObject(11, info.getTUserFavoriteHairStyleId());
		preparedStatement.setObject(12, info.getTUserLatestViewSalonId());
		preparedStatement.setObject(13, info.getTUserLatestViewStylistId());
		preparedStatement.setObject(14, info.getTUserLatestViewHairStyleId());
		preparedStatement.setObject(15, info.getTUserPoint());
		preparedStatement.setObject(16, info.getTUserHistorySalonId());
		preparedStatement.setObject(17, info.getTUserMasterSalonId());
		preparedStatement.setObject(18, info.getTUserTel());
		preparedStatement.setObject(19, info.getTUserReservationId());

		preparedStatement.setObject(20, info.getTUserId());
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
		
		String sql = "update `t_user` set "
			+ " `t_user_disableFlag` = ? "
			+ " where "
			+ " `t_user_Id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
