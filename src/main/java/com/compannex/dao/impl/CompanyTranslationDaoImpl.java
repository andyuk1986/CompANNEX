package com.compannex.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CompanyTranslationDao;
import com.compannex.model.CompanyTranslation;

public class CompanyTranslationDaoImpl extends HibernateDaoSupport implements
		CompanyTranslationDao {
	
	@Override
	public CompanyTranslation getCompanyTranslationByCompanyID(final int companyId, final int languageId) {

    	Session session = null;
    	try {
    		session = getSession();
        	CompanyTranslation translation = null;
            Object obj = session.createQuery("from CompanyTranslation as compTr where compTr.companyID= ? and compTr.languageID= ?").setCacheable(true)
                    .setInteger(0, companyId).setInteger(1, languageId)
                    .uniqueResult();
            if (obj != null) {
                translation = (CompanyTranslation) obj;
            }

            return translation;
        } finally {
        	if (session != null) session.close();
        }
	}

	@Override
	public void addCompanyTranslation(CompanyTranslation companyTranslation) {
		getHibernateTemplate().save(companyTranslation);
	}

	@Override
	public void editCompanyTranslation(CompanyTranslation companyTranslation) {
		getHibernateTemplate().update(companyTranslation);
	}

	@Override
	public void deleteCompanyTranslation(CompanyTranslation companyTranslation) {
		getHibernateTemplate().delete(companyTranslation);
	}
}
