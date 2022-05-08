package com.eshop.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eshop.demo.entity.cart;

public interface cartRepository extends JpaRepository<cart,Integer> {

	List<cart> userId(int id);
	
}
