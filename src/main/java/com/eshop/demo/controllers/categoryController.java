package com.eshop.demo.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.demo.dto.categoryDTO;
import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.entity.admin;
import com.eshop.demo.entity.category;
import com.eshop.demo.excelhelper.CategroyExcelExporter;
import com.eshop.demo.excelhelper.CustomerExcelExporter;
import com.eshop.demo.services.categoryService;

@Controller
public class categoryController  {
	
	@Autowired
	categoryService categoryService;
	
	@RequestMapping("eshop/category/viewall")
	public String viewallCategory(Model model)
	{
		List<categoryDTO> list=new ArrayList<categoryDTO>();
		list=categoryService.getallCategory();
		model.addAttribute("list",list);
		return "Category/index";
	}
	
	@RequestMapping("eshop/category/addnew")
	public String redirectAdd()
	{
		return "Category/add_category";
	}
	
	@RequestMapping("eshop/category/addnew/success")
	public String save(@ModelAttribute categoryDTO categoryDTO)
	{
		
		categoryDTO dto=new categoryDTO();
		dto=categoryService.addCategory(categoryDTO);
		return "redirect:/eshop/category/viewall";
	}
	@RequestMapping("eshop/category/delete/{id}")
	public String deleteCategory(@PathVariable ("id") int id)
	{
		categoryService.deleteCategory(id);
		return "redirect:/eshop/category/viewall";
	}
	@RequestMapping("eshop/category/update/{id}")
	public String redirectUpdate(@PathVariable ("id")int id,Model model)
	{
		categoryDTO categoryDTO=new categoryDTO();
		categoryDTO=categoryService.getCategorybyId(id);
		model.addAttribute("category",categoryDTO);
		return "Category/update_category";
	}
	@RequestMapping("/eshop/category/update/success")
	public String updateCategory(@ModelAttribute categoryDTO categoryDTO)
	{
		categoryService.updateCategory(categoryDTO.getCategory_id(),categoryDTO);
		return "redirect:/eshop/category/viewall";
	}
	@RequestMapping("/eshop/category/view")
	public String viewsingle(Model model,@RequestParam ("id") int id)
	{
		categoryDTO categoryDTO=new categoryDTO();
		categoryDTO=categoryService.getCategorybyId(id);
		model.addAttribute("category",categoryDTO);
		return "Category/view_each";
	}
	@RequestMapping("/eshop/category/view/{id}")
	public String viewEach(@PathVariable("id")int id,Model model)
	{
		categoryDTO categoryDTO=new categoryDTO();
		categoryDTO=categoryService.getCategorybyId(id);
		model.addAttribute("category",categoryDTO);
		return "Category/view_each";
	}
	@RequestMapping("eshop/caegroy/export")
	public String exportxl(HttpServletResponse response)throws IOException
	{
		response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=categroy_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<categoryDTO> allcustomer = categoryService.getallCategory();
        CategroyExcelExporter excelExporter = new CategroyExcelExporter(allcustomer);
	        excelExporter.export(response);    
	        return "redirect:/eshop/customer/";
	}
	
	
	
	
	
	

}
