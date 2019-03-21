package in;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Main {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.set("events/city/rome", "32,15,223,828");
//        jedis.lpush("queue#tasks", "firstTask");
//        jedis.lpush("queue#tasks", "secondTask");
//
//        String task = jedis.rpop("queue#tasks");
//        System.out.println(task);
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Received message " + message + " from channel " + channel);
            }
        }, "channel");
        while (true) {

        }
    }
}
