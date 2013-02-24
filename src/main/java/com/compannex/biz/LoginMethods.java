package com.compannex.biz;

import com.compannex.dao.CompanyDao;
import com.compannex.model.Company;
import com.compannex.util.StringUtil;

public class LoginMethods {
	
	private CompanyDao companyDao;
	
	private PasswordMethods passwordMethods;

	public boolean checkLogin(String email, String password) {
		
		Company comp = getCompanyDao().getCompanyByEmail(email);
		
		if (comp != null && StringUtil.equals(comp.getPassword(), passwordMethods.encrypt(password))) return true;
		
		return false;
	}

	public boolean checkLogin(String email) {
		
		Company comp = getCompanyDao().getCompanyByEmail(email);
		
		if (comp != null) return true;
		
		return false;
	}

	
	public String regenerateToken(String email) {
		Company comp = getCompanyDao().getCompanyByEmail(email);
		
		String newtoken = passwordMethods.generateSecureToken(email);
		
		comp.setToken(newtoken);
		
		getCompanyDao().editCompany(comp);
		
		return newtoken;
	}
	
	public boolean isTokenValid(String email, String token) {
		return getCompanyDao().getCompanyByEmailAndToken(email, token) != null; 
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public PasswordMethods getPasswordMethods() {
		return passwordMethods;
	}

	public void setPasswordMethods(PasswordMethods passwordMethods) {
		this.passwordMethods = passwordMethods;
	}
	
}
