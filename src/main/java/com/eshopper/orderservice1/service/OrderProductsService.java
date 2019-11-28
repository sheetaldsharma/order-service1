package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.dto.ProductIdDTO;
import com.eshopper.orderservice1.dto.UserDTO;
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

    @Autowired
    private KafkaTemplate<String, UserDTO> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    private KafkaTemplate<String, ProductIdDTO> kafkaTemplateProduct;

    //    private static final String TOPIC = "kafka-common";
//    private static final String TOPIC = "CUSTOMER_PRODUCER";

    public List<OrderProducts> getDetailedProductsInAnOrder(Integer orderNumber)
    {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        System.out.println("+++++++++++++++++++++++ getDetailedProductsInAnOrder ++++++++++++++++++");
//        System.out.println(list.size());
////        kafkaTemplateListInt.send("ORDER_PRODUCT_DETAILS", list);
////        kafkaTemplateString.send("ORDER_PRODUCT_DETAILS", "Hello from order");
//        ProductIdDTO productIdDTO = new ProductIdDTO();
//        productIdDTO.setProductIds(Arrays.asList(1,2));

//        kafkaTemplateProduct.send("ORDER_PRODUCT_DETAILS", productIdDTO);
        return orderProductsRepository.getDetailedProductsInAnOrder(orderNumber);
    }
}
