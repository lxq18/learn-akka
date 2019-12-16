
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import di2.SpringExtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lxq
 * @create 2019/12/15 23:01
 */
@RestController
public class HelloAkkaController {
    @Autowired
    private ActorSystem actorSystem;

    @RequestMapping("/api/akka/hello")
    public String hello() {
        ActorRef ref = actorSystem.actorOf(
                SpringExtProvider.getInstance().get(actorSystem).create("msgActor"));
        ref.tell("hello", ActorRef.noSender());

        return "hello akka " + new Date();
    }
}
