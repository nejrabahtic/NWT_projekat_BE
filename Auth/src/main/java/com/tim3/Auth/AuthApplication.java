package com.tim3.Auth;

import com.rabbitmq.client.AMQP;
import com.tim3.Auth.repositories.AuthRepository;
import com.tim3.Auth.services.AuthService;
import com.tim3.Auth.services.SeederService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableAutoConfiguration
public class AuthApplication {
	@Autowired
	private SeederService seederService;

	@EventListener(ApplicationReadyEvent.class)
	public void seedDatabase(){
		seederService.seedAuthTable();
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	public final static String LOG_QUEUE = "LOG_QUEUE";

	@Bean
	Queue queue(){ return new Queue(LOG_QUEUE, false); }

	@Bean
	TopicExchange exchange() { return new TopicExchange("spring-boot-exchange"); }

	@Bean
	Binding binding(Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(LOG_QUEUE);
	}
	@Bean
	MessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(LOG_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

}
