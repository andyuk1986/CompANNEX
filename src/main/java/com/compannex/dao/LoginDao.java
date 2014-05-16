package com.compannex.dao;

import com.compannex.model.Login;

public interface LoginDao {

	public Login getLoginById(final int loginId);
	
	public Login getLoginByEmail(final String email);
	
	public Login getLoginByEmailAndToken(final String email, final String token);
	
	public Login getLoginByPasswordToken(String email, int id, String passwordToken);

	public void addLogin(final Login login);

	public void editLogin(final Login login);

	public void deleteLogin(final Login login);

}
