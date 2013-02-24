package com.compannex.form;

import com.compannex.iface.SecureEditable;

public class ForgotPassword implements SecureEditable {

	private String email;
	
	private String sessionID;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@Override
	public String getSessionID() {
		return sessionID;
	}
	
}
