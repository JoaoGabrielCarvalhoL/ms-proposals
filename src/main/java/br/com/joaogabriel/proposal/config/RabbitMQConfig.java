package br.com.joaogabriel.proposal.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.pending.proposal.exchange}")
    private String exchange;

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initRabbitAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange createFanoutExchangePendingProposal() {
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }

    @Bean
    public Binding createBindingPendingProposalMicroserviceAnalysisCredit() {
        return BindingBuilder.bind(createPendingProposalQueueCreditAnalysis())
                .to(createFanoutExchangePendingProposal());
    }

    @Bean
    public Binding createBindingPendingProposalMicroserviceNotification() {
        return BindingBuilder.bind(createPendingProposalQueueNotification())
                .to(createFanoutExchangePendingProposal());
    }

    @Bean
    public Queue createPendingProposalQueueCreditAnalysis() {
        return QueueBuilder.durable("pending-proposal.microservice-credit-analysis").build();
    }

    @Bean
    public Queue createCompletedProposalQueueProposal() {
        return QueueBuilder.durable("completed-proposal.microservice-proposal").build();
    }

    @Bean
    public Queue createPendingProposalQueueNotification() {
        return QueueBuilder.durable("pending-proposal.microservice-notification").build();
    }

    @Bean
    public Queue createCompletedProposalQueueNotification() {
        return QueueBuilder.durable("completed-proposal.microservice-notification").build();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
