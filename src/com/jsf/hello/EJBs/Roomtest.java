package com.jsf.hello.EJBs;

import java.io.Serializable;



public class Roomtest implements Serializable {

	private static final long serialVersionUID = 1L;
	private int roomId;
	private String roomType;
	private int maxCapacity;
	private int capacityNow;
	private Long id; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Roomtest(int roomId, String roomType, int maxCapacity, int capacityNow) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.maxCapacity = maxCapacity;
		this.capacityNow = capacityNow;
	}

	public Roomtest() {
		// TODO Auto-generated constructor stub
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
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
	@Override
    public boolean equals(Object other) {
        return (other instanceof Roomtest) && (id != null)
            ? id.equals(((Roomtest) other).id)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
            ? (this.getClass().hashCode() + id.hashCode())
            : super.hashCode();
    }
    
	@Override
	public String toString() {
		return String.format("Room: [%d, %s, %d, %d]", roomId, roomType, maxCapacity, capacityNow);
	}

}