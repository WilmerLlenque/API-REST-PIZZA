package com.llenque.llenquepizzeria.service;

import com.llenque.llenquepizzeria.persistence.Projection.OrderSummary;
import com.llenque.llenquepizzeria.persistence.entity.OrderEntity;
import com.llenque.llenquepizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServices {
    private static final String DELIVERY="D";
    private static final String CARRYOUT="C";
    private static final String ON_SITE="S";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServices(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return orderRepository.findAll();
    }

    public List<OrderEntity> today(){
        LocalDateTime date= LocalDate.now().atTime(0,0);
        return orderRepository.findByDateAfter(date);
    }
    public List<OrderEntity> getOutsideOrders(){
        List<String> method= Arrays.asList(DELIVERY,CARRYOUT);
        return orderRepository.findByMethodIn(method);
    }

    public List<OrderEntity> getCustomerOrders(String id){
        return orderRepository.findCustomerOrders(id);
    }

    public OrderSummary sumaryOrder(int idOrder){
        return orderRepository.findSummary(idOrder);
    }
}
