package br.com.mubook.mubook.dto;

import br.com.mubook.mubook.enums.StatusReserva;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HistoricoReservaResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String nomeUsuario;
    private String nomeQuadra;
    private StatusReserva status;
}