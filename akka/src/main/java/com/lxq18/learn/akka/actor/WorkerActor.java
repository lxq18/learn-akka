package com.lxq18.learn.akka.actor;

import akka.actor.AbstractActor;
import com.lxq18.learn.akka.model.Message;
import com.lxq18.learn.akka.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


/**
 * @author lxq
 * @create 2019/12/15 23:52
 */

@Slf4j
@Component("workerActor")
@Scope("prototype")
public class WorkerActor extends AbstractActor {
    private int count = 0;

    @Autowired
    private BusinessService businessService;

    final private CompletableFuture<Message> future;

    public WorkerActor(CompletableFuture<Message> future) {
        this.future = future;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.class, msg -> {
                    businessService.perform(this + " " + msg);
                    log.info("count = {}", ++count);
                    future.complete(msg);
                    context().stop(self());
                })
                .match(Response.class, msg -> sender().tell(count, self()))
                .matchAny(message -> unhandled(message))
                .build();
    }

    public static class Request {
    }

    public static class Response {
    }
}
