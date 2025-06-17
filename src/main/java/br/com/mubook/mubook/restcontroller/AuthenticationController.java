package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.AuthenticationRequest;
import br.com.mubook.mubook.dto.AuthenticationResponse;
import br.com.mubook.mubook.security.JwtService;
import br.com.mubook.mubook.security.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UsuarioDetailsService usuarioDetailsService;

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.user(),
                        request.password()
                )
        );
        final String token = jwtService.generateToken(usuarioDetailsService.loadUserByUsername(request.user()));
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
