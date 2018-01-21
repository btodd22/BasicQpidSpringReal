/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.activemq.broker.BrokerService;
import org.springframework.stereotype.Component;

/**
 *
 * @author Brandon Todd
 */
//@Component
public class BaseBrokerConfiguration {
    private BrokerService broker;
    @PostConstruct
    public void setup() throws Exception
    {
        broker = new BrokerService();
        broker.addConnector("amqp://localhost:5678");
        broker.setPersistent(false);
        broker.start();
    }
    
    @PreDestroy
    public void shutdownbroker() throws Exception
    {
        broker.stop();
    }
            
}
