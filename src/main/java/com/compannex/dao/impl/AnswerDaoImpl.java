package com.compannex.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.AnswerDao;
import com.compannex.model.Answer;

public class AnswerDaoImpl extends HibernateDaoSupport implements AnswerDao {

	private static Logger logger = Logger.getLogger(AnswerDaoImpl.class);

	@Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
		Session session = null;
		try {
			List<Answer> answers = null;
			session = getSession();
			Object obj = session
					.createQuery("from Answer as ans where ans.questionID= ?").setCacheable(true)
					.setInteger(0, questionId).list();
			if (obj != null) {
				answers = (List<Answer>) obj;
			}

			return answers;
		} finally {
			if (session != null)
				session.close();
		}
    }

	@Override
    public void addAnswer(Answer answer) {
        getHibernateTemplate().save(answer);
    }
}
