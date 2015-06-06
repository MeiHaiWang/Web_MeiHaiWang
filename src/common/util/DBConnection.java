package common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBConnection {

	private Connection conn = null;
	private Statement statement = null;

	public Connection connectDB() throws FileNotFoundException {
		//TODO config.propertiesに設定を移動すること by ebina
		String configPath = "config.properties";
		PropertiesManager propertiesManager;
		try {
			propertiesManager = new PropertiesManager(new String[]{configPath});
			Properties conf = propertiesManager.getProperties();

			String url = conf.getProperty("url");
			String user = conf.getProperty("user");
			String password = conf.getProperty("password");

			String msg = "";
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, password);
				statement = conn.createStatement();
	            msg = "ドライバのロードに成功しました";
			} catch (ClassNotFoundException e) {
				msg = "ドライバのロードに失敗しました";
				e.printStackTrace();
			} catch (Exception e) {
				msg = "ドライバのロードに失敗しました";
				e.printStackTrace();
			}
			System.out.println(msg);

		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		return conn;
	}

	public void close() {

		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return this.conn;
	}

	public Statement getStatement(){
		return this.statement;
	}

	/*
	public Statement getUpdatableStatement(){
		Statement retStatement = null;
		try {
			this.statement = this.conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
			        ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(statement!=null) retStatement = this.statement;
		return retStatement;
	}
	*/
	
}