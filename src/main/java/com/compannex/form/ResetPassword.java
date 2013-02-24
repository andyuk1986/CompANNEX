package com.compannex.form;

import com.compannex.iface.SecureEditable;

public class ResetPassword implements SecureEditable {

	private String sessionID;
	
	private String email;
	
	private int id;
	
	private String token;

	private String newpassword;
	
	private String renewpassword;

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getRenewpassword() {
		return renewpassword;
	}

	public void setRenewpassword(String renewpassword) {
		this.renewpassword = renewpassword;
	}

	@Override
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@Override
	public String getSessionID() {
		return sessionID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
