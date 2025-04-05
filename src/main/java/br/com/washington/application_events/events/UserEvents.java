package br.com.washington.application_events.events;

import br.com.washington.application_events.events.custom.UpdateEmailEvent;
import br.com.washington.application_events.events.custom.UpdateNameEvent;
import br.com.washington.application_events.events.custom.UserCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Log4j2
@Component
public class UserEvents {

    @EventListener
    public void userCreated(UserCreatedEvent event){
       log.info("thread: {} - Message: {}", Thread.currentThread(), event.message());
    }

    @Async
    @EventListener
    public void userCreatedSMS(UserCreatedEvent event){
        log.info("Thread: {} - Send SMS to phone: {}", Thread.currentThread(), event.user().getPhone());
    }

    @TransactionalEventListener
    public void userCreatedAfterCommit(UserCreatedEvent event){
        log.info("after commit - message: {}", event.message());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void userCreatedAfterRollBack(UserCreatedEvent event){
        log.info("after rollback - error trying to create user {}", event.user().getUsername());
    }

    @TransactionalEventListener
    public void updatedNameAfterCommit(UpdateNameEvent event){
        log.info("updated name - message: {}", event.message());
    }

    @TransactionalEventListener
    public void updateEmailAfterCommit(UpdateEmailEvent event){
        log.info("after commit - message: {}", event.message());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void updateEmailAfterRollBack(UpdateEmailEvent event) {
        log.info("after rollback - error trying to update email of user: {}", event.user().getUsername());
    }
}
