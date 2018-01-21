package com.example;

import com.example.service.Sender;
import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
public class BasicApplication {
    @Autowired
    private Sender sender;
    
	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
        
   @PostConstruct
   public void sendStuff()
   {
       sender.send("com.package", "I love lucy.");
         sender.send("com.package", "I love lucy.");
          sender.send("com.package", "I love lucy.");
           sender.send("com.package", "I love lucy.");
            sender.send("com.package", "I love lucy.");
             sender.send("com.package", "I love lucy.");
   }
   
  @Bean
  public JmsListenerContainerFactory<?> myFactory(
      ConnectionFactory connectionFactory,
      DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    return factory;
  }
}
