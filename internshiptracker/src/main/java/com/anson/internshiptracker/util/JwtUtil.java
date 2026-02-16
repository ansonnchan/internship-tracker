package com.anson.internshiptracker.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;  
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    // generate token for user
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }
    
    // create token with claims and subject 
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)         
                .subject(subject)       
                .issuedAt(new Date(System.currentTimeMillis()))      
                .expiration(new Date(System.currentTimeMillis() + expiration))  
                .signWith(getSigningKey())  
                .compact();
    }
    
    // Get signing key from secret
    private SecretKey getSigningKey() { 
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    // extract email from token 
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    // extract token expiration data
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    // extract a specific claim from token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    // extract all claims from token 
    private Claims extractAllClaims(String token) {
        return Jwts.parser()                
                .verifyWith(getSigningKey())    
                .build()
                .parseSignedClaims(token)   
                .getPayload();              
    
    }
    // check if token expired 
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    // validation 
    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }
}