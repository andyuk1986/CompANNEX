package com.compannex.biz;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.dao.CompanyDao;
import com.compannex.dao.CompanyTranslationDao;

public class PartnerMethods {
	
	private static PartnerMethods instance = null;
	
	private CompanyDao companyDao;
	
	private CompanyTranslationDao companyTrDao;
	
	private CompanyCountryDao companyCountryDao;
	
	private PartnerMethods() {	
	}
	
	public static PartnerMethods getInstance() {
		if (instance == null) instance = new PartnerMethods();
		return instance;
	}
	
	public void addNewPartner() {
		
	}
		
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
	public CompanyTranslationDao getCompanyTranslationDao() {
		return companyTrDao;
	}

	public void setCompanyTranslationDao(CompanyTranslationDao companyTrDao) {
		this.companyTrDao = companyTrDao;
	}
	
	public CompanyCountryDao getCompanyCountryDao() {
		return companyCountryDao;
	}

	public void setCompanyCountryDao(CompanyCountryDao companyCountryDao) {
		this.companyCountryDao = companyCountryDao;
	}
}
