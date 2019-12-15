package com.lxq18.learn.akka.di2;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * @author lxq
 * @create 2019/12/15 22:44
 */
public class DIProducer implements IndirectActorProducer {
    private ApplicationContext context;
    private String beanName;

    public DIProducer(ApplicationContext context, String beanName) {
        this.context = context;
        this.beanName = beanName;
    }

    @Override
    public Actor produce() {
        return (Actor) context.getBean(beanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) context.getType(beanName);
    }
}