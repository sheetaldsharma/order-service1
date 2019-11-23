package com.eshopper.orderservice1.repository;

import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Integer> {
}
