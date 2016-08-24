package net.brainage.example.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by ms29.seo on 2016-08-24.
 */
@Slf4j
public class ExcelExportRequestMessageConverter implements MessageConverter {

    private static final String PROP_REQUEST_ID = "requestId";
    private static final String PROP_REQUEST_TYPE = "requestType";
    private static final String PROP_TIMESTAMP = "timestamp";

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        log.info(">> object: {}", object);

        ExcelExportRequestMessage requestMessage = (ExcelExportRequestMessage) object;
        MapMessage message = session.createMapMessage();
        message.setLong(PROP_REQUEST_ID, requestMessage.getRequestId());
        message.setString(PROP_REQUEST_TYPE, requestMessage.getRequestType());
        message.setLong(PROP_TIMESTAMP, requestMessage.getTimestamp());
        log.info(">> MapMessage: {}", message);
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        log.info("message: {}", message);
        MapMessage mm = (MapMessage) message;
        Long requestId = mm.getLongProperty(PROP_REQUEST_ID);
        String requestType = mm.getStringProperty(PROP_REQUEST_TYPE);
        long timestamp = mm.getLongProperty(PROP_TIMESTAMP);
        return new ExcelExportRequestMessage(requestId, requestType, timestamp);
    }

}
