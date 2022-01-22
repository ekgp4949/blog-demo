package com.todo.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		// timezone Asia/Seoul 셋팅
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		Locale.setDefault(Locale.KOREA);

		SpringApplication.run(TodoApplication.class, args);
	}

}
