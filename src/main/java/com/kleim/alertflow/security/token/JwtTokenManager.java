package com.kleim.alertflow.security.token;

import com.kleim.alertflow.security.auth.domain.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenManager {

    private final Long lifetime;
    private final SecretKey key;

    public JwtTokenManager(
          @Value("${jwt.token.lifetime}") Long lifetime,
          @Value("${jwt.token.signature}")  String key) {
        this.lifetime = lifetime;
        this.key = Keys.hmacShaKeyFor(key.getBytes());
    }

    public String generateToken(User user) {
        Map<String, Object> props = new HashMap<>();
        props.put("role", user.role().name());
        Date issuedTime = new Date();
        Date expiredTime = new Date(issuedTime.getTime() + lifetime);
        return Jwts.builder()
                .issuedAt(issuedTime)
                .expiration(expiredTime)
                .signWith(key)
                .subject(user.login())
                .claims(props)
                .compact();
    }

    public boolean isTokenValid(String token){
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException e) {
            return false;
        }
        return true;
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getId();
    }

}
