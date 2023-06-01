package com.spring.KafkaDemo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@KafkaListener(topics="messageName" , groupId="Group1")
	public void listenToTopic(String recievedMessage) {
		System.out.println("The message recieved is"+ recievedMessage);
	}
}
