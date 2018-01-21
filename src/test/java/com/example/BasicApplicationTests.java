package com.example;

import com.example.service.Sender;
import org.apache.activemq.broker.BrokerService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicApplicationTests {

    @Autowired
    private Sender sender;
    
    private static BrokerService broker;
    @BeforeClass
    public static void setupConnections() throws Exception
    {
        broker = new BrokerService();
        broker.addConnector("amqp://localhost:5672");
        broker.setPersistent(false);
        broker.start();
    }
    @Test
    public void contextLoads() 
    {
        sender.send("com.package", "I love lucy.");
         sender.send("com.package", "I love lucy.");
          sender.send("com.package", "I love lucy.");
           sender.send("com.package", "I love lucy.");
            sender.send("com.package", "I love lucy.");
             sender.send("com.package", "I love lucy.");
             
    }
        
    @AfterClass
    public static void destroyConnections() throws Exception
    {
        broker.stop();
    }
    
}
