package com.compannex.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 1/18/12
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "category_tr")
public class CategoryTranslation implements Serializable {
    private int id;
    private Category category;
    private Language language;
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

    @OneToMany
    @JoinColumn(name = "category_ID")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany
    @JoinColumn(name = "language_ID")
    public Language getLanguage() {
        return language;
    }

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
}
