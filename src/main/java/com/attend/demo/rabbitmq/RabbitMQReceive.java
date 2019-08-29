package com.attend.demo.rabbitmq;

import com.attend.demo.exception.MessageNotFoundException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceive {
    @RabbitListener(queues = Config.queueName)
    public void receiveMessage(Message message) {
        System.out.println("Enter the receiveMessage() method " + message);
        if (message != null) {
            System.out.println("Message nut empty " + message);
        } else {
            throw new MessageNotFoundException("Message Not Found Exception");
        }
        System.out.println("Exit the receiveMessage() method " + message);
    }

    @RabbitListener(queues = Config.queueName)
    public void receiveMessage(CustomMessageDto customMessageDto) {
        System.out.println("Enter the receiveMessage() method " + customMessageDto.toString());
        try {
            if (customMessageDto != null) {
                System.out.println("Customer Delivered. " + customMessageDto.getPriority());
            }
        } catch (Exception e) {
            System.out.println("Error of Message delivery " + e);
        }
        System.out.println("Enter the receiveMessage() method " + customMessageDto.toString());
    }
}
