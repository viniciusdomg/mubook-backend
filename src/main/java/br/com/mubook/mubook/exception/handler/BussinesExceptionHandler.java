package br.com.mubook.mubook.exception.handler;

import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.exception.ObjetoNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BussinesExceptionHandler {

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<String> handleBussinesExceptionCampoInvalido(BussinesException b) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo inválido: " + b.getMessage());
    }

    @ExceptionHandler(ObjetoNullException.class)
    public ResponseEntity<String> handleBussinesExceptionObjetoNull() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Objeto não pode ser nulo");
    }
}
