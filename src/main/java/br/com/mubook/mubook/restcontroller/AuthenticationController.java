package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.security.JwtService;
import br.com.mubook.mubook.security.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UsuarioDetailsService usuarioDetailsService;

    private final JwtService jwtService;


}
