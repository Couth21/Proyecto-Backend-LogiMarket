package com.springboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    // Usa una clave segura y larga codificada en Base64
    private final String secretKeyBase64 = Base64.getEncoder().encodeToString("unaClaveMuyLargaDe256BitsOmasParaJwtTokenSegura".getBytes());

    public String generateToken(String username, String role) {
        Key key = new SecretKeySpec(Base64.getDecoder().decode(secretKeyBase64), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas de expiración
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Claims extractClaims(String token) {
        Key key = new SecretKeySpec(Base64.getDecoder().decode(secretKeyBase64), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String username) {
        return (username.equals(extractClaims(token).getSubject()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
