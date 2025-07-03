package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.QuadraEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadraJpaRepository extends GenericRepository<QuadraEntity, Integer> {
}
