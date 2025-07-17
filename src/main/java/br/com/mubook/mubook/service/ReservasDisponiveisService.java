package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.model.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReservasDisponiveisService extends GenericService<Reserva, Long> {

    List<Reserva> findReservasWithFilter(Long idTipoQuadra, LocalDate data, LocalTime hora);

    void deleteAllByReservasDataBefore(LocalDateTime data);

    Boolean existsByQuadraAndDataHora(Quadra quadra, LocalDateTime data);

    void saveAll(List<Reserva> reservas);
}
