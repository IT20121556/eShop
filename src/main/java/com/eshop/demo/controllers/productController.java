package com.eshop.demo.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.eshop.demo.dto.ResponseDTO;
import com.eshop.demo.dto.categoryDTO;
import com.eshop.demo.dto.productDTO;
import com.eshop.demo.dto.siteDTO;
import com.eshop.demo.excelhelper.ProductExcelExporter;
import com.eshop.demo.services.FileUploadUtil;
import com.eshop.demo.services.categoryService;
import com.eshop.demo.services.productService;
import com.eshop.demo.services.siteService;

@Controller

public class productController {

	@Autowired
	productService productService;
	@Autowired
	siteService service;
	@Autowired
	categoryService categoryService;

	// image Convert Function
	private Byte[] convertToBytes(MultipartFile file) throws IOException {
		Byte[] byteObjects = new Byte[file.getBytes().length];
		int i = 0;
		for (byte b : file.getBytes()) {
			byteObjects[i++] = b;
		}
		return byteObjects;
	}

	@RequestMapping("/eshop/products/")
	public String home_page()
	{
		return "Product/home_page";
	}
	// get all Products
	@RequestMapping("/eshop/products/ViewProducts")
	public String getallProducts(Model model) {
		List<productDTO> allproducts = (List<productDTO>) productService.getAllProducts();
		model.addAttribute("productList", allproducts);

		siteDTO site = service.getSiteDetails();
		model.addAttribute("site", site);

		return "Product/index";
	}

	// redirect to add new
	@RequestMapping("/eshop/products/addNew")
	public String redirecttoAddnew(Model model) {
		List<categoryDTO>categories =categoryService.getallCategory();
		model.addAttribute("category",categories);
		return "Product/add_product";
	}

	// redirect to update product
	@RequestMapping("eshop/products/update/{id}")
	public String redirecttoupdate(@PathVariable("id") int id, Model model) {
		productDTO productDTO = productService.getProductById(id);
		List<categoryDTO>categories =categoryService.getallCategory();
		model.addAttribute("product", productDTO);
		model.addAttribute("category",categories);
		return "Product/update_product";
	}

	// Add Product
	@RequestMapping(value = "/eshop/products/addNew/sucess/", method = RequestMethod.POST, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public String saveProductDetails(@ModelAttribute productDTO productDTO,
									 @RequestParam("imagefile") MultipartFile file) throws IOException {
		Byte[] byteObjects = convertToBytes(file);
		productDTO.setImage(byteObjects);
		productDTO dto = productService.addProduct(productDTO);

		// String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String name = Integer.toString(dto.getProductId());
		String updload = "target/classes/static/productphotos";
		FileUploadUtil.saveFile(updload, name + ".jpg", file);

		return "redirect:/eshop/products/ViewProducts";
	}

	// Update Product
	@RequestMapping(value = "/eshop/products/update/sucess/{id}", method = RequestMethod.POST, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public String UpdateProductDetails(@ModelAttribute productDTO productDTO,
									   @RequestParam("imagefile") MultipartFile file) throws IOException {
		Byte[] byteObjects = convertToBytes(file);
		productDTO.setImage(byteObjects);
		productDTO dto = productService.addProduct(productDTO);

		// String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String name = Integer.toString(dto.getProductId());
		String updload = "target/classes/static/productphotos";
		FileUploadUtil.saveFile(updload, name + ".jpg", file);

		return "redirect:/eshop/products/ViewProducts";
	}

	// Delete Product
	@RequestMapping(value = "/eshop/products/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {

		productService.deleteProduct(id);
		return "redirect:/eshop/products/ViewProducts";

	}

	// view each product
	@RequestMapping(value = "eshop/products/view/{id}")
	public String viewProductbyId(@PathVariable("id") int id, Model model) {
		productDTO product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "Product/vieweachProduct";
	}

	// Search Product
	@RequestMapping(value = "eshop/products/view", method = RequestMethod.POST)
	public String searchProductbyId(@RequestParam("id") int id, Model model) {
		productDTO product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "Product/vieweachProduct";
	}

	@RequestMapping("eshop/products/export")
	public String exportxl(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=product_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<productDTO> allproducts = (List<productDTO>) productService.getAllProducts();
		ProductExcelExporter excelExporter = new ProductExcelExporter(allproducts);
		excelExporter.export(response);
		return "redirect:/eshop/products/ViewProducts";
	}

//	@RequestMapping("/{name}")
//	public ResponseDTO findProductbyId(@PathVariable("name") String name) {
//
//		try {
//			productDTO productById = productService.getProductById(id);
//			return new ResponseDTO(200, "Success", productById);
//		} catch (NoSuchElementException e) {
//			return new ResponseDTO(200, "No Product", "");
//		}
//
//	}

	//
//	@PutMapping("/{id}")
//	public ResponseDTO updateProduct(@PathVariable("id") int id, @RequestBody productDTO studentDTO) {
//
//		try {
//			productDTO studentDTO1 = productService.updateProduct(id, studentDTO);
//			return new ResponseDTO(200, "Success", studentDTO1);
//		} catch (NoSuchElementException e) {
//			return new ResponseDTO(200, "No Product Found", "");
//		}
//
//	}

}
