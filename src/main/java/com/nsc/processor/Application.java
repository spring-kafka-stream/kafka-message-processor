package com.nsc.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	@Autowired
	private SimpMessagingTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping(path = "/socket", method = RequestMethod.GET)
	public void send() {
		template.convertAndSend("/topic/event", "hello");
	}
}
