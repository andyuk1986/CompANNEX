package com.compannex.biz;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.compannex.dao.CompanyDao;
import com.compannex.dao.ConsultantDao;
import com.compannex.dao.LoginDao;
import com.compannex.mail.MailService;
import com.compannex.model.Company;
import com.compannex.model.Login;

public class PasswordMethods {
	
	private LoginDao loginDao;
	
	private CompanyDao companyDao;
	
	private ConsultantDao consultantDao;
	
	private MailService mailService;
	
	public String encrypt(String plainText) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1"); 
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		try {
			md.update(plainText.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return null;
		}

		String hash = (new Base64()).encodeAsString(md.digest());
		return hash;
	}
	
	public String generateSecureToken(String email) {
		return (new Base64()).encodeAsString((email+UUID.randomUUID().toString()).getBytes());
	}
	
	public void changePassword(int companyID, String newpassword) {
		Company comp = getCompanyDao().getCompanyById(companyID);
		
		comp.setPassword(encrypt(newpassword));
		
		getCompanyDao().editCompany(comp);
	}
	
	public void resetPassword(String email) {
		Login login = getLoginDao().getLoginByEmail(email);
		
		String passtoken = generateSecureToken(email);
		
		login.setPasswordToken(passtoken);
		login.setPasswordTokenDate(new Date());
		
		getLoginDao().editLogin(login);
		
		getMailService().resetPassword(email, login.getID(), passtoken);
	}
	
	public void resetPasswordToken(String email) {
		Login login = getLoginDao().getLoginByEmail(email);
		
		
		login.setPasswordToken(null);
		login.setPasswordTokenDate(null);
		
		getLoginDao().editLogin(login);		
	}
	
	public boolean isPasswordTokenValid(String email, int id, String token) {
		
		Login login = getLoginDao().getLoginByPasswordToken(email, id, token);
		if (login != null && login.getPasswordTokenDate() != null && ((int)( (new Date().getTime() - login.getPasswordTokenDate().getTime()) 
                / (1000 * 60 * 60 * 24) )) < 1) {
			return true;
		}
		return false;
	}
	
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public ConsultantDao getConsultantDao() {
		return consultantDao;
	}

	public void setConsultantDao(ConsultantDao consultantDao) {
		this.consultantDao = consultantDao;
	}
	
	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
}
