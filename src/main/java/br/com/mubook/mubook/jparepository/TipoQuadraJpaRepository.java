package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.TipoQuadraEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoQuadraJpaRepository extends GenericRepository<TipoQuadraEntity, Integer>{

    @Query("select count(t) > 0 from TipoQuadraEntity t where lower(t.nome) = lower(:nome)")
    Boolean existsByNome(String nome);
}
