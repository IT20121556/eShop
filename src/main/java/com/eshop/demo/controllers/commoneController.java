package com.eshop.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.demo.dto.siteDTO;
import com.eshop.demo.services.siteService;

@Controller
public class commoneController {
	@Autowired
	siteService  service;
	@RequestMapping("")
	public String loginPage()
	{
		
		return"Common/login_page";
	}

	@RequestMapping("/Admin")
	public String adminloginpage(Model model	)
	{
		siteDTO site=service.getSiteDetails();
		model.addAttribute("site", site);
		return"Common/ad_login_page";
	}
}
