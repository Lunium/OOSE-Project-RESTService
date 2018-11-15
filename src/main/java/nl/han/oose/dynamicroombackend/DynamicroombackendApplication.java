package nl.han.oose.dynamicroombackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
@EnableAsync
public class DynamicroombackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicroombackendApplication.class, args);
	}
}
