package com.lxq18.learn.akka.actor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author lxq
 * @create 2019/12/15 23:52
 */

@Slf4j
@Component("workerActor")
@Scope("prototype")
public class WorkerActor extends AbstractActor {
    private int count = 0;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Request.class, msg -> log.info("count = {}", ++count))
                .match(Response.class, msg -> sender().tell(count, self()))
                .matchAny(message -> unhandled(message))
                .build();
    }

    public static class Request {
    }

    public static class Response {
    }
}
