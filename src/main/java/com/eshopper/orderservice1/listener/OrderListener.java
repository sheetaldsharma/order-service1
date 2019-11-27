package com.eshopper.orderservice1.listener;
import com.eshopper.orderservice1.dto.UserDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {


    @KafkaListener(topics = "CUSTOMER_PRODUCER")
    public void consume(String message) {
        System.out.println("==================== Consumed message: " + message);
    }
//
//    @KafkaListener(topics = "CUSTOMER_PRODUCER")
//    public void consumeJson(UserDTO userDto) {
//        System.out.println("_________________________________________ Consumed JSON Message: " + userDto);
//    }
}
