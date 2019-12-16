package di2;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxq
 * @create 2019/12/15 22:55
 */
@Configuration
public class ScanConfig {
    private final ApplicationContext context;



    @Autowired
    public ScanConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public ActorSystem createSystem() {
        ActorSystem system = ActorSystem.create("akka-springmvc-system");
        SpringExtProvider.getInstance().get(system).init(context);
        return system;
    }
}
