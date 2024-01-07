package oi.github.D4N23.imageapi.application.user;

import org.springframework.stereotype.Component;

import oi.github.D4N23.imageapi.domain.entity.User;

@Component
public class UserMapper {
    
    public User mapToUser(UserDto userDto){
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .build();
    }
}
