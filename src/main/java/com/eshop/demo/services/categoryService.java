package com.eshop.demo.services;

import java.util.List;

import com.eshop.demo.dto.adminDTO;
import com.eshop.demo.dto.categoryDTO;

public interface categoryService {

	List<categoryDTO> getallCategory();
	categoryDTO addCategory(categoryDTO categoryDTO);
	boolean deleteCategory(int id);
	categoryDTO updateCategory(int id,categoryDTO categoryDTO);
    categoryDTO getCategorybyId(int id);
}
