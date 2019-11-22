package com.eshopper.orderservice1.controller;

import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAllOrdersDetails() {
        System.out.println("size = " + orderService.getAllOrdersDetails().size());
        return (List<Order>) orderService.getAllOrdersDetails();
    }

    @GetMapping("/{orderId}/details")
    public Optional<Order> getOrderDetails(@PathVariable("orderId") Integer orderId) {
        System.out.println("in getOrderDetails" + orderId);
        return orderService.getOrderDetails(orderId);
    }

    @PostMapping("/{customerId}/orderPlaced")
    public Order createOrder(@PathVariable("customerId") Integer customerId, @RequestBody Order order) {
        return orderService.createOrder(customerId, order);
    }

//    @PutMapping("/{customerid}/{orderId}/updateOrderDetails")
//    public Order updateOrder(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId, @RequestBody Order order) {
//        return orderService.createOrder(customerId, orderId);
//    }

    @GetMapping("/{customerId}/details")
    public List<Order> getCustomerAllOrderDetails(@PathVariable("customerId") Integer customerId) {
        return orderService.getCustomerOrderDetails(customerId);
    }

    @GetMapping("/{customerId}/{orderId}/details")
    public Order getSpecificOrderDetailsForACustomer(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId) {
        return orderService.getSpecificOrderDetailsForACustomer(customerId, orderId);
    }
}