package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CountryDao;
import com.compannex.model.Country;

/**
 * ReachLibrary DAO Hibernate Implementation.
 */
public class CountryDaoImpl extends HibernateDaoSupport implements CountryDao {

	private static Logger logger = Logger.getLogger(CountryDaoImpl.class);

	@Override
	public Country getCountryById(final int id) {
		Country doc = null;
		Object obj = getHibernateTemplate().load(Country.class, id);
		if (obj != null) {
			doc = (Country) obj;
		}
		return doc;
	}

	@Override
	public List<Country> getAllCountries() {
		List<Country> inds = getHibernateTemplate().loadAll(Country.class);

		if (inds == null)
			inds = new ArrayList<Country>();

		return inds;
	}

}
