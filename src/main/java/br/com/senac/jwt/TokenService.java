package br.com.senac.jwt;

import br.com.senac.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String secret;

    @Value("${api.security.issuer}")
    private String issuer;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String gerarToken(String email) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(email)
                .withExpiresAt(expiration())
                .sign(getAlgorithm());
    }

    public String validarToken(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }


    private Instant expiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
