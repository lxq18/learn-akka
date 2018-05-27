package com.lxq18.learn.akka;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.lxq18.learn.akka.messages.SetRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AkkaDbTest {
    ActorSystem system = ActorSystem.create();

    @Test
    public void test() {
        TestActorRef<AkkaDb> actorRef = TestActorRef.create(system, Props.create(AkkaDb.class));
        actorRef.tell(new SetRequest("key", "value"), TestActorRef.noSender());
        AkkaDb akkaDb = actorRef.underlyingActor();
        assertEquals(akkaDb.map.get("key"), "value");
    }

}