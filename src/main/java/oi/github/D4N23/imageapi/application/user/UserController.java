package oi.github.D4N23.imageapi.application.user;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oi.github.D4N23.imageapi.domain.entity.User;
import oi.github.D4N23.imageapi.domain.exception.DuplicatedTupleException;
import oi.github.D4N23.imageapi.domain.service.UserService;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity save(@RequestBody UserDto userDto){
      try{
        User user = mapper.mapToUser(userDto);
        service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
      }catch(DuplicatedTupleException e){
        Map<String, String> jsonResultado = Map.of("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResultado);
      }  
        
    }

    @PostMapping("/auth")
    public ResponseEntity authenticate(@RequestBody CredentialsDto credentialsDto){
        var token = service.authenticate(credentialsDto.getEmail(), credentialsDto.getPassword());
        if(token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(token);
    }
}
