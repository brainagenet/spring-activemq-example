/*
 * (#) net.brainage.example.config.ExcelExportProcessorConfig.java
 * Created on 2016-08-26.
 */
package net.brainage.example.config;

import net.brainage.example.exporter.ExcelExportProcessorFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ms29.seo
 */
@Configuration
public class ExcelExportProcessorConfig {

    @Bean(name = "excelExportProcessorFactory")
    public ServiceLocatorFactoryBean excelExportProcessorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ExcelExportProcessorFactory.class);
        return factoryBean;
    }

}
