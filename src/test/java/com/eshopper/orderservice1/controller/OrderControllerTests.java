package com.eshopper.orderservice1.controller;

import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.model.OrderProducts;
import com.eshopper.orderservice1.service.OrderProductsService;
import com.eshopper.orderservice1.service.OrderService;
import com.eshopper.orderservice1.service.OrderServiceTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTests {
    @MockBean
    OrderService orderService;

    @MockBean
    OrderProductsService orderProductsService;

    @InjectMocks
    OrderController orderController;

    @Autowired
    MockMvc mockMvc;

    public Order getOrderDetails1()
    {
        Order order1 = new Order();
        order1.setId(1);
        order1.setCustomerId(1);
        order1.setOrderStatusId(1);
        order1.setShippingAddress("Gurgaon");
        order1.setBillingAddress("Gurgaon");
        order1.setInvoiceDate(new Date());
        order1.setDeliveryDate(new Date());
        order1.setPaymentId(1);
        order1.setPaymentStatus(1);
        order1.setPaymentDate(new Date());
        order1.setShippmentCompanyId(1);
        order1.setTotal(100f);
        order1.setDiscount(10f);
        order1.setTax(2f);
        List<OrderProducts> orderProductsList = new ArrayList<>();
        orderProductsList.add(getOrderProducts1());
        orderProductsList.add(getOrderProducts2());
        order1.setOrderProductsList(orderProductsList);
        return order1;
    }

    public Order getOrderDetails2()
    {
        Order order2 = new Order();
        order2.setId(2);
        order2.setCustomerId(1);
        order2.setOrderStatusId(1);
        order2.setShippingAddress("Gurgaon1");
        order2.setBillingAddress("Gurgaon1");
        order2.setInvoiceDate(new Date());
        order2.setDeliveryDate(new Date());
        order2.setPaymentId(1);
        order2.setPaymentStatus(1);
        order2.setPaymentDate(new Date());
        order2.setShippmentCompanyId(1);
        order2.setTotal(100f);
        order2.setDiscount(10f);
        order2.setTax(2f);
        List<OrderProducts> orderProductsList = new ArrayList<>();
        orderProductsList.add(getOrderProducts1());
        orderProductsList.add(getOrderProducts2());
        order2.setOrderProductsList(orderProductsList);
        return order2;
    }

    public OrderProducts getOrderProducts1()
    {
        OrderProducts products1 = new OrderProducts();
        products1.setOrderId(1);
        products1.setProductId(1);
        products1.setOrderNumber(1);
        products1.setCategoryId(1);
        products1.setPrice(100f);
        products1.setQuantity(1);
        products1.setDiscount(1f);
        products1.setTotal(100);
        products1.setSkuid("AA");
        products1.setSize("M");
        products1.setColor("Red");
        return products1;
    }

    public OrderProducts getOrderProducts2()
    {
        OrderProducts products2 = new OrderProducts();
        products2.setOrderId(2);
        products2.setProductId(1);
        products2.setOrderNumber(1);
        products2.setCategoryId(1);
        products2.setPrice(100f);
        products2.setQuantity(1);
        products2.setDiscount(1f);
        products2.setTotal(100);
        products2.setSkuid("AA");
        products2.setSize("M");
        products2.setColor("Red");
        return products2;
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }}

    @Test
    public void shouldGetAllOrderDetails() throws Exception
    {
        List<Order> orderList = new ArrayList<>();
        orderList.add(getOrderDetails1());
        orderList.add(getOrderDetails2());
        given(orderService.getAllOrdersDetails()).willReturn(orderList);

        mockMvc.perform(get("/order/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void shouldGetOrderDetails() throws Exception
    {
//        @GetMapping(path = "/{orderId}/details", produces = APPLICATION_JSON_VALUE)
        Order order = getOrderDetails1();

        Optional<Order> tempOrder = Optional.of(order);
        given(orderService.getOrderDetails(1)).willReturn(tempOrder);
        mockMvc.perform(get("/order/{orderId}/details", order.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("shippingAddress").value("Gurgaon"))
                .andDo(MockMvcResultHandlers.print());
        verify(orderService, times(1)).getOrderDetails(order.getId());
        verifyNoMoreInteractions(orderService);

    }

    @Test
    public void shouldGetCustomerAllOrderDetails() throws Exception {
//        @GetMapping(path = "/{customerId}/orderDetails", produces = APPLICATION_JSON_VALUE)
        List<Order> orderList = new ArrayList<>();
        orderList.add(getOrderDetails1());
        orderList.add(getOrderDetails2());
        Integer customerId = 1;
        given(orderService.getCustomerOrderDetails(customerId)).willReturn(orderList);


        mockMvc.perform(get("/order/{customerId}/orderDetails", customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldGetSpecificOrderDetailsForACustomer() throws Exception {


        List<OrderProducts> orderProductsList = new ArrayList<>();
        orderProductsList.add(getOrderProducts1());
        orderProductsList.add(getOrderProducts2());
        Integer orderNumber = 1;
        given(orderProductsService.getDetailedProductsInAnOrder(orderNumber)).willReturn(orderProductsList);


        mockMvc.perform(get("/order/{orderNumber}/product/details", orderNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].orderId").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void shouldCreateOrder() throws Exception {
//        @PostMapping(path = "/customer/{customerId}/orderPlaced")
        Order order = getOrderDetails1();
        Integer customerId = 1;
        when(orderService.createOrder(order)).thenReturn(order);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/order/orderPlaced", customerId, order)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(order)))
                .andExpect(status().isOk());
    }

}
