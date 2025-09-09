package com.furkankayam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.account.queue}")
    private String accountQueueName;

    @Value("${rabbitmq.account.routing}")
    private String accountRoutingKey;

    @Value("${rabbitmq.verify.queue}")
    private String verifyQueueName;

    @Value("${rabbitmq.verify.routing}")
    private String verifyRoutingKey;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue accountQueue() {
        return new Queue(accountQueueName, true);
    }

    @Bean
    public Queue verifyQueue() {
        return new Queue(verifyQueueName, true);
    }

    @Bean
    public Binding accountBinding() {
        return BindingBuilder
                .bind(accountQueue())
                .to(directExchange())
                .with(accountRoutingKey);
    }

    @Bean
    public Binding verifyBinding() {
        return BindingBuilder
                .bind(verifyQueue())
                .to(directExchange())
                .with(verifyRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;
    }
}
