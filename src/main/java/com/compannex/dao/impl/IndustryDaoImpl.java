package com.compannex.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.IndustryDao;
import com.compannex.model.Industry;

/**
 * ReachLibrary DAO Hibernate Implementation.
 */
public class IndustryDaoImpl extends HibernateDaoSupport implements IndustryDao {

    private static Logger logger = Logger.getLogger(IndustryDaoImpl.class);

    @Override
    public Industry getIndustryById(final int id) {
        Industry doc = null;
        Object obj = getHibernateTemplate().load(Industry.class, id);
        if (obj != null) {
            doc = (Industry) obj;
        }
        return doc;
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

}
