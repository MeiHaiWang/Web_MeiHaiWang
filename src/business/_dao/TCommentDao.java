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

import common._model.TCommentInfo;
import common.util.DBConnection;

public abstract class TCommentDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TCommentInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TCommentInfo createTCommentInfo(ResultSet rs) throws SQLException {
		
		TCommentInfo info = new TCommentInfo();
		info.setTCommentId(rs.getInt("t_comment_id"));
		info.setTCommentUserId(rs.getInt("t_comment_userId"));
		info.setTCommentMessage(rs.getString("t_comment_message"));
		info.setTCommentReviewId(rs.getInt("t_comment_reviewId"));
		info.setTCommentDate(rs.getDate("t_comment_date"));
		return info;
		
	}
	
	/**
	 * {@link TCommentInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TCommentInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TCommentInfo> list = getByColumn(dbConnection, "t_comment_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TCommentInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TCommentInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TCommentInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TCommentInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TCommentInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TCommentInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		return getByColumns(dbConnection, map, offset, count, null, null);
	}

	/**
	 * {@link TCommentInfo} 一覧を取得します
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
	public List<TCommentInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count, String orderColumn, String direction) throws SQLException {
		
		String sql = "select * from `t_comment` ";
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
		
		List<TCommentInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTCommentInfo(rs));
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
		
		String sql = " select count(`t_comment_id`) count from `t_comment` ";
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
	public int save(DBConnection dbConnection, TCommentInfo info) throws SQLException {
		
		String sql = "insert into `t_comment` "
			+ " ( "
			+ " `t_comment_userId`, "
			+ " `t_comment_message`, "
			+ " `t_comment_reviewId`, "
			+ " `t_comment_date` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTCommentUserId());
		preparedStatement.setObject(2, info.getTCommentMessage());
		preparedStatement.setObject(3, info.getTCommentReviewId());
		preparedStatement.setObject(4, info.getTCommentDate());
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
	public int update(DBConnection dbConnection, TCommentInfo info) throws SQLException {
		
		String sql = "update `t_comment` set "

			+ " `t_comment_userId` = ?, "
			+ " `t_comment_message` = ?, "
			+ " `t_comment_reviewId` = ?, "
			+ " `t_comment_date` = ? "
			+ " where "
			+ " `t_comment_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTCommentUserId());
		preparedStatement.setObject(2, info.getTCommentMessage());
		preparedStatement.setObject(3, info.getTCommentReviewId());
		preparedStatement.setObject(4, info.getTCommentDate());

		preparedStatement.setObject(5, info.getTCommentId());
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
		
		String sql = "update `t_comment` set "
			+ " where "
			+ " `t_comment_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
