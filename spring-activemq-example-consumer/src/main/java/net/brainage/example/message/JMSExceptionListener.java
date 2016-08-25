/*
 * (#) net.brainage.example.message.JMSExceptionListener.java
 * Created on 2016-08-25.
 */
package net.brainage.example.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

/**
 *
 * @author ms29.seo
 */
@Slf4j
@Component
public class JMSExceptionListener implements ExceptionListener {

    @Override
    public void onException(JMSException exception) {
        log.warn("## exception occured!! -----------------------------", exception);
    }

}
