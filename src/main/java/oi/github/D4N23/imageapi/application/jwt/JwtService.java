package oi.github.D4N23.imageapi.application.jwt;

import org.springframework.stereotype.Service;

import oi.github.D4N23.imageapi.domain.AccessToken;
import oi.github.D4N23.imageapi.domain.entity.User;

@Service
public class JwtService {
    
    public AccessToken generatAccessToken(User user){
        return new AccessToken("");
    }
}
