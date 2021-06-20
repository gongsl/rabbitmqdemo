package com.gsl.springbootrabbitmqexample.consumer;

import com.gsl.springbootrabbitmqexample.config.MessagingConfig;
import com.gsl.springbootrabbitmqexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {


    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMsgFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from queue: "+orderStatus);
    }
}
