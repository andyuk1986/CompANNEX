package com.compannex.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CategoryDao;
import com.compannex.model.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
	private static Logger logger = Logger.getLogger(IndustryDaoImpl.class);

	@Override
	public Category getCategoryById(final int categoryId) {
		Session session = null;
		try {
			Category category = null;
			session = getSession();
			Object obj = session
					.createQuery("from Category as cat where cat.ID= ?")
					.setInteger(0, categoryId).uniqueResult();
			if (obj != null) {
				category = (Category) obj;
			}

			return category;
		} finally {
			if (session != null)
				session.close();
		}
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
	public List<Category> getCategoriesByIndustryID(final int industryId) {

		Session session = null;
		try {
			session = getSession();
			List<Category> categories = null;
			Object obj = session
					.createQuery(
							"from Category as categ where categ.industryId= ?")
					.setInteger(0, industryId).list();
			if (obj != null) {
				categories = (List<Category>) obj;
			}

			return categories;
		} finally {
			if (session != null)
				session.close();
		}
	}
}
