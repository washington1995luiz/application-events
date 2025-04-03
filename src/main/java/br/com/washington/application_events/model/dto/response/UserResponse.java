package br.com.washington.application_events.model.dto.response;

import br.com.washington.application_events.model.entity.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String username,
        String email,
        String phone,
        Boolean enabled
) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getEnabled());
    }
}
