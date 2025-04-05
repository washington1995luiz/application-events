package br.com.washington.application_events.events.custom;

import br.com.washington.application_events.model.entity.User;
import org.springframework.context.ApplicationEvent;

public record UpdateEmailEvent(User user, String message){
}
