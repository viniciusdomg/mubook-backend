package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.HorarioFuncionamentoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioFuncionamentoJpaRepository extends GenericRepository<HorarioFuncionamentoEntity, Integer> {
}
