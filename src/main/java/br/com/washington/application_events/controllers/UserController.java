package br.com.washington.application_events.controllers;

import br.com.washington.application_events.exceptions.BadFormattedUUIDException;
import br.com.washington.application_events.model.dto.request.CreateUserRequest;
import br.com.washington.application_events.model.dto.request.UpdateEmailRequest;
import br.com.washington.application_events.model.dto.request.UpdateNameRequest;
import br.com.washington.application_events.model.dto.request.UpdatePasswordRequest;
import br.com.washington.application_events.model.dto.response.ApiResponse;
import br.com.washington.application_events.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody CreateUserRequest request){
        return service.create(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse findAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @PutMapping(value = "/{id}/name")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updateName(@PathVariable(value = "id") String id, @RequestBody UpdateNameRequest request){
        return service.updateName(stringToUUID.apply(id), request);
    }

    @PutMapping(value = "/{id}/email")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updateEmail(@PathVariable(value = "id") String id, @RequestBody UpdateEmailRequest request){
        return service.updateEmail(stringToUUID.apply(id), request);
    }

    @PutMapping(value = "/{id}/password")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updatePassword(@PathVariable(value = "id") String id, @RequestBody UpdatePasswordRequest request){
        return service.updatePassword(stringToUUID.apply(id), request);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDelete(@PathVariable(value = "id") String id){
        service.softDelete(stringToUUID.apply(id));
    }

    private final Function<String, UUID> stringToUUID = id -> {
        try{
            return UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new BadFormattedUUIDException(id);
        }
    };
}
