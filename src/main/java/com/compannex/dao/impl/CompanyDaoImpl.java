package com.compannex.dao.impl;

import com.compannex.dao.CompanyDao;
import com.compannex.model.Category;
import com.compannex.model.Company;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class CompanyDaoImpl extends HibernateDaoSupport implements CompanyDao {
    @Override
    public Company getCompanyById(int companyId) {
        Company company = null;
        Object obj = getHibernateTemplate().load(Company.class, companyId);
        if (obj != null) {
            company = (Company) obj;
        }

        return company;
    }

    @Override
    public void addCompany(Company company) {
        getHibernateTemplate().save(company);
    }

    @Override
    public void editCompany(Company company) {
        getHibernateTemplate().update(company);
    }

    @Override
    public void deleteCompany(Company company) {
        getHibernateTemplate().delete(company);
    }
}
