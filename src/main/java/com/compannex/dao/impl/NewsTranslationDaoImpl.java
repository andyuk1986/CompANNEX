package com.compannex.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.NewsTranslationDao;
import com.compannex.model.NewsTranslation;

public class NewsTranslationDaoImpl extends HibernateDaoSupport implements
		NewsTranslationDao {

	private static Logger logger = Logger
			.getLogger(NewsTranslationDaoImpl.class);

	@Override
	public NewsTranslation getNewsTranslation(final int newsID,
			final int languageID) {
		Session session = null;
		try {
			NewsTranslation translation = null;
			session = getSession();
			Object obj = session
					.createQuery(
							"from NewsTranslation as newsTr where newsTr.newsID= ? and newsTr.languageID= ?").setCacheable(true)
					.setInteger(0, newsID).setInteger(1, languageID)
					.uniqueResult();
			if (obj != null) {
				translation = (NewsTranslation) obj;
			}

			return translation;
		} finally {
			if (session != null)
				session.close();
		}
	}

}
