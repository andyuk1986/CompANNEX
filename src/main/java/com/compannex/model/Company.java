package com.compannex.model;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company implements Serializable {
    
	private int ID;
    
    private String description;
    
    private String website;
    
    private Date createDate;
    
    private String logo;
    
    private int employmentCount;
    
    private String status;
    
    private Date addedDate;
    
    private Category categoryId;

    private CompanyTranslation translation;
    
    private Set<CompanyTranslation> translations;
    
    @Id
	@GeneratedValue
	@Column(name = "ID")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(name = "employment_count")
    public int getEmploymentCount() {
        return employmentCount;
    }

    public void setEmploymentCount(int employmentCount) {
        this.employmentCount = employmentCount;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "added_date")
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @OneToMany
    @JoinColumn(name = "category_ID")
    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }
    
	@OneToMany
	@JoinColumn (name = "company_ID")
	public Set<CompanyTranslation> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<CompanyTranslation> translations) {
		this.translations = translations;
	}
	
	public CompanyTranslation getTranslation() {
		return translation;
	}

	public void setTranslation(CompanyTranslation translation) {
		this.translation = translation;
	}	

}
