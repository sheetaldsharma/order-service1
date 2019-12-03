package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.dto.UserDTO;
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

//    @Autowired
//    private KafkaTemplate<String, List<Integer>>kafkaTemplateListInt;


    private static final String TOPIC = "ORDER_DETAILS";

    public List<Order> getAllOrdersDetails()
    {
        return orderRepository.findAll();
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


}
