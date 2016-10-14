package com.jsf.hello.EJBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.jsf.hello.MBs.HospitalStaffMB;

@ManagedBean(name="userBean")
@SessionScoped
public class HospitalStaffEJB {

	List<HospitalStaffMB> list;
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public List<HospitalStaffMB> getUserList()
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb", "root", "Sommar15");
			String myStat = "SELECT * FROM employee";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				HospitalStaffMB usr = new HospitalStaffMB();
				usr.setEmployeeId(rs.getInt("employeeId"));
				usr.setJobTitle(rs.getString("jobTitle"));
				usr.setFirstName(rs.getString("firstName"));
				usr.setLastName(rs.getString("lastName"));
				usr.setDepartmentId(rs.getInt("departmentId"));
				usr.setUserName(rs.getString("userName"));
				usr.setPassword(rs.getString("password"));
				list.add(usr);
			}
			con.close();
			stat.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		return list;
	}
	
	public void deleteAction(HospitalStaffEJB staff) {
		
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb", "root", "Sommar15");
	        String myStat ="delete FROM employee where user=?";
	        stat = con.prepareStatement(myStat); 
	        // Parameters start with 1
	        //stat.setString(1,"employee");

	        stat.executeUpdate();

	        System.out.println("user deleted successfully");


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }
	//list.remove(userBean);
	//return list;
	    }
	
}
