package br.com.washington.application_events.events.custom;

import br.com.washington.application_events.model.entity.User;

public record UserCreatedEvent(User user, String message) {
}
