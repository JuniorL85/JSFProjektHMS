package com.jsf.hello.MBs;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.EJBs.MedicalStoreEJB;

@ManagedBean(name = "medicalstore")
@SessionScoped
public class MedicalStoreMB {

	MedicalStoreEJB medicalstoreejb = new MedicalStoreEJB();
	
	private int medicalStoreId;
	private String Medicine;
	private int quantity;
	private float price;
	
	public MedicalStoreMB(){
		
	}

	public int getMedicalStoreId() {
		return medicalStoreId;
	}

	public void setMedicalStoreId(int medicalStoreId) {
		this.medicalStoreId = medicalStoreId;
	}

	public String getMedicine() {
		return Medicine;
	}

	public void setMedicine(String medicine) {
		Medicine = medicine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	public List<MedicalStoreMB> getMedStoreList(){
		return medicalstoreejb.getMedStoreList();
	}
	public List<MedicalStoreMB> getMedicineList(){
		return medicalstoreejb.getMedicineList();
	}
}
