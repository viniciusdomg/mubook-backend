package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.ChangePasswordRequest;
import br.com.mubook.mubook.dto.PasswordResetRequest;
import br.com.mubook.mubook.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final UsuarioService usuarioService;

    @PostMapping("/esqueci-senha")
    public ResponseEntity<String> esqueciSenha(@Valid @RequestBody PasswordResetRequest request) {
        try {
            usuarioService.gerarTokenDeResetDeSenha(request.cpf());
            // Mensagem genérica por segurança, para não confirmar se um CPF existe ou não na base.
            return ResponseEntity.ok("Se um usuário com este CPF existir em nossa base, um e-mail de redefinição será enviado.");
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok("Se um usuário com este CPF existir em nossa base, um e-mail de redefinição será enviado.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro inesperado.");
        }
    }

    @PostMapping("/trocar-senha")
    public ResponseEntity<String> trocarSenha(@Valid @RequestBody ChangePasswordRequest request) {
        try {
            usuarioService.trocarSenha(request.token(), request.novaSenha());
            return ResponseEntity.ok("Senha alterada com sucesso!");
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro inesperado ao tentar alterar a senha.");
        }
    }
}