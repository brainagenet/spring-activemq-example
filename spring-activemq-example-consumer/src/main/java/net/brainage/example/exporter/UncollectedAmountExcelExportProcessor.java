package net.brainage.example.exporter;

import lombok.extern.slf4j.Slf4j;
import net.brainage.example.model.ExcelExportRequestMessage;
import org.springframework.stereotype.Component;

/**
 * Created by ms29.seo on 2016-08-26.
 */
@Slf4j
@Component("uncollectedAmountExcelExportProcessor")
public class UncollectedAmountExcelExportProcessor implements ExcelExportProcessor {

    @Override
    public ExcelExportProcessor process(ExcelExportRequestMessage request) throws ExcelExportProcessorException {
        log.info("request type: {}", request.getRequestType());
        return null;
    }

}
