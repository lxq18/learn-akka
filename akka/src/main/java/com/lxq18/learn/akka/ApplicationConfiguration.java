package com.lxq18.learn.akka;

import akka.actor.ActorSystem;
import com.lxq18.learn.akka.di.SpringExtension;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxq
 * @create 2019/12/16 8:32
 */


@Configuration
class ApplicationConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean(destroyMethod = "terminate")
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("demo-actor-system", akkaConfiguration());
        springExtension.initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}
