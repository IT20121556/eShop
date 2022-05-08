package com.eshop.demo.seviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.demo.dto.CartDTO;
import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.entity.cart;
import com.eshop.demo.entity.customer;
import com.eshop.demo.repository.cartRepository;
import com.eshop.demo.services.cartService;
import com.eshop.demo.services.customerService;
@Service
public class cartServiceImpl implements cartService  {

	@Autowired
	cartRepository  cartRepository;
	@Override
	public List<CartDTO> getAllCart(int userId)
	{
		List<cart> cart=cartRepository.userId(userId);
		//System.out.println("Cart ID"+cart.get(1).getCartId());
		List<CartDTO> cartList=new ArrayList<CartDTO>();
		 //BeanUtils.copyProperties(cart, cartDTO);
		
		cart.forEach(carts ->{
			
			cartList.add(new CartDTO(
					carts.getCartId(),
					carts.getUserId(),
					carts.getQty(),
					carts.getDescription(),
					carts.getProductName(),
					carts.getPrice(),
					carts.getListprice()
					));
		});
	
		 return cartList;
		 
	}
	@Override
	public boolean deleteItem(int id)
	{
		   try {
	            cartRepository.delete(cartRepository.findById(id).get());
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	}
	@Override
	public CartDTO updateCart(int id, CartDTO cartDTO)
	{
		cart cart = cartRepository.findById(id).get();

        if (cart != null) {
            cartDTO.setCartId(id);
            CartDTO cartDTO1 = new CartDTO();
            BeanUtils.copyProperties(cartDTO, cart);
            cart save = cartRepository.save(cart);
            BeanUtils.copyProperties(save, cartDTO1);
            return cartDTO1;
        } else {
            return null;
        }
	}
	@Override
	public CartDTO addCart(CartDTO cartDTO)
	{
		    cart cart = new cart();
	        CartDTO cartDTO1 = new CartDTO();
	        BeanUtils.copyProperties(cartDTO, cart);
	        cart save = cartRepository.save(cart);
	        BeanUtils.copyProperties(save, cartDTO1);
	        return cartDTO1;
	}
	
	@Override
	public CartDTO getcartbyId(int id)
	{
		
		cart cart = cartRepository.findById(id).get();
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cart, cartDTO);
        return cartDTO;
	}
}
