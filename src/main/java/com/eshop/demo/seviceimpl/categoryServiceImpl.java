package com.eshop.demo.seviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.demo.dto.adminDTO;
import com.eshop.demo.dto.categoryDTO;
import com.eshop.demo.entity.admin;
import com.eshop.demo.entity.category;
import com.eshop.demo.repository.cartRepository;
import com.eshop.demo.repository.categoryRepository;
import com.eshop.demo.services.categoryService;

@Service
public class categoryServiceImpl implements categoryService  {
	@Autowired
	categoryRepository categoryRepository;
	@Override
   public List<categoryDTO> getallCategory()
	{
	   List<category> category=categoryRepository.findAll();
		List<categoryDTO> categorylist=new ArrayList<categoryDTO>();
	category.forEach(categorys ->{
			
			categorylist.add(new categoryDTO(
					
					categorys.getCategory_id(),
					categorys.getCategory_name(),
					categorys.getAlias(),
					categorys.getEnabled()
					));
		});
		return categorylist;
		
	}
	@Override
	public categoryDTO addCategory(categoryDTO categoryDTO)
	{
		category category=new category();
		BeanUtils.copyProperties(categoryDTO, category);
		categoryDTO categoryDTO1=new categoryDTO();
		category save=categoryRepository.save(category);
		BeanUtils.copyProperties(save, categoryDTO1);
		return categoryDTO1;
	}
	@Override
	public boolean deleteCategory(int id)
	{
		categoryRepository.delete(categoryRepository.findById(id).get());
		return true;
	}
	@Override
	public categoryDTO updateCategory(int id,categoryDTO categoryDTO)
	{
		category category=categoryRepository.findById(id).get();
		if(category!=null)
		{
			categoryDTO.setCategory_id(id);
			categoryDTO categoryDTO1=new categoryDTO();
			BeanUtils.copyProperties(categoryDTO,category);
			category save=categoryRepository.save(category);
			BeanUtils.copyProperties(save, categoryDTO1);
			return categoryDTO1;
			
		}
		else
		{
			return null;
		}
		
	}
	@Override
	public categoryDTO getCategorybyId(int id)
    {
		category category=categoryRepository.findById(id).get();
		categoryDTO categoryDTO=new categoryDTO();
		BeanUtils.copyProperties(category, categoryDTO);
		return categoryDTO;
    }
}
