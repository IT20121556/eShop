package com.eshop.demo.services;

import java.util.List;

import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.dto.productDTO;
import com.eshop.demo.entity.customer;

public interface customerService {
	List<customerDTO> getAllCustomer();

	customerDTO getCustomerById(int id);

	boolean deleteCustomer(int id);

	customerDTO updateCustomer(int id, customerDTO customerDTO);

	customerDTO addCustomer(customerDTO customerDTO);
	
	customerDTO find(String email,String password);
//	customerDTO login(customerDTO customerdto);
}
