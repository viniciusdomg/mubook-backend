package br.com.mubook.mubook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import br.com.mubook.mubook.enums.StatusReserva;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historico_reservas", schema = "mubook") // Adicionei o schema para consistência
public class HistoricoReservasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "quadra_id", nullable = false)
    private QuadraEntity quadra;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reserva_convidado",
            schema = "mubook",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "convidado_id")
    )
    private List<ConvidadoEntity> convidados;
}