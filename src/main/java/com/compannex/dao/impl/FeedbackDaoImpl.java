package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.constants.CompANNEXConstants;
import com.compannex.dao.FeedbackDao;
import com.compannex.model.Feedback;
import com.compannex.model.News;

public class FeedbackDaoImpl extends HibernateDaoSupport implements FeedbackDao {

	private static Logger logger = Logger.getLogger(FeedbackDaoImpl.class);

	@Override
	public List<Feedback> getAllFeedbacks() {
		List<Feedback> feedbacks = getHibernateTemplate().loadAll(Feedback.class);

		if (feedbacks == null)
			feedbacks = new ArrayList<Feedback>();

		return feedbacks;
	}

	@Override
	public List<Feedback> getLastFeedbacks() {
		Session session = null;
		try {
			List<Feedback> feedbacks = null;
			session = getSession();
			Object obj = session.createQuery("from Feedback as fb")
					.setMaxResults(CompANNEXConstants.FEEDBACKS_LIMIT).list();
			if (obj != null) {
				feedbacks = (List<Feedback>) obj;
			}

			return feedbacks;
		} finally {
			if (session != null)
				session.close();
		}
	}

}
