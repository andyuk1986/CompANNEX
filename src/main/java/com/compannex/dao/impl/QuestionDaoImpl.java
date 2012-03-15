package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.QuestionDao;
import com.compannex.model.Company;
import com.compannex.model.Question;

public class QuestionDaoImpl extends HibernateDaoSupport implements QuestionDao {

	private static Logger logger = Logger.getLogger(QuestionDaoImpl.class);

	@Override
	public List<Question> getAllQuestions() {
		List<Question> questions = getHibernateTemplate().loadAll(
				Question.class);

		if (questions == null)
			questions = new ArrayList<Question>();

		return questions;
	}

	@Override
	public List<Question> getNewQuestions() {
		Session session = null;
		try {
			List<Question> questions = null;
			session = getSession();
			Object obj = session.createQuery(
					"from Question as qu where qu.isNew = true").list();
			if (obj != null) {
				questions = (List<Question>) obj;
			}

			return questions;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
    public void addQuestion(Question question) {
        getHibernateTemplate().save(question);
    }
}
