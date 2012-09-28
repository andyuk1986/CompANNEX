package com.compannex.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "category_tr")
public class CategoryTranslation implements Serializable {
    
	private int ID;
    
	private int categoryID;
	
	private int languageID;
	
    private transient Category category;
    
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
    
    @Column(name = "category_ID")
    public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	@Column(name = "language_ID")
	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	@Transient
	public Category getCategory() {
        return category;
    }

	@Transient
    public void setCategory(Category category) {
        this.category = category;
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
		result = prime * result + categoryID;
		result = prime * result + ID;
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
		CategoryTranslation other = (CategoryTranslation) obj;
		if (categoryID != other.categoryID)
			return false;
		if (ID != other.ID)
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
