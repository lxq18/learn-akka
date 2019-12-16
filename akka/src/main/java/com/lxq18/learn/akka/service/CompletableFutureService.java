package com.lxq18.learn.akka.service;

/**
 * @author lxq
 * @create 2019/12/16 8:27
 */
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.lxq18.learn.akka.di.SpringExtension;
import com.lxq18.learn.akka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CompletableFutureService {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    public CompletableFuture<Message> get(String payload, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(
                springExtension.props("workerActor", future), "worker-actor");
        workerActor.tell(new Message(payload, id), null);
        return future;
    }
}
