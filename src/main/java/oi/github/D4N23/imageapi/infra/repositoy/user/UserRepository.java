package oi.github.D4N23.imageapi.infra.repositoy.user;

import org.springframework.data.jpa.repository.JpaRepository;

import oi.github.D4N23.imageapi.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
