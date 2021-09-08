package com.pedrospiet.msemail.application.domain;

import com.pedrospiet.msemail.application.domain.enums.StatusEmail;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Email {

    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
