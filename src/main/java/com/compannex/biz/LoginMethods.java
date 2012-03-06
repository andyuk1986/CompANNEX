package com.compannex.biz;

import com.compannex.dao.CompanyDao;
import com.compannex.model.Company;
import com.compannex.util.StringUtil;

public class LoginMethods {
	
	private CompanyDao companyDao;

	public boolean login(String email, String password) {
		
		Company comp = getCompanyDao().getCompanyByEmail(email);
		
		if (comp != null && StringUtil.equals(comp.getPassword(), password)) return true;
		
		return false;
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}
