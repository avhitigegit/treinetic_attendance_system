package com.attend.demo.rabbitmq;

import com.attend.demo.controller.EmployeeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rabbit-mq")
public class RabbitMqController {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);
    @Autowired
    RabbitMQSend rabbitMQSend;

    @ResponseBody
    @RequestMapping(value = "/send", method = RequestMethod.POST, headers = "Accept=application/json")
    public CustomMessageDto sendMessage(@RequestBody CustomMessageDto customMessageDto) {
        customMessageDto = rabbitMQSend.sendMessage(customMessageDto);
        return customMessageDto;
    }
}
