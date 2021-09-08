package com.pedrospiet.msemail.application.ports;

import com.pedrospiet.msemail.adapters.outbound.persistence.entities.EmailEntity;
import com.pedrospiet.msemail.application.domain.Email;

public interface SendEmailServicePort {
    void sendEmail(Email email);

}
