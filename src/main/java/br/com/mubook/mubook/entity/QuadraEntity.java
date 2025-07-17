package br.com.mubook.mubook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quadra", schema = "mubook")
public class QuadraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipo_quadra", nullable = false)
    private TipoQuadraEntity tipoQuadra;

    @Column(name = "quantidade_maxima", nullable = false)
    private Integer quantidadeMaxima;

    @Column(name = "foto_url")
    private String foto_url;
}
