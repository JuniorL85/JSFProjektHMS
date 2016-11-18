package com.jsf.hello.MBs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import com.jsf.hello.EJBs.PatientEJB;
import com.jsf.hello.MBs.Login;

@ManagedBean(name = "patient")
@SessionScoped
public class Patient {

	PatientEJB patientEjb = new PatientEJB();
	Login loginmb = new Login();

	String search;

	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	private long ssn;
	private String firstName;
	private String lastName;
	private String password;
	private Timestamp checkIn;

	private String tests;
	private String medicine;
	private String testResult;


	private int rooms_roomId; 

	private String notes;
	private int bill;
	private int doctorId;
	private int nurseId;
	private int testId;
	private int roomId;
	private int receptionistId;
	private int journalId;

	public void patientById(long ssn, String firstName, String lastName, String password, int doctorId,
			int nurseId) {
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.doctorId = doctorId;
		this.nurseId = nurseId;
		// this.testId = testId;
		// this.roomId = roomId;
		// this.receptionistId = receptionistId;
		// this.journalId = journalId;
		// this.notes = notes;
	}

	public void journalById(long ssn, String notes, String tests, String medicine) {
		this.ssn = ssn;
		this.tests = tests;
		//this.journalId = journalId;
		this.notes = notes;
		this.medicine = medicine;
		//this.doctorId = doctorId;
		//this.nurseId = nurseId;
	}
	public void billById(long ssn, int bill){
		this.ssn = ssn;
		this.bill = bill;
	}
	public void medicineById(long ssn, String medicine){
		this.ssn = ssn;
		this.medicine = medicine;
	}
	public void testResultById(long ssn, String tests, String testResult){
		this.ssn = ssn;
		this.tests = tests;
		this.testResult = testResult;
	}
	public Timestamp getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Timestamp checkIn) {
		this.checkIn = checkIn;
	}

	public int getRooms_roomId() {
		return rooms_roomId;
	}

	public void setRooms_roomId(int rooms_roomId) {
		this.rooms_roomId = rooms_roomId;
	}
	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
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

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
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
		clear();
	}

	public void delete(long ssn) {
		patientEjb.delete(ssn);
	}
	public List<Patient> searchPatient(){
		return patientEjb.searchPatient(search);
	}

	public void updatePatient() {
		patientEjb.update(this);
	}

	public void updateJournal() {
		patientEjb.updateJournal(this);
	}
	public void payBill(){
		patientEjb.payBill(this);
	}
	public void updateTestResult(){
		patientEjb.updateTestResult(this);
	}
	public void clear(){
		setSsn(0);
		setFirstName(null);
		setLastName(null);
		setPassword(null);
		setDoctorId(0);
		setNurseId(0);
	}
}
