package com.compannex.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 1/28/12
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "news")
public class News implements Serializable {
    private int ID;
    private Date date;

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
}
