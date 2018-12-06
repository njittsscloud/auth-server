package com.tss.authserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: MQG
 * @date: 2018/12/6
 */
@Component
@ConfigurationProperties(prefix = "authentication.oauth")
public class Oauth2Properties {
    
    private String clientId;
    private String clientSecret;
    private int tokenValidityInSeconds;
    private String[] scope;
    private String[] grantType;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String[] getScope() {
        return scope;
    }

    public void setScope(String[] scope) {
        this.scope = scope;
    }

    public String[] getGrantType() {
        return grantType;
    }

    public void setGrantType(String[] grantType) {
        this.grantType = grantType;
    }
}
