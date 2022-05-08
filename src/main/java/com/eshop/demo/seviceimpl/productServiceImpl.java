package com.eshop.demo.seviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.dto.productDTO;
import com.eshop.demo.entity.product;
import com.eshop.demo.repository.productRepository;
import com.eshop.demo.services.productService;


@Service
public class productServiceImpl implements productService {
	
	@Autowired
	productRepository productRepository;
	
	@Override
	public List<productDTO>getAllProducts()
	{
		
		List<productDTO>productList=new ArrayList<>();
		
		List<product>all=productRepository.findAll();
		all.forEach(product ->{
			
			productList.add(new productDTO(
					product.getProductId(),
					product.getProductName(),
					product.getBrand(),
					product.getDescription(),
					product.getCategory() ,
					product.getStatus(),
					product.getCost(),
					product.getListPrice(),
					product.getDiscount(),
					product.getImage(),
					product.getSpecification()
					));
		});
		
		return productList;
		
	}
	
	 @Override
	    public productDTO getProductById(int id) {

	        product product = productRepository.findById(id).get();
	        productDTO productDTO = new productDTO();
	        BeanUtils.copyProperties(product, productDTO);
	        return productDTO;
	        
	    }

	    @Override
	    public boolean deleteProduct(int id) {

	        try {
	            productRepository.delete(productRepository.findById(id).get());

	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }

	    }

	    @Override
	    public productDTO updateProduct(int id, productDTO productDTO) {

	    	product product = productRepository.findById(id).get();

	        if (product != null) {
	        	productDTO.setProductId(id);
	            productDTO producttDTO1 = new productDTO();
	            BeanUtils.copyProperties(productDTO, product);
	            product save = productRepository.save(product);
	            BeanUtils.copyProperties(save, producttDTO1);
	            return producttDTO1;
	        } else {
	            return null;
	        }

	    }

	    @Override
	    public productDTO addProduct(productDTO productDTO) {

	        product product = new product();
	        productDTO productDTO1 = new productDTO();
	        BeanUtils.copyProperties(productDTO, product);
	        product save = productRepository.save(product);
	        BeanUtils.copyProperties(save, productDTO1);

	        return productDTO1;
	    }


}
