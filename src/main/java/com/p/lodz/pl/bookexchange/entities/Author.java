package com.p.lodz.pl.bookexchange.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@Access(AccessType.FIELD)
public class Author {
	private long id;

	private String author;
	
	private double token;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	@Access(AccessType.PROPERTY)
	public long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getToken() {
		return token;
	}

	public void setToken(double token) {
		this.token = token;
	}

	public void setId(long id) {
		this.id = id;
	}

	

}
