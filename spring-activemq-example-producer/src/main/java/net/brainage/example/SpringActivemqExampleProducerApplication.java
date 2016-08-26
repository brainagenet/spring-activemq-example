package net.brainage.example;

import net.brainage.example.message.ExcelExportRequestMessageSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringActivemqExampleProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringActivemqExampleProducerApplication.class, args);
	}

	@Bean
	public ExcelExportRequestMessageSender excelExportRequestMessageSender() {
		ExcelExportRequestMessageSender sender = new ExcelExportRequestMessageSender();
		sender.setDestination("excel.export.request.queue");
		return sender;
	}

}
