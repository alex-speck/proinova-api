package br.com.senac.jwt;

import br.com.senac.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private String secret = "exercicio-jwt";
    private String withIssuer = "exercicio-jwt-api";
    private Algorithm algorithm = Algorithm.HMAC256(secret);

    public String gerarToken(User usuario) {
        try {

            String tokenJwt =
                    JWT.create()
                            .withIssuer(withIssuer)
                            .withSubject(usuario.getEmail())
                            .withExpiresAt(this.gerarDataValidaToken())
                            .sign(algorithm);
            return tokenJwt;

        } catch (Exception e) {
            throw new RuntimeException("Erro na geração do token!");
        }
    }

    public String validarToken(String token) {
        try {

            return JWT.require(algorithm)
                    .withIssuer(withIssuer)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (Exception e) {
            return null;
        }
    }

    private Instant gerarDataValidaToken() {
        return LocalDateTime
                .now()
                .plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
