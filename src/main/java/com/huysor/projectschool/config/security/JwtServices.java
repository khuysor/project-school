package com.huysor.projectschool.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServices {
    private final String SECRETKEY = "4e1372dee25b0a1b4331febeb2363fab1ed554c5610749d70486eec2dc8a6b64";
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigninKey())
                .setIssuer("School Backend").compact();
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build()
                .parseClaimsJws(token).getBody();
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractAllClaim(token,
                Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return isExpired(token).before(new Date());
    }

    private Date isExpired(String token) {
        return extractAllClaim(token, Claims::getExpiration);
    }

    public <T> T extractAllClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaim(token);
        return resolver.apply(claims);
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
