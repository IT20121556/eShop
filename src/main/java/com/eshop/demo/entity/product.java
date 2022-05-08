package com.eshop.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;
	String productName;
	String brand;
	String category;
	int status;
	int cost;
	int listPrice;
	int discount;
	String description;
	String specification;
	Byte[] image;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
	public product()
	{
		
	}
	public product(int productId, String productName, String brand,String description, String category, int status, int cost,
			int listPrice, int discount,Byte[] image,String specification) {
		this.productId = productId;
		this.productName = productName;
		this.brand = brand;
		this.category = category;
		this.status = status;
		this.cost = cost;
		this.listPrice = listPrice;
		this.discount = discount;
		this.description=description;
		this.image=image;
		this.specification=specification;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getListPrice() {
		return listPrice;
	}
	public void setListPrice(int listPrice) {
		this.listPrice = listPrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
	

}
