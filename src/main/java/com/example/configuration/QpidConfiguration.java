/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.configuration;


import org.apache.qpid.jms.JmsConnectionFactory;
import org.apache.qpid.jms.policy.JmsDefaultDeserializationPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
/**
 *
 * @author Brandon Todd
 */
public class QpidConfiguration {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(QpidConfiguration.class);
    private final JMSProperties properties;
    /**
     * Creates a new QpidJMSConnectionFactoryFactory instance
     *
     * @param properties
     *      The QpidJMSProperties to use when building new factories.
     */
    public QpidConfiguration(JMSProperties properties) {
        Assert.notNull(properties, "Properties must not be null");
        this.properties = properties;
    }
    
    public JmsConnectionFactory createConnectionFactory(Class<JmsConnectionFactory> factoryClass) {
        try {
            JmsConnectionFactory factory = new JmsConnectionFactory();

            factory.setRemoteURI(properties.getRemoteUrl());

            if (StringUtils.hasLength(properties.getUsername())) {
                factory.setUsername(properties.getUsername());
            }

            if (StringUtils.hasLength(properties.getPassword())) {
                factory.setPassword(properties.getPassword());
            }

            if (StringUtils.hasLength(properties.getClientId())) {
                factory.setClientID(properties.getClientId());
            }

            factory.setReceiveLocalOnly(properties.isReceiveLocalOnly());

            factory.setReceiveNoWaitLocalOnly(properties.isReceiveNoWaitLocalOnly());

            configureDeserializationPolicy(properties, factory);

            return factory;
        } catch (Exception ex) {
            LOGGER.error("Exception while createing the AMQP 1.0 JMS Connection Factory.", ex);
            throw new IllegalStateException("Failed to create the AMQP 1.0 JMS ConnectionFactory, " +
                "make sure the client Jar is on the Classpath.", ex);
        }
    }
    
    private void configureDeserializationPolicy(JMSProperties properties, JmsConnectionFactory factory) {
        JmsDefaultDeserializationPolicy deserializationPolicy =
            (JmsDefaultDeserializationPolicy) factory.getDeserializationPolicy();

        if (!ObjectUtils.isEmpty(properties.getDeserializationPolicy().getWhiteList())) {
            deserializationPolicy.setWhiteList(StringUtils.collectionToCommaDelimitedString(
                    properties.getDeserializationPolicy().getWhiteList()));
        }

        if (!ObjectUtils.isEmpty(properties.getDeserializationPolicy().getBlackList())) {
            deserializationPolicy.setBlackList(StringUtils.collectionToCommaDelimitedString(
                    properties.getDeserializationPolicy().getBlackList()));
        }
    }
}
