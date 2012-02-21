package com.compannex.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CategoryTranslationDao;
import com.compannex.model.CategoryTranslation;

public class CategoryTranslationDaoImpl extends HibernateDaoSupport implements CategoryTranslationDao {

    @Override
    public CategoryTranslation getCategoryTranslationById(final int categoryId, final int languageId) {

    	Session session = null;
    	try {
    		session = getSession();
        	CategoryTranslation translation = null;
            Object obj = session.createQuery("from CategoryTranslation as catTr where catTr.categoryId= ? and catTr.languageId= ?")
                    .setInteger(0, categoryId).setInteger(1, languageId)
                    .uniqueResult();
            if (obj != null) {
                translation = (CategoryTranslation) obj;
            }

            return translation;
        } finally {
        	if (session != null) session.close();
        }
    }


    @Override
    public void saveCategoryTranslation(CategoryTranslation categoryTranslation) {
        getHibernateTemplate().save(categoryTranslation);
    }

    @Override
    public void editCategoryTranslation(CategoryTranslation categoryTranslation) {
        getHibernateTemplate().update(categoryTranslation);
    }

    @Override
    public void deleteCategoryTranslation(CategoryTranslation categoryTranslation) {
        getHibernateTemplate().delete(categoryTranslation);
    }
}
