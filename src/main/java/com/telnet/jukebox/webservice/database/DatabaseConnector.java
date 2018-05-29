package com.telnet.jukebox.webservice.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

	public static Connection conStat() throws ClassNotFoundException, IOException, SQLException {
		// Connection con = null;

		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox?useSSL=false",
		// "root", "qqq");
		//
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// return conn;
		Properties props = new Properties();
		FileInputStream in = new FileInputStream(
				"/home/dev-10/Documents/rest/JukeboxWebService/src/main/resources/aplication.properties");
		props.load(in);
		in.close();

		String driver = props.getProperty("jdbc.driver");
		if (driver != null) {
			Class.forName(driver);
		}

		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		Connection con = DriverManager.getConnection(url, username, password);

		return con;
	}

}
