package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.ConsultantDao;
import com.compannex.model.Consultant;


public class ConsultantDaoImpl extends HibernateDaoSupport implements ConsultantDao {
    
	@Override
    public Consultant getConsultantById(int consultantId) {
		Session session = null;
		try {
			Consultant consultant = null;
			session = getSession();
			Object obj = session
					.createQuery("from Consultant as cons where cons.ID= ?").setCacheable(true)
					.setInteger(0, consultantId).uniqueResult();
			if (obj != null) {
				consultant = (Consultant) obj;
			}

			return consultant;
		} finally {
			if (session != null)
				session.close();
		}
    }
	
	@Override
	public Consultant getConsultantByEmail(final String email) {
		Session session = null;
		try {
			Consultant consultant = null;
			session = getSession();
			Object obj = session
					.createQuery("from Consultant as cons where cons.email= ?").setCacheable(true)
					.setString(0, email).uniqueResult();
			if (obj != null) {
				consultant = (Consultant) obj;
			}

			return consultant;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Consultant getConsultantByEmailAndToken(final String email, final String token) {
		Session session = null;
		try {
			Consultant consultant = null;
			session = getSession();
			Object obj = session
					.createQuery("from Consultant as cons where cons.email= ? AND cons.token= ?").setCacheable(true)
					.setString(0, email).setString(1, token).uniqueResult();
			if (obj != null) {
				consultant = (Consultant) obj;
			}

			return consultant;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@Override
	public Consultant getConsultantByPasswordToken(String email, int id, final String token) {
		Session session = null;
		try {
			Consultant consultant = null;
			session = getSession();
			Object obj = session
					.createQuery("from Consultant as cons where cons.email= ? AND cons.ID= ? AND cons.passwordToken= ?").setCacheable(true)
					.setString(0, email).setInteger(1, id).setString(2, token).uniqueResult();
			if (obj != null) {
				consultant = (Consultant) obj;
			}

			return consultant;
		} finally {
			if (session != null)
				session.close();
		}
	}

    @Override
    public List<Consultant> getConsultantsByIndustryId(final int industryId) {	
    	Session session = null;
    	try {
    		session = getSession();
        	List<Consultant> consanies = null;
            Object obj = session.createSQLQuery("select cons.* from consultant as cons inner join consultant_category as conscat inner join category as categ where categ.industry_ID= ?")
                    .addEntity(Consultant.class)
            		.setInteger(0, industryId)
                    .list();
            if (obj != null) {
            	consanies = (List<Consultant>) obj;
            }

            return consanies;
        } finally {
        	if (session != null) session.close();
        }
    }

    @Override
    public List<Consultant> getConsultantsByCategoryId(final int categoryId) {	
    	Session session = null;
    	try {
    		session = getSession();
        	List<Consultant> consanies = null;
            Object obj = session.createSQLQuery("select cons.* from consultant as cons inner join consultant_category as conscat where conscat.category_ID= ?")
                    .addEntity(Consultant.class)
                    .setInteger(0, categoryId)
                    .list();
            if (obj != null) {
            	consanies = (List<Consultant>) obj;
            }

            return consanies;
        } finally {
        	if (session != null) session.close();
        }
    }

    @Override
    public List<Consultant> getAllConsultants() {	
		List<Consultant> conss = getHibernateTemplate().loadAll(Consultant.class);

		if (conss == null)
			conss = new ArrayList<Consultant>();

		return conss;
    }
    
    @Override
    public void addConsultant(Consultant consultant) {
        getHibernateTemplate().save(consultant);
    }

    @Override
    public void editConsultant(Consultant consultant) {
        getHibernateTemplate().update(consultant);
    }

    @Override
    public void deleteConsultant(Consultant consultant) {
        getHibernateTemplate().delete(consultant);
    }
}
