package br.com.mubook.mubook.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalStateException(
                    "JWT secret key must be at least 32 characters long. " +
                            "Please set jwt.secret.key property");
        }
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    public String generateToken(UserDetails userDetails) {
        UsuarioDetails usuarioDetails = (UsuarioDetails) userDetails;

        String role = usuarioDetails.getUsuario().getRoleUser().getAuthority();

        return JWT.create()
                .withSubject(usuarioDetails.getUsername())
                .withClaim("role", role)
                .withJWTId(java.util.UUID.randomUUID().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .sign(algorithm);
    }


    public String extractUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    public String extractRole(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("role").asString();
    }

    public boolean isTokenValid(String token, String username) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return extractUsername(token).equals(username);
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

}
