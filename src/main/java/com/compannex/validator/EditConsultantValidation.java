package com.compannex.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.compannex.dao.ConsultantDao;
import com.compannex.form.EditConsultant;

@Component("editconsultantValidation")
public class EditConsultantValidation {
	
	private ConsultantDao consultantDao;
	
	public boolean supports(Class<?> klass) {
		return EditConsultant.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		EditConsultant editconsultant = (EditConsultant) target;

		if(editconsultant.getFirstName() == null || editconsultant.getFirstName().isEmpty()) {
            errors.rejectValue("name", "editconsultant.firstName.empty","* First Name should not be empty.");
        }
        
		if(editconsultant.getLastName() == null || editconsultant.getLastName().isEmpty()) {
            errors.rejectValue("name", "editconsultant.lastName.empty","* Last Name should not be empty.");
        }
		
        if(editconsultant.getAddress() == null || editconsultant.getAddress().isEmpty()) {
            errors.rejectValue("address", "editconsultant.address.empty", "* Please insert your address.");
        }

        if(editconsultant.getIndustry() == null || editconsultant.getIndustry().isEmpty() ||
        		editconsultant.getIndustry().equals("none")) {
            errors.rejectValue("industry", "editconsultant.industry.empty", "* Please choose the industry to which you want to consult.");
        }

		if (editconsultant.getCategory() == null || editconsultant.getCategory().isEmpty()
				|| editconsultant.getCategory().equals("none")) {
			errors.rejectValue("category", "editconsultant.category.empty", "* Please choose the category to which you want to consult.");
		}        
	}

	public ConsultantDao getConsultantDao() {
		return consultantDao;
	}

	public void setConsultantDao(ConsultantDao consultantDao) {
		this.consultantDao = consultantDao;
	}
}
