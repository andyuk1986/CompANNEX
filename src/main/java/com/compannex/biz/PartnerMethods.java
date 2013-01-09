package com.compannex.biz;

import java.util.Date;
import java.util.List;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.dao.CompanyDao;
import com.compannex.dao.CompanyTranslationDao;
import com.compannex.enums.CompanyStatusEnum;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.model.Company;
import com.compannex.model.CompanyCountry;
import com.compannex.model.CompanyTranslation;

public class PartnerMethods {
	
	private CompanyDao companyDao;
	
	private CompanyTranslationDao companyTranslationDao;
	
	private CompanyCountryDao companyCountryDao;
	
	private PasswordMethods passwordMethods;
	
	public int addNewPartner(String name,
			String email,
			String password,
			String category,
			String websiteurl,
			String telephone,
			String fax,
			String contactperson,
			String address,
			String city,
			String region,
			String zipCode,
			String country,
			String slogan,
			String employeecount,
			String description, final int languageID) {
		
		Company company = new Company();
		company.setAddedDate(new Date());
		company.setCategoryId(Integer.parseInt(category));
		company.setCreateDate(new Date());
		company.setEmail(email);
		company.setPassword(getPasswordMethods().encrypt(password));
        if(employeecount != null && !employeecount.isEmpty()) {
            try {
                company.setEmployeeCount(Integer.parseInt(employeecount));
            } catch(NumberFormatException ex) {
            }
        }
		company.setStatus(CompanyStatusEnum.ACTIVE.getValue());
		company.setTelephone(telephone);
		company.setFax(fax);
		company.setZipCode(zipCode);
		company.setWebsite(websiteurl);
		
		getCompanyDao().addCompany(company);
		
		CompanyTranslation companyTr = new CompanyTranslation();
		companyTr.setAddress(address);
		companyTr.setContacts(contactperson);
		companyTr.setDescription(description);
		companyTr.setLanguageID(languageID);
		companyTr.setName(name);
		companyTr.setSlogan(slogan);
		companyTr.setCity(city);
		companyTr.setRegion(region);
		companyTr.setCompanyID(company.getID());
		
		getCompanyTranslationDao().addCompanyTranslation(companyTr);
		
		CompanyCountry compCountr = new CompanyCountry();
		compCountr.setCompanyID(company.getID());
		compCountr.setCountryID(Integer.parseInt(country));
		compCountr.setCreateDate(new Date());
		
		getCompanyCountryDao().addCompanyCountry(compCountr);
		
		return company.getID(); 
	}
	
	public void editPartner(final int ID, String name,
			String category,
			String websiteurl,
			String telephone,
			String fax,
			String contactperson,
			String address,
			String zipcode,
			String city,
			String region,
			String country,
			String slogan,
			String employeecount,
			String description, final int languageID) {
		
		Company company = getCompanyDao().getCompanyById(ID);
		company.setCategoryId(Integer.parseInt(category));
		company.setCreateDate(new Date());
        if(employeecount != null && !employeecount.isEmpty()) {
            try {
                company.setEmployeeCount(Integer.parseInt(employeecount));
            } catch(NumberFormatException ex) {
            }
        }
		company.setStatus(CompanyStatusEnum.ACTIVE.getValue());
		company.setTelephone(telephone);
		company.setFax(fax);
		company.setZipCode(zipcode);
		company.setWebsite(websiteurl);
		
		getCompanyDao().editCompany(company);
		
		CompanyTranslation companyTr = getCompanyTranslationDao().getCompanyTranslationByCompanyID(ID, languageID);
		companyTr.setAddress(address);
		companyTr.setCity(city);
		companyTr.setRegion(region);
		companyTr.setContacts(contactperson);
		companyTr.setDescription(description);
		companyTr.setLanguageID(languageID);
		companyTr.setName(name);
		companyTr.setSlogan(slogan);
		
		getCompanyTranslationDao().editCompanyTranslation(companyTr);
		
		CompanyCountry compCountr = getCompanyCountryDao().getCompanyCountriesByCompany(ID).get(0);
		compCountr.setCountryID(Integer.parseInt(country));
		compCountr.setCreateDate(new Date());
		
		getCompanyCountryDao().editCompanyCountry(compCountr);
		
	}
	
	public void editPartnerLogo(int partnerID, String logoPath) {
		Company comp = getCompanyDao().getCompanyById(partnerID);
		comp.setLogo(logoPath);
		getCompanyDao().editCompany(comp);
	}
	
	public int getCountryIDByCompanyID(int companyID) throws CompANNEXException {
		List<CompanyCountry> countries = getCompanyCountryDao().getCompanyCountriesByCompany(companyID);
		if (countries == null || countries.size() == 0) throw new CompANNEXException("No Countries are registered for company " + companyID);
		
		return countries.get(0).getCountryID();
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

	public PasswordMethods getPasswordMethods() {
		return passwordMethods;
	}

	public void setPasswordMethods(PasswordMethods passwordMethods) {
		this.passwordMethods = passwordMethods;
	}
}
