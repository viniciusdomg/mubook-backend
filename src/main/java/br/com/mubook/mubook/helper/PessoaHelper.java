package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.AtualizarPerfilRequest;
import br.com.mubook.mubook.model.Endereco;
import br.com.mubook.mubook.model.Pessoa;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PessoaHelper {

    public Pessoa RequestToPessoa(AtualizarPerfilRequest request){
        Endereco endereco = new Endereco();
        endereco.setCep(request.cep());
        endereco.setRua(request.endereco());
        endereco.setComplemento(request.complemento());

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.nome());
        pessoa.setCpf(request.cpf());
        pessoa.setEmail(request.email());
        pessoa.setDataNascimento(LocalDate.parse(request.dataNascimento()));
        pessoa.setGenero(request.genero());
        pessoa.setEndereco(endereco);
        pessoa.setTelefone(request.telefone());
        return pessoa;
    }
}
