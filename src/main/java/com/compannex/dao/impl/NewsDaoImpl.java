package com.compannex.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.NewsDao;

public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao {
    private static Logger logger = Logger.getLogger(NewsDaoImpl.class);

}
