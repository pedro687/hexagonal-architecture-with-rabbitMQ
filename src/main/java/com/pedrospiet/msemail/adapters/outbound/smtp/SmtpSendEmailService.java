package com.pedrospiet.msemail.adapters.outbound.smtp;

import com.pedrospiet.msemail.application.domain.Email;
import com.pedrospiet.msemail.application.ports.SendEmailServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpSendEmailService implements SendEmailServicePort {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());

        javaMailSender.send(message);

    }
}
