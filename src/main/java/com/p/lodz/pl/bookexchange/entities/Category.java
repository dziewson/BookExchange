package com.p.lodz.pl.bookexchange.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Access(AccessType.FIELD)
public class Category {
	private String categoryName;
	private long id;
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	@Access(AccessType.PROPERTY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


}
