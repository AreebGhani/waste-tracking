package org.aacounty.wastetrackingng.security;

import org.springframework.stereotype.Component;

@Component
public class JwtIdTokenCredidentialsHolder {
    private String idToken;

    public JwtIdTokenCredidentialsHolder() {
    }

    public String getIdToken() {
        return this.idToken;
    }

    public JwtIdTokenCredidentialsHolder setIdToken(String idToken) {
        this.idToken = idToken;
        return this;
    }
}