/*
 * (#) net.brainage.example.exporter.ExcelExportProcessException.java
 * Created on 2016-08-26.
 */
package net.brainage.example.exporter;

import org.springframework.core.NestedRuntimeException;

/**
 * ExcelExportProcessor에서 발생하는 예외의 최상위 클래스로 ExcelExportProcessor 구현체 내부에서 발생하는 모든 예외는
 * 이 클래스를 상속받아야 한다.
 *
 * @author ms29.seo
 */
public class ExcelExportProcessorException extends NestedRuntimeException {

    private static final long serialVersionUID = -208733014692488470L;

    public ExcelExportProcessorException(String msg) {
        super(msg);
    }

    public ExcelExportProcessorException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
