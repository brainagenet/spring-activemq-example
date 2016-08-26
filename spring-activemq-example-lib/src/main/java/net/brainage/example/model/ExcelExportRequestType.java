/*
 * (#) net.brainage.example.model.ExcelExportRequestType.java
 * Created on 2016-08-26.
 */
package net.brainage.example.model;

/**
 * @author ms29.seo
 */
public enum ExcelExportRequestType {

    SAMPLE("sample"), UNCOLLECTED_AMOUNT("uncollectedAmount");

    private final String value;

    ExcelExportRequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ExcelExportRequestType{" +
                "value='" + value + '\'' +
                '}';
    }

}
