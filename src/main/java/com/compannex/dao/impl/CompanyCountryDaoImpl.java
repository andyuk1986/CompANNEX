package com.compannex.dao.impl;

import com.compannex.dao.CompanyCountryDao;
import com.compannex.model.Company;
import com.compannex.model.CompanyCountry;
import com.compannex.model.Country;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyCountryDaoImpl extends HibernateDaoSupport implements CompanyCountryDao {

    @Override
    public CompanyCountry getCompanyCountryById(int id) {
        return null;
    }

    @Override
    public void saveCompanyCountry(CompanyCountry companyCountry) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void editCompanyCountry(CompanyCountry companyCountry) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteCompanyCountry(CompanyCountry companyCountry) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CompanyCountry getCompanyCountryByCompany(Company company) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CompanyCountry getCompanyCountryByCountry(Country country) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
