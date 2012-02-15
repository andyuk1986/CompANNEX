package com.compannex.dao;

import com.compannex.model.Category;
import com.compannex.model.Company;

public interface CompanyDao {
	
    public Company getCompanyById(final int companyId);

    public void addCompany(final Company company);

    public void editCompany(final Company company);

    public void deleteCompany(final Company company);

}
