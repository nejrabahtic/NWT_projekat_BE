package com.tim3.Log;

import com.tim3.Log.models.Reciever;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
	}

	public final static String LOG_QUEUE = "LOG_QUEUE";
	public final static String LOG_EXCHANGE = "LOG_INBOUND";
	public final static String ROUTING_KEY = "LOG.AUTH.#";


	@Bean
	Queue queue(){ return new Queue(LOG_QUEUE, false); }

	@Bean
	TopicExchange exchange() { return new TopicExchange(LOG_EXCHANGE); }

	@Bean
	Binding binding(Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}


	@Bean
	MessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(LOG_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	@Bean
	MessageListenerAdapter listenerAdapter(Reciever receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

}
