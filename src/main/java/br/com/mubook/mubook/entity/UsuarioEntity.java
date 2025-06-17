package br.com.mubook.mubook.entity;

import br.com.mubook.mubook.enums.RoleUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(generator = "gen_usuario_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_usuario_id", schema = "polare", sequenceName = "seq_usuario_id",
            allocationSize = 1)
    private Long id;

    @NotNull
    @Column
    private boolean ativo;

    @Column(unique = true, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    @OneToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    private PessoaEntity pessoa;
}
