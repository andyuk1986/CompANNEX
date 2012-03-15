package com.compannex.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.form.Question;
import com.compannex.form.Registration;

import java.util.regex.Pattern;

@Component("questionValidator")
public class QuestionValidation {
	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		Question question = (Question) target;
		
		if (question.getCompanyID() == null) {
	        if(question.getPerson() == null || question.getPerson().isEmpty()) {
	            errors.rejectValue("person", "question.person.empty","* The person name should not be empty.");
	        }

	        if(question.getEmail() == null || question.getEmail().isEmpty()) {
	            errors.rejectValue("email", "question.email.empty", "* The email address should not be empty.");
	        }

	        if(question.getEmail() != null && !question.getEmail().trim().isEmpty()) {
	            if (!Pattern.matches("([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+", question.getEmail())) {
	                errors.rejectValue("email", "question.email.invalid", "* Please enter valid email.");
	            }
	        }
		}

        if(question.getSubject() == null || question.getSubject().isEmpty()) {
            errors.rejectValue("subject", "question.subject.empty", "* The subject should not be empty.");
        }
        
        if(question.getText() == null || question.getText().isEmpty()) {
            errors.rejectValue("text", "question.text.empty", "* The text should not be empty.");
        }
	}
}
