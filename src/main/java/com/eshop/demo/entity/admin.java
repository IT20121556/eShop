package com.eshop.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	String fName;
	String lName;
	String email;
	String password; 
	Byte[] Image;
	
	
	
	
	public admin()
	{
		
	}
	public admin(int userId, String fName, String lName, String email, String password, Byte[] image) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		Image = image;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Byte[] getImage() {
		return Image;
	}
	public void setImage(Byte[] image) {
		Image = image;
	}
	
	
}
