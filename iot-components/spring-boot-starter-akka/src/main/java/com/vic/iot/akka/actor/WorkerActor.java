package com.vic.iot.akka.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WorkerActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), "WorkerActor");

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    log.info("WorkerActor Hello: {}", s);
                })
                .build();
    }
}
