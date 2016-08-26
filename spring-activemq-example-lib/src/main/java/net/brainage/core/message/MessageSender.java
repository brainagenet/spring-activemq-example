/*
 * (#) net.brainage.core.message.MessageSender.java
 * Created on 2016-08-24.
 */
package net.brainage.core.message;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.util.StringUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;

/**
 * @author ms29.seo
 */
@Slf4j
public abstract class MessageSender<T extends Serializable> {

    @Setter
    private String destination;

    @Autowired
    @Setter
    private JmsTemplate jmsTemplate;

    public void sendMessage(final T requestMessage) {
        // jmsTemplate.convertAndSend(destination, requestMessage);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(requestMessage);
            }
        });
    }

}
