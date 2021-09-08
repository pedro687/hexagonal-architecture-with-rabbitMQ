package com.pedrospiet.msemail.adapters.outbound.persistence.entities;

import com.pedrospiet.msemail.application.domain.enums.StatusEmail;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_EMAIL")
public class EmailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
