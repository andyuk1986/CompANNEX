package com.compannex.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.IndustryTranslationDao;
import com.compannex.model.IndustryTranslation;

public class IndustryTranslationDaoImpl extends HibernateDaoSupport implements IndustryTranslationDao {

    @Override
    public IndustryTranslation getIndustryTranslation(final int industryID, final int languageID) {
    	Session session = null;
    	try {
	    	IndustryTranslation translation = null;
	        session = getSession();
	        Object obj = session.createQuery("from IndustryTranslation as indTr where indTr.industryID= ? and indTr.languageID= ?").setCacheable(true)
	                .setInteger(0, industryID).setInteger(1, languageID)
	                .uniqueResult();
	        if (obj != null) {
	            translation = (IndustryTranslation) obj;
	        }
	
	        return translation;
        } finally {
        	if (session != null) session.close();
        }
    }

    @Override
    public void saveIndustryTranslation(IndustryTranslation industryTranslation) {
        getHibernateTemplate().save(industryTranslation);
    }

    @Override
    public void editIndustryTranslation(IndustryTranslation industryTranslation) {
        getHibernateTemplate().update(industryTranslation);
    }

    @Override
    public void deleteIndustryTranslation(IndustryTranslation industryTranslation) {
        getHibernateTemplate().delete(industryTranslation);
    }
}
