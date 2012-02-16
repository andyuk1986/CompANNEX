package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.constants.CompANNEXConstants;
import com.compannex.dao.NewsDao;
import com.compannex.model.News;

public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao {

	private static Logger logger = Logger.getLogger(NewsDaoImpl.class);

	@Override
	public List<News> getAllNews() {
		List<News> news = getHibernateTemplate().loadAll(News.class);

		if (news == null)
			news = new ArrayList<News>();

		return news;
	}

	@Override
	public List<News> getLastNews() {
		Session session = null;
		try {
			List<News> news = null;
			session = getSession();
			Object obj = session.createQuery("from News as news")
					.setMaxResults(CompANNEXConstants.NEWS_LIMIT).list();
			if (obj != null) {
				news = (List<News>) obj;
			}

			return news;
		} finally {
			if (session != null)
				session.close();
		}
	}

}
