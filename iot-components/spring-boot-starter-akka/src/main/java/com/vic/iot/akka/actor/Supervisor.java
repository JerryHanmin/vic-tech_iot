package com.vic.iot.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.Routee;
import akka.routing.Router;
import akka.routing.SmallestMailboxRoutingLogic;
import com.vic.iot.akka.config.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Supervisor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), "Supervisor");

    @Autowired
    private SpringExtension springExtension;

    private Router router;


    @Override
    public void preStart() throws Exception {
        log.info("Starting up");


        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ActorRef childActor = getContext().actorOf(springExtension.props("workerActor"));
            getContext().watch(childActor);
            routees.add(new ActorRefRoutee(childActor));
        }
        router = new Router(new SmallestMailboxRoutingLogic(), routees);

        super.preStart();
    }


    @Override
    public void postStop() throws Exception {
        log.info("Shutting down");
        super.postStop();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    router.route(s, getSender());
                })
                .match(Terminated.class, System.out::println)
                .matchAny(u -> System.out.println("test " + u))
                .build();
    }
}
