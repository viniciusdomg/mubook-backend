package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.model.TipoQuadra;

import java.time.DayOfWeek;
import java.util.Optional;

public interface HorarioFuncionamentoService extends GenericService<HorarioFuncionamento, Integer> {
    Optional<HorarioFuncionamento> findByTipoQuadraEntityAndDiaSemana(TipoQuadra tipo, DayOfWeek diaSemana);
}
