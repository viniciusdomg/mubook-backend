package br.com.mubook.mubook.exception;

public class AuthenticationInvalidException extends RuntimeException {
    public AuthenticationInvalidException(String message) {
        super(message);
    }
}
