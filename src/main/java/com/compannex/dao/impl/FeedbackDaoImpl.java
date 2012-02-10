package com.compannex.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.FeedbackDao;

public class FeedbackDaoImpl extends HibernateDaoSupport implements FeedbackDao {
    private static Logger logger = Logger.getLogger(FeedbackDaoImpl.class);

}
