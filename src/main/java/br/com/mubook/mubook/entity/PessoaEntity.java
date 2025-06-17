package br.com.mubook.mubook.entity;

import br.com.mubook.mubook.model.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "mubook", name = "pessoa")
public class PessoaEntity   {

    @Id
    @GeneratedValue(generator = "gen_pessoa_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_pessoa_id", schema = "mubook", sequenceName = "seq_pessoa_id",
            allocationSize = 1)
    private Long id;

    @NotNull
    @Column
    private boolean ativo;

    @NotNull(message = "nome necessário")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "CPF necessário")
    @Column
    private String cpf;

    @NotNull(message = "email necessário")
    @Column (nullable = false)
    private String email;

    @Column
    private String telefone;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String genero;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rua", column = @Column(name = "end_rua")),
            @AttributeOverride(name = "numero", column = @Column(name = "end_numero")),
            @AttributeOverride(name = "bairro", column = @Column(name = "end_bairro")),
            @AttributeOverride(name = "complemento", column = @Column(name = "end_complemento")),
            @AttributeOverride(name = "cep", column = @Column(name = "end_cep"))
    })
    private Endereco endereco;
}
