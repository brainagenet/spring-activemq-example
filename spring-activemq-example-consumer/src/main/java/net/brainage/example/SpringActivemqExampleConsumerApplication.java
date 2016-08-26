/*
 * (#) net.brainage.example.SpringActivemqExampleConsumerApplication.java
 * Created on 2016-08-24.
 */
package net.brainage.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author ms29.seo
 */
@SpringBootApplication
public class SpringActivemqExampleConsumerApplication {

    /*
    @Bean
    public ActiveMQConnectionFactory mqConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("failover:(tcp://localhost:61616,tcp://localhost:61619)?randomize=true");
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultDestinationName("sample.queue");
        // template.setMessageConverter(new ExcelExportRequestMessageConverter());
        return template;
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(SpringActivemqExampleConsumerApplication.class, args);
    }

}
