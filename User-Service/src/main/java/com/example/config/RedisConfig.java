package com.example.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration //redisin nerden bağlanması gerektiğini belirtmek için kullanılır. Local host mu normal id adresi mi
@EnableRedisRepositories //Redisi repository olarak kullanmaya yarar. MongoDB repository gibi
@EnableCaching //Sistem üzerinde cash kullanıcağımızı söylüyoruz cashleme olarak redis kullanıcaz

// Cashleme: veritabanına veri eklemek, verileri silmek, verileri güncellemek, verileri aramak (Ramde tutar)
public class RedisConfig {
    //Bağlantı için bean yarat
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }
}
