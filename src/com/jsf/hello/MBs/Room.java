package com.jsf.hello.MBs;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Room {

	private int roomNumber;
	private String roomType;
	
	
	List<String> roomTypeOptions;
	
	public Room(){
		//populate list of StaffRole
				roomTypeOptions = new ArrayList<>();
				
				roomTypeOptions.add("Private Room");
				roomTypeOptions.add("General Ward");
				roomTypeOptions.add("Examination Room");
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
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
	
	public String getRoomValues(){
		System.out.println("Room Type =" + roomTypeOptions);
		return "submittedRoomInfo.xhtml";
	}
}
