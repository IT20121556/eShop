package com.eshop.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.demo.entity.admin;
import com.eshop.demo.entity.customer;

public interface customerRepository extends JpaRepository<customer,Integer>  {

//	@Query("SELECT customer_id, address, city, country, email, f_name, l_name, password, phone, postal_code, province FROM customer where email= ?1")
	public customer email(String email);

	public customer findByEmailAndPassword(String email,String password);
}
