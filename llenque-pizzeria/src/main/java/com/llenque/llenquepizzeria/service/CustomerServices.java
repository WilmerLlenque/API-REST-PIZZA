package com.llenque.llenquepizzeria.service;

import com.llenque.llenquepizzeria.persistence.entity.CustomerEntity;
import com.llenque.llenquepizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phoneNumber){
        return  this.customerRepository.findByPhone(phoneNumber);
    }
}
