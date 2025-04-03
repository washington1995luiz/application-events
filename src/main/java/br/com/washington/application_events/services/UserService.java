package br.com.washington.application_events.services;

import br.com.washington.application_events.model.dto.request.CreateUserRequest;
import br.com.washington.application_events.model.dto.request.UpdateEmailRequest;
import br.com.washington.application_events.model.dto.request.UpdateNameRequest;
import br.com.washington.application_events.model.dto.request.UpdatePasswordRequest;
import br.com.washington.application_events.model.dto.response.ApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    ApiResponse create(CreateUserRequest request);

    ApiResponse updateName(UUID id, UpdateNameRequest request);
    ApiResponse updateEmail(UUID id, UpdateEmailRequest request);
    ApiResponse updatePassword(UUID id, UpdatePasswordRequest request);
    void softDelete(UUID id);

    ApiResponse findAll(Pageable pageable);
}
