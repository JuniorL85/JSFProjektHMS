package com.jsf.hello.MBs;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Admin {

	private boolean showPopUp;
	
	//create no-arg constructor
	public Admin(){

	}		
	public boolean isShowPopUp() {
		return showPopUp;
	}

	public void setShowPopUp(boolean showPopUp) {
		this.showPopUp = showPopUp;
	}

	public void show(){
		showPopUp=true;
	}
	public void hide(){
		showPopUp=false;
	}
	
}
