package com.compannex.dao.impl;

import com.compannex.dao.CategoryTranslationDao;
import com.compannex.model.Category;
import com.compannex.model.CategoryTranslation;
import com.compannex.model.Industry;
import com.compannex.model.Language;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryTranslationDaoImpl extends HibernateDaoSupport implements CategoryTranslationDao {
    @Override
    public CategoryTranslation getCategoryTranslationById(final int categoryId) {
        CategoryTranslation doc = null;
        Object obj = getHibernateTemplate().load(CategoryTranslation.class, categoryId);
        if (obj != null) {
            doc = (CategoryTranslation) obj;
        }
        return doc;
    }

    @Override
    public CategoryTranslation getCategoryTranslationByName(final String categoryName) {
        CategoryTranslation translation = null;
        Object obj = getSession().createQuery("from CategoryTranslation as catTr where catTr.name = ?")
                .setString(0, categoryName)
                .uniqueResult();
        if (obj != null) {
            translation = (CategoryTranslation) obj;
        }

        return translation;
    }

    @Override
    public CategoryTranslation getCategoryTranslationByLanguage(final Language language) {
        CategoryTranslation translation = null;
        Object obj = getSession().createQuery("from CategoryTranslation as catTr where catTr.language_ID= ?")
                .setInteger(0, language.getID())
                .uniqueResult();
        if (obj != null) {
            translation = (CategoryTranslation) obj;
        }

        return translation;
    }

    @Override
    public CategoryTranslation getCategoryTranslationByCategory(final Category category) {
        CategoryTranslation translation = null;
        Object obj = getSession().createQuery("from CategoryTranslation as catTr where catTr.category_ID= ?")
                .setInteger(0, category.getId())
                .uniqueResult();
        if (obj != null) {
            translation = (CategoryTranslation) obj;
        }

        return translation;
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
