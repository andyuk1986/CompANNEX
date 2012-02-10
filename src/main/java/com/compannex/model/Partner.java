package com.compannex.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Partner implements Serializable {

	private int ID;

	private int company1Id;

	private int company2Id;

	private Date addedDate;

	private String status;

	private boolean isActive;

	private String description;

	private String feedback;

	private Date createDate;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "company_ID1")
	public int getCompany1Id() {
		return company1Id;
	}

	public void setCompany1Id(int company1Id) {
		this.company1Id = company1Id;
	}

	@Column(name = "company_ID2")
	public int getCompany2Id() {
		return company2Id;
	}

	public void setCompany2Id(int company2Id) {
		this.company2Id = company2Id;
	}

	@Column(name = "added_date")
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "is_active")
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "feedback")
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result
				+ ((addedDate == null) ? 0 : addedDate.hashCode());
		result = prime * result + company1Id;
		result = prime * result + company2Id;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partner other = (Partner) obj;
		if (ID != other.ID)
			return false;
		if (addedDate == null) {
			if (other.addedDate != null)
				return false;
		} else if (!addedDate.equals(other.addedDate))
			return false;
		if (company1Id != other.company1Id)
			return false;
		if (company2Id != other.company2Id)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (isActive != other.isActive)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
