package br.com.mubook.mubook.helper;

import br.com.mubook.mubook.dto.PerfilRequest;
import br.com.mubook.mubook.dto.PerfilResponse;
import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.model.Endereco;
import br.com.mubook.mubook.model.Pessoa;
import br.com.mubook.mubook.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class PessoaHelper {

    public Pessoa RequestToPessoa(PerfilRequest request, Pessoa pessoa){
        validate(request);

        Endereco endereco = new Endereco();
        endereco.setCep(request.cep());
        endereco.setRua(request.endereco());
        endereco.setComplemento(request.complemento());

        pessoa.setFoto_url(request.foto_url());
        pessoa.setNome(request.nome());
        pessoa.setCpf(request.cpf());
        pessoa.setEmail(request.email());
        pessoa.setDataNascimento(request.dataNascimento() != null && !request.dataNascimento().isEmpty()
                ? LocalDate.parse(request.dataNascimento()) : null);
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

    public void validate(PerfilRequest request) {
        if (request.telefone() != null && !request.telefone().matches("\\d{10,11}")) {
            throw new BussinesException("Telefone inválido. Informe apenas números com 10 ou 11 dígitos.");
        }

        if (request.cpf() != null && !request.cpf().matches("\\d{11}")) {
            throw new BussinesException("CPF inválido. Informe 11 dígitos numéricos.");
        }

        if (request.email() != null && !request.email().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            throw new BussinesException("Email em formato inválido.");
        }

        if (request.cep() != null && !request.cep().isEmpty() && !request.cep().matches("\\d{8}")) {
            throw new BussinesException("CEP inválido. Informe 8 dígitos numéricos.");
        }

        if (request.endereco() != null && !request.endereco().isBlank()) {
            if (!request.endereco().matches(".*,\\s*\\d+$")) {
                throw new BussinesException("O endereço deve conter número da residência após a vírgula. Exemplo: Rua X, 123");
            }
        }

        if (request.dataNascimento() != null && !request.dataNascimento().isEmpty()) {
            try {
                LocalDate.parse(request.dataNascimento());
            } catch (DateTimeParseException ex) {
                throw new BussinesException("Data de nascimento em formato inválido. Use AAAA-MM-DD.");
            }
        }
    }

}
