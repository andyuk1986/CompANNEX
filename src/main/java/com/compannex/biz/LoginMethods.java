package com.compannex.biz;

import com.compannex.dao.CompanyDao;
import com.compannex.dao.ConsultantDao;
import com.compannex.dao.LoginDao;
import com.compannex.enums.LoginType;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.model.Login;
import com.compannex.util.StringUtil;

public class LoginMethods {
	
	private CompanyDao companyDao;
	
	private LoginDao loginDao;
	
	private ConsultantDao consultantDao;
	
	private PasswordMethods passwordMethods;
	
	public void addLogin(String email, String password, LoginType loginType) throws CompANNEXException {
		
		Login login = getLoginDao().getLoginByEmail(email);
		
		if (login != null) throw new CompANNEXException("Email address entered already exists.");
		
		login = new Login();
		login.setEmail(email);
		login.setPassword(getPasswordMethods().encrypt(password));
		
		getLoginDao().addLogin(login);
		
	}

	public boolean checkLogin(String email, String password, LoginType loginType) {
		
		Login login = getLoginDao().getLoginByEmail(email);
		if (login == null || !StringUtil.equals(login.getPassword(), passwordMethods.encrypt(password))) return false;
		
		if (LoginType.COMPANY.equals(loginType)) {
			if (getCompanyDao().getCompanyByLoginId(login.getID()) != null) return true;
		} else if (LoginType.CONSULTANT.equals(loginType)) {
			if (getConsultantDao().getConsultantByLoginId(login.getID()) != null) return true;
		}

		return false;
	}

	public boolean checkLogin(String email) {
		
		Login login = getLoginDao().getLoginByEmail(email);
		
		if (login != null) return true;
		
		return false;
	}

	
	public String regenerateToken(String email) {
		Login login = getLoginDao().getLoginByEmail(email);
		
		String newtoken = passwordMethods.generateSecureToken(email);
		
		login.setToken(newtoken);
		
		getLoginDao().editLogin(login);
		
		return newtoken;
	}
	
	public boolean isTokenValid(String email, String token) {
		return getLoginDao().getLoginByEmailAndToken(email, token) != null; 
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public ConsultantDao getConsultantDao() {
		return consultantDao;
	}

	public void setConsultantDao(ConsultantDao consultantDao) {
		this.consultantDao = consultantDao;
	}
	
	public PasswordMethods getPasswordMethods() {
		return passwordMethods;
	}

	public void setPasswordMethods(PasswordMethods passwordMethods) {
		this.passwordMethods = passwordMethods;
	}
	
}
