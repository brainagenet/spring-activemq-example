/*
 * (#) net.brainage.example.message.ExcelExportRequestMessageSenderTest.java
 * Created on 2016-08-24.
 */
package net.brainage.example.message;

import net.brainage.example.model.ExcelExportRequestMessage;
import net.brainage.example.model.ExcelExportRequestType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ms29.seo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExcelExportRequestMessageSenderTest {

    @Autowired
    ExcelExportRequestMessageSender excelExportRequestMessageSender;

    @Test
    public void test_send_excel_export_request_message() {
        excelExportRequestMessageSender.sendMessage(new ExcelExportRequestMessage(1L, ExcelExportRequestType.SAMPLE));
    }

}
