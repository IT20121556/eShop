package com.eshop.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int customerId;
	String fName;
	String lName;
	String email;
	String password;
	int phone;
	String address;
	String city;
	String country;
	String province;
	int postalCode;
	public customer(int customerId, String fName, String lName, String email, String password, int phone,
			String address, String city, String country, String province, int postalCode) {
	
		this.customerId = customerId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.province = province;
		this.postalCode = postalCode;
	}
	
	public customer()
	{
		
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	

}
