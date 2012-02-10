package com.compannex.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "category_tr")
public class CategoryTranslation implements Serializable {
    
	private int id;
    
	private int categoryId;
	
	private int languageId;
	
    private transient Category category;
    
    private transient Language language;
    
    private String name;

    @Id
	@GeneratedValue
	@Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "category_ID")
    public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "language_ID")
	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
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
		result = prime * result + categoryId;
		result = prime * result + id;
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
		CategoryTranslation other = (CategoryTranslation) obj;
		if (categoryId != other.categoryId)
			return false;
		if (id != other.id)
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
