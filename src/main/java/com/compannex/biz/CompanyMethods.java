
package com.compannex.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.dao.CompanyDao;
import com.compannex.dao.CompanyTranslationDao;
import com.compannex.dao.LoginDao;
import com.compannex.enums.CompanyStatusEnum;
import com.compannex.enums.LoginType;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.model.Company;
import com.compannex.model.CompanyCountry;
import com.compannex.model.CompanyTranslation;
import com.compannex.model.Login;

public class CompanyMethods {

	private CompanyDao companyDao;

	private LoginDao loginDao;

	private LoginMethods loginMethods;

	private CompanyTranslationDao companyTranslationDao;

	private CompanyCountryDao companyCountryDao;

	private PasswordMethods passwordMethods;

	public List<Company> getLatestClientCompanies() {

		List<Company> companys = new ArrayList<Company>();

		CompanyTranslation translation1 = new CompanyTranslation();
		translation1.setName("Web Goofy");
		translation1.setSlogan("Neque porro quisquam dolorem");
		Company comp1 = new Company();
		comp1.setTranslation(translation1);
		comp1.setLogo("images/page4-img1.jpg");
		comp1.setID(1);

		CompanyTranslation translation2 = new CompanyTranslation();
		translation2.setName("Creator Technologies");
		translation2.setSlogan("Neque porro quisquam dolorem");
		Company comp2 = new Company();
		comp2.setTranslation(translation2);
		comp2.setLogo("images/page4-img2.jpg");
		comp2.setID(2);

		CompanyTranslation translation3 = new CompanyTranslation();
		translation3.setName("World Class");
		translation3.setSlogan("Neque porro quisquam dolorem");
		Company comp3 = new Company();
		comp3.setTranslation(translation3);
		comp3.setLogo("images/page4-img3.jpg");
		comp3.setID(3);

		CompanyTranslation translation4 = new CompanyTranslation();
		translation4.setName("Spark Media");
		translation4.setSlogan("Neque porro quisquam dolorem");
		Company comp4 = new Company();
		comp4.setTranslation(translation4);
		comp4.setLogo("images/page4-img4.jpg");
		comp4.setID(4);

		CompanyTranslation translation5 = new CompanyTranslation();
		translation5.setName("World Peace");
		translation5.setSlogan("Neque porro quisquam dolorem");
		Company comp5 = new Company();
		comp5.setTranslation(translation5);
		comp5.setLogo("images/page4-img5.jpg");
		comp5.setID(5);

		CompanyTranslation translation6 = new CompanyTranslation();
		translation6.setName("Nature First");
		translation6.setSlogan("Neque porro quisquam dolorem");
		Company comp6 = new Company();
		comp6.setTranslation(translation6);
		comp6.setLogo("images/page4-img6.jpg");
		comp6.setID(6);

		companys.add(comp1);
		companys.add(comp2);
		companys.add(comp3);
		companys.add(comp4);
		companys.add(comp5);
		companys.add(comp6);

		return companys;
	}

	public List<Company> getAllClientCompanies(Integer industryID, Integer categoryID, final int languageID) {

		List<Company> companies = null;

		if (industryID != null) {
			companies = getCompanyDao().getCompaniesByIndustryId(industryID);
		} else if (categoryID!= null) {
			companies = getCompanyDao().getCompaniesByCategoryId(categoryID);
		} else {
			companies = getCompanyDao().getAllCompanies();
		}

		for (Company comp : companies) {
			comp.setTranslation(getCompanyTranslationDao().getCompanyTranslationByCompanyID(comp.getID(), languageID));
		}

		return companies;
	}

	public Company getCompanyByEmail(final String email, final int languageID) {

		Login login = getLoginDao().getLoginByEmail(email);

		Company comp = getCompanyDao().getCompanyByLoginId(login.getID());
		comp.setTranslation(getCompanyTranslationDao().getCompanyTranslationByCompanyID(comp.getID(), languageID));

		comp.setLogin(login);

		return comp;
	}

	public Company getCompanyByID(final int companyID, final int languageID) {

		Company comp = getCompanyDao().getCompanyById(companyID);
		comp.setTranslation(getCompanyTranslationDao().getCompanyTranslationByCompanyID(comp.getID(), languageID));

		return comp;
	}

	@Transactional
	public int addCompany(String name,
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
			String description, final int languageID) throws CompANNEXException {

		Company company = new Company();
		company.setAddedDate(new Date());
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

		getLoginMethods().addLogin(email, password, LoginType.COMPANY);

		return company.getID();
	}

	@Transactional
	public void editCompany(final int ID, String name,
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

	@Transactional
	public void editCompanyLogo(int partnerID, String logoPath) {
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

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public LoginMethods getLoginMethods() {
		return loginMethods;
	}

	public void setLoginMethods(LoginMethods loginMethods) {
		this.loginMethods = loginMethods;
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
