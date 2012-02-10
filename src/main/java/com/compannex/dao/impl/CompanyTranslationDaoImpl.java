package com.compannex.dao.impl;

import com.compannex.dao.CompanyTranslationDao;
import com.compannex.dao.CompanyTranslationDao;
import com.compannex.model.Category;
import com.compannex.model.CompanyTranslation;
import com.compannex.model.Industry;
import com.compannex.model.Language;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CompanyTranslationDaoImpl extends HibernateDaoSupport implements CompanyTranslationDao {
    @Override
    public CompanyTranslation getCompanyTranslationById(final int categoryId) {
        CompanyTranslation doc = null;
        Object obj = getHibernateTemplate().load(CompanyTranslation.class, categoryId);
        if (obj != null) {
            doc = (CompanyTranslation) obj;
        }
        return doc;
    }

    @Override
    public CompanyTranslation getCompanyTranslationByName(final String categoryName) {
        CompanyTranslation translation = null;
        Object obj = getSession().createQuery("from CompanyTranslation as catTr where catTr.name = ?")
                .setString(0, categoryName)
                .uniqueResult();
        if (obj != null) {
            translation = (CompanyTranslation) obj;
        }

        return translation;
    }

    @Override
    public CompanyTranslation getCompanyTranslationByLanguage(final Language language) {
        CompanyTranslation translation = null;
        Object obj = getSession().createQuery("from CompanyTranslation as catTr where catTr.languageId= ?")
                .setInteger(0, language.getID())
                .uniqueResult();
        if (obj != null) {
            translation = (CompanyTranslation) obj;
        }

        return translation;
    }

    @Override
    public void saveCompanyTranslation(CompanyTranslation companyTranslation) {
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
