package com.compannex.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CountryTranslationDao;
import com.compannex.model.CountryTranslation;

public class CountryTranslationDaoImpl extends HibernateDaoSupport implements CountryTranslationDao {

    @Override
    public CountryTranslation getCountryTranslation(final int countryID, final int languageID) {
    	Session session = null;
    	try {
	    	CountryTranslation translation = null;
	        session = getSession();
	        Object obj = session.createQuery("from CountryTranslation as countrTr where countrTr.countryId= ? and countrTr.languageId= ?")
	                .setInteger(0, countryID).setInteger(1, languageID)
	                .uniqueResult();
	        if (obj != null) {
	            translation = (CountryTranslation) obj;
	        }
	
	        return translation;
        } finally {
        	if (session != null) session.close();
        }
    }
}
