package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.CriarAtualizarUsuarioRequest;
import br.com.mubook.mubook.dto.FiltrosUsuarioRequest;
import br.com.mubook.mubook.helper.UsuarioHelper;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class GerenciarUsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioHelper helper;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Page<Usuario>> listar(@RequestParam(required = false, defaultValue = "0") int offset,
                                                            @RequestParam(required = false, defaultValue = "0") int limit,
                                                            @RequestParam(required = false) FiltrosUsuarioRequest filtros) {
        try {
            Page<Usuario> usuarios = usuarioService.findAllWithFilters(filtros, offset, limit);
            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<String> register(@Valid @RequestBody CriarAtualizarUsuarioRequest request) {

        try {
            Usuario usuario = helper.RegisterRequestToUsuario(request.nome(), request.cpf(), request.email(), request.senha(), request.role());
            usuarioService.save(usuario);

            return ResponseEntity.ok("Conta cadastrada com sucesso!");
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/all")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<String> deleteAll(@Valid @RequestBody Iterable<Long> request) {
        try{
            usuarioService.hardDeleteAll(request);
            return ResponseEntity.ok("Usuários deletados com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
