package com.example.secondservicenavigatorrest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import service.SecreteRepository;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import java.util.Date;

@ManagedBean
public class JwtTokenProvider {

    private String secret;

    @Inject
    private SecreteRepository secreteRepository;


    public String resolveToken(ContainerRequestContext request) {
        String bearerToken = request.getHeaders().getFirst("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserRole(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getAudience();
    }

    public boolean isTokenExpired(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return claimsJws.getBody().getExpiration().before(new Date());
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @PostConstruct
    private void setSecret() {
        secret = secreteRepository.getSecrete().getSecret();
    }
}
