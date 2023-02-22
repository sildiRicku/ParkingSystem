package com.example.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TrainingApplication {
@GetMapping("/hello")

@PostMapping("/hellow")
public String helloWorldi(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello " + name + "!";
	}
	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);

	}

}
