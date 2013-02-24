package com.compannex.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.biz.LoginMethods;
import com.compannex.form.ForgotPassword;

@Component("forgotPasswordValidation")
public class ForgotPasswordValidation {
	
	public boolean supports(Class<?> klass) {
		return ForgotPassword.class.isAssignableFrom(klass);
	}
	
	private LoginMethods loginMethods;
	
	public void validate(Object target, Errors errors) {
		ForgotPassword forgotPassword = (ForgotPassword) target;

        if (forgotPassword.getEmail() == null || forgotPassword.getEmail().isEmpty()) {
            errors.rejectValue("email", "login.email.empty", "* The email address should not be empty.");
        } else if (forgotPassword.getEmail() != null && !forgotPassword.getEmail().trim().isEmpty()) {
            if (!Pattern.matches("([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+", forgotPassword.getEmail())) {
                errors.rejectValue("email", "login.email.invalid", "* Please enter valid email.");
            }
        } else if (!getLoginMethods().checkLogin(forgotPassword.getEmail())) {
        	errors.rejectValue("email", "login.wrong", "Email or Password are wrong.");
        }
	}
	
	public LoginMethods getLoginMethods() {
		return loginMethods;
	}

	public void setLoginMethods(LoginMethods loginMethods) {
		this.loginMethods = loginMethods;
	}
}
