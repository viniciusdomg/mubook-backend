package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.AtualizarPerfilRequest;
import br.com.mubook.mubook.dto.PerfilResponse;
import br.com.mubook.mubook.helper.PessoaHelper;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.security.JwtService;
import br.com.mubook.mubook.service.PessoaService;
import br.com.mubook.mubook.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/perfil/")
@RequiredArgsConstructor
public class PerfilController {

    private final PessoaService pessoaService;

    private final UsuarioService usuarioService;

    private final PessoaHelper helper;

    private final JwtService jwtService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRADOR', 'ROLE_ASSOCIADO')")
    public ResponseEntity<PerfilResponse> getPerfil(@RequestHeader("Authorization") String token) {
        try{
            String email = jwtService.extractUsername(token.substring(7));
            Optional<Usuario> usuario = usuarioService.findByEmailOrCpf(email);
            return usuario.map(value -> ResponseEntity.ok(helper.PessoaToPerfilResponse(value))).orElseGet(()
                    -> ResponseEntity.internalServerError().build());
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar perfil " + e.getMessage());
        }
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRADOR', 'ROLE_ASSOCIADO')")
    public ResponseEntity<String> updatePerfil(@RequestBody AtualizarPerfilRequest request) {
        try{
            var pessoa = pessoaService.findById(request.id());
            pessoa = helper.RequestToPessoa(request, pessoa);
            pessoaService.save(pessoa);
            return ResponseEntity.ok("Perfil atualizado com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar perfil: "+ e.getMessage());
        }
    }
}
