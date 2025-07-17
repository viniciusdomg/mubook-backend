package br.com.mubook.mubook.service;

import br.com.mubook.mubook.dto.ReservaCreateDto; // Importar o DTO
import br.com.mubook.mubook.dto.ReservaUpdateDto;
import br.com.mubook.mubook.model.Reserva;
import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoReservasService extends GenericService<Reserva, Long> {

    /**
     * Cria uma nova reserva com base nos dados fornecidos.
     * @param dto O DTO contendo os IDs para a nova reserva.
     * @return A reserva criada.
     */
    Reserva criarReserva(ReservaCreateDto dto);

    Reserva cancelarReserva(Long id);

    Reserva remarcarReserva(Long id, LocalDateTime novoHorario);

    Reserva finalizarReserva(Long id);

    Reserva editarReserva(Long id, ReservaUpdateDto dto);

    Reserva adicionarConvidados(Long reservaId, List<Long> convidadosIds);
    Reserva removerConvidados(Long reservaId, List<Long> convidadosIds);
}