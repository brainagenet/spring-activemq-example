/*
 * (#) net.brainage.example.exporter.SampleExcelExportProcessor.java
 * Created on 2016-08-26.
 */
package net.brainage.example.exporter;

import lombok.extern.slf4j.Slf4j;
import net.brainage.core.mapper.SqlMapMapper;
import net.brainage.example.model.ExcelExportRequestMessage;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ExcelExportRequestMessage의 type이 Sample일 경우 실행될 ExcelExportProcessor를 구현합니다.
 * Database의 조회해 온 결과를 Excel로 생성해 줍니다.
 *
 * @author ms29.seo
 */
@Slf4j
@Component("sampleExcelExportProcessor")
public class SampleExcelExportProcessor implements ExcelExportProcessor {

    @Autowired
    SqlMapMapper sqlMapMapper;

    @Override
    public void process(ExcelExportRequestMessage request) throws ExcelExportProcessorException {
        log.info("request type: {}", request.getRequestType());

        sqlMapMapper.select("net.brainage.example.mapper.TestBookMapper.getAll", new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                log.debug("[{}] - {}", resultContext.getResultCount(), resultContext.getResultObject().toString());
            }
        });
    }

}
