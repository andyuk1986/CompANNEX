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
@Table(name = "consultant_category")
public class ConsultantCategory implements Serializable {

	private int ID;

	private int consultantID;

	private int categoryID;

	private transient Consultant consultant;

	private transient Category category;

	private String description;

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

	@Column(name = "consultant_ID")
	public int getConsultantID() {
		return consultantID;
	}

	public void setConsultantID(int consultantID) {
		this.consultantID = consultantID;
	}

	@Column(name = "category_ID")
	public int getCaategoryID() {
		return categoryID;
	}

	public void setCategoryD(int categoryID) {
		this.categoryID = categoryID;
	}

	@Transient
	public Consultant getConsultant() {
		return consultant;
	}

	@Transient
	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}

	@Transient
	public Category getCategory() {
		return category;
	}

	@Transient
	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		result = prime * result + consultantID;
		result = prime * result + categoryID;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		ConsultantCategory other = (ConsultantCategory) obj;
		if (ID != other.ID)
			return false;
		if (consultantID != other.consultantID)
			return false;
		if (categoryID != other.categoryID)
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
		return true;
	}

}
