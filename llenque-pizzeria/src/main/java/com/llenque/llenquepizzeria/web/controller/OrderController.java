package com.llenque.llenquepizzeria.web.controller;

import com.llenque.llenquepizzeria.persistence.Projection.OrderSummary;
import com.llenque.llenquepizzeria.persistence.entity.OrderEntity;
import com.llenque.llenquepizzeria.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServices orderServices;

    @Autowired
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping("/all")
    public List<OrderEntity> getAll(){
        return orderServices.getAll();
    }

    @GetMapping("/today")
    public List<OrderEntity> today(){
        return orderServices.today();
    }

    @GetMapping("/outside")
    public List<OrderEntity> getOutsideOrders(){
        return orderServices.getOutsideOrders();
    }

    @GetMapping("/customer/{id}")
    public List<OrderEntity> getCustomerOrders(@PathVariable String id){
        return orderServices.getCustomerOrders(id);
    }

    @GetMapping("/sumary/{idOrder}")
    public OrderSummary sumaryOrder(@PathVariable int idOrder){
        return orderServices.sumaryOrder(idOrder);
    }
}
