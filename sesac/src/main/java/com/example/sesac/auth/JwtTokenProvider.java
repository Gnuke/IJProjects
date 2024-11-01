package com.example.sesac.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("86400000")
    private int jwtExpiration;

    public String generateToken(Authentication auth) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUid()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .claim("authorities", userPrincipal.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch( MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
