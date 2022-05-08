package com.eshop.demo.services;

import java.util.List;

import com.eshop.demo.dto.adminDTO;

public interface adminService {

	
	 adminDTO getUser(String email,String password);
	 adminDTO addUser(adminDTO adminDTO);
	 boolean deleteuser(int id);
	 adminDTO updateuser(int id,adminDTO adminDTO);
	 List<adminDTO>getallusers();
	 adminDTO getuserbyId(int id);
	
}
