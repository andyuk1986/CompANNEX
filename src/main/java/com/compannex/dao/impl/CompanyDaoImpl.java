package com.compannex.dao.impl;

import com.compannex.dao.CompanyDao;
import com.compannex.model.Category;
import com.compannex.model.Company;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
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
    public void saveCompany(Company company) {
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

    @Override
    public Company getCompanyByCategory(Category category) {
        Company company = null;

        return company;
    }
}
