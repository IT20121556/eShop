package com.eshop.demo.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.dto.productDTO;
import com.eshop.demo.dto.siteDTO;
import com.eshop.demo.excelhelper.CustomerExcelExporter;
import com.eshop.demo.excelhelper.ProductExcelExporter;
import com.eshop.demo.services.customerService;
import com.eshop.demo.services.siteService;

@Controller

public class customerController {
	@Autowired
	customerService customerService;
	@Autowired
	siteService  service;
	// Show All Customers
	@RequestMapping("/eshop/customer/")
	public String getallCustomers(Model model) {
		List<customerDTO> allcustomer = customerService.getAllCustomer();
		siteDTO site=service.getSiteDetails();
		model.addAttribute("site", site);
		model.addAttribute("Customers", allcustomer);
		return "Customer/index";
	}
	

	@RequestMapping(value = "/eshop/customer/addNew/sucess/", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute customerDTO customerDTO) {

		customerDTO customer = customerService.addCustomer(customerDTO);
		return "redirect:/eshop/customer/";

	}

	// Success Update
	@RequestMapping(value = "/eshop/customer/update/sucess/{id}", method = RequestMethod.POST)
	public String updateCustomer(@PathVariable("id") int id, @ModelAttribute customerDTO customerDTO) {

		customerDTO customerDTO1 = customerService.updateCustomer(id, customerDTO);
		return "redirect:/eshop/customer/";

	}

	// Update Form
	@RequestMapping("/eshop/customer/update/{id}")
	public String redirecttopage(@PathVariable("id") int id, Model model) {
		customerDTO customerDTO = customerService.getCustomerById(id);
		model.addAttribute("customer", customerDTO);
		return "Customer/update_customer";

	}

	// Redirecting to Add New
	@RequestMapping("eshop/customer/addNew")
	public String redirecttopage() {
		return "Customer/add_new";
	}

	// Delete Customer
	@RequestMapping("/eshop/customer/delete/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {

		customerService.deleteCustomer((id));
		return "redirect:/eshop/customer/";

	}

	// View Each Customer
	@RequestMapping("/eshop/customer/view/{id}")
	public String findCustomerbyId(@PathVariable("id") int id, Model model) {

		customerDTO customerById = customerService.getCustomerById(id);
		model.addAttribute("customer", customerById);
		return "Customer/view_customer";

	}

	// Search Customer
	@RequestMapping("/eshop/customer/view")
	public String viewEachCustomer(@RequestParam("id") int id, Model model) {
		customerDTO customerById = customerService.getCustomerById(id);
		model.addAttribute("customer", customerById);
		return "Customer/view_customer";

	}
	
	@RequestMapping("eshop/customer/export")
	public String exportxl(HttpServletResponse response)throws IOException
	{
		response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<customerDTO> allcustomer = customerService.getAllCustomer();
		 CustomerExcelExporter excelExporter = new CustomerExcelExporter(allcustomer);
	        excelExporter.export(response);    
	        return "redirect:/eshop/customer/";
	}
	
	@RequestMapping ("eshop/customer/login")
	public String login(@RequestParam ("password")String password,@RequestParam("email")String email,Model model)
	{
		String n;
		try
		{
			customerDTO customerDTO=customerService.find(email, password);
			model.addAttribute("customer_id",customerDTO.getCustomerId());
			System.out.println(customerDTO.getCustomerId());
			int userid=customerDTO.getCustomerId();
			n="Cart/home_page";
			//n="redirect:/eshop/customer/products/"+userid+"";
		
		}
		catch(Exception ex)
		{
			model.addAttribute("err","Incorrect Login Details");
			n="Common/login_page";
		}
		
		return n;
	}

}
