package com.jsf.hello.MBs;

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

	
	public List<String> getEmplCount(){
		return departmentEjb.getEmplCount();
	}
	public List<String> getDocCount(){
		return departmentEjb.getDocCount();
	}
	public List<String> getNurseCount(){
		return departmentEjb.getNurseCount();
	}
	public List<String> getPatientCount(){
		return departmentEjb.getPatientCount();
	}
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

	public List<DepartmentMB> getDeptList(){
		return departmentEjb.getDeptList();
	}
	public void add(){
		departmentEjb.add(this);
		clear();
    }
	
	public void delete(int departmentId) {
		departmentEjb.delete(departmentId);
	}

	public void deptById(int departmentId, String deptName){
		this.departmentId = departmentId;
		this.deptName = deptName;
	}
	
	public void updateDept(){
		departmentEjb.update(this);
	}
	public List<DepartmentMB> searchDept(){
		return departmentEjb.searchDept(search);
	}
	
	public void clear(){
		setDepartmentId(0);
		setDeptName(null);
	}
	public String LoginOK(){
		return departmentEjb.LoginOK();
	}
	public String LogOut(){
		return departmentEjb.LogOut();
	}
}
