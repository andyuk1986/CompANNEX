package com.compannex.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "news_tr")
public class NewsTranslation implements Serializable {
    private int ID;
    private News news;
    private String header;
    private String text;
    private Language language;

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
    @JoinColumn(name = "news_ID")
    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Column(name = "header")
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @OneToMany
    @JoinColumn(name = "language_ID")
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
