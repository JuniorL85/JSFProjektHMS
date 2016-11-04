package com.jsf.hello.MBs;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.EJBs.RoomEJB;

@ManagedBean(name = "room")
@SessionScoped
public class RoomMB {

	RoomEJB roomEjb = new RoomEJB();

	private int roomId;
	private String roomType;
	private int roomStatus;
	private int receptionistId;
	private String search;
	private int patient_ssn;

	private int maxCapacity;
	private int capacityNow;

	List<String> roomTypeOptions;

	public RoomMB() {
		// populate list of StaffRole
		roomTypeOptions = new ArrayList<>();

		roomTypeOptions.add("Private Room");
		roomTypeOptions.add("General Ward");
		roomTypeOptions.add("Examination Room");
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

	public int getPatient_ssn() {
		return patient_ssn;
	}

	public void setPatient_ssn(int patient_ssn) {
		this.patient_ssn = patient_ssn;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomNumber) {
		this.roomId = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public List<String> getRoomTypeOptions() {
		return roomTypeOptions;
	}

	public void setRoomTypeOptions(List<String> roomTypeOptions) {
		this.roomTypeOptions = roomTypeOptions;
	}

	public int getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(int roomStatus) {
		this.roomStatus = roomStatus;
	}

	public int getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<RoomMB> getRoomList() {
		return roomEjb.getRoomList();
	}

	public void add() {
		roomEjb.add(this);
	}

	public void delete(int roomId) {
		roomEjb.delete(roomId);
	}

	public void roomById(int roomId, String roomType, int maxCapacity) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.maxCapacity = maxCapacity;
	}

	public void updateRoom() {
		roomEjb.update(this);
	}

	public List<RoomMB> searchRoom() {
		return roomEjb.searchRoom(search);
	}
}
