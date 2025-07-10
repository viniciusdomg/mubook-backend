package br.com.mubook.mubook.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para receber os dados de cadastro de uma nova reserva.
 * Utiliza o padrão 'record' para imutabilidade e clareza.
 *
 * @param dataHora   A data e hora futuras para a qual a reserva está a ser agendada.
 * @param idQuadra   O identificador único da quadra que está a ser reservada.
 * @param convidados Lista de convidados para esta reserva. A lista pode ser vazia.
 */
public record ReservaCadastroDto(
        @NotNull(message = "A data e hora não podem ser nulas.")
        @Future(message = "A data da reserva deve ser uma data futura.")
        LocalDateTime dataHora,

        @NotNull(message = "O ID da quadra não pode ser nulo.")
        Long idQuadra,

        @Valid // Garante que os objetos na lista também sejam validados
        List<ConvidadoCadastroDto> convidados
) {
}