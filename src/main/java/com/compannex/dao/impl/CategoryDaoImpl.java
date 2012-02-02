package com.compannex.dao.impl;

import com.compannex.dao.CategoryDao;
import com.compannex.model.Category;
import com.compannex.model.Industry;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 1/28/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
    private static Logger logger = Logger.getLogger(IndustryDaoImpl.class);

    @Override
    public Category getCategoryById(final int categoryId) {
        Category doc = null;
        Object obj = getHibernateTemplate().load(Industry.class, categoryId);
        if (obj != null) {
            doc = (Category) obj;
        }
        return doc;
    }

    @Override
    public void saveCategory(Category category) {
        getHibernateTemplate().save(category);
    }

    @Override
    public void editCategory(Category category) {
        getHibernateTemplate().update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        getHibernateTemplate().delete(category);
    }

    @Override
    public Category getCategoryByIndustry(final Industry industry) {
        Category category = null;
        Object obj = getSession().createQuery("from Category as categ where categ.industry_ID= ?")
                .setInteger(0, industry.getID())
                .uniqueResult();
        if (obj != null) {
            category = (Category) obj;
        }

        return category;
    }
}
