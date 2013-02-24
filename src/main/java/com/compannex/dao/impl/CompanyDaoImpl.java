package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.CompanyDao;
import com.compannex.model.Company;


public class CompanyDaoImpl extends HibernateDaoSupport implements CompanyDao {
    
	@Override
    public Company getCompanyById(int companyId) {
		Session session = null;
		try {
			Company company = null;
			session = getSession();
			Object obj = session
					.createQuery("from Company as comp where comp.ID= ?").setCacheable(true)
					.setInteger(0, companyId).uniqueResult();
			if (obj != null) {
				company = (Company) obj;
			}

			return company;
		} finally {
			if (session != null)
				session.close();
		}
    }
	
	@Override
	public Company getCompanyByEmail(final String email) {
		Session session = null;
		try {
			Company company = null;
			session = getSession();
			Object obj = session
					.createQuery("from Company as comp where comp.email= ?").setCacheable(true)
					.setString(0, email).uniqueResult();
			if (obj != null) {
				company = (Company) obj;
			}

			return company;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Company getCompanyByEmailAndToken(final String email, final String token) {
		Session session = null;
		try {
			Company company = null;
			session = getSession();
			Object obj = session
					.createQuery("from Company as comp where comp.email= ? AND comp.token= ?").setCacheable(true)
					.setString(0, email).setString(1, token).uniqueResult();
			if (obj != null) {
				company = (Company) obj;
			}

			return company;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@Override
	public Company getCompanyByPasswordToken(String email, int id, final String token) {
		Session session = null;
		try {
			Company company = null;
			session = getSession();
			Object obj = session
					.createQuery("from Company as comp where comp.email= ? AND comp.ID= ? AND comp.passwordToken= ?").setCacheable(true)
					.setString(0, email).setInteger(1, id).setString(2, token).uniqueResult();
			if (obj != null) {
				company = (Company) obj;
			}

			return company;
		} finally {
			if (session != null)
				session.close();
		}
	}

    @Override
    public List<Company> getCompaniesByIndustryId(final int industryId) {	
    	Session session = null;
    	try {
    		session = getSession();
        	List<Company> companies = null;
            Object obj = session.createSQLQuery("select comp.* from company as comp inner join category as categ on comp.category_ID = categ.ID where categ.industry_ID= ?")
                    .addEntity(Company.class)
            		.setInteger(0, industryId)
                    .list();
            if (obj != null) {
            	companies = (List<Company>) obj;
            }

            return companies;
        } finally {
        	if (session != null) session.close();
        }
    }

    @Override
    public List<Company> getCompaniesByCategoryId(final int categoryId) {	
    	Session session = null;
    	try {
    		session = getSession();
        	List<Company> companies = null;
            Object obj = session.createQuery("from Company as comp where comp.categoryID= ?")
                    .setInteger(0, categoryId)
                    .list();
            if (obj != null) {
            	companies = (List<Company>) obj;
            }

            return companies;
        } finally {
        	if (session != null) session.close();
        }
    }

    @Override
    public List<Company> getAllCompanies() {	
		List<Company> comps = getHibernateTemplate().loadAll(Company.class);

		if (comps == null)
			comps = new ArrayList<Company>();

		return comps;
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
