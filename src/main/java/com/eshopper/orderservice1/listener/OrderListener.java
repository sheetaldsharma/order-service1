package com.eshopper.orderservice1.listener;
import com.eshopper.orderservice1.dto.UserDTO;
import com.eshopper.orderservice1.exception.OrderServiceException;
import com.eshopper.orderservice1.model.OrderProducts;
import com.eshopper.orderservice1.service.OrderProductsService;
import com.eshopper.orderservice1.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderListener {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private OrderProductsService orderProductsService;
//
//    //@KafkaListener(topics = "CUSTOMER_PRODUCER")
//    public void orderDetailsListener(String message) throws IOException, OrderServiceException {
//        System.out.println("==================== Consumed message: " + message);
//        ObjectMapper mapper = new ObjectMapper();
//        UserDTO userDTO = mapper.readValue(message.getBytes(), UserDTO.class);
//        System.out.println(userDTO.toString());
//        List<OrderProducts> orderProductsList = new ArrayList<>();
//        orderProductsList = orderProductsService.getDetailedProductsInAnOrder(userDTO.getOrderId());
//    }
////
////    @KafkaListener(topics = "CUSTOMER_PRODUCER")
////    public void consumeJson(UserDTO userDto) {
////        System.out.println("_________________________________________ Consumed JSON Message: " + userDto);
////    }
}
