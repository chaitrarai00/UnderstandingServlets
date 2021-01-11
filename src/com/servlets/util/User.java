package com.servlets.util;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID=987546382474285611L;
	
	public User(String name, String email, String id, String country) {
	
		this.name = name;
		this.email = email;
		this.id = id;
		this.country = country;
	}
	private String name;
	private String email;
	private String id;
	private String country;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", country=" + country + "]";
	}
	
}
