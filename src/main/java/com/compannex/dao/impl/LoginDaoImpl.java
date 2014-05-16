package com.compannex.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.LoginDao;
import com.compannex.model.Login;


public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {
    
	@Override
    public Login getLoginById(int loginId) {
		Session session = null;
		try {
			Login company = null;
			session = getSession();
			Object obj = session
					.createQuery("from Login as login where login.ID= ?").setCacheable(true)
					.setInteger(0, loginId).uniqueResult();
			if (obj != null) {
				company = (Login) obj;
			}

			return company;
		} finally {
			if (session != null)
				session.close();
		}
    }
	
	@Override
	public Login getLoginByEmail(final String email) {
		Session session = null;
		try {
			Login login = null;
			session = getSession();
			Object obj = session
					.createQuery("from Login as login where login.email= ?").setCacheable(true)
					.setString(0, email).uniqueResult();
			if (obj != null) {
				login = (Login) obj;
			}

			return login;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Login getLoginByEmailAndToken(final String email, final String token) {
		Session session = null;
		try {
			Login login = null;
			session = getSession();
			Object obj = session
					.createQuery("from Login as login where login.email= ? AND login.token= ?").setCacheable(true)
					.setString(0, email).setString(1, token).uniqueResult();
			if (obj != null) {
				login = (Login) obj;
			}

			return login;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@Override
	public Login getLoginByPasswordToken(String email, int id, final String token) {
		Session session = null;
		try {
			Login login = null;
			session = getSession();
			Object obj = session
					.createQuery("from Login as login where login.email= ? AND login.ID= ? AND login.passwordToken= ?").setCacheable(true)
					.setString(0, email).setInteger(1, id).setString(2, token).uniqueResult();
			if (obj != null) {
				login = (Login) obj;
			}

			return login;
		} finally {
			if (session != null)
				session.close();
		}
	}
    
    @Override
    public void addLogin(Login login) {
        getHibernateTemplate().save(login);
    }

    @Override
    public void editLogin(Login login) {
        getHibernateTemplate().update(login);
    }

    @Override
    public void deleteLogin(Login login) {
        getHibernateTemplate().delete(login);
    }
}
