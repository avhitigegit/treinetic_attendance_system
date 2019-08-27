package com.attend.demo.rabbitmq;

import com.attend.demo.exception.MessageNotFoundException;
import com.attend.demo.exception.MessageNotSendException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 3000L)
    public CustomMessageDto sendMessage(CustomMessageDto customMessageDto) {
//        rabbitTemplate.convertAndSend(Config.EXCHANGE_NAME, Config.ROUTING_KEY, customMessageDto);
        Object o = rabbitTemplate.convertSendAndReceive(Config.EXCHANGE_NAME, Config.ROUTING_KEY, customMessageDto);
        if (o != null) {
            return customMessageDto;
        } else {
            throw new MessageNotSendException("Message Send Failure Exception");
        }
    }

    @RabbitListener(queues = Config.QUEUE_SPECIFIC_NAME)
    public CustomMessageDto receiveMessage(CustomMessageDto customMessageDto) {
        if (customMessageDto != null) {
            System.out.println("Message Delivered.");
            return customMessageDto;
        } else {
            throw new MessageNotFoundException("Message Not Found Exception");
        }
    }
//    @RabbitListener(queues = SpringbootRabbitmqExampleApplication.QUEUE_GENERIC_NAME)
//    public void receiveMessage(final Message message) {
//        log.info("Received message as generic: {}", message.toString());
//    }
}
