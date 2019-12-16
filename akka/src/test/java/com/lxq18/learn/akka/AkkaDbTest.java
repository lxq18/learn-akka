package com.lxq18.learn.akka;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import messages.SetRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

@Slf4j
public class AkkaDbTest {
    private ActorSystem system = ActorSystem.create();

    @Test
    public void test() {
        TestActorRef<AkkaDb> actorRef = TestActorRef.create(system, Props.create(AkkaDb.class));
        AkkaDb akkaDb = actorRef.underlyingActor();
        assertEquals(akkaDb.map.get("key"), "value");
    }

    public static void main(String[] args) throws InterruptedException {
        new AkkaDbTest().corrency();
        Thread.sleep(30000);
    }


    public void corrency() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AtomicInteger num = new AtomicInteger(0);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                TestActorRef.create(system, Props.create(AkkaDb.class)).tell(new SetRequest("key", String.valueOf(num.incrementAndGet())), TestActorRef.noSender());
                log.info("running");
            });
        }
    }

}