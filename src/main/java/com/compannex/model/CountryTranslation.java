package com.compannex.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 1/28/12
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "country_tr")
public class CountryTranslation implements Serializable {
    private int ID;
    private Country countryId;
    private Language languageId;
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

    @OneToMany
    @JoinColumn(name = "country_ID")
    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
    }

    @OneToMany
    @JoinColumn(name = "language_ID")
    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
