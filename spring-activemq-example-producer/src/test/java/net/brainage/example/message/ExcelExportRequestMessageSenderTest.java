package net.brainage.example.message;

import com.google.common.collect.Lists;
import net.brainage.example.model.ExcelExportRequestMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.TransportListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

/**
 * Created by ms29.seo on 2016-08-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ExcelExportRequestMessageSenderTest {


    @Configuration
    static class TestConfig {

        @Bean
        public ActiveMQConnectionFactory mqConnectionFactory() {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            factory.setBrokerURL("failover:(tcp://localhost:61616,tcp://192.168.56.110:61616)?randomize=false");
            factory.setTrustedPackages(Lists.newArrayList("net.brainage.example.model"));
            factory.setTrustAllPackages(false);
            return factory;
        }

        @Bean
        public JmsTemplate jmsTemplate(ActiveMQConnectionFactory mqConnectionFactory) {
            JmsTemplate template = new JmsTemplate(mqConnectionFactory);
            template.setDefaultDestinationName("sample.queue");
            // template.setMessageConverter(new ExcelExportRequestMessageConverter());
            return template;
        }

        @Bean
        public ExcelExportRequestMessageSender excelExportRequestMessageSender() {
            ExcelExportRequestMessageSender sender = new ExcelExportRequestMessageSender();
            sender.setDestination("excel.export.request.queue");
            return sender;
        }

    }

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ExcelExportRequestMessageSender excelExportRequestMessageSender;

    @Test
    public void test_send_excel_export_request_message() {
        final ExcelExportRequestMessage requestMessage = new ExcelExportRequestMessage(1L, "미수금조회");
        // excelExportRequestMessageSender.sendMessage(requestMessage);

        jmsTemplate.send("excel.export.request.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                /*
                MapMessage msg = session.createMapMessage();
                msg.setLong("requestId", requestMessage.getRequestId());
                msg.setString("requestType", requestMessage.getRequestType());
                msg.setLong("timestamp", requestMessage.getTimestamp());
                return msg;
                */
                return session.createObjectMessage(requestMessage);
            }
        });
    }

}
