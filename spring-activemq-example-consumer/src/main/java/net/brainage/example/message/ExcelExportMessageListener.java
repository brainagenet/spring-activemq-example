/*
 * (#) net.brainage.example.message.ExcelExportMessageListener.java
 * Created on 2016-08-24.
 */
package net.brainage.example.message;

import lombok.extern.slf4j.Slf4j;
import net.brainage.core.message.MessageListener;
import net.brainage.example.model.ExcelExportRequestMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author ms29.seo
 */
@Slf4j
@Component
public class ExcelExportMessageListener implements MessageListener<ExcelExportRequestMessage> {

    @JmsListener(concurrency = "1-1", destination = "excel.export.request.queue")
    @Override
    public void onMessage(ExcelExportRequestMessage message) {
        log.info("input message: {}", message);
    }

}
