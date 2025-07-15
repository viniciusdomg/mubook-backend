package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.UsuarioResponse;
import br.com.mubook.mubook.enums.RoleUser;
import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioHelper {

    public Usuario RegisterRequestToUsuario(String nome, String cpf, String email, String senha, RoleUser role){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);
        return new Usuario(senha, role, pessoa);
    }

    public List<UsuarioResponse> PageUsuarioToUsuarioResponse(List<Usuario> page){
        return page.stream().map(usuario -> new UsuarioResponse(
                usuario.getId(),
                usuario.getPessoa() != null ? usuario.getPessoa().getNome() : null,
                usuario.getPessoa() != null ? usuario.getPessoa().getCpf() : null,
                usuario.getPessoa() != null ? usuario.getPessoa().getEmail() : null,
                usuario.getRoleUser() != null ? usuario.getRoleUser().getDescricao() : null,
                usuario.isAtivo()
        )).toList();
    }

    public UsuarioResponse UsuarioToUsuarioResponse(Usuario usuario){
        return new UsuarioResponse(usuario.getId(), usuario.getPessoa().getNome(), usuario.getPessoa().getCpf(),
                usuario.getPessoa().getEmail(), usuario.getRoleUser().getDescricao(), usuario.isAtivo());
    }
}
