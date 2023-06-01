package com.spring.jms.template.activemq;

import java.util.Date;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

@SpringBootApplication
@ComponentScan("com.spring.jms.template.activemq")
@EnableJms
public class JmsTemplateEmbeddedActiveMqApplication {

	public static void main(String[] args) {
	    // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(JmsTemplateEmbeddedActiveMqApplication.class, args);
 
        //Get JMS template bean reference
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
 
        // Send a message
        System.out.println("Sending a message.");
        
        jmsTemplate.convertAndSend("jms.message.mq", 
					        		new Message(1990L, 
					        				"Writing message in embedded activemq ",
					        				new Date()));
		System.out.println("message sent.");
        
	}
	
	 @Bean
	 public JmsListenerContainerFactory<?> myFactory(
	                            ConnectionFactory connectionFactory,
	                            DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    	
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        
	     // anonymous class
	        factory.setErrorHandler(
	            new ErrorHandler() {
	              @Override
	              public void handleError(Throwable t) {
	                System.err.println("An error has occurred in the transaction");
	              }
	        });
	       
	        // lambda function
	        //factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction"));
	        
	        
	        // This provides all boot's default to this factory, including the message converter
	        configurer.configure(factory, connectionFactory);
	        // You could still override some of Boot's default if necessary.
	        
	        return factory;
	    }
	 
	    @Bean
	    public MessageConverter jacksonJmsMessageConverter() {
	    	
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        converter.setTargetType(MessageType.TEXT);
	        converter.setTypeIdPropertyName("_type");
	        
	        return converter;
	    }
	
}
