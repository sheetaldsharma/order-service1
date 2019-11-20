package com.eshopper.orderservice1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "orderid")
    Integer orderId;

    @Column(name = "customerid")
    Integer customerId;

    @Column(name = "ordernumber")
    Integer orderNumber;

    @Column(name = "paymentid")
    Integer paymentId ;

    @Column(name = "orderdate")
    Date orderDate;

    @Column(name = "shipdate")
    Date shipDate;

    @Column(name = "requireddate")
    Date requiredDate;

    @Column(name = "shippmentcompanyid")
    Integer shippmentCompanyId;

    @Column(name = "status")
    String status ;

    @Column(name = "paymentstatus")
    String paymentStatus;

    @Column(name = "paymentdate")
    Date paymentDate ;
}
