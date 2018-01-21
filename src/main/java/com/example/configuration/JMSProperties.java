/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.configuration;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author Brandon Todd
 */
@ConfigurationProperties(prefix = "spring.diamd.qpid")
public class JMSProperties {
    /**
     * The URL of the remote peer the AMQP client should connect to.
     */
    private String remoteUrl = "amqp://localhost:5672";

    /**
     * AMQP broker username.
     */
    private String username;

    /**
     * Login password of the AMQP broker.
     */
    private String password;

    /**
     * JMS clientID to use for connections. A clientID can only be used by one Connection at a time, so setting it
     * will restrict the ConnectionFactory to creating a single open Connection at a time.
     */
    private String clientId;

    /**
     * Whether the client only checks its local message buffer when using receive calls with a timeout.
     */
    private boolean receiveLocalOnly = false;

    /**
     * Whether the client only checks its local message buffer when using receiveNoWait calls.
     */
    private boolean receiveNoWaitLocalOnly = false;

    private final DeserializationPolicy deserializationPolicy = new DeserializationPolicy();

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isReceiveLocalOnly() {
        return receiveLocalOnly;
    }

    public void setReceiveLocalOnly(boolean receiveLocalOnly) {
        this.receiveLocalOnly = receiveLocalOnly;
    }

    public boolean isReceiveNoWaitLocalOnly() {
        return receiveNoWaitLocalOnly;
    }

    public void setReceiveNoWaitLocalOnly(boolean receiveNoWaitLocalOnly) {
        this.receiveNoWaitLocalOnly = receiveNoWaitLocalOnly;
    }

    public DeserializationPolicy getDeserializationPolicy() {
        return deserializationPolicy;
    }

    public static class DeserializationPolicy {

        /**
         * Whitelist of classes or packages.
         */
        private List<String> whiteList;

        /**
         * Blacklist of classes or packages. Blacklist overrides the whitelist, entries that could match both are
         * counted as blacklisted.
         */
        private List<String> blackList;

        public List<String> getWhiteList() {
            return this.whiteList;
        }

        public void setWhiteList(List<String> whiteList) {
            this.whiteList = whiteList;
        }

        public List<String> getBlackList() {
            return this.blackList;
        }

        public void setBlackList(List<String> blackList) {
            this.blackList = blackList;
        }
    }
}
