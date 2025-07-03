package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.AuthenticationRequest;
import br.com.mubook.mubook.dto.AuthenticationResponse;
import br.com.mubook.mubook.dto.CriarAtualizarUsuarioRequest;
import br.com.mubook.mubook.helper.UsuarioHelper;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.security.JwtService;
import br.com.mubook.mubook.security.UsuarioDetailsService;
import br.com.mubook.mubook.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

    private final UsuarioService usuarioService;

    private final UsuarioHelper helper;

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

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody CriarAtualizarUsuarioRequest request) {
        try {
            Usuario usuario = helper.RegisterRequestToUsuario(request.nome(), request.cpf(), request.email(), request.senha(), request.role());
            usuarioService.save(usuario);

            return ResponseEntity.ok("Conta cadastrada com sucesso!");
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
