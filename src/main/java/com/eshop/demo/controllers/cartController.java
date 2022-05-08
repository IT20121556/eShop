package com.eshop.demo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.demo.dto.CartDTO;
import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.dto.productDTO;
import com.eshop.demo.entity.product;
import com.eshop.demo.repository.productRepository;
import com.eshop.demo.services.cartService;
import com.eshop.demo.services.productService;

@Controller
public class cartController {

	@Autowired
	cartService cartService;
	@Autowired
	productService productservice;

//Show All Cart Item
	@RequestMapping("/eshop/cart/{userid}")
	public String getallCartItems(@PathVariable("userid") int id, Model model) {
		List<CartDTO> allcartItems = cartService.getAllCart(id);
		model.addAttribute("cartdetails", allcartItems);
		model.addAttribute("userid", id);
		// System.out.println(allcartItems.get(0).getCartId());
		return "Cart/user_cart";
	}

//Delete
	@RequestMapping("/eshop/customer/cart/{userid}/{cartId}/")
	public String deleteCartItem(@PathVariable("userid") int userId, @PathVariable("cartId") int cartId) {

		cartService.deleteItem(cartId);
	return "redirect:/eshop/cart/{userid}";

	}

//Update Form
	@RequestMapping("/eshop/cart/{userid}/{cartId}/updateCart")
	public String redirectUpdate(@PathVariable("userid") int userId, @PathVariable("cartId") int cartId, Model model) {
		CartDTO cartDTO = cartService.getcartbyId(cartId);
		model.addAttribute("cart", cartDTO);
		return "Cart/update_cart";
	}

//Success Update
	@RequestMapping(value = "/eshop/cart/{userid}/{cartId}/sucess", method = RequestMethod.POST)
	public String updateCart(@PathVariable("cartId") int cartId, @PathVariable("userid") int userId,
			@ModelAttribute CartDTO cartDTO) {
		CartDTO cartDTO1 = cartService.updateCart(cartId, cartDTO);
		System.out.println(cartDTO.getListprice());
//return"redirect: redirect:/eshop/customer/";
		return "redirect:/eshop/cart/{userid}";
	}
	//Add to Cart
	@RequestMapping(value="/eshop/cart/{userid}/{productId}",method = RequestMethod.POST)
	public String addtoCart(@PathVariable("productId")int productId,@PathVariable("userid") int userid,@RequestParam ("qty")int qty,@RequestParam ("price")int price)
	{
		productDTO product=productservice.getProductById(productId);
		CartDTO cartDTO=new CartDTO(0,userid,qty,product.getDescription(),product.getProductName(),price,product.getListPrice());
		cartService.addCart(cartDTO);
		return "redirect:/eshop/customer/products/{userid}";
	}
	//Customer Home
	@RequestMapping("/eshop/customer/products/{userid}")
	public String viewproducts(@PathVariable("userid") int userid,Model model)
	{
		List<productDTO> product = (List<productDTO>) productservice.getAllProducts();
		model.addAttribute("product", product);
		model.addAttribute("userid",userid);
		return "Cart/addProducts";
	}
	@RequestMapping("/eshop/customer/products/{userid}/checkout")
	public String redirecttopayment(Model model,@PathVariable ("userid") int userid)
	{
		model.addAttribute("userid",userid);
		return"Cart/checkout";
	}
	@RequestMapping("/eshop/customer/products/{userid}/card")
	public String redirecttocard(Model model,@PathVariable ("userid") int userid)
	{
		model.addAttribute("userid",userid);
		return"Cart/CardPayment";
	}
	@RequestMapping("/eshop/customer/products/{userid}/done")
	public String redirecttodone(Model model,@PathVariable ("userid") int userid)
	{
		model.addAttribute("userid",userid);
		return"Cart/done";
	}

}
