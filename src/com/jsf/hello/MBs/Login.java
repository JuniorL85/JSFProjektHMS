package com.jsf.hello.MBs;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Login {

	private String username;
	private String password;
	
	public Login(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String LoginOK(){
		if(username.equals("hej") && password.equals("hej")){
			return "welcomePage";
		}
		else {
			System.out.println("Wrong username or password!!!!");
		}
		return "hej";
	}
}
