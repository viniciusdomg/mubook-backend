package br.com.mubook.mubook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MubookApplication {

	public static void main(String[] args) {
		SpringApplication.run(MubookApplication.class, args);
	}

}
