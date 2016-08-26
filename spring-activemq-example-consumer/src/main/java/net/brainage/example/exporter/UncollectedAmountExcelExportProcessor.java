/*
 * (#) net.brainage.example.exporter.UncollectedAmountExcelExportProcessor.java
 * Created on 2016-08-26.
 */
package net.brainage.example.exporter;

import lombok.extern.slf4j.Slf4j;
import net.brainage.example.model.ExcelExportRequestMessage;
import org.springframework.stereotype.Component;

/**
 *
 * @author ms29.seo
 */
@Slf4j
@Component("uncollectedAmountExcelExportProcessor")
public class UncollectedAmountExcelExportProcessor implements ExcelExportProcessor {

    @Override
    public void process(ExcelExportRequestMessage request) throws ExcelExportProcessorException {
        log.info("request type: {}", request.getRequestType());
    }

}
