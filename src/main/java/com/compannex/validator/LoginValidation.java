package com.compannex.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.biz.LoginMethods;
import com.compannex.form.Login;

@Component("loginValidator")
public class LoginValidation {
	
	public boolean supports(Class<?> klass) {
		return Login.class.isAssignableFrom(klass);
	}
	
	private LoginMethods loginMethods;

	public void validate(Object target, Errors errors) {
		Login login = (Login) target;

        if (login.getEmail() == null || login.getEmail().isEmpty()) {
            errors.rejectValue("email", "login.email.empty", "* The email address should not be empty.");
        }
        
        if (login.getEmail() != null && !login.getEmail().trim().isEmpty()) {
            if (!Pattern.matches("([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+", login.getEmail())) {
                errors.rejectValue("email", "login.email.invalid", "* Please enter valid email.");
            }
        }
        
        if (login.getPassword() == null || login.getPassword().isEmpty()) {
            errors.rejectValue("password", "login.password.empty", "* The password should not be empty.");
        } else if (!getLoginMethods().checkLogin(login.getEmail(), login.getPassword())) {
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
