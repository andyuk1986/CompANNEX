package com.compannex.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "news")
public class News implements Serializable {
	
    private int ID;
    
    private Date date;
    
    private NewsTranslation translation;
    
    private Set<NewsTranslation> translations;

    @Id
	@GeneratedValue
	@Column(name = "ID")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
	@OneToMany
	@JoinColumn (name = "industry_ID")
	public Set<NewsTranslation> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<NewsTranslation> translations) {
		this.translations = translations;
	}
	
	public NewsTranslation getTranslation() {
		return translation;
	}

	public void setTranslation(NewsTranslation translation) {
		this.translation = translation;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((translations == null) ? 0 : translations.hashCode());
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
		News other = (News) obj;
		if (ID != other.ID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (translations == null) {
			if (other.translations != null)
				return false;
		} else if (!translations.equals(other.translations))
			return false;
		return true;
	}
	
	
}
