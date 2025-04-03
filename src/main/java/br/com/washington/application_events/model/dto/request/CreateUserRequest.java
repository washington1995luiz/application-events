package br.com.washington.application_events.model.dto.request;

import br.com.washington.application_events.model.entity.User;

public record CreateUserRequest(
        String name,
        String username,
        String password,
        String email,
        String phone
) {

    public User create() {
        return new User(username, name, email, password, phone);
    }
}
