package com.pedrospiet.msemail.application.ports;

import com.pedrospiet.msemail.application.domain.Email;
import com.pedrospiet.msemail.application.domain.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmailRepositoryPort {
    Email save(Email emailModel);
    List<Email> findAll(PageInfo pageable);

    Optional<Email> findById(String UUID);
}
