package com.lxq18.learn.akka.model;

/**
 * @author lxq
 * @create 2019/12/16 8:25
 */
public class Message {

    final private String payload;
    final private long id;

    public Message(String payload, long id) {
        this.payload = payload;
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" + "payload='" + payload + '\'' + ", id=" + id + '}';
    }
}
