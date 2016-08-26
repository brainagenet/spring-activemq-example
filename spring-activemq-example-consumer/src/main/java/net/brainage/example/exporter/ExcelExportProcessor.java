/*
 * (#) net.brainage.example.exporter.ExcelExportProcess.java
 * Created on 2016-08-26.
 */
package net.brainage.example.exporter;

import net.brainage.example.model.ExcelExportRequestMessage;

/**
 * Excel Export Process Interface로 ExcelExportReqeustMessage를 파라메터로 받아서 처리한다.
 *
 * @author ms29.seo
 */
public interface ExcelExportProcessor {

    void process(ExcelExportRequestMessage request) throws ExcelExportProcessorException;

}
