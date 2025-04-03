package br.com.washington.application_events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
