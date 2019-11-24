package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.model.OrderProducts;
import com.eshopper.orderservice1.repository.OrderProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductsService {
    @Autowired
    OrderProductsRepository orderProductsRepository;

    public List<OrderProducts> getDetailedProductsInAnOrder(Integer orderNumber)
    {
        return orderProductsRepository.getDetailedProductsInAnOrder(orderNumber);
    }
}
