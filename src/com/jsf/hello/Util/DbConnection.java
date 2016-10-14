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
			
			String myStat = "SELECT * FROM employee";
		      ResultSet rs = stat.executeQuery(myStat);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int employeeId  = rs.getInt("employeeId");
		         String jobTitle = rs.getString("jobTitle");
		         String firstName = rs.getString("firstName");
		     	 String lastName = rs.getString("lastName");
		     	 int departmentId = rs.getInt("departmentId");
		     	 String userName = rs.getString("userName");
		     	 String password = rs.getString("password");

		         //Display values
		         System.out.print("ID: " + employeeId);
		         System.out.print(", Title: " + jobTitle);
		         System.out.print(", First name: " + firstName);
		         System.out.print(", Last name: " + lastName);
		         System.out.print(", Dept ID: " + departmentId);
		         System.out.print(", Username: " + userName);
		         System.out.println(", Password: " + password);
		      }
		      rs.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		
	}

}
