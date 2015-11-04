package com.mhw.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

import com.google.common.base.CaseFormat;

import freemarker.template.Template;

public class Generator {
	
	private static final String SCHEMA = "MEIHAIWAN_TEST";
	
	private static final String HOST = "localhost";
	
	private static final String DB_USER = "root";
	
	private static final String DB_PASSWORD = "root";
	
	private static final String TEMPLATE_LOADER_PATH = "generator/template";
	
	private static final String OUTPUT_DIR = "src/main/resources/generator/output";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + SCHEMA, DB_USER, DB_PASSWORD);
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet tables = databaseMetaData.getTables(null, null, null, null);
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
			System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName));
			data.put("tableName", tableName);
			
			ResultSet columns = databaseMetaData.getColumns(null, null, tableName, null);
			
			List<Field> fields = new ArrayList<>();
			while (columns.next()) {
				
				String name = columns.getString("COLUMN_NAME");
				String type = columns.getString("TYPE_NAME");
				fields.add(new Field(name, type));
			}
			data.put("fields", fields);
			
			write("entity.ftl", "entity/" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName), data);
			write("repository.ftl", "repository/" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName)
				+ "Repository", data);
			write("form.ftl", "form/" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName) + "Form", data);
			write("controller.ftl", "controller/Admin" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName) + "Controller", data);
		}
		connection.close();
		System.out.println("fin.");
	}
	
	private static void write(String templateName, String outputName, Map<String, Object> data) throws Exception {
		FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
		factory.setTemplateLoaderPath(TEMPLATE_LOADER_PATH);
		factory.setDefaultEncoding("UTF-8");
		freemarker.template.Configuration configuration = factory.createConfiguration();
		
		File output = new File(OUTPUT_DIR + "/" + outputName + ".java");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(output)));
		Template template = configuration.getTemplate(templateName);
		template.process(data, pw);
		pw.flush();
		pw.close();
	}
	
	@AllArgsConstructor
	@Data
	public static class Field {
		
		private String name;
		
		private String type;
		
	}
}
