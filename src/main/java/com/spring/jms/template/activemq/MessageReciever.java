package com.spring.jms.template.activemq;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever  {

	@JmsListener(destination="jms.message.mq")
	public void recieveMessage(Message msg) {
		System.out.println("Recieved"+ msg);
	}
}
