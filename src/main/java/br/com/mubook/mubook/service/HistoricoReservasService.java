package br.com.mubook.mubook.service;

import br.com.mubook.mubook.dto.ReservaUpdateDto;
import br.com.mubook.mubook.model.Convidado;
import br.com.mubook.mubook.model.Reserva;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface HistoricoReservasService extends GenericService<Reserva, Long> {

    Page<Reserva> findReservasWithFilterPageable(Long idTipoQuadra, LocalDate data, LocalTime hora, int offset, int limit);

    List<Reserva> findReservasWithFilter(Long idTipoQuadra, LocalDate data, LocalTime hora, Long idUsuario);

    Reserva agendarReserva(Reserva reserva);

    Reserva cancelarReserva(Long id);

    Reserva finalizarReserva(Long id);

    Reserva editarReserva(Long id, ReservaUpdateDto dto);

    Reserva adicionarConvidados(Long reservaId, List<Convidado> convidadosIds);

    void removerConvidados(List<Long> convidadosIds);

    void removerConvidado(Long id);
}