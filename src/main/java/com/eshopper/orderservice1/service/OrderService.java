package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.dto.UserDTO;
import com.eshopper.orderservice1.exception.OrderServiceException;
import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, UserDTO> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    public List<Order> getAllOrdersDetails() throws OrderServiceException {

        List<Order> orderList = null;
        orderList = orderRepository.findAll();
        if(orderList.size() == 0)
        {
            throw new OrderServiceException("No order details are available");
        }
        return orderList;
    }

    public Optional<Order> getOrderDetails(Integer orderId) throws OrderServiceException {

        Optional<Order> order = Optional.empty();
        order = orderRepository.findById(orderId);
        if(order.isEmpty())
        {
            throw new OrderServiceException("No such order available");
        }
        return order;
    }


    public List<Order> getCustomerOrderDetails(Integer customerId) throws OrderServiceException {
        List<Order> orderList = null;
        orderList = orderRepository.findOrdersByCustomerId(customerId);
        if(orderList.size() == 0)
        {
            throw new OrderServiceException("No order details are available for a customer");
        }
        return orderList;
    }

    public Order createOrder(Order order) throws OrderServiceException {
        Order order1 = null;
        try {
            order1 = orderRepository.save(order);
        }
        catch (Exception ex)
        {
            throw new OrderServiceException("Unable to create new order");
        }
        return  order1;
    }
}
