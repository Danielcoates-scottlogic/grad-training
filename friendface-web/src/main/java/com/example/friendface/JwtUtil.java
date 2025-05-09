package com.example.friendface;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Base64;

import java.util.Date;
@Component
public class JwtUtil {

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    @Min(500000)
    private long jwtExpiration;

    public String generateToken(String username) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(Keys.hmacShaKeyFor(keyBytes))
                    .compact();
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException("Token generation failed");
        }
    }

    public String extractUsername(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            return Jwts.parserBuilder()
                    .setSigningKey(keyBytes)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Invalid or expired token");
            throw new IllegalArgumentException("Invalid or expired token");
        }
    }


}
