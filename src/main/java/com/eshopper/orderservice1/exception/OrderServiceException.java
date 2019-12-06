package com.eshopper.orderservice1.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
public class OrderServiceException extends Exception {
    private String errorMessage;

    public OrderServiceException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public OrderServiceException() {
        super();
    }
}