/*
 * (#) net.brainage.example.model.ExcelExportRequest.java
 * Created on 2016-08-24.
 */
package net.brainage.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 * @author ms29.seo
 */
@Data
@ToString
@EqualsAndHashCode
public class ExcelExportRequestMessage implements Serializable {

    private static final long serialVersionUID = -5037356708682400358L;

    private final Long requestId;
    private final String requestType;
    private final long timestamp;

    public ExcelExportRequestMessage(Long requestId, ExcelExportRequestType requestType) {
        this.requestId = requestId;
        this.requestType = requestType.getValue();
        this.timestamp = System.currentTimeMillis();
    }

    public ExcelExportRequestMessage(Long requestId, ExcelExportRequestType requestType, long timestamp) {
        this.requestId = requestId;
        this.requestType = requestType.getValue();
        this.timestamp = timestamp;
    }

    public ExcelExportRequestType getType() {
        return ExcelExportRequestType.valueOf(this.requestType);
    }

}
