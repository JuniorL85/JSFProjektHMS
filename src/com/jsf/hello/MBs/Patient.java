package com.jsf.hello.MBs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import com.jsf.hello.EJBs.PatientEJB;

@ManagedBean(name = "patient")
@SessionScoped
public class Patient {

	PatientEJB patientEjb = new PatientEJB();

	String search;

	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	int ssn;
	String firstName;
	String lastName;
	String userName;
	String password;

	String tests;
	String medicine;



	String notes;
	int doctorId;
	int nurseId;
	int testId;
	int roomId;
	int receptionistId;
	int journalId;

	public void patientById(int ssn, String firstName, String lastName, String userName, String password, int doctorId,
			int nurseId) {
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.doctorId = doctorId;
		this.nurseId = nurseId;
		// this.testId = testId;
		// this.roomId = roomId;
		// this.receptionistId = receptionistId;
		// this.journalId = journalId;
		// this.notes = notes;
	}

	public void journalById(int ssn, String notes, String tests, String medicine) {
		this.ssn = ssn;
		this.tests = tests;
		//this.journalId = journalId;
		this.notes = notes;
		this.medicine = medicine;
		//this.doctorId = doctorId;
		//this.nurseId = nurseId;
	}
	
	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
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

	public List<Patient> searchPat() {
		return patientEjb.searchPat(search);
	}

	public List<Patient> getPatList() {
		return patientEjb.getPatList();
	}

	public void add() {
		patientEjb.add(this);
	}

	public void delete(int ssn) {
		patientEjb.delete(ssn);
	}

	public void updatePatient() {
		System.out.println("in update patient.. before calling update");
		patientEjb.update(this);
		System.out.println("in update patient.. after calling update");
	}

	public void updateJournal() {
		System.out.println("in update journal.. before calling update");
		patientEjb.updateJournal(this);
		System.out.println("in update journal.. after calling update");
	}
}
