package com.jsf.hello.MBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.jsf.hello.EJBs.HospitalStaffEJB;;
 
@ManagedBean(name = "staff")
@RequestScoped
public class HospitalStaffMB {
	//private static final long serialVersionUID = 1L;
	HospitalStaffEJB hospitalStaffEjb = new HospitalStaffEJB();
	
	private int employeeId;
	private String jobTitle;
	private String firstName;
	private String lastName;
	private int departmentId;
	private String userName;
	private String password;
	private String search;
	
	//list of StaffRole
	List<String> staffRoleOptions;
		
	//list of DepartmentId
	List<Integer> deptId;
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public HospitalStaffMB(){
		//populate list of StaffRole
				staffRoleOptions = new ArrayList<>();
				
				staffRoleOptions.add("Admin");
				staffRoleOptions.add("Doctor");
				staffRoleOptions.add("Nurse");
				staffRoleOptions.add("Receptionist");
				
				//populate list of StaffRole
				deptId = new ArrayList<Integer>();
				
				deptId.add(10);
				deptId.add(11);
				deptId.add(12);
				deptId.add(13);
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getStaffRoleOptions() {
		return staffRoleOptions;
	}

	public void setStaffRoleOptions(List<String> staffRoleOptions) {
		this.staffRoleOptions = staffRoleOptions;
	}
	public List<Integer> getDeptId() {
		return deptId;
	}

	public void setDeptId(List<Integer> deptId) {
		this.deptId = deptId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void add(){
		
		try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="INSERT INTO employee(jobTitle,firstName,lastName,departmentId,userName,password) VALUES(?,?,?,?,?,?)";
	        stat = con.prepareStatement(myStat); 
	        
	        stat.setString(1, jobTitle);
	        stat.setString(2, firstName);
	        stat.setString(3, lastName);
	        stat.setInt(4, departmentId);
	        stat.setString(5, userName);
	        stat.setString(6, password);

	        stat.executeUpdate();

	        System.out.println("Info added successfully");

	        con.close();
			stat.close();
	    } 
		catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }
    }
	
	public void delete(int employeeId) {
		
		if (employeeId !=0){
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="delete FROM employee WHERE employeeId=" + employeeId;
	        stat = con.prepareStatement(myStat); 
	        int i = stat.executeUpdate();
	        if (i >0){

	        System.out.println("user deleted successfully");
	        }
	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }

	    }}
	public void update(int employeeId) {
		
		if (employeeId !=0){
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="UPDATE employee set jobTitle=?, firstName =?, lastName=?, departmentId=?, userName=?, password=?" + "WHERE employeeId=?";
	        stat = con.prepareStatement(myStat);
	        
	        stat.setString(1, jobTitle);
	        stat.setString(2, firstName);
	        stat.setString(3, lastName);
	        stat.setString(4, lastName);
	        stat.setInt(5, departmentId);
	        stat.setString(6, userName);
	        stat.setString(7, password);
	        int i = stat.executeUpdate();
	        if (i >0){

	        System.out.println("User updated successfully");
	        }
	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }

	    }}
	public void staffById(String jobTitle, String firstName, String lastName, int departmentId, String userName, String password){
		this.jobTitle = jobTitle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.userName = userName;
		this.password = password;
	}
	
	public void updateStaff(){
		//RoomEJB room1 = new RoomEJB();
		//room1.getRoomList();
		update(employeeId);
	}
	public List<HospitalStaffMB> searchStaff(){
		return hospitalStaffEjb.searchUser(search);
	}
	
}
