package com.compannex.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.dao.CompanyDao;
import com.compannex.form.Registration;

@Component("registrationValidation")
public class RegistrationValidation extends EditCompanyValidation {
	
	private CompanyDao companyDao;
	
	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors, boolean isRegister) {

		super.validate(target, errors);
		
		if (!isRegister) return; 
		
		Registration registration = (Registration) target;
		
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
            errors.rejectValue("repassword", "registration.repassword.empty", "* The re-password should not be empty.");
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

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}
