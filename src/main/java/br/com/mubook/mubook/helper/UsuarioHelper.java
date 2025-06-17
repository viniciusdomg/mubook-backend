package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.enums.RoleUser;
import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioHelper {

    public Usuario RegisterRequestToUsuario(String nome, String cpf, String email, String senha, RoleUser role){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);
        return new Usuario(senha, role, pessoa);
    }
}
