package com.jsf.hello.MBs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String staffRole;
	private String dbuserName;
	private String jobTitle;
	  
    private String dbpassword;

	//list of StaffRole
	List<String> staffRoleOptions;
	
	Connection con = null;
	Statement stat = null;
	ResultSet rs = null;
	
	public Login(){
		//populate list of StaffRole
		staffRoleOptions = new ArrayList<>();
		
		staffRoleOptions.add("Admin");
		staffRoleOptions.add("Doctor");
		staffRoleOptions.add("Nurse");
		staffRoleOptions.add("Receptionist");
	}
	

	public String getStaffRole() {
		return staffRole;
	}


	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}


	public List<String> getStaffRoleOptions() {
		return staffRoleOptions;
	}


	public void setStaffRoleOptions(List<String> staffRoleOptions) {
		this.staffRoleOptions = staffRoleOptions;
	}


	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDbuserName() {
		return dbuserName;
	}


	public void setDbuserName(String dbuserName) {
		this.dbuserName = dbuserName;
	}


	public String getDbpassword() {
		return dbpassword;
	}


	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public void dbData(String userName){
		
		try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        stat = con.createStatement();
	        String myStat ="SELECT * from employee WHERE userName like('"+userName+"')";
	        rs = stat.executeQuery(myStat);
	        rs.next();
	        dbuserName = rs.getString("UserName");
	        dbpassword = rs.getString("password");
	        staffRole = rs.getString("jobTitle");
   
	    } 
		catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }
    }
	
	public String LoginOK(){
		dbData(userName);
		if(userName.equals(dbuserName) && password.equals(dbpassword) && staffRole.equals("Admin")){
			return "adminWelcomePage.xhtml";
		}
		if(userName.equals(dbuserName) && password.equals(dbpassword)){
			return "welcomePage.xhtml";
		}
		if(userName.equals(dbuserName) && password.equals(dbpassword)){
			return "welcomePage.xhtml";
		}
		if(userName.equals(dbuserName) && password.equals(dbpassword)){
			return "welcomePage.xhtml";
		}
		else{
				return "forgotPassword.xhtml";
			}
		}
	
	public String LogOut(){
		return "login.xhtml?faces-redirect=true";
	}
}
