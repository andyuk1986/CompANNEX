package com.compannex.dao;

import com.compannex.model.Category;
import com.compannex.model.Company;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyDao {
    public Company getCompanyById(final int companyId);

    public void saveCompany(final Company company);

    public void editCompany(final Company company);

    public void deleteCompany(final Company company);

    public Company getCompanyByCategory(final Category category);
}
