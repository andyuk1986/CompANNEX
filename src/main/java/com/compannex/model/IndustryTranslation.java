package com.compannex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "industry_tr")
public class IndustryTranslation implements java.io.Serializable {
	
	private String ID;
	
	private String name;
	
	private int industryID;
	
	private int languageID;
	
	private transient Language language;
	
	private transient Industry industry;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "industry_ID")
	public int getIndustryID() {
		return industryID;
	}

	public void setIndustryID(int industryID) {
		this.industryID = industryID;
	}

	@Column(name = "language_ID")
	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	@Transient
	public Language getLanguage() {
		return language;
	}

	@Transient
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	@Transient
	public Industry getIndustry() {
		return industry;
	}
	
	@Transient
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + industryID;
		result = prime * result + languageID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		IndustryTranslation other = (IndustryTranslation) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (industryID != other.industryID)
			return false;
		if (languageID != other.languageID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
