package br.com.mubook.mubook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import br.com.mubook.mubook.enums.StatusReserva;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    private Long id;
    private LocalDateTime dataHora;
    private Usuario usuario;
    private StatusReserva status;
    private Quadra quadra;
    private List<Convidado> convidados;

    public void cancelar() {
        this.status = StatusReserva.CANCELADA;
    }

}