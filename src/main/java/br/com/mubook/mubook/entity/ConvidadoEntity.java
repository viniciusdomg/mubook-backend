package br.com.mubook.mubook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "convidado")
public class ConvidadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private ReservaEntity reserva;
}
