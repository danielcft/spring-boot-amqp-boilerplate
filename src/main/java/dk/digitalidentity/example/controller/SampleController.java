package dk.digitalidentity.example.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.digitalidentity.example.dto.Receiver;

@RestController
public class SampleController {
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	Receiver receiver;

	@RequestMapping
	public String pushMessage() throws InterruptedException {
		rabbitTemplate.convertAndSend("myqueue", "Hello from RabbitMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		return "";
	}
}