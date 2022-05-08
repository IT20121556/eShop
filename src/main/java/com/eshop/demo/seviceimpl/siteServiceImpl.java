package com.eshop.demo.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.demo.dto.siteDTO;
import com.eshop.demo.entity.site;
import com.eshop.demo.repository.siteRepository;
import com.eshop.demo.services.siteService;

@Service
public class siteServiceImpl implements siteService{

	@Autowired
	siteRepository siterepository;
	
	@Override
	public siteDTO getSiteDetails()
	{
		site site=siterepository.getById(1);
		siteDTO siteDTO=new siteDTO();
		siteDTO.setFooter(site.getFooter());
		siteDTO.setHeader(site.getHeader());
		return siteDTO;
		
	}
	@Override
	public siteDTO updateSiteDetails()
	{
		siteDTO siteDTO=new siteDTO();
		return siteDTO;
	}
	@Override
	public siteDTO saveSiteDetails()
	{
		siteDTO siteDTO=new siteDTO();
		return siteDTO;
	}
}
