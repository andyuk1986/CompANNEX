package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.IndustryDao;
import com.compannex.model.Industry;
import com.compannex.model.IndustryTranslation;

/**
 * Industry DAO Hibernate Implementation.
 */
public class IndustryDaoImpl extends HibernateDaoSupport implements IndustryDao {

    private static Logger logger = Logger.getLogger(IndustryDaoImpl.class);

    @Override
    public Industry getIndustryById(final int id) {
    	Session session = null;
    	try {
	    	Industry industry = null;
	        session = getSession();
	        Object obj = session.createQuery("from Industry as ind where ind.ID= ?")
	                .setInteger(0, id)
	                .uniqueResult();
	        if (obj != null) {
	        	industry = (Industry) obj;
	        }
	
	        return industry;
        } finally {
        	if (session != null) session.close();
        }
    }

    @Override
    public void saveIndustry(final Industry ind) {
        getHibernateTemplate().save(ind);
    }

    @Override
    public void editIndustry(final Industry ind) {
        getHibernateTemplate().update(ind);
    }

    @Override
    public Industry getIndustryByName(final String indName) {
        final List<Industry> inds = getHibernateTemplate().findByNamedQuery("industry.by.name", indName);
        if (inds != null && inds.size() > 0) {
            return inds.get(0);
        } else {
            return null;
        }
    }
    
    @Override
    public List<Industry> getAllIndustries() {
    	List<Industry> inds = getHibernateTemplate().loadAll(Industry.class);
    	
    	if (inds == null) inds = new ArrayList<Industry>();
    	
    	return inds;
    }

}
