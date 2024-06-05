package com.llenque.llenquepizzeria.web.controller;

import com.llenque.llenquepizzeria.persistence.entity.CustomerEntity;
import com.llenque.llenquepizzeria.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerServices customerServices;

    @Autowired
    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping("/findByPhone/{phoneNumber}")
    public CustomerEntity findByPhone(@PathVariable String phoneNumber){
        return this.customerServices.findByPhone(phoneNumber);
    }
}
