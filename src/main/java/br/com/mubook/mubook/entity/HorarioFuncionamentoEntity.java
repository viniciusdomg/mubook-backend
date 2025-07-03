package br.com.mubook.mubook.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horario_funcionamento")
public class HorarioFuncionamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private DayOfWeek diaSemana;

    @Column(name = "horario_abertura", nullable = false)
    private LocalTime horarioAbertura;

    @Column(name = "horario_fechamento", nullable = false)
    private LocalTime horarioFechamento;

    @ManyToOne
    @JoinColumn(name = "tipo_quadra_id", nullable = false)
    private TipoQuadraEntity tipoQuadraEntity;
}