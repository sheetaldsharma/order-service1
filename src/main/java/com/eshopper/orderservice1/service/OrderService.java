package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService{
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrdersDetails()
    {
        return (List<Order>)orderRepository.findAll();
    }

    public Optional<Order> getOrderDetails(Integer orderId)
    {
        System.out.println("in service ="+orderId);
        return orderRepository.findById(orderId);
    }

    public List<Order> getCustomerOrderDetails(Integer customerId)
    {
        return orderRepository.findOrdersByCustomerId(customerId);
    }

    public Order createOrder(Integer customerId, Order order)
    {
        return orderRepository.save(order);
    }

    public Order getSpecificOrderDetailsForACustomer(Integer customerId, Integer orderId)
    {
        return orderRepository.findSpecificOrderDetailsForACustomer(customerId, orderId);
    }

}
