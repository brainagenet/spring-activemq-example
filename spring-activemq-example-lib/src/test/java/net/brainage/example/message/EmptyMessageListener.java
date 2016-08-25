/*
 * (#) net.brainage.example.message.EmptyMessageListener.java
 * Created on 2016-08-24.
 */
package net.brainage.example.message;

import lombok.extern.slf4j.Slf4j;
import net.brainage.core.message.MessageListener;
import org.springframework.jms.annotation.JmsListener;

/**
 *
 * @author ms29.seo
 */
@Slf4j
public class EmptyMessageListener implements MessageListener<String> {

    @JmsListener(concurrency = "1-1", destination = "test.queue")
    @Override
    public void onMessage(String message) {
        log.info("input message: {}", message);
    }

}
