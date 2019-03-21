package in;

import redis.clients.jedis.Jedis;

public class JedisPublisher {
    public static void main(String[] args) {
        Jedis jedisPublisher = new Jedis();
        jedisPublisher.publish("channel", "how are you doing");
    }
}
