package com.compannex.dao;

import java.util.List;

import com.compannex.model.Consultant;

public interface ConsultantDao {

	public Consultant getConsultantById(final int consultantId);
	
	public Consultant getConsultantByEmail(final String email);
	
	public Consultant getConsultantByEmailAndToken(final String email, final String token);
	
	public Consultant getConsultantByPasswordToken(String email, int id, String passwordToken);

	public List<Consultant> getConsultantsByIndustryId(final int industryId);

	public List<Consultant> getConsultantsByCategoryId(final int industryId);

	public List<Consultant> getAllConsultants();

	public void addConsultant(final Consultant consultant);

	public void editConsultant(final Consultant consultant);

	public void deleteConsultant(final Consultant consultant);

}
