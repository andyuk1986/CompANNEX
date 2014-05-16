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
@Table(name = "answer")
public class Answer implements Serializable, Answerable {

	private int ID;

	private Integer questionID;

	private transient Question question;

	private Integer consultantID;

	private transient Consultant consultant;
	
	private String text;

	private Date date;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
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

	@Column(name = "consultant_ID")
	public Integer getConsultantID() {
		return consultantID;
	}

	public void setConsultantID(Integer consultantID) {
		this.consultantID = consultantID;
	}

	@Transient
	public Consultant getConsultant() {
		return consultant;
	}

	@Transient
	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}
	
	@Column(name = "text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
