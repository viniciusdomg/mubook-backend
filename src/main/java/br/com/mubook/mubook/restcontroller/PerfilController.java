package br.com.mubook.mubook.restcontroller;

import br.com.mubook.mubook.dto.AtualizarPerfilRequest;
import br.com.mubook.mubook.helper.PessoaHelper;
import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.security.JwtService;
import br.com.mubook.mubook.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil/")
@RequiredArgsConstructor
public class PerfilController {

    private final PessoaService pessoaService;

    private final PessoaHelper helper;

    private final JwtService jwtService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRADOR', 'ROLE_ASSOCIADO')")
    public Pessoa getPerfil(@RequestHeader("Authorization") String token) {
        try{
            String email = jwtService.extractUsername(token.substring(7));
            return pessoaService.findByEmail(email);
        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar dados de pessoa: "+ e.getMessage());
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRADOR', 'ROLE_ASSOCIADO')")
    public ResponseEntity<String> updatePerfil(@RequestBody AtualizarPerfilRequest request) {
        try{
            var pessoa = helper.RequestToPessoa(request);
            pessoaService.save(pessoa);
            return ResponseEntity.ok("Perfil atualizado com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar perfil: "+ e.getMessage());
        }
    }
}
