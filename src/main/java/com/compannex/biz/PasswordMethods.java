package com.compannex.biz;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.compannex.dao.CompanyDao;
import com.compannex.mail.MailService;
import com.compannex.model.Company;

public class PasswordMethods {
	
	private CompanyDao companyDao;
	
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
		Company comp = getCompanyDao().getCompanyByEmail(email);
		
		String passtoken = generateSecureToken(email);
		
		comp.setPasswordToken(passtoken);
		comp.setPasswordTokenDate(new Date());
		
		getCompanyDao().editCompany(comp);
		
		getMailService().resetPassword(email);
	}
	
	public void resetPasswordToken(String email) {
		Company comp = getCompanyDao().getCompanyByEmail(email);
		
		
		comp.setPasswordToken(null);
		comp.setPasswordTokenDate(null);
		
		getCompanyDao().editCompany(comp);		
	}
	
	public boolean isPasswordTokenValid(String email, int id, String token) {
		Company comp = getCompanyDao().getCompanyByPasswordToken(email, id, token);
		if (comp != null && comp.getPasswordTokenDate() != null && ((int)( (new Date().getTime() - comp.getPasswordTokenDate().getTime()) 
                / (1000 * 60 * 60 * 24) )) < 1) {
			return true;
		}
		return false;
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
}
