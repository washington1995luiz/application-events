package br.com.washington.application_events.services.impl;

import br.com.washington.application_events.events.custom.UpdateEmailEvent;
import br.com.washington.application_events.events.custom.UpdateNameEvent;
import br.com.washington.application_events.events.custom.UserCreatedEvent;
import br.com.washington.application_events.exceptions.UserAlreadyDeletedException;
import br.com.washington.application_events.exceptions.UserAlreadyExistsException;
import br.com.washington.application_events.exceptions.UserNotFoundException;
import br.com.washington.application_events.model.dto.request.CreateUserRequest;
import br.com.washington.application_events.model.dto.request.UpdateEmailRequest;
import br.com.washington.application_events.model.dto.request.UpdateNameRequest;
import br.com.washington.application_events.model.dto.request.UpdatePasswordRequest;
import br.com.washington.application_events.model.dto.response.ApiResponse;
import br.com.washington.application_events.model.dto.response.UserResponse;
import br.com.washington.application_events.model.entity.User;
import br.com.washington.application_events.repositories.UserRepository;
import br.com.washington.application_events.services.EmailService;
import br.com.washington.application_events.services.SMSService;
import br.com.washington.application_events.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final SMSService smsService;
    private final EmailService emailService;

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public ApiResponse create(CreateUserRequest request) {

        if (repository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("User already exists for this username: " + request.username());
        }
        if (repository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("User already exists for this email: " + request.email());
        }
        if (repository.existsByPhone(request.phone())) {
            throw new UserAlreadyExistsException("User already exists for this phone: " + request.phone());
        }

        User user = request.create();
        repository.save(user);

        log.info("thread: {}", Thread.currentThread());

        return new ApiResponse(
                "success",
                "User created successfully",
                UserResponse.fromEntity(user),
                null
        );
    }

    @Transactional
    @Override
    public ApiResponse updateName(UUID id, UpdateNameRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.updateName(request.name());
        repository.save(user);

        eventPublisher.publishEvent(new UpdateNameEvent(user, "User name updated successfully"));

        return new ApiResponse(
                "success",
                "User name updated successfully",
                UserResponse.fromEntity(user),
                null
        );
    }

    @Transactional
    @Override
    public ApiResponse updateEmail(UUID id, UpdateEmailRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.updateEmail(request.email());
        repository.save(user);

        eventPublisher.publishEvent(new UpdateEmailEvent(user, "User email updated successfully"));

        if(true){
            throw new RuntimeException("error");
        }

        return new ApiResponse(
                "success",
                "User email updated successfully",
                UserResponse.fromEntity(user),
                null
        );
    }

    @Override
    public ApiResponse updatePassword(UUID id, UpdatePasswordRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.updatePassword(request.password());
        repository.save(user);

        emailService.sendMail(user.getEmail(), "Password updated successfully");
        smsService.send(user.getPhone(), "Password updated successfully");

        return new ApiResponse(
                "success",
                "User password updated successfully",
                UserResponse.fromEntity(user),
                null
        );
    }

    @Override
    public void softDelete(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        if (user.getDeletedAt() != null) {
            throw new UserAlreadyDeletedException();
        }
        user.softDelete();
        repository.save(user);

        emailService.sendMail(user.getEmail(), "User deleted successfully");
        smsService.send(user.getPhone(), "User deleted successfully");
    }

    @Override
    public ApiResponse findAll(Pageable pageable) {
        Page<User> all = repository.findAllActive(pageable);
        if (all.getContent().isEmpty()) {
            return new ApiResponse(
                    "success",
                    "No users found",
                    null,
                    null
            );
        }
        return new ApiResponse(
                "success",
                "Users found successfully",
                all.map(UserResponse::fromEntity),
                null
        );
    }
}
