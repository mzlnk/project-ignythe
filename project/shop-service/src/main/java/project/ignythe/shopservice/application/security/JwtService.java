package project.ignythe.shopservice.application.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.lang.Nullable;

public class JwtService {

    private final JWTVerifier verifier;

    public JwtService(TokenProperties tokenProperties) {
        this.verifier = JWT.require(Algorithm.RSA256(tokenProperties.publicKey(), null))
                .withIssuer(tokenProperties.issuer())
                .build();
    }

    public Long getPrincipal(@Nullable String token) {
        return Try.of(() -> extractPrincipalFromToken(token)).getOrNull();
    }

    private Long extractPrincipalFromToken(String token) {
        return Option.of(token)
                .map(verifier::verify)
                .map(DecodedJWT::getSubject)
                .map(Long::parseLong)
                .getOrNull();
    }

    public JwtCredentials getCredentials(String token) {
        return Try.of(() -> extractCredentialsFromToken(token)).getOrNull();
    }

    private JwtCredentials extractCredentialsFromToken(String token) {
        return Option.of(token)
                .map(verifier::verify)
                .map(jwt -> jwt.getClaim("roles"))
                .map(claim -> claim.asList(String.class))
                .map(roles -> new JwtCredentials(token, roles))
                .getOrNull();
    }

}
