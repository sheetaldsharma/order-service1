package com.eshopper.orderservice1.repository;

import com.eshopper.orderservice1.model.Order;
import com.eshopper.orderservice1.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProducts, Integer> {
    @Query(value = "SELECT * FROM orderproducts WHERE ordernumber = ?1", nativeQuery = true)
    public List<OrderProducts> getDetailedProductsInAnOrder(Integer orderNumber);
}
