/*
 * (#) net.brainage.example.message.ExcelExportMessageListener.java
 * Created on 2016-08-24.
 */
package net.brainage.example.message;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.brainage.core.message.MessageListener;
import net.brainage.example.exporter.ExcelExportProcessor;
import net.brainage.example.exporter.ExcelExportProcessorFactory;
import net.brainage.example.model.ExcelExportRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ms29.seo
 */
@Slf4j
@Component
public class ExcelExportMessageListener implements MessageListener<ExcelExportRequestMessage> {

    @Autowired
    ExcelExportProcessorFactory excelExportProcessorFactory;

    private List<String> supportTypes = Lists.newArrayList("sample", "uncollectedAmount");

    @JmsListener(concurrency = "1-1", destination = "excel.export.request.queue")
    @Override
    public void onMessage(ExcelExportRequestMessage message) {
        log.info("input message: {}", message);
        final String requestedType = message.getRequestType();
        boolean contains = Iterables.any(supportTypes, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.equals(requestedType);
            }
        });
        if (!contains) {
            log.warn("Requested not supported request type for '{}'", requestedType);
            return;
        }
        ExcelExportProcessor excelExportProcessor = excelExportProcessorFactory.getExcelExportProcessor(message.getRequestType() + "ExcelExportProcessor");
        if (excelExportProcessor != null) {
            excelExportProcessor.process(message);
        }

    }

}
