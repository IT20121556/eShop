package com.eshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.demo.entity.product;

public interface productRepository extends JpaRepository<product, Integer> {

}
