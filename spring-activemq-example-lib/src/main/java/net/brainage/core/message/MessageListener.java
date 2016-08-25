/*
 * (#) net.brainage.core.message.MessageListener.java
 * Created on 2016-08-24.
 */
package net.brainage.core.message;

/**
 *
 * @author ms29.seo
 */
public interface MessageListener<T> {

    void onMessage(T message);

}
