package br.com.washington.application_events.services.impl;

import br.com.washington.application_events.services.SMSService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SMSServiceImpl implements SMSService {


    @Override
    public void send(String phone, String message) {
        log.info("phone: {} message: {}", phone, message);
    }
}
