/*
 * (#) net.brainage.example.exporter.ExcelExportProcessorFactory.java
 * Created on 2016-08-26.
 */
package net.brainage.example.exporter;

/**
 * ExcelExportRequest Type 별 ExcelExportProcessor를 찾기 위한 Factory Interface로 이것을 구현한 구현체를 사용하던지 아니면
 * Spring에서 제공하는 ServiceLocatorFactoryBean을 정의하여 사용한다.
 *
 * @author ms29.seo
 */
public interface ExcelExportProcessorFactory {

    ExcelExportProcessor getExcelExportProcessor(String processType);

}
