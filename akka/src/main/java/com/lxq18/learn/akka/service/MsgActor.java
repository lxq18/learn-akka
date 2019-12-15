package com.lxq18.learn.akka.service;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lxq
 * @create 2019/12/15 22:57
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MsgActor extends AbstractActor {

    @Value("${appName}")
    private String appName;

    @PostConstruct
    public void init() {
        log.info("appName = {}", appName);
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(o -> {
            int total = 0;
            /*for (int i = 0; i < 1000000; i++) {
                total += i;
            }*/
            log.info("接受到消息 ={}, total = {}, sender = {}", o, total, sender());
        }).build();
    }
}
