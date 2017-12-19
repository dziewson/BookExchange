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

	private String authorName;

	private String authorLastName;
	
	private long authorValue;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	@Access(AccessType.PROPERTY)
	public long getId() {
		return id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAuthorValue() {
		return authorValue;
	}

	public void setAuthorValue(long authorValue) {
		this.authorValue = authorValue;
	}

}
