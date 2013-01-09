package com.compannex.biz;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.compannex.dao.CompanyDao;
import com.compannex.model.Company;

public class PasswordMethods {
	
	private CompanyDao companyDao;
	
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
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	

}
