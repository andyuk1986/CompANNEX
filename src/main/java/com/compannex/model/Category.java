package com.compannex.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private int ID;

	private int industryID;

	private transient Industry industry;

	private String description;

	private transient CategoryTranslation translation;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "industry_ID")
	public int getIndustryID() {
		return industryID;
	}

	public void setIndustryID(int industryID) {
		this.industryID = industryID;
	}

	@Transient
	public Industry getIndustry() {
		return industry;
	}

	@Transient
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public CategoryTranslation getTranslation() {
		return translation;
	}

	@Transient
	public void setTranslation(CategoryTranslation translation) {
		this.translation = translation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ID;
		result = prime * result + industryID;
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
		Category other = (Category) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (ID != other.ID)
			return false;
		if (industryID != other.industryID)
			return false;
		return true;
	}

}
