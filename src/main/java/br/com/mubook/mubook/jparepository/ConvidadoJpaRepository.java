package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.ConvidadoEntity;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade ConvidadoEntity.
 * Estende GenericRepository para herdar as operações padrão de banco de dados.
 * O tipo da entidade é ConvidadoEntity e o tipo do ID é Long.
 */
@Repository
public interface ConvidadoJpaRepository extends GenericRepository<ConvidadoEntity, Long> {
}