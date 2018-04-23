package com.telnet.jukebox.webservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	public static Connection conStat() throws ClassNotFoundException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox?useSSL=false", "root", "qqq");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
