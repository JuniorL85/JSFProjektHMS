package com.jsf.hello.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	private static String dbURL = "jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false";

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection(dbURL, "root", "Sommar15")){
			
			Statement stat = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			//System.out.print(conn);
			
			String myStat = "SELECT * FROM department";
		      ResultSet rs = stat.executeQuery(myStat);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int departmentId  = rs.getInt("departmentId");
		         String deptName = rs.getString("deptName");

		         //Display values
		         System.out.print("ID: " + departmentId);
		         System.out.println(", DeptName: " + deptName);
		      }
		      rs.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
	}

}
