package com.compannex.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "news_tr")
public class NewsTranslation implements Serializable {

	private int ID;

	private int newsID;

	private int languageID;

	private transient News news;

	private String header;

	private String text;

	private transient Language language;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "news_ID")
	public int getNewsID() {
		return newsID;
	}

	public void setNewsID(int newsID) {
		this.newsID = newsID;
	}

	@Column(name = "language_ID")
	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	@Transient
	public News getNews() {
		return news;
	}

	@Transient
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

	@Transient
	public Language getLanguage() {
		return language;
	}

	@Transient
	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + languageID;
		result = prime * result + newsID;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		NewsTranslation other = (NewsTranslation) obj;
		if (ID != other.ID)
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (languageID != other.languageID)
			return false;
		if (newsID != other.newsID)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
}
