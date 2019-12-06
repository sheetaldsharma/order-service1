package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.dto.ProductIdDTO;
import com.eshopper.orderservice1.dto.UserDTO;
import com.eshopper.orderservice1.exception.OrderServiceException;
import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.model.OrderProducts;
import com.eshopper.orderservice1.repository.OrderProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderProductsService {
    @Autowired
    OrderProductsRepository orderProductsRepository;

    public List<OrderProducts> getDetailedProductsInAnOrder(Integer orderNumber) throws OrderServiceException {
        List<OrderProducts> orderProductsList = null;

        orderProductsList = orderProductsRepository.getDetailedProductsInAnOrder(orderNumber);
        if(orderProductsList.size() == 0)
        {
            throw new OrderServiceException("No order details are available");
        }
        return orderProductsList;
    }
}
