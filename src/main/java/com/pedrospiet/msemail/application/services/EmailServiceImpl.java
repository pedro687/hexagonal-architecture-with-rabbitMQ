package com.pedrospiet.msemail.application.services;

import com.pedrospiet.msemail.adapters.outbound.persistence.PostgreEmailRepository;
import com.pedrospiet.msemail.adapters.outbound.persistence.entities.EmailEntity;
import com.pedrospiet.msemail.application.domain.Email;
import com.pedrospiet.msemail.application.domain.PageInfo;
import com.pedrospiet.msemail.application.domain.enums.StatusEmail;
import com.pedrospiet.msemail.application.ports.EmailServicePort;
import com.pedrospiet.msemail.application.ports.SendEmailServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmailServiceImpl implements EmailServicePort {

    private final PostgreEmailRepository repository;

    private final SendEmailServicePort sendEmailServicePort;
    public EmailServiceImpl(PostgreEmailRepository repository, SendEmailServicePort sendEmailServicePort) {
        this.repository = repository;
        this.sendEmailServicePort = sendEmailServicePort;
    }

    @Override
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            sendEmailServicePort.sendEmail(email);
            email.setStatusEmail(StatusEmail.SENT);
        } catch (Exception e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return save(email);
        }
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        //inserir manipulação de dados/regras
        return  repository.findAll(pageInfo);
    }
    @Override
    public Optional<Email> findById(String emailId) {
        //inserir manipulação de dados/regras
        return repository.findById(emailId);
    }

    @Override
    public Email save(Email email) {
        return repository.save(email);
    }

}
