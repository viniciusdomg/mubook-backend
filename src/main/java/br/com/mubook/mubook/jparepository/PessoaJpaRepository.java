package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.PessoaEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJpaRepository extends GenericRepository<PessoaEntity, Long>{
}
