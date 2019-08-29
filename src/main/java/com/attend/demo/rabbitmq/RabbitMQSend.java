package com.attend.demo.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSend {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public CustomMessageDto sendMessage(CustomMessageDto customMessageDto) {
        try {
            customMessageDto.setPriority(100);
            this.rabbitTemplate.convertAndSend(Config.topicExchangeName, Config.queueName, customMessageDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customMessageDto;
    }

    public Message convertDTOtoMsgAndSend(CustomMessageDto customMessageDto) {
        Message message = null;
        try {
            customMessageDto.setPriority(100);
            String orderJson = objectMapper.writeValueAsString(customMessageDto);
            message = MessageBuilder
                    .withBody(orderJson.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            this.rabbitTemplate.convertAndSend(Config.topicExchangeName, Config.queueName, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
