package br.com.washington.application_events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadFormattedUUIDException extends ResponseStatusException {
    public BadFormattedUUIDException(String message) {
        super(HttpStatus.BAD_REQUEST, "Bad formatted UUID: " + message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
