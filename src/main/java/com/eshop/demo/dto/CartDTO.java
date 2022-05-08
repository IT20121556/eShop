package com.eshop.demo.dto;

public class CartDTO {
	
 int cartId;
 int userId;
 int qty;
 String description;
 String productName;
 int price;
 int listprice;
public CartDTO(int cartId, int userId, int qty, String description, String productName, int price, int listprice) {
	
	this.cartId = cartId;
	this.userId = userId;
	this.qty = qty;
	this.description = description;
	this.productName = productName;
	this.price = price;
	this.listprice=listprice;
}

public CartDTO()
{
	
}

public int getListprice() {
	return listprice;
}

public void setListprice(int listprice) {
	this.listprice = listprice;
}

public int getCartId() {
	return cartId;
}
public void setCartId(int cartId) {
	this.cartId = cartId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
} 
 
 


}
