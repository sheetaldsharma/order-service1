package com.eshopper.orderservice1.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    Integer customerId;
    Integer orderId;

    @Override
    public String toString() {
        return "UserDTO{" +
                "customerId=" + customerId +
                ", orderId=" + orderId +
                '}';
    }
}
