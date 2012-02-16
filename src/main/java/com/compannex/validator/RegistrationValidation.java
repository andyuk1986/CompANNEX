package com.compannex.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import com.compannex.form.Registration;

@Component("registrationValidator")
public class RegistrationValidation {
	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		Registration registration = (Registration) target;
		if (!(registration.getPassword()).equals(registration.getRepassword())) {
			errors.rejectValue("password",
					"matchingPassword.registration.password",
					"Password and Confirm Password Not match.");
		}
	}
}
