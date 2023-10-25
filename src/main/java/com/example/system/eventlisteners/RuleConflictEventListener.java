package com.example.system.eventlisteners;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RuleConflictEventListener implements ApplicationListener<ApplicationEvent> {

    private final EventHandler eventHandler;

    public RuleConflictEventListener(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            eventHandler.handleContextRefreshEvent();
        }
    }


}

