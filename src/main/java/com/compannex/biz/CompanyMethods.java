
package com.compannex.biz;

import java.util.ArrayList;
import java.util.List;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.dao.CompanyDao;
import com.compannex.dao.CompanyTranslationDao;
import com.compannex.model.Company;
import com.compannex.model.CompanyTranslation;

public class CompanyMethods {
	
	private CompanyDao companyDao;
	
	private CompanyTranslationDao companyTranslationDao;
	
	private CompanyCountryDao companyCountryDao;
	
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
		
		Company comp = getCompanyDao().getCompanyByEmail(email);
		comp.setTranslation(getCompanyTranslationDao().getCompanyTranslationByCompanyID(comp.getID(), languageID));
		
		return comp;
	}
	
	public Company getCompanyByID(final int companyID, final int languageID) {
		
		Company comp = getCompanyDao().getCompanyById(companyID);
		comp.setTranslation(getCompanyTranslationDao().getCompanyTranslationByCompanyID(comp.getID(), languageID));
		
		return comp;
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
