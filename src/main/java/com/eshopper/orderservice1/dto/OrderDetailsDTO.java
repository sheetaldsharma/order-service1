package com.eshopper.orderservice1.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private Integer orderId;
    private Integer customerId;
    private String orderStatus;
    private Date invoiceDate;
    private String shippingAddress;
    private Float total;
    private Float discount;
    private Float tax;
    private List<ProductDTO> productDTOList;

}
