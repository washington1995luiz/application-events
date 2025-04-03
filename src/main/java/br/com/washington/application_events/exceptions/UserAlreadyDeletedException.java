package br.com.washington.application_events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyDeletedException extends ResponseStatusException {
    public UserAlreadyDeletedException() {
        super(HttpStatus.GONE, "User already deleted");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
