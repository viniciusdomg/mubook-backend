package br.com.mubook.mubook.entity;

import br.com.mubook.mubook.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(generator = "gen_quadro_entrega_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_quadro_entrega_id", schema = "polare", sequenceName = "seq_quadro_entrega_id",
            allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @OneToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    private PessoaEntity pessoa;
}
