package com.vic.iot.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.vic.iot.akka.config.SpringExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import scala.concurrent.Future;

/**
 * http://kimrudolph.de/blog/spring-boot-meets-akka
 */
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootAkkaExApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(SpringBootAkkaExApplication.class, args);
        ActorSystem system = context.getBean(ActorSystem.class);

        final LoggingAdapter log = Logging.getLogger(system, "Application");

        log.info("Starting up");

        SpringExtension ext = context.getBean(SpringExtension.class);

        // Use the Spring Extension to create props for a named actor bean
        ActorRef supervisor = system.actorOf(
                ext.props("supervisor")
        );

        for (int i = 1; i < 100; i++) {
            supervisor.tell("Hello " + i, null);
        }

        supervisor.tell(PoisonPill.getInstance(), null);

        while (!supervisor.isTerminated()) {
            Thread.sleep(100);
        }

        log.info("Shutting down");

        Future<Terminated> terminateFuture = system.terminate();

        while (!terminateFuture.isCompleted()) {
        }

        System.out.println("Finish");
    }
}
