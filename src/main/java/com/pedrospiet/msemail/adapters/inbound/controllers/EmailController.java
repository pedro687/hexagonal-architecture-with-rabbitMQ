package com.pedrospiet.msemail.adapters.inbound.controllers;

import com.pedrospiet.msemail.adapters.inbound.dtos.EmailDTO;
import com.pedrospiet.msemail.application.domain.Email;
import com.pedrospiet.msemail.application.domain.PageInfo;
import com.pedrospiet.msemail.application.ports.EmailServicePort;
import com.pedrospiet.msemail.application.services.EmailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {
    @Autowired
    EmailServicePort emailServicePort;

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        return new ResponseEntity<>(emailServicePort.sendEmail(email), HttpStatus.CREATED);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<Email>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<Email> emailList = emailServicePort.findAll(pageInfo);
        return new ResponseEntity<>(new PageImpl<Email>(emailList, pageable, emailList.size()), HttpStatus.OK);
    }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") String emailId){
        Optional<Email> emailModelOptional = emailServicePort.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
}