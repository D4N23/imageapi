package oi.github.D4N23.imageapi.domain.service;

import oi.github.D4N23.imageapi.domain.AccessToken;
import oi.github.D4N23.imageapi.domain.entity.User;

public interface UserService {
    
    User getByEmail(String email);
    User save(User user);
    AccessToken authenticate(String email, String password);
}
