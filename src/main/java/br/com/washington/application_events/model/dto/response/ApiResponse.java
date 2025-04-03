package br.com.washington.application_events.model.dto.response;

public record ApiResponse(
        String status,
        String message,
        Object data,
        Object error

){}
