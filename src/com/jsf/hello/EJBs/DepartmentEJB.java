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

@ManagedBean(name="deptBean")
@SessionScoped

public class DepartmentEJB {
	
	List<DepartmentMB> list;
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public List<DepartmentMB> getDeptList()
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb", "root", "Sommar15");
			String myStat = "SELECT * FROM department";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				DepartmentMB usr = new DepartmentMB();
				usr.setDepartmentId(rs.getInt("departmentId"));
				usr.setDeptName(rs.getString("deptName"));
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

}
