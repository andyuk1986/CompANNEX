package com.compannex.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 1/18/12
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {
    private int id;
    private Industry industryId;
    private String description;

    @Id
	@GeneratedValue
	@Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany
    @JoinColumn(name = "industry_ID")
    public Industry getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Industry industryId) {
        this.industryId = industryId;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
