package com.jsf.hello.MBs;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Admin {

	private String firstName;
	private String lastName;
	private int ssn;
	private String userName;
	private String password;
	private String staffRole;

	//list of StaffRole
	List<String> staffRoleOptions;
	
	//create no-arg constructor
	public Admin(){
		//populate list of StaffRole
		staffRoleOptions = new ArrayList<>();
		
		staffRoleOptions.add("Doctor");
		staffRoleOptions.add("Nurse");
		staffRoleOptions.add("Receptionist");
	}

	//define getter/setter methods
	public List<String> getStaffRoleOptions() {
		return staffRoleOptions;
	}

	public void setStaffRoleOptions(List<String> staffRoleOptions) {
		this.staffRoleOptions = staffRoleOptions;
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
	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
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
	

	public String getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}
	
	public String getFormValues(){
		System.out.println("firstname ="+ firstName);
		return "submittedUserInfo.xhtml";
	}
	
}
