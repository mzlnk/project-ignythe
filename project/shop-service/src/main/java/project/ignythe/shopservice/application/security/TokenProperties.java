package project.ignythe.shopservice.application.security;

import java.security.interfaces.RSAPublicKey;

public class TokenProperties {

    private String issuer;
    private RSAPublicKey publicKey;

    public String issuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public RSAPublicKey publicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

}
