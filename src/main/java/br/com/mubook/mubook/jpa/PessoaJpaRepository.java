package br.com.mubook.mubook.jpa;

import br.com.mubook.mubook.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, Long>{
}
