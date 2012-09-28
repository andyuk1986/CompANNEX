package com.compannex.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.model.Category;
import com.compannex.model.CompanyCountry;

public class CompanyCountryDaoImpl extends HibernateDaoSupport implements
		CompanyCountryDao {

	@Override
	public CompanyCountry getCompanyCountryByID(int ID) {
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
	public List<CompanyCountry> getCompanyCountriesByCompany(final int companyID) {

		Session session = null;
		try {
			session = getSession();
			List<CompanyCountry> countries = null;
			Object obj = session
					.createQuery(
							"from CompanyCountry as cc where cc.companyID= ?")
					.setInteger(0, companyID).list();
			if (obj != null) {
				countries = (List<CompanyCountry>) obj;
			}

			return countries;
		} finally {
			if (session != null)
				session.close();
		}
	}

}
