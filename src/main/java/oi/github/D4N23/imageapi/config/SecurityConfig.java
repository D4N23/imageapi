package oi.github.D4N23.imageapi.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import oi.github.D4N23.imageapi.application.jwt.JwtService;
import oi.github.D4N23.imageapi.config.filter.JwtFilter;
import oi.github.D4N23.imageapi.domain.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtFilter jwtFilter(JwtService jwtService, UserService userService){
        return new JwtFilter(jwtService, userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{
        
        return http
              .csrf(AbstractHttpConfigurer::disable)
              .cors(cors -> cors.configure(http))
              .authorizeHttpRequests(auth -> {  
                auth.requestMatchers("/v1/users/**").permitAll();
                auth.anyRequest().authenticated();
              })
              .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
              .build();
    }

    @Bean 
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        cors.registerCorsConfiguration("/**", config);

        return cors;
    }
}


