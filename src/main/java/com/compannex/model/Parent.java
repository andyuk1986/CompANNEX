package com.compannex.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "parent")
public class Parent implements Serializable {
    private int ID;
    private News parentId;
    private News childId;
    private Date addedDate;
    private Date createDate;
    private String description;

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
    @JoinColumn(name = "parent_ID")
    public News getParentId() {
        return parentId;
    }

    public void setParentId(News parentId) {
        this.parentId = parentId;
    }

    @OneToMany
    @JoinColumn(name = "child_ID")
    public News getChildId() {
        return childId;
    }

    public void setChildId(News childId) {
        this.childId = childId;
    }

    @Column(name = "added_date")
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
