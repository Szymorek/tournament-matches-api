package com.example.tournamentmatches.jwt;

import com.example.tournamentmatches.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private final String JWT_TOKEN_ISSUER = "torunamentmatches.com";
    private final String JWT_TOKEN_KEY = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s;%s", user.getId(), user.getUsername()))
                .setIssuer(JWT_TOKEN_ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes()))
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes())).build().parseClaimsJws(token).getBody();
        return claims.getSubject().split(";")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes())).build().parseClaimsJws(token).getBody();
        logger.info("Claims - {}", claims.toString());
        return claims.getSubject().split(";")[1];
    }

    public Date getExpiration(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes())).build().parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public Boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes())).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            logger.error("Jwt token is invalid - {}", e.getMessage());
        }
        return false;

    }


}
