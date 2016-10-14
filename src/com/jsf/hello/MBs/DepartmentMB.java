package com.jsf.hello.MBs;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="department")
public class DepartmentMB {

	private String departmentName;
	private String departmentType;
	
	List<String> departmentTypeOptions;
	
	public DepartmentMB(){
		//populate list of StaffRole
		departmentTypeOptions = new ArrayList<>();
		
		departmentTypeOptions.add("Lab");
		departmentTypeOptions.add("Statistical Dept");
		departmentTypeOptions.add("OT");
		departmentTypeOptions.add("Medical Store");
		
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public List<String> getDepartmentTypeOptions() {
		return departmentTypeOptions;
	}

	public void setDepartmentTypeOptions(List<String> departmentTypeOptions) {
		this.departmentTypeOptions = departmentTypeOptions;
	}

	public String getDepartmentValues(){
		System.out.println("Department name =" + departmentName);
		return "submittedDeptInfo.xhtml";
	}
}
