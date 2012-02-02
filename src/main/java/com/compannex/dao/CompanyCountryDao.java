package com.compannex.dao;

import com.compannex.model.Company;
import com.compannex.model.CompanyCountry;
import com.compannex.model.Country;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyCountryDao {

    public CompanyCountry getCompanyCountryById(final int id);

    public void saveCompanyCountry(final CompanyCountry companyCountry);

    public void editCompanyCountry(final CompanyCountry companyCountry);

    public void deleteCompanyCountry(final CompanyCountry companyCountry);

    public CompanyCountry getCompanyCountryByCompany(final Company company);

    public CompanyCountry getCompanyCountryByCountry(final Country country);
}
