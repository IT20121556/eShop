package com.eshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.demo.entity.category;

public interface categoryRepository extends JpaRepository<category, Integer> {

}
