package com.eshop.demo.services;

import java.util.List;

import com.eshop.demo.dto.CartDTO;
import com.eshop.demo.dto.customerDTO;

public interface cartService  {

	List<CartDTO> getAllCart(int userId);
	
	boolean deleteItem(int id);

	CartDTO updateCart(int id, CartDTO cartDTO);

	CartDTO addCart(CartDTO cartDTO);
	
	CartDTO getcartbyId(int id);
	
}
