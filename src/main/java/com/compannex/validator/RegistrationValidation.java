package com.compannex.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.constants.CompANNEXConstants;
import com.compannex.dao.CompanyDao;
import com.compannex.form.Registration;
import com.compannex.util.StringUtil;

import java.util.regex.Pattern;

@Component("registrationValidator")
public class RegistrationValidation {
	
	private CompanyDao companyDao;
	
	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors, boolean register) {
		Registration registration = (Registration) target;
        if(registration.getName() == null || registration.getName().isEmpty()) {
            errors.rejectValue("name", "registration.companyName.empty","* The company name should not be empty.");
        }

        if (register) {
	        if(registration.getEmail() == null || registration.getEmail().isEmpty()) {
	            errors.rejectValue("email", "registration.email.empty", "* The email address should not be empty.");
	        }
	
	        if(registration.getEmail() != null && !registration.getEmail().trim().isEmpty()) {
	            if (!Pattern.matches("([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+", registration.getEmail())) {
	                errors.rejectValue("email", "registration.email.invalid", "* Please enter valid email.");
	            }
	        }
	
	        if (registration.getEmail() != null && getCompanyDao().getCompanyByEmail(registration.getEmail()) != null) {
	        	errors.rejectValue("email", "registration.email.existing", "* The email address you have entered already exists.");
	        }
	        
	        if(registration.getPassword() == null || registration.getPassword().isEmpty()) {
	            errors.rejectValue("password", "registration.password.empty", "* The password should not be empty.");
	        }
	
	        if(registration.getRepassword() == null || registration.getRepassword().isEmpty()) {
	            errors.rejectValue("repassword", "registration.repassword.empty", "* The company name should not be empty.");
	        }
	
			if (registration.getPassword() != null && registration.getRepassword() != null
	                && !registration.getRepassword().isEmpty()
	                && !registration.getRepassword().isEmpty()
	                && !(registration.getPassword()).equals(registration.getRepassword())) {
				errors.rejectValue("password",
						"registration.password.matchingPassword",
						"* Password and Confirm Password Not match.");
			}

        }
        
        if(registration.getAddress() == null || registration.getAddress().isEmpty()) {
            errors.rejectValue("address", "registration.address.empty", "* Please insert the address of your company.");
        }

        if(registration.getCountry() == null || registration.getCountry().isEmpty()
                || registration.getCountry().equals("none")) {
            errors.rejectValue("country", "registration.country.empty", "* Please select country of the residence.");
        }

        if(registration.getIndustry() == null || registration.getIndustry().isEmpty() ||
                registration.getIndustry().equals("none")) {
            errors.rejectValue("industry", "registration.industry.empty", "* Please choose the industry to which your company belongs.");
        }

		if (registration.getCategory() == null || registration.getCategory().isEmpty()
				|| registration.getCategory().equals("none")) {
			errors.rejectValue("category", "registration.category.empty", "* Please choose the category to which your company belongs.");
		}
        
		if(registration.getLogo() != null && registration.getLogo().getSize() > CompANNEXConstants.MAX_FILE_SIZE){
			errors.rejectValue("logo", "registration.logo.big", "* Please choose the logo file which is less than 20KB.");
		} else if (registration.getLogo() != null && !StringUtil.endsWith(registration.getLogo().getOriginalFilename(), CompANNEXConstants.EXTENSIONS)) {
			errors.rejectValue("logo", "registration.logo.wrong", "* Please choose only JPEG, PNG or GIF files.");
		}

	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}
