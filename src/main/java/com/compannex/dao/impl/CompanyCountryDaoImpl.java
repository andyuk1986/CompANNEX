package com.compannex.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.model.Company;
import com.compannex.model.CompanyCountry;
import com.compannex.model.Country;

public class CompanyCountryDaoImpl extends HibernateDaoSupport implements
		CompanyCountryDao {

	@Override
	public CompanyCountry getCompanyCountryById(int id) {
		return null;
	}

	@Override
	public void addCompanyCountry(CompanyCountry companyCountry) {
		getHibernateTemplate().save(companyCountry);
	}

	@Override
	public void editCompanyCountry(CompanyCountry companyCountry) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

	@Override
	public void deleteCompanyCountry(CompanyCountry companyCountry) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

	@Override
	public CompanyCountry getCompanyCountryByCompany(Company company) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public CompanyCountry getCompanyCountryByCountry(Country country) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}
}
