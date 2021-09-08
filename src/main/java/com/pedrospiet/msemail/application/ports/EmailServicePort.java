package com.pedrospiet.msemail.application.ports;

import com.pedrospiet.msemail.adapters.outbound.persistence.entities.EmailEntity;
import com.pedrospiet.msemail.application.domain.Email;
import com.pedrospiet.msemail.application.domain.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailServicePort {
    Email sendEmail(Email email);

    List<Email> findAll(PageInfo pageInfo);
    Optional<Email> findById(String emailId);
    Email save(Email email);
}
