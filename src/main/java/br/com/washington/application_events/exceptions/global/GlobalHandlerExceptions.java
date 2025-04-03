package br.com.washington.application_events.exceptions.global;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalHandlerExceptions extends ResponseEntityExceptionHandler {
}
