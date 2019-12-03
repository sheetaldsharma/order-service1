package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.controller.OrderController;
import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.model.OrderProducts;
import com.eshopper.orderservice1.repository.OrderProductsRepository;
import com.eshopper.orderservice1.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTests {


    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    public Order getOrderDetails1() {
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

    public Order getOrderDetails2() {
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

    public OrderProducts getOrderProducts1() {
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

    public OrderProducts getOrderProducts2() {
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
        }
    }

    @Test
    public void shouldGetAllOrderDetails(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(getOrderDetails1());
        orderList.add(getOrderDetails2());

        given(orderRepository.findAll()).willReturn(orderList);
        List<Order> actualOrderList = orderService.getAllOrdersDetails();
        assertThat(orderList.size()).isEqualTo(2);
        assertThat(actualOrderList.size()).isEqualTo(orderList.size());
    }

    @Test
    public void shouldGetOrderDetails() throws Exception {
        Order expectedOrder = new Order();
        expectedOrder = getOrderDetails1();
        given(orderRepository.findById(expectedOrder.getId())).willReturn(Optional.of(expectedOrder));

        Optional<Order> actualOrder = orderService.getOrderDetails(expectedOrder.getId());
        assertNotNull(actualOrder);
        assertEquals(Optional.of(expectedOrder), actualOrder);
    }

    @Test
    public void shouldGetCustomerAllOrderDetails() throws Exception {

        List<Order> orderList = new ArrayList<>();
        orderList.add(getOrderDetails1());
        orderList.add(getOrderDetails2());
        Integer customerId = 1;

        given(orderRepository.findOrdersByCustomerId(customerId)).willReturn(orderList);
        List<Order> actualOrderList = orderService.getCustomerOrderDetails(customerId);
        assertThat(orderList.size()).isEqualTo(2);
        assertThat(actualOrderList.size()).isEqualTo(orderList.size());
    }

    @Test
    public void shouldCreateOrder() throws Exception {
        Order order = getOrderDetails1();
        Integer customerId = 1;
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        Order actualUser = orderService.createOrder(customerId, order);
        Assertions.assertEquals(actualUser, order);
    }

}


