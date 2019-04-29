package com.tim3.Company;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class CompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
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

}
