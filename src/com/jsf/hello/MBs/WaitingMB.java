package com.jsf.hello.MBs;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import com.jsf.hello.EJBs.WaitingEJB;

@ManagedBean(name="waiting")
@SessionScoped
public class WaitingMB {

	WaitingEJB waitingEjb = new WaitingEJB();
	private int waitingId;
	private int receptionstId;
	private long patient_ssn;
	private int rooms_roomId;
	private String roomType; 
	private int maxCapacity;
	private int capacityNow;
	private String search;
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public int getCapacityNow() {
		return capacityNow;
	}
	public void setCapacityNow(int capacityNow) {
		this.capacityNow = capacityNow;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getWaitingId() {
		return waitingId;
	}
	public void setWaitingId(int waitingId) {
		this.waitingId = waitingId;
	}
	public int getReceptionstId() {
		return receptionstId;
	}
	public void setReceptionstId(int receptionstId) {
		this.receptionstId = receptionstId;
	}
	public long getPatient_ssn() {
		return patient_ssn;
	}
	public void setPatient_ssn(long patient_ssn) {
		this.patient_ssn = patient_ssn;
	}
	public int getRooms_roomId() {
		return rooms_roomId;
	}
	public void setRooms_roomId(int rooms_roomId) {
		this.rooms_roomId = rooms_roomId;
	} 
	public List<WaitingMB> searchPatroom() {
		return waitingEjb.searchPatroom(search);
	}
	public List<WaitingMB> getWaitList(){
		return waitingEjb.getWaitList();
	}
	public List<WaitingMB> getPatientSsnList(){
		return waitingEjb.getPatientSsnList();
	}
	public void waitingById(long patient_ssn, int rooms_roomId) {
		this.patient_ssn = patient_ssn;
		this.rooms_roomId = rooms_roomId;
		
	}
	public void UpdateWaitPat() {
		waitingEjb.update(this);
	}
}
