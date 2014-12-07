package com.compannex.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.dao.ConsultantDao;
import com.compannex.dao.LoginDao;
import com.compannex.form.ConsultantRegistration;

@Component("consultantregistrationValidation")
public class ConsultantRegistrationValidation extends EditConsultantValidation {

	private ConsultantDao consultantDao;

	private LoginDao loginDao;

	public boolean supports(Class<?> klass) {
		return ConsultantRegistration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors, boolean isRegister) {

		super.validate(target, errors);

		if (!isRegister) return;

		ConsultantRegistration registration = (ConsultantRegistration) target;

        if(registration.getEmail() == null || registration.getEmail().isEmpty()) {
            errors.rejectValue("email", "registration.email.empty", "* The email address should not be empty.");
        }

        if(registration.getEmail() != null && !registration.getEmail().trim().isEmpty()) {
            if (!Pattern.matches("([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+", registration.getEmail())) {
                errors.rejectValue("email", "registration.email.invalid", "* Please enter valid email.");
            }
        }

        if (registration.getEmail() != null && getLoginDao().getLoginByEmail(registration.getEmail()) != null) {
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

	public ConsultantDao getConsultantDao() {
		return consultantDao;
	}

	public void setConsultantDao(ConsultantDao consultantDao) {
		this.consultantDao = consultantDao;
	}

	public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }
}
