package com.compannex.biz;

import java.util.Date;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.dao.CompanyDao;
import com.compannex.dao.CompanyTranslationDao;
import com.compannex.enums.CompanyStatusEnum;
import com.compannex.model.Company;
import com.compannex.model.CompanyCountry;
import com.compannex.model.CompanyTranslation;

public class PartnerMethods {
	
	private CompanyDao companyDao;
	
	private CompanyTranslationDao companyTranslationDao;
	
	private CompanyCountryDao companyCountryDao;
	
	
	public void addNewPartner(String name,
			String email,
			String password,
			String category,
			String websiteurl,
			String telephone,
			String fax,
			String contactperson,
			String address,
			String country,
			String slogan,
			String employeecount,
			String description) {
		
		Company company = new Company();
		company.setAddedDate(new Date());
		company.setCategoryId(Integer.parseInt(category));
		company.setCreateDate(new Date());
		company.setEmail(email);
		company.setPassword(password);
		company.setEmploymentCount(Integer.parseInt(employeecount));
		company.setStatus(CompanyStatusEnum.ACTIVE.getValue());
		company.setTelephone(telephone);
		company.setFax(fax);
		company.setWebsite(websiteurl);
		
		getCompanyDao().addCompany(company);
		
		CompanyTranslation companyTr = new CompanyTranslation();
		companyTr.setAddress(address);
		companyTr.setContacts(contactperson);
		companyTr.setDescription(description);
		companyTr.setLanguageId(1);
		companyTr.setName(name);
		companyTr.setSlogan(slogan);
		companyTr.setCompanyId(company.getID());
		
		getCompanyTranslationDao().addCompanyTranslation(companyTr);
		
		CompanyCountry compCountr = new CompanyCountry();
		compCountr.setCompanyId(company.getID());
		compCountr.setCountryId(Integer.parseInt(country));
		compCountr.setCreateDate(new Date());
		
		getCompanyCountryDao().addCompanyCountry(compCountr);
		
	}
		
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
	public CompanyTranslationDao getCompanyTranslationDao() {
		return companyTranslationDao;
	}

	public void setCompanyTranslationDao(CompanyTranslationDao companyTranslationDao) {
		this.companyTranslationDao = companyTranslationDao;
	}
	
	public CompanyCountryDao getCompanyCountryDao() {
		return companyCountryDao;
	}

	public void setCompanyCountryDao(CompanyCountryDao companyCountryDao) {
		this.companyCountryDao = companyCountryDao;
	}
}
