/*
 * (#) net.brainage.example.config.MessageConfig.java
 * Created on 2016-08-26.
 */
package net.brainage.example.config;

import net.brainage.example.message.JMSExceptionListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author ms29.seo
 */
@Configuration
public class MessageConfig {

    @Bean
    public ActiveMQConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(
                "failover:(tcp://localhost:61616,tcp://192.168.56.110:61616)" +
                        "?randomize=true&maxReconnectDelay=1000");
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(jmsConnectionFactory());
        connectionFactory.setSessionCacheSize(20);
        connectionFactory.setReconnectOnException(true);
        connectionFactory.setExceptionListener(new JMSExceptionListener());
        return connectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory());
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate(cachingConnectionFactory());
        template.setDefaultDestinationName("excel.export.request.queue");
        return template;
    }

}
