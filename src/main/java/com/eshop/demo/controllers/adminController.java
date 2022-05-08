package com.eshop.demo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.demo.dto.adminDTO;
import com.eshop.demo.dto.productDTO;
import com.eshop.demo.services.FileUploadUtil;
import com.eshop.demo.services.adminService;
@Controller
public class adminController {
	@Autowired
	adminService adminService;
	 // image Convert Function
		private Byte[] convertToBytes(MultipartFile file) throws IOException {
			Byte[] byteObjects = new Byte[file.getBytes().length];
			int i = 0;
			for (byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}
			return byteObjects;
		}

	@RequestMapping("eshop/admin")
	public String getadmin()
	{
		String path;
        String msg;
		adminDTO adminDTO=adminService.getUser("difj@g", "12345");
		if(adminDTO.getUserId()>0)
		{
			path="Cart/user_cart";
		}
		else
		{
			path="Cart/update_cart";
		}
	
		return path;
	}
	
	//get all users
	@RequestMapping("eshop/admin/home")
	public String homepage(Model model)
	{
		List<adminDTO> allusers = (List<adminDTO>) adminService.getallusers();
		model.addAttribute("userlist", allusers);
		return "Admin/index";
	}
	//search user
		@RequestMapping(value = "eshop/admin/view", method = RequestMethod.POST)
		public String searchProductbyId(@RequestParam("id") int id,Model model)
		{
			adminDTO admin=adminService.getuserbyId(id);
			model.addAttribute("user",admin);
			return"Admin/vieweach";
		}
		//view each user
				@RequestMapping(value = "eshop/admin/view/{id}")
				public String viewUserbyId(@PathVariable("id") int id,Model model)
				{
					adminDTO admin=adminService.getuserbyId(id);
					model.addAttribute("user",admin);
					return"Admin/vieweach";
				}
				
	//redirect to add user
	@RequestMapping("eshop/admin/adduser")
	public String adduserpage()
	{
		return "Admin/adduser";
	}
	//adduser action
	@RequestMapping(value="eshop/admin/adduser/sucess", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public String addsuccessuser(@ModelAttribute adminDTO adminDTO,@RequestParam("imagefile")MultipartFile file)throws IOException
	{
		Byte[] byteObjects = convertToBytes(file);
		adminDTO.setImage(byteObjects);
		adminDTO dto = adminService.addUser(adminDTO);
		String name = Integer.toString(dto.getUserId());
		String updload = "target/classes/static/userphotos";
		FileUploadUtil.saveFile(updload, name + ".jpg", file);

		return "redirect:/eshop/admin/home";
	}
	
	// redirect to update User
		@RequestMapping("eshop/admin/update/{id}")
		public String redirecttoupdate(@PathVariable("id") int id, Model model) {
			adminDTO adminDTO = adminService.getuserbyId(id);
			model.addAttribute("user", adminDTO);
			System.out.println(adminDTO.getPassword());
			return "Admin/update";
		}
	
	// Update User
	@RequestMapping(value = "/eshop/admin/update/sucess/{id}", method = RequestMethod.POST, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public String UpdateUserDetails(@ModelAttribute adminDTO adminDTO,
			@RequestParam("imagefile") MultipartFile file) throws IOException {
		Byte[] byteObjects = convertToBytes(file);
		adminDTO.setImage(byteObjects);
		adminDTO dto = adminService.addUser(adminDTO);
		String name = Integer.toString(dto.getUserId());
		String updload = "target/classes/static/userphotos";
		FileUploadUtil.saveFile(updload, name + ".jpg", file);
		return "redirect:/eshop/admin/home";
	}
	//delete user
	@RequestMapping("eshop/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id)
	{
		adminService.deleteuser(id);
		return"redirect:/eshop/admin/home";
	}
	
	
}
