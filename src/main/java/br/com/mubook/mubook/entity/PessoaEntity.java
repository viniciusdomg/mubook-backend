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
public class PessoaEntity   {

    @Id
    @GeneratedValue(generator = "gen_quadro_entrega_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_quadro_entrega_id", schema = "polare", sequenceName = "seq_quadro_entrega_id",
            allocationSize = 1)
    private Long id;

    @NotNull(message = "nome necessário")
    @Column(nullable = false)
    private String nome;

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
