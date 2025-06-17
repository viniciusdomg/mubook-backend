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
@Table(schema = "mubook", name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(generator = "gen_usuario_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_usuario_id", schema = "mubook", sequenceName = "seq_usuario_id",
            allocationSize = 1)
    private Long id;

    @NotNull
    @Column
    private boolean ativo;

    @Column(unique = true, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_user", nullable = false)
    private RoleUser roleUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private PessoaEntity pessoa;
}
