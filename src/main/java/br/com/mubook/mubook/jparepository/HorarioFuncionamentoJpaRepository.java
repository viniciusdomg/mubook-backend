package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import br.com.mubook.mubook.entity.TipoQuadraEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HorarioFuncionamentoJpaRepository extends GenericRepository<HorarioFuncionamentoEntity, Integer> {

    @Query("SELECT h from HorarioFuncionamentoEntity h where h.tipoQuadraEntity = :tipo and h.diaSemana LIKE %:dia%")
    Optional<HorarioFuncionamentoEntity> findbyTipoQuadraEntityAndDiaSemana(TipoQuadraEntity tipo, String dia);

}
