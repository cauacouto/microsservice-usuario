package dev.couto.microsservice_user.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.couto.microsservice_user.domin.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("user-microsservice")
                    .withSubject(usuario.getEmail())
                    .withClaim("id",usuario.getId().toString())
                    .withClaim("role",usuario.getRole().name())
                    .withExpiresAt(genereteExpirationDate())
                    .sign(algorithm);
            return token;

        }catch (JWTCreationException ex){
            throw new RuntimeException("token invalido");

        }
    }


    public String verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("user-microsservice")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException ex){
            return null;

        }
    }


    private Instant genereteExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));
    }




}
