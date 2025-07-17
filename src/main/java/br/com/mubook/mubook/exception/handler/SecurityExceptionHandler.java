package br.com.mubook.mubook.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException b) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas: " + b.getMessage());
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabledUser(DisabledException d) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário desativado: " + d.getMessage());
    }
}
