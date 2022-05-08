package com.eshop.demo.seviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eshop.demo.dto.customerDTO;
import com.eshop.demo.entity.customer;
import com.eshop.demo.repository.customerRepository;
import com.eshop.demo.services.customerService;

@Service
public class customerServiceImpl implements customerService {

	@Autowired
	customerRepository customerRepository;
	
	@Override
	public List<customerDTO> getAllCustomer()
	{
		
		List<customerDTO>customerList=new ArrayList<>();
		
//		customer customers=customerRepository.email("p.kkg@live.com");
//		customerDTO customerDTO1=new customerDTO();
//		BeanUtils.copyProperties(customers, customerDTO1);
//		System.out.println(customerDTO1.getAddress());
		List<customer>all=customerRepository.findAll();
		all.forEach(customer ->{
			
			customerList.add(new customerDTO(
					customer.getCustomerId(),
					customer.getfName(),
					customer.getlName(),
					customer.getEmail() ,
					customer.getPassword(),
					customer.getPhone(),
					customer.getAddress(),
					customer.getCity(),
					customer.getCountry(),
					customer.getProvince(),
					customer.getPostalCode()
					));
		});
		
		return customerList;
		
	}
	
	 @Override
	    public customerDTO getCustomerById(int id) {

	        customer customer = customerRepository.findById(id).get();
	        customerDTO customerDTO = new customerDTO();
	        BeanUtils.copyProperties(customer, customerDTO);
	        return customerDTO;
	        
	    }

	    @Override
	    public boolean deleteCustomer(int id) {

	        try {
	            customerRepository.delete(customerRepository.findById(id).get());

	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }

	    }

	    @Override
	    public customerDTO updateCustomer(int id, customerDTO customerDTO) {

	    	customer customer = customerRepository.findById(id).get();

	        if (customer != null) {
	            customerDTO.setCustomerId(id);
	            customerDTO customerDTO1 = new customerDTO();
	            BeanUtils.copyProperties(customerDTO, customer);
	            customer save = customerRepository.save(customer);
	            BeanUtils.copyProperties(save, customerDTO1);
	            return customerDTO1;
	        } else {
	            return null;
	        }

	    }

	    @Override
	    public customerDTO addCustomer(customerDTO customerDTO) {

	        customer customer = new customer();
	        customerDTO customerDTO1 = new customerDTO();
	        BeanUtils.copyProperties(customerDTO, customer);
	        customer save = customerRepository.save(customer);
	        BeanUtils.copyProperties(save, customerDTO1);
	        return customerDTO1;
	    }
	    
	    @Override
	    public customerDTO find(String email,String password)
	    {
	    	customer customer=new customer();
	    	customer=customerRepository.findByEmailAndPassword(email, password);
	    	customerDTO customerDTO=new customerDTO();
	    	BeanUtils.copyProperties(customer, customerDTO);
	    	return customerDTO;
//	    	customerDTO customerDTO2=new customerDTO();
//	    	BeanUtils.copyProperties(customerDTO, customer);
//	    	  customer save = customerRepository.findBy(customerDTO.getEmail(), customerDTO.getPassword());
	    	
	    }
//	    @Override
//	    public customerDTO login (customerDTO customerDTO)
//	    {
//	    	customer customer=new customer();
//	    	customerDTO customerDTO2=new customerDTO();
//	    	BeanUtils.copyProperties(customerDTO, customer);
//	    	  customer save = customerRepository.findBy(customerDTO.getEmail(), customerDTO.getPassword());
//	    }

}
