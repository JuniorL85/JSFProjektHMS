package com.jsf.hello.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

	public static Connection getDBConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(Constants.DB_DRIVER_CLASS);
			conn = DriverManager.getConnection(Constants.DB_CONNECTION_URL + "/" + Constants.DB_INSTANCE_NAME,
					Constants.DB_USER_NAME, Constants.DB_USER_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}


	public static ResultSet getDBResultSet(Connection conn, String query) throws SQLException {
		ResultSet rs = null;
		if (null != conn) {
			PreparedStatement st = conn.prepareStatement(query);
			rs = st.executeQuery();
		}
		return rs;
	}


	public static void runQuery(Connection conn, String query) throws SQLException {
		if (null != conn) {
			PreparedStatement st = conn.prepareStatement(query);
			st.executeUpdate();
		} else {
			System.out.println("Query execution failed!");
		}
	}


	public static void closePreparedStatement(PreparedStatement ps) throws SQLException {
		if (null != ps) {
			ps.close();
		}
	}


	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (null != rs) {
			rs.close();
		}
	}


	public static void closeDBConnection(Connection conn) throws SQLException {
		if (null != conn) {
			conn.close();
		}
	}
}
