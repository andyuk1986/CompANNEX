package com.compannex.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.form.Answer;

@Component("answerValidator")
public class AnswerValidation {
	public boolean supports(Class<?> klass) {
		return Answer.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		Answer answer = (Answer) target;
        
		if (answer.getQuestionID() == null) {
			errors.rejectValue("question", "answer.question.empty", "* The question ID should not be empty.");
		}
		
        if (answer.getText() == null || answer.getText().isEmpty()) {
            errors.rejectValue("text", "answer.text.empty", "* The text should not be empty.");
        }
	}
}
