package com.poc.r2dbcissue;

import com.poc.r2dbcissue.repository.TestRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class R2dbcIssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(R2dbcIssueApplication.class, args);
	}


}
