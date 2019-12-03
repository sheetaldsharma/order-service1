package com.eshopper.orderservice1.service;

import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.model.OrderProducts;
import com.eshopper.orderservice1.repository.OrderProductsRepository;
import com.eshopper.orderservice1.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderProductsServiceTests {

    @Mock
    OrderProductsRepository orderProductsRepository;

    @InjectMocks
    OrderProductsService orderProductsService;

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

    @Test
    public void shouldGetDetailedProductsInAnOrder()
    {
        List<OrderProducts> orderProductsList = new ArrayList<>();
        orderProductsList.add(getOrderProducts1());
        orderProductsList.add(getOrderProducts2());
        Integer orderNumber = 1;

        when(orderProductsRepository.getDetailedProductsInAnOrder(orderNumber)).thenReturn(orderProductsList);
        List<OrderProducts> actualOrderProductsList = new ArrayList<>();
        actualOrderProductsList = orderProductsService.getDetailedProductsInAnOrder(orderNumber);

        assertThat(orderProductsList.size()).isEqualTo(2);
        assertThat(actualOrderProductsList.size()).isEqualTo(orderProductsList.size());


    }

}
