package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Quadra;
import br.com.mubook.mubook.model.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservasDisponiveisService extends GenericService<Reserva, Long> {

    void deleteAllByReservasDataBefore(LocalDateTime data);

    Boolean existsByQuadraAndDataHora(Quadra quadra, LocalDateTime data);

    List<Reserva> saveAll(List<Reserva> reservas);
}
