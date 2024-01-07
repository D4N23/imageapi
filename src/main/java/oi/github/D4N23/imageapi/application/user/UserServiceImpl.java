package oi.github.D4N23.imageapi.application.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import oi.github.D4N23.imageapi.application.jwt.JwtService;
import oi.github.D4N23.imageapi.domain.AccessToken;
import oi.github.D4N23.imageapi.domain.entity.User;
import oi.github.D4N23.imageapi.domain.exception.DuplicatedTupleException;
import oi.github.D4N23.imageapi.domain.service.UserService;
import oi.github.D4N23.imageapi.infra.repositoy.user.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public User getByEmail(String email) {
       return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {

        var possibleUser = getByEmail(user.getEmail());
        if(possibleUser != null){
            throw new DuplicatedTupleException("User already exists");
        }

        encodePassword(user);

       return repository.save(user);
    }

    @Override
    public AccessToken authenticate(String email, String password) {
       var user = getByEmail(email);
       if(user == null){
        return null;
       }

       boolean matches = passwordEncoder.matches(password, user.getPassword());
       if(matches){
        return jwtService.generatAccessToken(user);
       }

       return null;
    }

    private void encodePassword(User user){
        String rawPassword = user.getPassword();
        String encodePassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodePassword); 
    }
    
}
