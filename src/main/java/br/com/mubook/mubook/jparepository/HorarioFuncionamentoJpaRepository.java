package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import br.com.mubook.mubook.entity.TipoQuadraEntity;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface HorarioFuncionamentoJpaRepository extends GenericRepository<HorarioFuncionamentoEntity, Integer> {

    Optional<HorarioFuncionamentoEntity> findByTipoQuadraEntityAndDiaSemana(TipoQuadraEntity tipo, DayOfWeek diaSemana);
}
