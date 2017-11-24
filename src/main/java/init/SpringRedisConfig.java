package init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import util.AppConfig;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: Spring和Redis的整合
 * @date 2017/11/24
 */
@Configuration
public class SpringRedisConfig {

    //①配置redis的连接池
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        String maxActive = AppConfig.getProperty("redis.maxActive");
        jedisPoolConfig.setMaxTotal(Integer.valueOf(maxActive));

        //最大空闲数
        String maxIdle = AppConfig.getProperty("redis.maxIdle");
        jedisPoolConfig.setMaxIdle(Integer.valueOf(maxIdle));

        //建立连接的最大等待时间
        String maxWaitMillis = AppConfig.getProperty("redis.maxWaitMillis");
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(maxWaitMillis));

        String testOnBorrow = AppConfig.getProperty("redis.testOnBorrow");
        jedisPoolConfig.setTestOnBorrow(Boolean.getBoolean(testOnBorrow));

        return jedisPoolConfig;
    }

    //②redis的连接工厂
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(AppConfig.getProperty("redis.hostName"));
        connectionFactory.setPort(Integer.valueOf(AppConfig.getProperty("redis.port")));
        connectionFactory.setPassword(AppConfig.getProperty("redis.passWord"));
        connectionFactory.setPoolConfig(jedisPoolConfig());
        return connectionFactory;
    }

    //③对redis操作封装的redisTemplate配置
    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
