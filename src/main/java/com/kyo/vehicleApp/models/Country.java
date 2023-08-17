package com.kyo.vehicleApp.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Country {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "postBody") //description to postBody
	private String postBody;
	@Column(name = "postTitle") //capital to postTitle
	private String postTitle;

	@Column(name = "username") // Make sure the column name matches your database schema
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

}