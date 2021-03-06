package com.compannex.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "question")
public class Question implements Serializable, Answerable {

	private int ID;

	private String subject;
	
	private Integer companyID;

	private transient Company company;

	private Integer questionID;

	private transient Question question;
	
	private String text;

	private String person;
	
	private String email;

	private Date date;

	private boolean isNew;
	
	private boolean isPrivate;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "company_ID")
	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	@Transient
	public Company getCompany() {
		return company;
	}

	@Transient
	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "question_ID")
	public Integer getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}

	@Transient
	public Question getQuestion() {
		return question;
	}

	@Transient
	public void setQuestion(Question question) {
		this.question = question;
	}

	@Column(name = "text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "person")
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "is_new")
	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isnew) {
		this.isNew = isnew;
	}

	@Column(name = "is_private")
	public boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
}
