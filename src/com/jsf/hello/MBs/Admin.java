package com.jsf.hello.MBs;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Admin {

	private boolean showPopUp;
	private boolean showPopUp1;
	
	//create no-arg constructor
	public Admin(){

	}		
	public boolean isShowPopUp() {
		return showPopUp;
	}

	public void setShowPopUp(boolean showPopUp) {
		this.showPopUp = showPopUp;
	}

	public boolean isShowPopUp1() {
		return showPopUp1;
	}
	public void setShowPopUp1(boolean showPopUp1) {
		this.showPopUp1 = showPopUp1;
	}
	public void show(){
		showPopUp=true;
	}
	public void showUpdate(){
		showPopUp1=true;
	}
	public void hide(){
		showPopUp=false;
	}
	public void hideUpdate(){
		showPopUp1=false;
	}
	
}
