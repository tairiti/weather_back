package com.example.weatherback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherBackApplication.class, args);
	}

}
