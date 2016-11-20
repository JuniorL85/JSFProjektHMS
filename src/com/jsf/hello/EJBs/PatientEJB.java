package com.jsf.hello.EJBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.MBs.Patient;

@ManagedBean(name = "patBean")
@SessionScoped
public class PatientEJB {

	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	public List<Patient> getPatList() {
		list = new ArrayList<>();

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "SELECT * FROM patient";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while (rs.next()) {

				Patient usr = new Patient();
				usr.setSsn(rs.getLong("ssn"));
				usr.setFirstName(rs.getString("firstName"));
				usr.setLastName(rs.getString("lastName"));
				usr.setPassword(rs.getString("password"));
				usr.setDoctorId(rs.getInt("doctorId"));
				usr.setNurseId(rs.getInt("nurseId"));
				usr.setCheckIn(rs.getTimestamp("checkIn"));

				list.add(usr);
			}
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void add(Patient patient) {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "INSERT INTO patient(ssn,firstName,lastName,password,doctorId,nurseId) VALUES(?,?,?,?,?,?)";
			stat = con.prepareStatement(myStat);

			stat.setLong(1, patient.getSsn());
			stat.setString(2, patient.getFirstName());
			stat.setString(3, patient.getLastName());
			stat.setString(4, patient.getPassword());
			stat.setInt(5, patient.getDoctorId());
			stat.setInt(6, patient.getNurseId());
			stat.executeUpdate();

			System.out.println("Info added successfully");

			con.close();
			stat.close();

		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}
	}

	public void delete(long ssn) {

		if (ssn != 0) {
			try {
				// Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
						"root", "Sommar15");
				String myStat = "delete FROM patient WHERE ssn=" + ssn;
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

	public void update(Patient patient) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "UPDATE patient SET firstName = ?, lastName= ?, password= ?, doctorId =?, nurseId=? WHERE ssn = ?";
			stat = con.prepareStatement(myStat);
			stat.setString(1, patient.getFirstName());
			stat.setString(2, patient.getLastName());
			stat.setString(3, patient.getPassword());
			stat.setInt(4, patient.getDoctorId());
			stat.setInt(5, patient.getNurseId());
			stat.setLong(6, patient.getSsn());
			stat.executeUpdate();

			con.close();
			stat.close();

		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}

	}

	public void updateJournal(Patient journal) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "UPDATE journal SET notes= ?, tests= ?, medicine= ? WHERE patient_ssn = ?";
			stat = con.prepareStatement(myStat);
			stat.setString(1, journal.getNotes());
			stat.setString(2, journal.getTests());
			stat.setLong(4, journal.getSsn());
			stat.setString(3, journal.getMedicine());
			stat.executeUpdate();

			con.close();
			stat.close();

		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}

	}

	public List<Patient> searchPatient(String search) {
		list = new ArrayList<>();

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "SELECT patient_ssn, rooms_roomId FROM waiting WHERE patient_ssn LIKE '%" + search + "%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while (rs.next()) {

				Patient usr = new Patient();
				usr.setSsn(rs.getLong("patient_ssn"));
				usr.setRooms_roomId(rs.getInt("rooms_roomId"));
				list.add(usr);
			}
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public List<Patient> searchPat(String search) {
		list = new ArrayList<>();

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "SELECT patient.ssn, notes, tests, medicine, testResult FROM patient JOIN journal ON journal.patient_ssn = patient.ssn WHERE patient.ssn LIKE '%"
					+ search + "%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while (rs.next()) {

				Patient usr = new Patient();
				usr.setSsn(rs.getLong("ssn"));
				usr.setNotes(rs.getString("notes"));
				usr.setTests(rs.getString("tests"));
				usr.setMedicine(rs.getString("medicine"));
				usr.setTestResult(rs.getString("testResult"));
				list.add(usr);
			}
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public void payBill(Patient patient) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "UPDATE patient SET bill = bill - ? WHERE ssn = ?";
			stat = con.prepareStatement(myStat);
			stat.setInt(1, patient.getBill());
			stat.setLong(2, patient.getSsn());
			stat.executeUpdate();

			con.close();
			stat.close();

		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}

	}

	public void updateTestResult(Patient patient) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "UPDATE journal SET testResult = ? WHERE patient_ssn = ?";
			stat = con.prepareStatement(myStat);
			stat.setString(1, patient.getTestResult());
			stat.setLong(2, patient.getSsn());
			stat.executeUpdate();

			con.close();
			stat.close();

		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}

	}

	public void updatePatientRemiss(Patient patient) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "UPDATE patient SET doctorId = ?, nurseId = ?  WHERE ssn = ?";
			stat = con.prepareStatement(myStat);
			stat.setInt(1, patient.getDoctorId());
			stat.setInt(2, patient.getNurseId());
			stat.setLong(3, patient.getSsn());
			stat.executeUpdate();

			con.close();
			stat.close();

		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}

	}

	public List<Patient> searchPatientRemiss(String search) {
		list = new ArrayList<>();

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "SELECT ssn, firstName, lastName, doctorId, nurseId FROM patient WHERE ssn LIKE '%" + search + "%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while (rs.next()) {

				Patient usr = new Patient();
				usr.setSsn(rs.getLong("ssn"));
				usr.setFirstName(rs.getString("firstName"));
				usr.setLastName(rs.getString("lastName"));
				usr.setDoctorId(rs.getInt("doctorId"));
				usr.setNurseId(rs.getInt("nurseId"));
				list.add(usr);
			}
			con.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}