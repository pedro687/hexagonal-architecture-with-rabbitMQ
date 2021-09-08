package com.pedrospiet.msemail.adapters.configurations;

import com.pedrospiet.msemail.MsEmailApplication;
import com.pedrospiet.msemail.adapters.outbound.persistence.PostgreEmailRepository;
import com.pedrospiet.msemail.application.ports.EmailRepositoryPort;
import com.pedrospiet.msemail.application.ports.SendEmailServicePort;
import com.pedrospiet.msemail.application.services.EmailServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@ComponentScan(basePackageClasses = MsEmailApplication.class)
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    EmailServiceImpl emailService(PostgreEmailRepository postgreEmailRepository, SendEmailServicePort sendEmailServicePort) {
        return new EmailServiceImpl(postgreEmailRepository, sendEmailServicePort);
    }
}
