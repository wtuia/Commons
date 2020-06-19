package restful.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;  
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@PropertySource(value={"file:application.properties"})
public class ServiceMain {

	private static final Logger logger = LoggerFactory.getLogger(ServiceMain.class);
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ServiceMain.class, args);
		logger.error("接口执行监听");
	}
}
