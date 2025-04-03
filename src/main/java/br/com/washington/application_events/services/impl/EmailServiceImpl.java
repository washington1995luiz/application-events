package br.com.washington.application_events.services.impl;

import br.com.washington.application_events.services.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendMail(String email, String message) {
        log.info("email: {} message: {}", email, message);
    }
}
