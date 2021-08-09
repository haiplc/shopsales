package shopsale.com.asmspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AsmspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsmspringbootApplication.class, args);
	}

}
