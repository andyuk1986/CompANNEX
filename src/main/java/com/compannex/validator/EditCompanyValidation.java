package com.compannex.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.constants.CompANNEXConstants;
import com.compannex.dao.CompanyDao;
import com.compannex.form.EditCompany;
import com.compannex.util.StringUtil;

@Component("editcompanyValidation")
public class EditCompanyValidation {
	
	private CompanyDao companyDao;
	
	public boolean supports(Class<?> klass) {
		return EditCompany.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		EditCompany editcomapny = (EditCompany) target;

		if(editcomapny.getName() == null || editcomapny.getName().isEmpty()) {
            errors.rejectValue("name", "editcomapny.companyName.empty","* The company name should not be empty.");
        }
        
        if(editcomapny.getAddress() == null || editcomapny.getAddress().isEmpty()) {
            errors.rejectValue("address", "editcomapny.address.empty", "* Please insert the address of your company.");
        }

        if(editcomapny.getCity() == null || editcomapny.getCity().isEmpty()) {
            errors.rejectValue("city", "editcomapny.city.empty", "* Please insert the city of your company.");
        }
        
        if(editcomapny.getRegion() == null || editcomapny.getRegion().isEmpty()) {
            errors.rejectValue("region", "editcomapny.region.empty", "* Please insert the region of your company.");
        }
        
        if(editcomapny.getZipcode() == null || editcomapny.getZipcode().isEmpty()) {
            errors.rejectValue("zipcode", "editcomapny.zipcode.empty", "* Please insert the zipcode of your company.");
        }
        
        if(editcomapny.getCountry() == null || editcomapny.getCountry().isEmpty()
                || editcomapny.getCountry().equals("none")) {
            errors.rejectValue("country", "editcomapny.country.empty", "* Please select country of the residence.");
        }

        if(editcomapny.getIndustry() == null || editcomapny.getIndustry().isEmpty() ||
                editcomapny.getIndustry().equals("none")) {
            errors.rejectValue("industry", "editcomapny.industry.empty", "* Please choose the industry to which your company belongs.");
        }

		if (editcomapny.getCategory() == null || editcomapny.getCategory().isEmpty()
				|| editcomapny.getCategory().equals("none")) {
			errors.rejectValue("category", "editcomapny.category.empty", "* Please choose the category to which your company belongs.");
		}
        
		if(editcomapny.getLogo() != null && editcomapny.getLogo().getSize() > CompANNEXConstants.MAX_FILE_SIZE){
			errors.rejectValue("logo", "editcomapny.logo.big", "* Please choose the logo file which is less than 20KB.");
		} else if (editcomapny.getLogo() != null && !StringUtil.isBlank(editcomapny.getLogo().getOriginalFilename()) && !StringUtil.endsWith(editcomapny.getLogo().getOriginalFilename(), CompANNEXConstants.EXTENSIONS)) {
			errors.rejectValue("logo", "editcomapny.logo.wrong", "* Please choose only JPEG, PNG or GIF files.");
		}

	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}
