package com.jsf.hello.MBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import com.jsf.hello.EJBs.DepartmentEJB;

@ManagedBean(name="department")
public class DepartmentMB {
	
	DepartmentEJB departmentEjb = new DepartmentEJB();

	private int departmentId;
	private String deptName;
	private String search;
	
	
	List<String> departmentTypeOptions;
	
	public DepartmentMB(){
		//populate list of StaffRole
		departmentTypeOptions = new ArrayList<>();
		
		departmentTypeOptions.add("Lab");
		departmentTypeOptions.add("Statistical Dept");
		departmentTypeOptions.add("OT");
		departmentTypeOptions.add("Medical Store");
		
	}
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String departmentName) {
		this.deptName = departmentName;
	}
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public List<String> getDepartmentTypeOptions() {
		return departmentTypeOptions;
	}

	public void setDepartmentTypeOptions(List<String> departmentTypeOptions) {
		this.departmentTypeOptions = departmentTypeOptions;
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
	        String myStat ="INSERT INTO department(departmentId,deptName) VALUES(?,?)";
	        stat = con.prepareStatement(myStat); 
	        
	        stat.setInt(1, departmentId);
	        stat.setString(2, deptName);

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
	
	public void delete(int departmentId) {
		
		if (departmentId !=0){
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="delete FROM department WHERE departmentId=" + departmentId;
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
	public void update(int departmentId) {
		
		if (departmentId !=0){
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="UPDATE department set departmentId=?, deptName =?" + "WHERE departmentId=?";
	        stat = con.prepareStatement(myStat);
	        	        
	        stat.setInt(1, departmentId);
	        stat.setString(2, deptName);
	        int i = stat.executeUpdate();
	        if (i >0){

	        System.out.println("Department updated successfully");
	        }
	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }

	    }}
	public void deptById(int departmentId, String deptName){
		this.departmentId = departmentId;
		this.deptName = deptName;
	}
	
	public void updateDept(){
		//RoomEJB room1 = new RoomEJB();
		//room1.getRoomList();
		update(departmentId);
	}
	public List<DepartmentMB> searchDept(){
		return departmentEjb.searchDept(search);
	}
}
