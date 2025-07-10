package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.CriarAtualizarUsuarioRequest;
import br.com.mubook.mubook.dto.FiltrosUsuarioRequest;
import br.com.mubook.mubook.dto.PageResponse;
import br.com.mubook.mubook.dto.UsuarioResponse;
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

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario/")
public class GerenciarUsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioHelper helper;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public PageResponse<UsuarioResponse> listar(@RequestParam(required = false, defaultValue = "1") int offset,
                                        @RequestParam(required = false, defaultValue = "1") int limit,
                                        @RequestParam(required = false) String nome, @RequestParam(required = false) String cpf,
                                        @RequestParam(required = false) String genero) {
        try {
            FiltrosUsuarioRequest filtros = new FiltrosUsuarioRequest(nome, cpf, genero);
            Page<Usuario> page = usuarioService.findAllWithFilters(filtros, offset, limit);
            List<UsuarioResponse> response = helper.PageUsuarioToUsuarioResponse(page.getContent());
            return new PageResponse<>(response, page.getTotalElements(), page.getTotalPages(), page.getSize(), page.getNumber());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<String> register(@Valid @RequestBody CriarAtualizarUsuarioRequest request) {
        try {
            System.out.println("Valor do role: "+ request.tipo());
            Usuario usuario = helper.RegisterRequestToUsuario(request.nome(), request.cpf(), request.email(), request.senha(), request.tipo());
            usuarioService.save(usuario);

            return ResponseEntity.ok("Conta cadastrada com sucesso!");
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try{
            usuarioService.hardDeleteById(id);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("all")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<String> deleteAll(@Valid @RequestBody Iterable<Long> request) {
        try{
            usuarioService.hardDeleteAll(request);
            return ResponseEntity.ok("Usuários deletados com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
