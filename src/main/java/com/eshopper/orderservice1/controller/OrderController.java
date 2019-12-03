package com.eshopper.orderservice1.controller;

import ch.lambdaj.Lambda;
import com.eshopper.orderservice1.dto.OrderDetailsDTO;
import com.eshopper.orderservice1.dto.ProductDTO;
import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.model.OrderProducts;
import com.eshopper.orderservice1.service.OrderProductsService;
import com.eshopper.orderservice1.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProductsService orderProductsService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public List<Order> getAllOrdersDetails() {
        System.out.println("size = " + orderService.getAllOrdersDetails().size());
        return orderService.getAllOrdersDetails();
    }

    //Get order details from order table
    @GetMapping(path = "/{orderId}/details", produces = APPLICATION_JSON_VALUE)
    public Optional<Order> getOrderDetails(@PathVariable("orderId") Integer orderId) {
        return orderService.getOrderDetails(orderId);
    }

    //get all orders for a customer
    @GetMapping(path = "/{customerId}/orderDetails", produces = APPLICATION_JSON_VALUE)
    public List<Order> getCustomerAllOrderDetails(@PathVariable("customerId") Integer customerId) {
        return orderService.getCustomerOrderDetails(customerId);
    }

    //Get specific order details including products

    @GetMapping(path = "/{orderNumber}/product/details", produces = APPLICATION_JSON_VALUE)
    public List<OrderProducts> getSpecificOrderDetailsForACustomer(@PathVariable("orderNumber") Integer orderNumber) throws JsonProcessingException {
        return orderProductsService.getDetailedProductsInAnOrder(orderNumber);
    }


    @PostMapping(path = "/customer/{customerId}/orderPlaced")
    public Order createOrder(@PathVariable("customerId") Integer customerId, @RequestBody Order order) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%% "+order.getOrderProductsList().toString());
        return orderService.createOrder(customerId, order);
    }
//
////    @PutMapping("/{customerid}/{orderId}/updateOrderDetails")
////    public Order updateOrder(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId, @RequestBody Order order) {
////        return orderService.createOrder(customerId, orderId);
////    }
}