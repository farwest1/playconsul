package com.moeller.rest;

import com.moeller.business.domain.Customer;
import com.moeller.business.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bernd on 26.06.2017.
 *
 * Package com.moeller.rest
 */
@Controller
public class CustomerController {

  @Autowired
  CustomerRepository customerRepository;

  @RequestMapping("/customer")
  @ResponseBody
  public String myCustomer(){

    customerRepository.save(new Customer("Bernd", "Moeller"));

    return "Hello Customer";
  }

}
