package com.adidas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_article")
public class Article {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private String url;
	private String image;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Article{" + "id='" + id + '\'' + ", url='" + url + '\'' + ", image='" + image + '\'' + '}';
	}	
	
}
