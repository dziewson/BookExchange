package com.p.lodz.pl.bookexchange.entities;

import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.p.lodz.pl.bookexchange.utils.ImageUtils;

@Entity
@Table(name = "posts")
@Access(AccessType.FIELD)
public class Post {
	private long id;
	private String text;
	private String authorName;
	private Timestamp dateTime;
	private byte[] image;
	private byte[] authorImage;
	private String bookCategory;
	private boolean bookPost;
	private long bookCategoryId;

	@Id
	@GeneratedValue
	@Column(name = "post_id", unique = true, nullable = false)
	@Access(AccessType.PROPERTY)
	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
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

	public byte[] getAuthorImage() {
		return authorImage;
	}

	public String getAuthorDecodedImage() {
		return ImageUtils.getDecodedImage(authorImage);
	}

	public void setAuthorImage(byte[] authorImage) {
		this.authorImage = authorImage;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public boolean isBookPost() {
		return bookPost;
	}

	public void setBookPost(boolean bookPost) {
		this.bookPost = bookPost;
	}

	public long getBookCategoryId() {
		return bookCategoryId;
	}

	public void setBookCategoryId(long bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}

}
