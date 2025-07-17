package br.com.mubook.mubook.dto;

import jakarta.validation.constraints.Future;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaUpdateDto {

    @Future(message = "A data da reserva deve ser no futuro.")
    private LocalDateTime dataHora;

    private Integer quadraId;
}