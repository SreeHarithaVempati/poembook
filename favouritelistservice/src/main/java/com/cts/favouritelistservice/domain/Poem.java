package com.cts.favouritelistservice.domain;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="poem")
public class Poem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "author")
	private String author;

	@Column(name="comments")
	private String comments;
	@Column(name="userId")
	private String userId;
	@Column(name="favourite")
	private boolean favourite;
	
	
	public Poem() {
		super();
	}
	public Poem(String title, String author,  String comments, String userId,boolean favourite) {
		super();
		
		this.title = title;
		this.author = author;
		
		this.comments = comments;
		this.userId = userId;
		this.favourite=favourite;
	}
	public int getId() {
		return id;
	}
	public boolean isFavourite() {
		return favourite;
	}
	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Poem [id=" + id + ", title=" + title + ", author=" + author + ", comments=" + comments + ", userId="
				+ userId + ", favourite=" + favourite + "]";
	}
	
	
	

}
