package com.eshop.demo.seviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.demo.dto.CartDTO;
import com.eshop.demo.dto.adminDTO;
import com.eshop.demo.entity.admin;
import com.eshop.demo.entity.cart;
import com.eshop.demo.repository.adminRepository;
import com.eshop.demo.repository.productRepository;
import com.eshop.demo.services.adminService;
@Service
public class adminServiceImpl implements adminService {
	@Autowired
	adminRepository  adminRepository;
	@Override 
	public  adminDTO getuserbyId(int id)
	{
		admin admin=adminRepository.findById(id).get();
		adminDTO adminDTO=new adminDTO();
		BeanUtils.copyProperties(admin, adminDTO);
		return adminDTO;
		
		
	}
	@Override
	public List<adminDTO>getallusers()
	{
		List<admin> admin=adminRepository.findAll();
		List<adminDTO> userList=new ArrayList<adminDTO>();
	
		
		admin.forEach(admins ->{
			
			userList.add(new adminDTO(
					
					admins.getUserId(),
					admins.getfName(),
					admins.getlName(),
					admins.getEmail(),
					admins.getPassword(),
					admins.getImage()
					
					
					));
		});
	
		 return userList;
	}
	@Override
	public adminDTO getUser(String email,String password)
	{
		admin admin=adminRepository.findByEmailAndPassword(email, password);
		adminDTO adminDTO=new adminDTO();
		if(admin.equals(null))
		{
		
		
		}
		else
		{
			adminDTO.setUserId(admin.getUserId());
			adminDTO.setfName(admin.getfName());
			adminDTO.setlName(admin.getlName());
		}
		System.out.println(admin.getUserId());
		return adminDTO;
	}
	@Override 
	public adminDTO addUser(adminDTO adminDTO)
	{
		admin admin=new admin();
		BeanUtils.copyProperties(adminDTO, admin);
		adminDTO adminDTO1=new adminDTO();
		admin save=adminRepository.save(admin);
		BeanUtils.copyProperties(save, adminDTO1);
		return adminDTO1;
	}
	@Override
	public boolean deleteuser(int id)
	{
		adminRepository.delete(adminRepository.findById(id).get());
		return true;
		
	}
	@Override 
	public adminDTO updateuser(int id,adminDTO adminDTO)
	{
		admin admin=adminRepository.findById(id).get();
		if(admin!=null)
		{
			adminDTO.setUserId(id);
			adminDTO adminDTO1=new adminDTO();
			BeanUtils.copyProperties(adminDTO,admin);
			admin save=adminRepository.save(admin);
			BeanUtils.copyProperties(save, adminDTO1);
			return adminDTO1;
			
		}
		else
		{
			return null;
		}
		
	}

}
