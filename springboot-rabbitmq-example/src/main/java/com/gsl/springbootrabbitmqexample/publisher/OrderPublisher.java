package com.gsl.springbootrabbitmqexample.publisher;

import com.gsl.springbootrabbitmqexample.config.MessagingConfig;
import com.gsl.springbootrabbitmqexample.dto.Order;
import com.gsl.springbootrabbitmqexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurant}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurant){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus=new OrderStatus(order,"PROCESS","Order places successfully in "+restaurant);
        template.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,orderStatus);
        return "success !!";
    }
}
