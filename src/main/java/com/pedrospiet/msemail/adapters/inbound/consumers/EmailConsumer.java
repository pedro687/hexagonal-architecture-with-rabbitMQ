package com.pedrospiet.msemail.adapters.inbound.consumers;

import com.pedrospiet.msemail.adapters.inbound.dtos.EmailDTO;
import com.pedrospiet.msemail.adapters.outbound.persistence.entities.EmailEntity;
import com.pedrospiet.msemail.application.domain.Email;
import com.pedrospiet.msemail.application.ports.EmailServicePort;
import com.pedrospiet.msemail.application.services.EmailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Autowired
    private EmailServicePort emailServicePort;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailServicePort.sendEmail(email);
        System.out.println("Email Status: " + email.getStatusEmail().toString());
    }
}
