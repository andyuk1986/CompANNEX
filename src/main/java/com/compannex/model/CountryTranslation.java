package com.compannex.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "country_tr")
public class CountryTranslation implements Serializable {

	private int ID;

	private int countryId;

	private int languageId;

	private transient Country country;

	private transient Language language;

	private String name;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "country_ID")
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Column(name = "language_ID")
	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	@Transient
	public Country getCountry() {
		return country;
	}

	@Transient
	public void setCountry(Country country) {
		this.country = country;
	}

	@Transient
	public Language getLanguage() {
		return language;
	}

	@Transient
	public void setLanguage(Language language) {
		this.language = language;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + countryId;
		result = prime * result + languageId;
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
		CountryTranslation other = (CountryTranslation) obj;
		if (ID != other.ID)
			return false;
		if (countryId != other.countryId)
			return false;
		if (languageId != other.languageId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
