package com.p.lodz.pl.bookexchange.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intrests")
@Access(AccessType.FIELD)
public class Intrests {
	private long categoryId;
	private long id;
	private long userId;
	private long authorValue;
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	@Access(AccessType.PROPERTY)
	public long getId() {
		return id;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getAuthorValue() {
		return authorValue;
	}
	public void setAuthorValue(long authorValue) {
		this.authorValue = authorValue;
	}
	
}
