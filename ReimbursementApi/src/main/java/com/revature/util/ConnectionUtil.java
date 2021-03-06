package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String url = System.getenv("TRAINING_DB_URL");
		String username = System.getenv("REIMB_DB_USERNAME");
		String password = System.getenv("REIMB_DB_PASSWORD");
		return DriverManager.getConnection(url, username, password);
	}
}
