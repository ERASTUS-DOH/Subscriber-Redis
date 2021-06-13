package io.inspired.Subscriber;

import io.inspired.Subscriber.models.Joke;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootApplication
public class RedisSubscriberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSubscriberServiceApplication.class, args);
	}

	@Bean
	public ReactiveRedisOperations<String, Joke> jokeTemplate(LettuceConnectionFactory lettuceConnectionFactory){
		RedisSerializer<Joke> jokeRedisSerializer = new Jackson2JsonRedisSerializer<Joke>(Joke.class);
		RedisSerializationContext<String,Joke> serializationContext = RedisSerializationContext.<String ,Joke> newSerializationContext(RedisSerializer.string()).value(jokeRedisSerializer).build();
		return new ReactiveRedisTemplate<String,Joke>(lettuceConnectionFactory, serializationContext);
	}
}
