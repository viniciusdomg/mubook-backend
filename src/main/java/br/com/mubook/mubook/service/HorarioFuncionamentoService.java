package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.HorarioFuncionamento;
import br.com.mubook.mubook.model.TipoQuadra;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface HorarioFuncionamentoService extends GenericService<HorarioFuncionamento, Integer> {
    Optional<HorarioFuncionamento> findByTipoQuadraEntityAndDiaSemana(TipoQuadra tipo, String diaSemana);

    Page<HorarioFuncionamento> findAllPageable(int offset, int limit);
}
