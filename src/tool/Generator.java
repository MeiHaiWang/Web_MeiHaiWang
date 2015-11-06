package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.CaseFormat;
import common.util.DBConnection;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Generator {
	
	private static final String CATALOG = "MEIHAIWAN_TEST";
	
	public static void main(String[] args) throws Exception {
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.connectDB();
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet tables = databaseMetaData.getTables(CATALOG, "%", "%", null);
		Map<String, Object> data = new HashMap<>();
		
		// utils
		data.put("StringUtils", new StringUtils());
		data.put("UPPER_UNDERSCORE", CaseFormat.UPPER_UNDERSCORE);
		data.put("LOWER_UNDERSCORE", CaseFormat.LOWER_UNDERSCORE);
		data.put("UPPER_CAMEL", CaseFormat.UPPER_CAMEL);
		data.put("LOWER_CAMEL", CaseFormat.LOWER_CAMEL);
		
		String tableName = null;
		while (tables.next()) {
			
			tableName = tables.getString("TABLE_NAME");
			data.put("tableName", tableName);
			
			ResultSet columns = databaseMetaData.getColumns(CATALOG, "%", tableName, null);
			
			List<Field> fields = new ArrayList<>();
			while (columns.next()) {
				
				String name = columns.getString("COLUMN_NAME");
				String type = columns.getString("TYPE_NAME");
				fields.add(new Field(name, type));
			}
			data.put("fields", fields);

			String entityName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName));
			System.out.println(entityName);
			write("model.ftl", "src/common/_model/" + entityName + "Info", data);
			write("dao.ftl", "src/business/_dao/" + entityName + "Dao", data);
		}
		connection.close();
	}
	
	private static void write(String templateName, String outputName, Map<String, Object> data) throws Exception {
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("generator/templates"));
		
		File output = new File(outputName + ".java");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(output)));
		Template template = configuration.getTemplate(templateName);
		template.process(data, pw);
		pw.flush();
		pw.close();
	}
	
	public static class Field {
		
		private String name;
		
		private String type;
		
		private Field(String name, String type) {
			this.name = name;
			this.type = type;
			
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
	}
}
