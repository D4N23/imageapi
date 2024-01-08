package oi.github.D4N23.imageapi.application.jwt;

public class InvalidTokenException extends RuntimeException{
    
    public InvalidTokenException(String message){
        super(message);
    }
}
