package com.compannex.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.biz.LoginMethods;
import com.compannex.form.Login;
import com.compannex.form.ResetPassword;

@Component("resetPasswordValidation")
public class ResetPasswordValidation {
	
	public boolean supports(Class<?> klass) {
		return ResetPassword.class.isAssignableFrom(klass);
	}
	
	private LoginMethods loginMethods;
	
	public void validate(Object target, Errors errors) {
		ResetPassword resetPassword = (ResetPassword) target;

        if(resetPassword.getNewpassword() == null || resetPassword.getNewpassword().isEmpty()) {
            errors.rejectValue("newpassword", "newpassword.empty", "* The New Password should not be empty.");
        }

        if(resetPassword.getRenewpassword() == null || resetPassword.getRenewpassword().isEmpty()) {
            errors.rejectValue("renewpassword", "renewpassword.empty", "* The Confirm New Password should not be empty.");
        }

		if (resetPassword.getNewpassword() != null && resetPassword.getRenewpassword() != null
                && !resetPassword.getRenewpassword().isEmpty()
                && !resetPassword.getNewpassword().isEmpty()
                && !(resetPassword.getNewpassword()).equals(resetPassword.getRenewpassword())) {
			errors.rejectValue("renewpassword",
					"renewpassword.matchingPassword",
					"* New Password and Confirm New Password Not match.");
		}
	}
	
	public LoginMethods getLoginMethods() {
		return loginMethods;
	}

	public void setLoginMethods(LoginMethods loginMethods) {
		this.loginMethods = loginMethods;
	}
}
