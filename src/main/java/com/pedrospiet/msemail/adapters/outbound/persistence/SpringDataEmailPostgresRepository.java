package com.pedrospiet.msemail.adapters.outbound.persistence;


import com.pedrospiet.msemail.adapters.outbound.persistence.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataEmailPostgresRepository extends JpaRepository<EmailEntity, String> {
    public Optional<EmailEntity> findById(String UUID);
}
