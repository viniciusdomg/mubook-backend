package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.PessoaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaJpaRepository extends GenericRepository<PessoaEntity, Long>{

    @Query("select p " +
            "from PessoaEntity p where p.email = :search")
    PessoaEntity findByEmail(String search);
}
