package com.compannex.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.biz.LoginMethods;
import com.compannex.biz.PasswordMethods;
import com.compannex.form.ChangePassword;

@Component("changePasswordValidation")
public class ChangePasswordValidation {
	
	public boolean supports(Class<?> klass) {
		return ChangePassword.class.isAssignableFrom(klass);
	}
	
	private LoginMethods loginMethods;
	
	private PasswordMethods passwordMethods;

	public void validate(Object target, Errors errors, String email) {
		ChangePassword changePassword = (ChangePassword) target;
        
        if (changePassword.getOldpassword() == null || changePassword.getOldpassword().isEmpty()) {
            errors.rejectValue("oldpassword", "oldpassword.empty", "* The Old Password should not be empty.");
        } else if (!getLoginMethods().checkLogin(email, changePassword.getOldpassword())) {
        	errors.rejectValue("oldpassword", "oldpassword.wrong", "The Old Password is wrong.");
        }
        
        if(changePassword.getNewpassword() == null || changePassword.getNewpassword().isEmpty()) {
            errors.rejectValue("newpassword", "newpassword.empty", "* The New Password should not be empty.");
        }

        if(changePassword.getRenewpassword() == null || changePassword.getRenewpassword().isEmpty()) {
            errors.rejectValue("renewpassword", "renewpassword.empty", "* The Confirm New Password should not be empty.");
        }

		if (changePassword.getNewpassword() != null && changePassword.getRenewpassword() != null
                && !changePassword.getRenewpassword().isEmpty()
                && !changePassword.getNewpassword().isEmpty()
                && !(changePassword.getNewpassword()).equals(changePassword.getRenewpassword())) {
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

	public PasswordMethods getPasswordMethods() {
		return passwordMethods;
	}

	public void setPasswordMethods(PasswordMethods passwordMethods) {
		this.passwordMethods = passwordMethods;
	}
}
