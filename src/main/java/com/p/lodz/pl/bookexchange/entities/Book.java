package com.p.lodz.pl.bookexchange.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.p.lodz.pl.bookexchange.utils.ImageUtils;

@Entity
@Table(name = "books")
@Access(AccessType.FIELD)
public class Book {

	private long id;

	private long ownerId;

	private String title;

	private String authorName;

	private String authorLastName;

	private String ownerName;

	private String edition;

	private byte[] image;

	@Id
	@GeneratedValue
	@Column(name = "book_id", unique = true, nullable = false)
	@Access(AccessType.PROPERTY)
	public long getId() {
		return id;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getDecodedImage() {
		return ImageUtils.getDecodedImage(getImage());
	}

}
