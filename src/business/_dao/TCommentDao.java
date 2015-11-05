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
	
	private TCommentInfo createTCommentInfo(ResultSet rs) throws SQLException {
		
		TCommentInfo info = new TCommentInfo();
		info.setTCommentId(rs.getInt("t_comment_id"));
		info.setTCommentUserId(rs.getInt("t_comment_userId"));
		info.setTCommentMessage(rs.getString("t_comment_message"));
		info.setTCommentReviewId(rs.getInt("t_comment_reviewId"));
		info.setTCommentDate(rs.getDate("t_comment_date"));
		return info;
		
	}
	
	public TCommentInfo get(DBConnection dbConnection, int id) throws SQLException {
		
		List<TCommentInfo> list = getByColumn(dbConnection, "t_comment_id", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<TCommentInfo> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	public List<TCommentInfo> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {
		
		String sql = "select * from `t_comment` ";
		String where = " where ";

		for (String columnName : map.keySet()) {
			
			where += "`" + columnName + "` = ? AND";
		}
		
		if (!map.isEmpty()) {
			where = where.substring(0, where.length() -3);
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
		
		List<TCommentInfo> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(createTCommentInfo(rs));
		}
		return list;
	}
	
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
