package br.com.washington.application_events.events.custom;

import br.com.washington.application_events.model.entity.User;

public record UpdateNameEvent(User user, String message) {
}
