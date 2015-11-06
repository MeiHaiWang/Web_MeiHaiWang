<#assign classNamePrefix = LOWER_UNDERSCORE.to(UPPER_CAMEL, LOWER_CAMEL.to(LOWER_UNDERSCORE, tableName)) />
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

import common._model.${classNamePrefix}Info;
import common.util.DBConnection;

public abstract class ${classNamePrefix}Dao extends BaseDao {
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * {@link ${classNamePrefix}Info} を作成します。
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private ${classNamePrefix}Info create${classNamePrefix}Info(ResultSet rs) throws SQLException {
		
		${classNamePrefix}Info info = new ${classNamePrefix}Info();
		<#list fields as field>
		<#assign fieldName = LOWER_UNDERSCORE.to(LOWER_CAMEL, LOWER_CAMEL.to(LOWER_UNDERSCORE, field.name)) />
		<#assign fieldNameCamel = StringUtils.capitalise(fieldName) />
		<#if field.type == 'INT UNSIGNED' || field.type == 'INT'>
		info.set${fieldNameCamel}(rs.getInt("${field.name}"));
		<#elseif field.type == 'VARCHAR'>
		info.set${fieldNameCamel}(rs.getString("${field.name}"));
		<#elseif field.type == 'DOUBLE' || field.type == 'DOUBLE UNSIGNED'>
		info.set${fieldNameCamel}(rs.getDouble("${field.name}"));
		<#elseif field.type == 'DATETIME'>
		info.set${fieldNameCamel}(rs.getDate("${field.name}"));
		<#else>
		error exists. ${field.type} type is not supported...
		</#if>
		</#list>
		return info;
		
	}
	
	/**
	 * {@link ${classNamePrefix}Info} を取得します。
	 * @param dbConnection
	 * @param id PK
	 * @return
	 * @throws SQLException
	 */
	public ${classNamePrefix}Info get(DBConnection dbConnection, int id) throws SQLException {
		
		List<${classNamePrefix}Info> list = getByColumn(dbConnection, "${fields[0].name}", id);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * {@link ${classNamePrefix}Info} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param columnName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<${classNamePrefix}Info> getByColumn(DBConnection dbConnection, String columnName, Object value) throws SQLException {
		
		Map<String, Object> map = new HashMap<>();
		map.put(columnName, value);
		return getByColumns(dbConnection, map);
	}
	
	/**
	 * {@link ${classNamePrefix}Info} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map 
	 * @return
	 * @throws SQLException
	 */
	public List<${classNamePrefix}Info> getByColumns(DBConnection dbConnection, Map<String, Object> map) throws SQLException {

		return getByColumns(dbConnection, map, null, null);
	}

	/**
	 * {@link ${classNamePrefix}Info} 一覧を取得します
	 * 
	 * @param dbConnection
	 * @param map
	 * @param offset
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public List<${classNamePrefix}Info> getByColumns(DBConnection dbConnection, Map<String, Object> map, Integer offset, Integer count) throws SQLException {
		
		String sql = "select * from `${tableName}` ";
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
		
		List<${classNamePrefix}Info> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(create${classNamePrefix}Info(rs));
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
		
		String sql = " select count(`${fields[0].name}`) count from `${tableName}` ";
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
	public int save(DBConnection dbConnection, ${classNamePrefix}Info info) throws SQLException {
		
		String sql = "insert into `${tableName}` "
			+ " ( "
			<#list fields as field>
				<#if field_index != 0>
			+ " `${field.name}`<#if field_has_next>,</#if> "
				</#if>
			</#list>
			+ " ) "
			+ " values "
			+ " ( "
			<#list fields as field>
				<#if field_index != 0>
			+ " ?<#if field_has_next>,</#if> "
				</#if>
			</#list>
			+ " );";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
		
		<#list fields as field>
			<#if field_index != 0>
				<#assign fieldName = LOWER_UNDERSCORE.to(LOWER_CAMEL, LOWER_CAMEL.to(LOWER_UNDERSCORE, field.name)) />
				<#assign fieldNameCamel = StringUtils.capitalise(fieldName) />
		preparedStatement.setObject(${field_index}, info.get${fieldNameCamel}());
			</#if>
		</#list>
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
	public int update(DBConnection dbConnection, ${classNamePrefix}Info info) throws SQLException {
		
		String sql = "update `${tableName}` set "

			<#list fields as field>
				<#if field_index != 0>
			+ " `${field.name}` = ?<#if field_has_next>,</#if> "
				</#if>
			</#list>
			+ " where "
			+ " `${fields[0].name}` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		<#list fields as field>
			<#if field_index != 0>
				<#assign fieldName = LOWER_UNDERSCORE.to(LOWER_CAMEL, LOWER_CAMEL.to(LOWER_UNDERSCORE, field.name)) />
				<#assign fieldNameCamel = StringUtils.capitalise(fieldName) />
		preparedStatement.setObject(${field_index}, info.get${fieldNameCamel}());
			</#if>
		</#list>

		<#assign idFieldName = LOWER_UNDERSCORE.to(LOWER_CAMEL, LOWER_CAMEL.to(LOWER_UNDERSCORE, fields[0].name)) />
		<#assign idFieldNameCamel = StringUtils.capitalise(idFieldName) />
		preparedStatement.setObject(${fields?size}, info.get${idFieldNameCamel}());
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
		
		String sql = "update `${tableName}` set "
			<#list fields as field>
				<#if field.name?contains('disableFlag')>
			+ " `${field.name}` = ? "
				</#if>
			</#list>
			+ " where "
			+ " `${fields[0].name}` = ?;";
		
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
		
		preparedStatement.setObject(1, 1);
		preparedStatement.setObject(2, id);
		logger.debug(sql);
		
		return preparedStatement.executeUpdate();
	}
}
