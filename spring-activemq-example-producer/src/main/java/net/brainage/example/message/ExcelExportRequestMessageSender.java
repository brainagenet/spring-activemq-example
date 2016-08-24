/*
 * (#) net.brainage.example.message.ExcelExportRequestMessageSender.java
 * Created on 2016-08-24.
 */
package net.brainage.example.message;

import lombok.extern.slf4j.Slf4j;
import net.brainage.core.message.MessageSender;
import net.brainage.example.model.ExcelExportRequestMessage;
import org.springframework.stereotype.Component;

/**
 * @author ms29.seo
 */
@Slf4j
public class ExcelExportRequestMessageSender extends MessageSender<ExcelExportRequestMessage> {
}
