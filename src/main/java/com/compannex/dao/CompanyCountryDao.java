package com.compannex.dao;

import com.compannex.model.Company;
import com.compannex.model.CompanyCountry;
import com.compannex.model.Country;


public interface CompanyCountryDao {

    public CompanyCountry getCompanyCountryById(final int id);

    public void addCompanyCountry(final CompanyCountry companyCountry);

    public void editCompanyCountry(final CompanyCountry companyCountry);

    public void deleteCompanyCountry(final CompanyCountry companyCountry);

    public CompanyCountry getCompanyCountryByCompany(final Company company);

    public CompanyCountry getCompanyCountryByCountry(final Country country);
}
