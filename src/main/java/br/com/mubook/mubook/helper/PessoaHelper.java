package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.AtualizarPerfilRequest;
import br.com.mubook.mubook.dto.PerfilResponse;
import br.com.mubook.mubook.model.Endereco;
import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PessoaHelper {

    public Pessoa RequestToPessoa(AtualizarPerfilRequest request, Pessoa pessoa){
        Endereco endereco = new Endereco();
        endereco.setCep(request.cep());
        endereco.setRua(request.endereco());
        endereco.setComplemento(request.complemento());

        pessoa.setNome(request.nome());
        pessoa.setCpf(request.cpf());
        pessoa.setEmail(request.email());
        pessoa.setDataNascimento(request.dataNascimento() != null ? LocalDate.parse(request.dataNascimento()) : null);
        pessoa.setGenero(request.genero());
        pessoa.setEndereco(endereco);
        pessoa.setTelefone(request.telefone());
        return pessoa;
    }

    public PerfilResponse PessoaToPerfilResponse(Usuario u){
        Pessoa p = u.getPessoa();
        return new PerfilResponse(p.getId(),
                u.getFoto_url(),
                p.getNome(),
                p.getCpf(),
                p.getEmail(),
                p.getDataNascimento() != null ? p.getDataNascimento().toString() : null,
                p.getGenero(),
                p.getEndereco() != null ? p.getEndereco().getCep() : null,
                p.getEndereco() != null ? p.getEndereco().getRua() : null,
                p.getEndereco() != null ? p.getEndereco().getComplemento() : null,
                p.getTelefone());
    }
}
