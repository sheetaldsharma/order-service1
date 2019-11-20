package com.eshopper.orderservice1.controller;

import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders()
    {
        System.out.println("size = "+ orderService.getAllOrders().size());
        return (List<Order>) orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Optional<Order> getOrderDetails(@PathVariable("orderId") Integer orderId)
    {
        System.out.println("in getOrderDetails"+orderId);
        return orderService.getOrderDetails(orderId) ;
    }

    @GetMapping("/{customerId}/orderDetails")
    public List<Order> getCustomerOrderDetails(@PathVariable("customerId") Integer customerId)
    {
        return orderService.getCustomerOrderDetails(customerId);
    }
}