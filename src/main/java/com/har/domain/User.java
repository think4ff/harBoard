package com.har.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	private String userId;
	private String password;
	private String name;
	private String email;

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPasword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void update(User modUser) {
//		this.password = modUser.password; //TODO:why parameter is null?
		this.name = modUser.name;
		this.email = modUser.email;
	}

	public boolean matchPassword(String inPassword) {
		if (inPassword == null)
			return false;

		return inPassword.equals(this.password);
	}

	public boolean matchId(Long inId) {
		if (inId == null)
			return false;

		return inId.equals(this.id);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

}
