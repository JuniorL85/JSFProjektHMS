package com.jsf.hello.EJBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.jsf.hello.MBs.DepartmentMB;
import com.jsf.hello.Util.DBHelper;

@ManagedBean(name = "deptBean")
@SessionScoped

public class DepartmentEJB {

	private String departmentUsername;
	private String departmentPassword;

	List<DepartmentMB> list;

	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	public String getDepartmentUsername() {
		return departmentUsername;
	}

	public void setDepartmentUsername(String departmentUsername) {
		this.departmentUsername = departmentUsername;
	}

	public String getDepartmentPassword() {
		return departmentPassword;
	}

	public void setDepartmentPassword(String departmentPassword) {
		this.departmentPassword = departmentPassword;
	}
	
	public List<String> getPatientCount() {

		List<String> count= new ArrayList<String>();
		
		try {
			con = DBHelper.getDBConnection();
			String myStat = "SELECT COUNT(*) as rowcount FROM patient";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				count.add((rs.getString("rowcount")));  
			}
		
			
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	
	public List<String> getNurseCount() {

		List<String> count= new ArrayList<String>();
		
		try {
			con = DBHelper.getDBConnection();
			String myStat = "SELECT COUNT(*) as rowcount FROM nurse";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				count.add((rs.getString("rowcount")));  
			}
		
			
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<String> getDocCount() {

		List<String> count= new ArrayList<String>();
		
		try {
			con = DBHelper.getDBConnection();
			String myStat = "SELECT COUNT(*) as rowcount FROM doctor";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				count.add((rs.getString("rowcount")));  
			}
		
			
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<String> getEmplCount() {

		List<String> count= new ArrayList<String>();
		
		try {
			con = DBHelper.getDBConnection();
			String myStat = "SELECT COUNT(*) as rowcount FROM employee";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				count.add((rs.getString("rowcount")));  
			}
	
			
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}



	public List<DepartmentMB> getDeptList() {
		list = new ArrayList<>();

		try {
			con = DBHelper.getDBConnection();
			String myStat = "SELECT * FROM department";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while (rs.next()) {

				DepartmentMB usr = new DepartmentMB();
				usr.setDepartmentId(rs.getInt("departmentId"));
				usr.setDeptName(rs.getString("deptName"));
				list.add(usr);
			}
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void add(DepartmentMB departmentmb) {

		try {
			con = DBHelper.getDBConnection();
			String myStat = "INSERT INTO department(departmentId,deptName) VALUES(?,?)";
			stat = con.prepareStatement(myStat);

			stat.setInt(1, departmentmb.getDepartmentId());
			stat.setString(2, departmentmb.getDeptName());

			stat.executeUpdate();

			System.out.println("Info added successfully");

			con.close();
			stat.close();
		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}
	}

	public void delete(int departmentId) {

		if (departmentId != 0) {
			try {
				con = DBHelper.getDBConnection();
				String myStat = "delete FROM department WHERE departmentId=" + departmentId;
				stat = con.prepareStatement(myStat);
				int i = stat.executeUpdate();
				if (i > 0) {

					System.out.println("user deleted successfully");
				}
				con.close();
				stat.close();

			} catch (Exception e) {
				System.out.println(" SQLException :(");
				e.printStackTrace();
			}

		}
	}

	public void update(DepartmentMB departmentmb) {

		try {
			con = DBHelper.getDBConnection();
			String myStat = "UPDATE department SET deptName = ? WHERE departmentId = ?";
			stat = con.prepareStatement(myStat);

			stat.setString(1, departmentmb.getDeptName());
			stat.setInt(2, departmentmb.getDepartmentId());
			stat.executeUpdate();

			con.close();
			stat.close();

		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}

	}

	public List<DepartmentMB> searchDept(String search) {
		list = new ArrayList<>();

		try {
			con = DBHelper.getDBConnection();
			String myStat = "SELECT * FROM department WHERE deptName LIKE '%" + search + "%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while (rs.next()) {

				DepartmentMB usr = new DepartmentMB();
				usr.setDepartmentId(rs.getInt("departmentId"));
				usr.setDeptName(rs.getString("deptName"));
				list.add(usr);
			}
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String LoginOK() {
		if (departmentUsername.equals("medicalstore") && departmentPassword.equals("medicalstore")) {
			return "medicalstorePage.xhtml?faces-redirect=true";
		}
		if (departmentUsername.equals("statistic") && departmentPassword.equals("statistic")) {
			return "statisticPage.xhtml?faces-redirect=true";
		}
		if (departmentUsername.equals("lab") && departmentPassword.equals("lab")) {
			return "labPage.xhtml?faces-redirect=true";
		} else {
			return "forgotPassword.xhtml?faces-redirect=true";
		}
	}

	public String LogOut() {
		return "departmentLogin.xhtml?faces-redirect=true";
	}

}
