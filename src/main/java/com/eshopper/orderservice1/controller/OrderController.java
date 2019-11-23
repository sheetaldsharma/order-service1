package com.eshopper.orderservice1.controller;

import com.eshopper.orderservice1.dto.OrderDetailsDTO;
import com.eshopper.orderservice1.dto.ProductDTO;
import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.service.OrderService;
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

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

//    @RequestMapping(value = "/all", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public List<Order> getAllOrdersDetails() {
        System.out.println("size = " + orderService.getAllOrdersDetails().size());
        return (List<Order>) orderService.getAllOrdersDetails();
    }

    @GetMapping(path = "/{orderId}/details", produces = APPLICATION_JSON_VALUE)
    public Optional<Order> getOrderDetails(@PathVariable("orderId") Integer orderId) {
        System.out.println("in getOrderDetails" + orderId);
        String orderAPI = "http://INVENTORY-SERVICE1/inventory/productIds/details";

        List<Integer> ids = Arrays.asList(1,2);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(orderAPI)
                .queryParam("productIds",ids)
                .build();
        System.out.println("------------ builder.toUriString() -------------->"+builder.toUriString());
        List<ProductDTO> details = new ArrayList<>();
        try {
            ResponseEntity<List<ProductDTO>> orderServiceResponse = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProductDTO>>() {});
            if(orderServiceResponse != null && orderServiceResponse.hasBody()){
                details = orderServiceResponse.getBody();
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        return orderService.getOrderDetails(orderId);
    }

    @PostMapping(path = "/{customerId}/orderPlaced")
    public Order createOrder(@PathVariable("customerId") Integer customerId, @RequestBody Order order) {
        return orderService.createOrder(customerId, order);
    }

//    @PutMapping("/{customerid}/{orderId}/updateOrderDetails")
//    public Order updateOrder(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId, @RequestBody Order order) {
//        return orderService.createOrder(customerId, orderId);
//    }

    @GetMapping(path = "/{customerId}/customerDetails", produces = APPLICATION_JSON_VALUE)
    public List<Order> getCustomerAllOrderDetails(@PathVariable("customerId") Integer customerId) {
        return orderService.getCustomerOrderDetails(customerId);
    }

    @GetMapping(path = "/{customerId}/{orderId}/details", produces = APPLICATION_JSON_VALUE)
    public Order getSpecificOrderDetailsForACustomer(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId) {
        return orderService.getSpecificOrderDetailsForACustomer(customerId, orderId);
    }
}