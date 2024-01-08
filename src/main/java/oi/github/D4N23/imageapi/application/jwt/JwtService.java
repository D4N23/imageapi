package oi.github.D4N23.imageapi.application.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import oi.github.D4N23.imageapi.domain.AccessToken;
import oi.github.D4N23.imageapi.domain.entity.User;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final SecretKeyGenerator keyGenerator;

    public AccessToken generatAccessToken(User user){

        var key = keyGenerator.getKey();
        var expirationDate = generateExporationDate();
        var claims =  generateTokenClaims(user);

       String token = Jwts.builder()
                        .signWith(key)
                        .subject(user.getEmail())
                        .expiration(expirationDate)
                        .claims(claims)
                        .compact();

        return new AccessToken(token);
    }

    private Date generateExporationDate(){
        var expirationMinutes = 60;
        LocalDateTime now = LocalDateTime.now().plusMinutes(expirationMinutes);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Map<String, Object> generateTokenClaims(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        return claims;
    }

    public String getEmailFromToken(String tokenJwt){

        try{    
            JwtParser  build = Jwts.parser()
                                .verifyWith(keyGenerator.getKey())
                                .build();
            Jws<Claims> jwsClaims = build.parseSignedClaims(tokenJwt);

            Claims claims = jwsClaims.getPayload();

            return claims.getSubject();
        }catch(JwtException e){
            throw new InvalidTokenException(e.getMessage());
        }
    }
}
