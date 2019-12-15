package com.lxq18.learn.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.lxq18.learn.akka.messages.SetRequest;
import com.lxq18.learn.akka.messages.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * akka实现的数据库
 */
public class AkkaDb extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    protected final Map<String, Object> map = new HashMap<String, Object>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(SetRequest.class, message -> {
                    Thread.sleep(3000);

                    log.info("received message = {}", message);
                    map.put(message.getKey(), message.getValue());
                    sender().tell(new Status.Sucess(message.getKey()), self());
                })
                .matchAny(o -> log.info("received unknown message: {}", o))
                .build();
    }
}
