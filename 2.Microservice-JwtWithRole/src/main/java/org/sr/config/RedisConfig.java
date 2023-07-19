//package org.sr.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
////	@Value("${spring.redis.host}")
////	private String host;
////
////	@Value("${spring.redis.port}")
////	private int port;
//
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
////		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
//				"redis.dev.skaleup.tech", 6379);
//		return new JedisConnectionFactory(redisStandaloneConfiguration);
//	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		template.setDefaultSerializer(new StringRedisSerializer());
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		return template;
//	}
//}
