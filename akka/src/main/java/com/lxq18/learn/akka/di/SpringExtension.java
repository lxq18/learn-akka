package com.lxq18.learn.akka.di;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author lxq
 * @create 2019/12/16 0:02
 */
@Component
public class SpringExtension implements Extension {

    private ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String actorBeanName) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
    }

    public Props props(String actorBeanName, Object... args) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName, args);
    }
}
