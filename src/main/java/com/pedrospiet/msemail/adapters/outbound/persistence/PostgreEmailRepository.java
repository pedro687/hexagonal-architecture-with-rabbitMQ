package com.pedrospiet.msemail.adapters.outbound.persistence;

import com.pedrospiet.msemail.adapters.outbound.persistence.entities.EmailEntity;
import com.pedrospiet.msemail.application.domain.Email;
import com.pedrospiet.msemail.application.domain.PageInfo;
import com.pedrospiet.msemail.application.ports.EmailRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgreEmailRepository implements EmailRepositoryPort {

    private final SpringDataEmailPostgresRepository emailRepository;

    @Autowired
    ModelMapper modelMapper;

    public PostgreEmailRepository(SpringDataEmailPostgresRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Email save(Email email) {
        var entity = emailRepository.save(modelMapper.map(email, EmailEntity.class));
        return modelMapper.map(entity, Email.class);
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        return emailRepository.findAll(pageable).stream().map(entity -> modelMapper.map(entity, Email.class))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Email> findById(String emailId) {
        Optional<EmailEntity> emailEntity = emailRepository.findById(emailId);
        if (emailEntity.isPresent()) {
            return Optional.of(modelMapper.map(emailEntity.get(), Email.class));
        } else {
            return Optional.empty();
        }
    }
}
