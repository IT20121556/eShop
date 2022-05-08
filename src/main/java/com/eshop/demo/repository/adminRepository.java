package com.eshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.demo.dto.adminDTO;
import com.eshop.demo.entity.admin;


public interface adminRepository extends JpaRepository<admin,Integer> {
	
	admin findByEmailAndPassword(String email,String password);
	
	

}
