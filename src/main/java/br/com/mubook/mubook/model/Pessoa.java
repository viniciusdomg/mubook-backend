package br.com.mubook.mubook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    private Long id;

    private String foto_url;

    private boolean ativo;
    
    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private LocalDate dataNascimento;

    private String genero;

    private Endereco endereco;

}
