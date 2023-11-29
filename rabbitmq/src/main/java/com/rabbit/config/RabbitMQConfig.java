package com.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${topic_exchange}")
	private String topicName;
	
	@Value("${stringque}")
	private String stringQueueName;
	
	@Value("${stringque_routing_key}")
	private String strRoutingKey;
	
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Queue stringQueue() {
		return new Queue(stringQueueName);
	}
	
	@Bean
	public Binding stringQueueBinding() {
		return BindingBuilder
					.bind(stringQueue())
					.to(exchange())
					.with(strRoutingKey);
					
	}
	
}
