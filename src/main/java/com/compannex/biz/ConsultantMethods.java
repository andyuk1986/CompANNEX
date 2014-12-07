
package com.compannex.biz;

import com.compannex.dao.ConsultantDao;
import com.compannex.model.Consultant;

public class ConsultantMethods {
	
	private ConsultantDao consultantDao;
	
	public Consultant getConsultantByEmail(final String email, final int languageID) {
		
		Consultant comp = getConsultantDao().getConsultantByEmail(email);
		
		return comp;
	}
	
	public Consultant getConsultantByID(final int consultantID, final int languageID) {
		
		Consultant comp = getConsultantDao().getConsultantById(consultantID);	
		
		return comp;
	}
	
	public int addConsultant() {
		return -1;
	}

	public ConsultantDao getConsultantDao() {
		return consultantDao;
	}

	public void setConsultantDao(ConsultantDao consultantDao) {
		this.consultantDao = consultantDao;
	}
}
