package br.com.mubook.mubook.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservaCreateDto {

        @NotNull(message = "A data e hora são obrigatórias.")
        @Future(message = "A data da reserva deve ser no futuro.")
        private LocalDateTime dataHora;

        @NotNull(message = "O ID do usuário é obrigatório.")
        private Long usuarioId;

        @NotNull(message = "O ID da quadra é obrigatório.")
        private Integer quadraId;

        // Lista de IDs dos convidados que participarão da reserva. Pode ser vazia.
        private List<Long> convidadosIds;
}