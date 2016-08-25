package net.brainage.example.message;

import lombok.extern.slf4j.Slf4j;
import net.brainage.core.message.MessageListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by ms29.seo on 2016-08-24.
 */
@Slf4j
@Component
public class SampleMessageListener implements MessageListener<String> {

    @JmsListener(destination = "sample.queue", concurrency = "1-10")
    @Override
    public void onMessage(String message) {
        log.info("inbound message: {}", message);
    }

}
