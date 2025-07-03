package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.ReservaEntity;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade ReservaEntity.
 * A anotação @Repository indica ao Spring que esta é uma classe de acesso a
 * dados.
 * Estende GenericRepository para herdar as operações básicas de CRUD.
 * O tipo da entidade é ReservaEntity e o tipo do ID é Long.
 */
@Repository
public interface ReservaJpaRepository extends GenericRepository<ReservaEntity, Long> {
}