package com.example.sb30;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class App {

	@GetMapping("/")
	Mono<String> index() {
		return Mono.fromCallable(() -> "Spring Boot V3.0");
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
