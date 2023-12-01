package com.rabbit.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitListner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitListener.class);

	
	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	private void listenToQueue(String message) {
		 LOGGER.info(String.format("Received message -> %s", message));
	}
}
