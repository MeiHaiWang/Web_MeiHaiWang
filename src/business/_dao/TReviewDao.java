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

import common._model.TReviewInfo;
import common.util.DBConnection;

public abstract class TReviewDao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link TReviewInfo} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TReviewInfo createTReviewInfo(ResultSet rs) throws SQLException {
		
		TReviewInfo info = new TReviewInfo();
		info.setTReviewId(rs.getInt("t_review_id"));
		info.setTReviewUserId(rs.getInt("t_review_userId"));
		info.setTReviewPostedDate(rs.getDate("t_review_postedDate"));
		info.setTReviewCommentId(rs.getString("t_review_commentId"));
		info.setTReviewText(rs.getString("t_review_text"));
		info.setTReviewEvaluationPoint(rs.getString("t_review_evaluation_point"));
		return info;
		
	}
	
	/**
	 * {@link TReviewInfo} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public TReviewInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TReviewInfo> list = getByColumn(dbConnection, "t_review_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link TReviewInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<TReviewInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link TReviewInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<TReviewInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link TReviewInfo} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<TReviewInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		return getByColumns(dbConnection, map, offset, count, null, null);
	}

	/**
	 * {@link TReviewInfo} 一覧を取得します
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
	public List<TReviewInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count, String orderColumn, String direction) throws SQLException {
		
		String sql = "select * from `t_review` ";
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
		
		List<TReviewInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTReviewInfo(rs));
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
		
		String sql = " select count(`t_review_id`) count from `t_review` ";
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
	public int save(DBConnection dbConnection, TReviewInfo info) throws SQLException {
		
		String sql = "insert into `t_review` "
			+ " ( "
			+ " `t_review_userId`, "
			+ " `t_review_postedDate`, "
			+ " `t_review_commentId`, "
			+ " `t_review_text`, "
			+ " `t_review_evaluation_point` "
			+ " ) "
			+ " values "
			+ " ( "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ?, "
			+ " ? "
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setObject(1, info.getTReviewUserId());
		preparedStatement.setObject(2, info.getTReviewPostedDate());
		preparedStatement.setObject(3, info.getTReviewCommentId());
		preparedStatement.setObject(4, info.getTReviewText());
		preparedStatement.setObject(5, info.getTReviewEvaluationPoint());
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
	public int update(DBConnection dbConnection, TReviewInfo info) throws SQLException {
		
		String sql = "update `t_review` set "

			+ " `t_review_userId` = ?, "
			+ " `t_review_postedDate` = ?, "
			+ " `t_review_commentId` = ?, "
			+ " `t_review_text` = ?, "
			+ " `t_review_evaluation_point` = ? "
			+ " where "
			+ " `t_review_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, info.getTReviewUserId());
		preparedStatement.setObject(2, info.getTReviewPostedDate());
		preparedStatement.setObject(3, info.getTReviewCommentId());
		preparedStatement.setObject(4, info.getTReviewText());
		preparedStatement.setObject(5, info.getTReviewEvaluationPoint());

		preparedStatement.setObject(6, info.getTReviewId());
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
		
		String sql = "update `t_review` set "
			+ " where "
			+ " `t_review_id` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
