package com.eshop.demo.services;

import java.util.List;

import com.eshop.demo.dto.productDTO;


public interface productService {

	List<productDTO> getAllProducts();

	productDTO getProductById(int id);

	boolean deleteProduct(int id);

	productDTO updateProduct(int id, productDTO productDTO);

	productDTO addProduct(productDTO productDTO);

}
